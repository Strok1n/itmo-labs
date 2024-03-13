package interpreter.commands.concrete;

import business.LabWork;
import interpreter.commands.Command;

public class RemoveById extends Command
{

    public RemoveById()
    {
        this.help = "remove_by_id {id}: remove element from the collection by id";
    }


    @Override
    public void execute() {
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
            this.outputStream.println("The element with id = " + finalId + " successfully removed from the collection");
        }
        else
        {
            this.outputStream.println("The collection does not contain an element with specified id");
        }


    }
}
