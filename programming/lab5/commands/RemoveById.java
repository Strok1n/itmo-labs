package commands;

import interpreter.Command;
import java.util.*;
import base.*;

public class RemoveById extends Command
{
	
	
	
	
	@Override
	public void execute()
	{
		int id = -1;
		try
		{
			 id = Integer.parseInt(this.arguments[0]);
		}
		catch (NumberFormatException e)
		{
			this.outputStream.println("Wrong id format");
			return;
		}
		
	
		for (LabWork labWork : this.baseCollection.getBaseCollection())
			if ( labWork.getId() == id )
			{
				this.baseCollection.getBaseCollection().remove(labWork);
				this.outputStream.println("Object successfully deleted");
				return;
			}
			
		this.outputStream.println("An object with specified index not found in the collection");
	}
	
}