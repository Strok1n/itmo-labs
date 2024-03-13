package interpreter.commands.concrete;

import interpreter.commands.Command;

public class Show extends Command
{
    public Show()
    {
        this.help = "show: display all collection elements as strings";
    }

    @Override
    public void execute() {
        if (this.baseCollection.getContainer().isEmpty())
        {
            this.outputStream.println("The collection is empty");
            return;
        }
        for (Object object : this.baseCollection.getContainer())
            this.outputStream.println(object);

    }
}
