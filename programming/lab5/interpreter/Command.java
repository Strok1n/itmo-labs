package interpreter;

import java.util.List;

public abstract class Command
{
	protected List<String> commandArguments;
	
	
	abstract public void execute();
}