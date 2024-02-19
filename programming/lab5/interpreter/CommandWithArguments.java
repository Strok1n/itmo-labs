package interpreter;

import java.util.List;

public abstract class CommandWithArguments extends Command
{
	public abstract void setArguments(String[] arguments);
}