package interpreter.commands.concrete;

import business.LabWork;
import interpreter.Interpreter;
import interpreter.commands.Command;
import interpreter.commands.InputValidator;
import util.CommandExecutionMode;
import util.ObjectCreator;
import util.ToXmlAble;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class ExecuteScript extends Command
{
    public ExecuteScript()
    {
        this.help = "execute_script {file_name}: execute all commands from the file";
    }


    private Interpreter interpreter;

    public void setInterpreter(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    @Override
    public void execute() throws FileNotFoundException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        String fileName = this.arguments[0];
        Scanner fileScanner = new Scanner(new File(fileName));

        this.interpreter.getCommand("add")
                .setIOStreams(fileScanner, this.outputStream);

        this.interpreter.set(fileScanner);
        int scriptsFileNamesSetPreviousSize = this.interpreter.getScriptsFileNames().size();
        this.interpreter.addScriptFileName(fileName);
        if (this.interpreter.getScriptsFileNames().size() == scriptsFileNamesSetPreviousSize)
        {
            this.outputStream.println("Recurrent execution execute_script");
            return;
        }

        ((Insert)this.interpreter.getCommand("add"))
                .setCommandExecutionMode(CommandExecutionMode.FROM_FILE);
        ((Insert)this.interpreter.getCommand("add"))
                .setObjectCreator(new ObjectCreator(
                        LabWork.class,
                        new InputValidator(),
                        this.outputStream,
                        fileScanner,
                        CommandExecutionMode.FROM_FILE

                ));
        while (fileScanner.hasNextLine())
            this.interpreter.handleOneInputString(fileScanner.nextLine());

        this.interpreter.getScriptsFileNames().poll();
    }
}
