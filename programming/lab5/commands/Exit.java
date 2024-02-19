package commands;

import interpreter.Command;

public class Exit extends Command
{
	@Override
	public void execute()
	{
		System.exit(0);
	}
	
}