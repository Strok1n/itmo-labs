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
		this.outputStream.println("Type of the collection: " +
		this.baseMap.getClass().getSimpleName() + "\n" + 
		"Date of the collection initialization: " + 
		this.baseCollection.getCollectionInitializationDate() +  "\n" + 
		"Number of elements in the collection: " + 
		this.baseCollection.getBaseCollection().size());
	}
	
}