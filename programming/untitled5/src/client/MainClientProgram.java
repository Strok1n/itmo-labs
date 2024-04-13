package client;

import java.io.InputStream;
import java.io.PrintStream;

public class MainClientProgram
{

    private ConsoleReader consoleReader;
    private ConsoleWriter consoleWriter;

    private InputHandler inputHandler;


    public MainClientProgram(PrintStream outputStream, InputStream inputStream)
    {
        this.consoleReader = new ConsoleReader(inputStream);
        this.consoleWriter = new ConsoleWriter(outputStream);
        this.inputHandler = new InputHandler(this.consoleReader, this.consoleWriter);
    }


    public void start()
    {
        while (true)
        {
            this.consoleWriter.printlnToOutputStream("Введите команду:");
            String output = inputHandler.handleInput(this.consoleReader.getNextLine());
            this.consoleWriter.printlnToOutputStream(output);
        }
    }



}
