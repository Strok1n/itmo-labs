package client.io;

import java.io.PrintStream;

public class ConsoleWriter
{
    private PrintStream outputStream;

    public ConsoleWriter(PrintStream outputStream)
    {
        this.outputStream = outputStream;
    }


    public void printlnToTheOutputStream(String string)
    {
        this.outputStream.println(string);
    }
}
