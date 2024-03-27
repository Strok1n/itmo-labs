package interpreter.commands.concrete;

import business.LabWork;
import exceptions.InsertException;
import interpreter.commands.Command;
import util.InputValidator;
import trash.CommandExecutionMode;
import util.*;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Insert extends Command
{
    private int id;
    private ObjectCreator objectCreator;

    public void setObjectCreator(ObjectCreator objectCreator) {
        this.objectCreator = objectCreator;
    }

    public Insert() throws NoSuchFieldException
    {
        this.help = "insert {element}: add new element to the collection";
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
    public void setIOStreams(Scanner scanner, PrintStream stream)
    {
        super.setIOStreams(scanner, stream);
        this.objectCreator.setIOStreams(scanner, stream);
    }

    @Override
    public void execute() throws IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, InsertException {

//        LabWork labWork = (LabWork) this.objectCreator.createObject();
//
//        int maxId = 0;
//        for (Object l : this.baseCollection.getContainer())
//            if ( maxId < ((LabWork) l).getId() )
//                maxId = ((LabWork) l).getId();
//
//        int id = 1 + maxId;
//
//        labWork.setId(id);
//        labWork.setCreationDate(java.time.ZonedDateTime.now());
//
//        this.baseCollection.getContainer().add(labWork);
//        this.outputStream.println("LabWork successfully added to the collection");


        AddLabWorkToTheCollection addLabWorkToTheCollection = new AddLabWorkToTheCollection();

        addLabWorkToTheCollection.setBaseCollection(this.baseCollection);


        throw new InsertException(LabWork.class, new InputValidator(), CommandExecutionMode.MANUAL_INPUT, addLabWorkToTheCollection);


    }
}
