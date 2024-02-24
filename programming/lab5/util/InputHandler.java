package util;

import java.lang.reflect.*;
import java.util.function.Predicate;

public interface InputHandler
{
	boolean isInputCorrect();
	
	
	public static <T> void handleInput(Field field, PrintStream outputStream, Scanner scanner, 
		Predicate<String> fieldValidator, String onErrorMessage)
	{
		boolean isInputIncorrect = true;
		String input = "";
		
		while (isInputIncorrect)
		{
			outputStream.println("Enter the " + field.getName() + " of the LabWork:");
			input = scanner.next();
			
			
			if ( !fieldValidator(input) )
				outputStream.println(onErrorMessage);
			else
			{
			
			}
				
		}
	}
	
}