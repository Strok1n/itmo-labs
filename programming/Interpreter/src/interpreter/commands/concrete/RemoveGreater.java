package interpreter.commands.concrete;

import business.LabWork;
import interpreter.commands.Command;
import interpreter.commands.InputValidator;
import util.CommandExecutionMode;
import util.ObjectCreator;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class  RemoveGreater extends Command
{
    ObjectCreator objectCreator;


    public RemoveGreater()
    {
        this.help = "remove_greater {element}: remove all collection elements which greater than a given one";
    }




    @Override
    public void execute() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        this.objectCreator = new ObjectCreator(
                LabWork.class,
                new InputValidator(),
                System.out,
                new Scanner(System.in),
                CommandExecutionMode.MANUAL_INPUT
        );

        LabWork labWork = (LabWork) objectCreator.createObject();


        int maxId = 0;
        for (Object l : this.baseCollection.getContainer())
            if ( maxId < ((LabWork) l).getId() )
                maxId = ((LabWork) l).getId();

        int id = 1 + maxId;

        labWork.setId(id);
        labWork.setCreationDate(java.time.ZonedDateTime.now());



        if (this.baseCollection.getContainer().removeIf(element -> ((LabWork) element).compareTo(labWork) > 0 ))
        {
            this.outputStream.println("Some elements of the collection removed");
        }
        else
        {
            this.outputStream.println("The collection did not change");
        }

//        this.baseCollection.getBaseCollection().removeIf(
//                element -> element <
//       ););




    }
}
