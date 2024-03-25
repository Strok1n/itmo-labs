package interpreter.commands.concrete;

import interpreter.commands.Command;

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
