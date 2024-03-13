package interpreter.commands.concrete;

import interpreter.commands.Command;


public class Clear extends Command
{
    public Clear()
    {
        this.help = "clear: clear the collection";
    }

    @Override
    public void execute()
    {
        this.baseCollection.getContainer().clear();
        this.outputStream.println("Collection successfully cleared");
    }
}
