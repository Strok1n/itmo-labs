package commands;

import interpreter.Command;
import java.util.Arrays;


public class Help extends Command
{
	private Command[] availableCommands;
	
	public Help()
	{
		this.help = "help: display a list of available commands with help for each";
	}
	
	public void setAvailableCommands(Command[] commands)
	{
		Arrays.toString( commands );
		System.out.println("##");
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