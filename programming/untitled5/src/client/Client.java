package client;

import client.io.ConsoleReader;
import client.io.ConsoleWriter;
import client.util.ClientInitializer;

import java.util.Arrays;

public class Client
{

    final private ConsoleReader consoleReader;
    final private ConsoleWriter consoleWriter;
    private final CommandPreparerForSendingToTheServer commandPreparerForSendingToTheServer;
    private final CommandSenderToTheServer commandSenderToTheServer;
    private final OutputStringBuilder outputStringBuilder;

    public Client()
    {
        this.consoleReader = ClientInitializer.initializeConsoleReader();
        this.consoleWriter = ClientInitializer.initializeConsoleWriter();

        this.commandPreparerForSendingToTheServer = ClientInitializer.initializeCommandPreparerForSendingToTheServer(this.consoleReader, this.consoleWriter);
        this.commandSenderToTheServer = ClientInitializer.initializeCommandSenderToTheServer();
        this.outputStringBuilder = ClientInitializer.initializeOutputStringBuilder();
    }
    public void start()
    {
        while (true)
        {
            this.consoleWriter.printlnToTheOutputStream("Введите команду:");
            String input = this.consoleReader.getNextLine();

            String[] tokens = input.split(" ");
            String commandName = tokens[0];
            String[] commandArguments = Arrays.copyOfRange(tokens, 1, tokens.length);


            this.consoleWriter.printlnToTheOutputStream(
                    this.outputStringBuilder.buildOutputString(
                            this.commandSenderToTheServer.sendCommandDTOToTheServer(
                                    this.commandPreparerForSendingToTheServer.
                                            prepareCommandDTOForSending(commandName, commandArguments)
                            )
                    )
            );
        }
    }



}
