package client;

import java.io.PrintStream;

public class ConsoleWriter
{
    private PrintStream outputStream;

    public ConsoleWriter(PrintStream outputStream)
    {
        this.outputStream = outputStream;
    }


    public void printlnToOutputStream(String string)
    {
        this.outputStream.println(string);
    }
}
