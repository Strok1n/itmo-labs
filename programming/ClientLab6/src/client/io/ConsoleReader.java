package client.io;

import java.io.InputStream;
import java.util.Scanner;

public class ConsoleReader
{
    private Scanner consoleScanner;


    public ConsoleReader(InputStream inputStream)
    {
        this.consoleScanner = new Scanner(inputStream);
    }

    public String getNextLine()
    {
        return this.consoleScanner.nextLine();
    }

    public boolean hasNextLine()
    {
        return this.consoleScanner.hasNextLine();
    }



}
