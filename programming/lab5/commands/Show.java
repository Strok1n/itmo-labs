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
		
		for (LabWork labWork : this.baseCollection.getBaseCollection())
			this.outputStream.println(labWork);
		if (this.baseCollection.getBaseCollection().isEmpty())
			this.outputStream.println("The collection is empty");
	}
	
}