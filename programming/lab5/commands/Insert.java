package commands;

import interpreter.Command;
import base.*;
import java.io.*;
import java.util.*;

public class Insert extends Command
{
	public Insert()
	{
		this.help = "insert: add new element to the collection";
	}
	
	@Override
	public void execute()
	{
		String name = null;
		float x = 0.0f;
		long y = 0;
		int minimalPoint = 0;
		long tunedInWorks = 0;
		Difficulty difficulty = null;
		String disciplineName = null;
		int disciplineLabsCount = 0;
		
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
				this.outputStream.println("y must be a long integer");
			}
		}
		
		isInputIncorrect = true;
		
		while(isInputIncorrect)
		{
			this.outputStream.println("Enter minimal point:");
			try
			{
				minimalPoint = Integer.parseInt(this.scanner.next());
				isInputIncorrect = false;
				if (minimalPoint <= 0)
				{
					this.outputStream.println("minimal point must be greater than 0");
					isInputIncorrect = true;
				}
			}
			catch(Exception exception)
			{
				this.outputStream.println("minimal point must be an integer");
			}
		}
		
		isInputIncorrect = true;
		
		while(isInputIncorrect)
		{
			this.outputStream.println("Enter tuned in works:");
			try
			{
				tunedInWorks = Integer.parseInt(this.scanner.next());
				isInputIncorrect = false;
			}
			catch(Exception exception)
			{
				this.outputStream.println("tuned in works must be a long integer");
			}
		}
		
		isInputIncorrect = true;
		
		while(isInputIncorrect)
		{
			this.outputStream.println("Enter difficulty:");
			try
			{
				difficulty = Difficulty.valueOf(this.scanner.next());
				isInputIncorrect = false;
			}
			catch(Exception exception)
			{
				this.outputStream.println("difficulty must match one of the next values:\n"
				+ Arrays.toString(Difficulty.values()));
			}
		}
		
		isInputIncorrect = true;
		
		while(isInputIncorrect)
		{
			this.outputStream.println("Enter the discipline name:");
			disciplineName = this.scanner.next();
			if (disciplineName == "" && disciplineName == null)
				this.outputStream.println("discipline name must not be empty string or null");
			else
				isInputIncorrect = false;
		}
		
		isInputIncorrect = true;
		
		while(isInputIncorrect)
		{
			this.outputStream.println("Enter discipline labs count:");
			try
			{
				disciplineLabsCount = Integer.parseInt(this.scanner.next());
				isInputIncorrect = false;
			}
			catch(Exception exception)
			{
				this.outputStream.println("discipline labs count must be an integer");
			}
		}
		
		int id = 0;
		
		LabWork labWork = new LabWork(
			id,
			name,
			new Coordinates(x, y),
			minimalPoint,
			tunedInWorks,
			difficulty,
			new Discipline(disciplineName, disciplineLabsCount)
		);
		
		this.baseMap.put(labWork, "0");
		
	}
	
}