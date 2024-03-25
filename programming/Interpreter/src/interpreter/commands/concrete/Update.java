package interpreter.commands.concrete;

import business.LabWork;
import interpreter.commands.Command;
import util.InputValidator;
import trash.CommandExecutionMode;
import util.ObjectCreator;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Update extends Command
{
    private ObjectCreator objectCreator;


    public Update() throws NoSuchFieldException {
        super();
        this.help = "update id {element}: update the collection element whose id is equal to a given one";

        this.objectCreator = new ObjectCreator(
                LabWork.class,
                new InputValidator(),
                System.out,
                new Scanner(System.in),
                CommandExecutionMode.MANUAL_INPUT
        );

    }

    public void setCommandExecutionMode(CommandExecutionMode commandExecutionMode) {
        this.objectCreator.setObjectCreationMode(commandExecutionMode);

    }


    @Override
    public void execute() throws IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        int id;
        try
        {
            id = Integer.parseInt(this.arguments[0]);
        }
        catch (Exception exception)
        {
            this.outputStream.println("Id must be an integer");
            return;
        }
        int finalId = id;
        if (this.baseCollection.getContainer()
            .removeIf((element)-> ((LabWork)element).getId() == finalId))
        {
            LabWork labWork =(LabWork) this.objectCreator.createObject();
            labWork.setId(finalId);
            labWork.setCreationDate(java.time.ZonedDateTime.now());
            this.baseCollection.getContainer().add(labWork);
            this.outputStream.println("LabWork with id = " + finalId +
                        " successfully updated in the collection");
        }
        else
        {
            this.outputStream.println("The collection does not contain an element with specified id");
        }
        this.arguments = null;
    }
}
