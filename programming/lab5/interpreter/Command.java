package interpreter;

import java.util.*;
import java.io.*;
import base.*;

public abstract class Command
{
	
	protected Map<LabWork, String> baseMap;
	protected String[] arguments;
	protected String help;
	protected Scanner scanner;
	protected PrintStream outputStream;
	
	
	final public String getHelp()
	{
		return this.help;
	}
	
	final public void setBaseMap(Map<LabWork, String> map)
	{
		this.baseMap = map;
	}
	
	final public void setIOStreams(Scanner scanner, PrintStream printStream)
	{
		this.scanner = scanner;
		this.outputStream = printStream;
	}
	
	final public void setArguments(String[] arguments)
	{
		this.arguments = arguments;
	}
	
	abstract public void execute();
}