package interpreter;

import java.util.List;
import java.io.*;
import java.util.*;

public abstract class CommandWithIOStreams extends Command
{
	protected Scanner scanner;
	protected PrintStream outputStream;
	
	final public void setIOStreams(Scanner scanner, PrintStream printStream)
	{
		this.scanner = scanner;
		this.outputStream = printStream;
	}
}