package interpreter.commands.concrete;

import interpreter.commands.Command;
import util.ToXmlAble;

public class Exit extends Command
{

    public Exit()
    {
        this.help = "exit: exit interpreter without saving collection to a file";
    }

    @Override
    public void execute()
    {
        System.exit(0);
    }
}
