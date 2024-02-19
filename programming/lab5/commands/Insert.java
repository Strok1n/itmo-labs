package commands;

import interpreter.CommandWithIOStreams;
import java.io.*;


public class Insert extends CommandWithIOStreams
{
	public Insert()
	{
		this.help = "insert: add new element to the collection";
	}

	
	@Override
	public void execute()
	{
		String name;
		float x;
		long y;
		int minimalPoint;
		long tunedInWorks;
		Difficulty difficulty;
		Discipline discipline;
		
		boolean isInputIncorrect = true;
		
		while(isInputIncorrect)
		{
			this.outputStream.println("Enter the LabWork name:");
			name = this.scanner.next();
			if (name == "" && name == null)
				this.outputStream.println("LabWork name must not be empty string or null");
			else
				isInputIncorrect = false;
		}
		
		isInputIncorrect = true;
		
		this.outputStream.println("Enter the LabWork Coordinates:");
		
		while(isInputIncorrect)
		{
			this.outputStream.println("Enter x:");
			try
			{
				x = Float.parseFloat(this.scanner.next());
				isInputIncorrect = false;
			}
			catch(Exception exception)
			{
				this.outputStream.println("x must be a float number");
			}
		}
		
			
		isInputIncorrect = true;
		
		while(isInputIncorrect)
		{
			this.outputStream.println("Enter y:");
			try
			{
				y = Long.parseLong(this.scanner.next());
				isInputIncorrect = false;
				if (y <= -161)
				{
					this.outputStream.println("y must be greater than -161");
					isInputIncorrect = true;
				}
			}
			catch(Exception exception)
			{
				this.outputStream.println("y must be a long number");
			}
		}
		
		
		
	}
	
}