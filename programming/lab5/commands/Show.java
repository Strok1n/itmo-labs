package commands;

import interpreter.Command;
import base.LabWork;


public class Show extends Command
{
	public Show()
	{
		this.help = "show: display all collection elements as strings";
	}
	
	
	
	@Override
	public void execute()
	{
		
		for (LabWork labWork : this.baseMap.keySet())
			this.outputStream.println(labWork);
	}
	
}