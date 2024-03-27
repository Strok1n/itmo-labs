package interpreter.commands.concrete;

import business.LabWork;
import exceptions.OutputTextException;
import interpreter.commands.Command;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class AddLabWorkToTheCollection extends Command {

    private LabWork labWork;


    public AddLabWorkToTheCollection()
    {

    }
    public AddLabWorkToTheCollection(LabWork labWork)
    {
        this.labWork = labWork;
    }

    public void setLabWork(LabWork labWork) {
        this.labWork = labWork;
    }

    @Override
    public void execute() throws IllegalAccessException, FileNotFoundException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, OutputTextException {

        int maxId = 0;
        for (Object l : this.baseCollection.getContainer())
            if ( maxId < ((LabWork) l).getId() )
                maxId = ((LabWork) l).getId();

        int id = 1 + maxId;

        labWork.setId(id);
        labWork.setCreationDate(java.time.ZonedDateTime.now());

        this.baseCollection.getContainer().add(labWork);
       // this.outputStream.println("LabWork successfully added to the collection");
        throw new OutputTextException("LabWork successfully added to the collection");
    }
}
