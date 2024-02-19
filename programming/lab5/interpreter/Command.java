package interpreter;

import java.util.List;

public abstract class Command
{
	protected List<String> commandArguments;
	protected String help;
	
	public final String getHelp()
	{
		return this.help;
	}
	
	abstract public void execute();
}