package interpreter.commands.concrete;

import interpreter.Interpreter;
import interpreter.commands.Command;
import trash.CommandExecutionMode;

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
    public void execute() throws IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, FileNotFoundException {
        if (this.arguments == null)
        {
            this.outputStream.println("File name argument does not found");
            return;
        }
        String fileName = this.arguments[0];


        Scanner fileScanner;
        try {
            fileScanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            this.outputStream.println("File not found");
            return;
        }

        this.interpreter.getCommand("add")
                .setIOStreams(fileScanner, this.outputStream);

        this.interpreter.set(fileScanner);
        int scriptsFileNamesSetPreviousSize = this.interpreter.getScriptsFileNames().size();
        this.interpreter.addScriptFileName(fileName);
        if (this.interpreter.getScriptsFileNames().size() == scriptsFileNamesSetPreviousSize)
        {
            this.outputStream.println("Recurrent execution of execute_script");
            return;
        }

        ((Insert)this.interpreter.getCommand("add")).setCommandExecutionMode(CommandExecutionMode.FROM_FILE);
        ((Insert)this.interpreter.getCommand("add")).setIOStreams(
                fileScanner, this.outputStream
        );

        ((Update)this.interpreter.getCommand("update")).setCommandExecutionMode(CommandExecutionMode.FROM_FILE);
        ((Update)this.interpreter.getCommand("update")).setIOStreams(fileScanner, this.outputStream);



        while (fileScanner.hasNextLine())
            this.interpreter.handleInputString(fileScanner.nextLine());

        this.interpreter.getScriptsFileNames().poll();

        ((Insert)this.interpreter.getCommand("add")).setCommandExecutionMode(CommandExecutionMode.MANUAL_INPUT);
        ((Update)this.interpreter.getCommand("update")).setCommandExecutionMode(CommandExecutionMode.MANUAL_INPUT);

        ((Insert)this.interpreter.getCommand("add")).setIOStreams(this.scanner, this.outputStream);
        ((Update)this.interpreter.getCommand("update")).setIOStreams(
                this.scanner, this.outputStream
        );

    }
}
