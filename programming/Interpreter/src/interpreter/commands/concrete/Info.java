package interpreter.commands.concrete;

import interpreter.commands.Command;

public class Info extends Command
{
    public Info()
    {
        this.help = "info: display information about collection";
    }

    @Override
    public void execute()
    {
        this.outputStream.println("Type of the collection: " +
                this.baseCollection.getContainer().getClass().getSimpleName() + "\n" +
                "Date of the collection initialization: " +
                this.baseCollection.getCollectionInitializationDate() +  "\n" +
                "Number of elements in the collection: " +
                this.baseCollection.getContainer().size());
    }
}
