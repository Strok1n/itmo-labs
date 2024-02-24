package commands;

import interpreter.Command;
import base.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import util.*;
import java.util.function.*;

public class Insert extends Command
{
	private Map<Field, Predicate<Field>> fieldValidators;
	
	
	public Insert()
	{
		this.help = "insert: add new element to the collection";
		
		this.fieldValidators = new HashMap<Field, Predicate<Object>>();
		
		fieldValidators.put( LabWork.class.getDeclaredField("name"), 
			(name) -> ((name != null) && (name != "")) );
		fieldValidators.put( LabWork.class.getDeclaredField("name"), 
			(name) -> ((name != null) && (name != "")) );
		
	}
	
	
	private boolean isPrimitiveOrWrapperOrString(){}
	
	
	
	private <T> void handleInput(LabWork labWork, Field field, 
	Predicate<T> fieldValidator, String onErrorMessage)
	{
		boolean isInputIncorrect = true;
		String input = "";
		
		while (isInputIncorrect)
		{
			this.outputStream.println("Enter the " + field.getName() + " of the " 
			+ field.getDeclaringClass().getName() + ":" );
			if (field.getType().equals(Integer.class) || 
				field.getType().equals(Float.class) || 
				field.getType().equals(Double.class) || 
				field.getType().equals(Byte.class) || 
				field.getType().equals(Short.class) || 
				field.getType().equals(Long.class) || 
				field.getType().equals(Boolean.class) || 
				field.getType().equals(Character.class) ||
				field.getType().equals(String.class) || 
				field.getType().isPrimitive() )
			{
				input = this.scanner.next();
				// check field and set value.
			}
			else
			{
				
			}
			
			if ( !fieldValidator(input) )
				outputStream.println(onErrorMessage);
			else
			{
			
			}
				
		}
	}
	
	
	
	
	@Override
	public void execute()
	{
		LabWork labWork = new LabWork();
		
		for (Field field : LabWork.class.getDeclaredFields())
		{
			//InputHandler.handleInput( field );
			this.handleInput(labWork, field, );
		}
		
		
		
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
		
		int maxId = 0;
		
		
		for (LabWork labWork : this.baseMap.keySet())
			if ( maxId < labWork.getId() )
				maxId = labWork.getId();
		
		int id = 1 + maxId;
		
	
		System.out.println("id: " + id);
		

		
		this.baseMap.put(new LabWork(
			id,
			name,
			new Coordinates(x, y),
			minimalPoint,
			tunedInWorks,
			difficulty,
			new Discipline(disciplineName, disciplineLabsCount)
		), "");
		
	}
	
}