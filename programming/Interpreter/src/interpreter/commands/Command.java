package interpreter.commands;

import business.LabWork;
import interpreter.collection.MainCollection;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Scanner;

public abstract class Command
{
    protected MainCollection baseCollection;

    protected String[] arguments;
    protected String help;
    protected Scanner scanner;
    protected PrintStream outputStream;

    final public String getHelp()
    {
        return this.help;
    }
    final public void setBaseCollection(MainCollection baseCollection)
    {
        this.baseCollection = baseCollection;
    }

     public void setIOStreams(Scanner scanner, PrintStream printStream)
    {
        this.scanner = scanner;
        this.outputStream = printStream;
    }

    final public void setArguments(String[] arguments)
    {
        this.arguments = arguments;
    }

    abstract public void execute() throws IllegalAccessException, FileNotFoundException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException;
}
