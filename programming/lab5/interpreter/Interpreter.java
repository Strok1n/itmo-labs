package interpreter;


import java.util.*;
import commands.*;
import java.io.*;

import static java.lang.System.*;



public class Interpreter
{
	private Map<String, Command> availableCommands;
	private Scanner consoleScanner;
	





	public Interpreter()
	{
		this.availableCommands = new HashMap<String, Command>();
		this.availableCommands.put("help", new Help());
		this.availableCommands.put("info", new Info());
		this.availableCommands.put("show", new Show());
		this.availableCommands.put("insert", new Insert());
		this.availableCommands.put("update", new Update());
		this.availableCommands.put("remove_key", new RemoveKey());
		this.availableCommands.put("clear", new Clear());
		this.availableCommands.put("save", new Save());
		this.availableCommands.put("execute_script", new ExecuteScript());
		this.availableCommands.put("exit", new Exit());
		this.availableCommands.put("remove_lower", new RemoveLower());
		this.availableCommands.put("replace_if_lower", new ReplaceIfLower());
		this.availableCommands.put("remove_lower_key", new RemoveLowerKey());
		this.availableCommands.put("average_of_minimal_point", new AverageOfMinimalPoint());
		this.availableCommands.put("print_unique_discipline", new PrintUniqueDiscipline());
		this.availableCommands.put("print_field_descending_difficulty ", new PrintFieldDescendingDifficulty());
		
		
		this.consoleScanner = new Scanner(in);
	}
	
	public Command putCommand(String commandName, Command command)
	{
		return this.availableCommands.put(commandName, command);
	}
	
	
	
	
	public void handleInput()
	{
		boolean exit = false;
		while (!exit)
		{
			out.println("Enter the command:");
			String inputString = this.consoleScanner.next();
			String[] tokens = inputString.split(" ");
			
			String commandName = tokens[0];
			
			
			if (this.availableCommands.keySet().contains(commandName))
			{
				this.availableCommands.get(commandName).execute();
			}
			
		}

	}
}