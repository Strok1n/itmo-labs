package interpreter.commands.concrete;

import interpreter.commands.Command;

import java.util.Set;

public class Help extends Command
{
    private Set<Command> availableCommands;

    public Help()
    {
        this.help = "help: display a list of available commands with help for each";
    }

    public void setAvailableCommands(Set<Command> commands)
    {
        this.availableCommands = commands;
    }

    @Override
    public void execute()
    {
        for (Command command : this.availableCommands)
        {
            System.out.println(command.getHelp());
        }
    }

}
