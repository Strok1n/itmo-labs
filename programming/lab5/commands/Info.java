package commands;

import interpreter.Command;



public class Info extends Command
{
	
	public Info()
	{
		this.help = "info: display information about collection";
	}
	
	
	@Override
	public void execute()
	{
		this.outputStream.println("Type of the collection: " + this.baseMap.getClass().getTypeName() + 
		"\n" + "Date of the collection initialization: " );
	}
	
}