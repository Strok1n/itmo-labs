import interpreter.*;
import commands.*;


public class InterpreterDemonstration
{
	static public void main(String[] args)
	{
		
		
		
		
				Interpreter interpreter = new Interpreter();
				
				
				interpreter.putCommand("help", new Help());
				
				
				
				
				
				
				
				interpreter.handleInput();
		
	}
}