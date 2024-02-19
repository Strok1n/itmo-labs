package commands;

import interpreter.Command;



public class Show extends Command
{
	public Show()
	{
		this.help = "show: display all collection elements as strings";
	}
	
	
	
	@Override
	public void execute()
	{
		System.out.println(this.baseMap.keySet());
	}
	
}