package client;

import client.io.ConsoleReader;
import client.io.ConsoleWriter;
import client.util.ClientInitializer;
import client.util.CommandDTOExtractor;
import contract.CommandName;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Client
{
    final private ConsoleReader consoleReader;
    final private ConsoleWriter consoleWriter;
    private final CommandPreparerForSendingToTheServer commandPreparerForSendingToTheServer;
    private final CommandSenderToTheServer commandSenderToTheServer;
    private final OutputStringBuilder outputStringBuilder;

    private ArrayDeque<CommandName> history;


    public Client()
    {
        this.history = new ArrayDeque<>();
        this.consoleReader = ClientInitializer.initializeConsoleReader();
        this.consoleWriter = ClientInitializer.initializeConsoleWriter();
        this.commandPreparerForSendingToTheServer = ClientInitializer.initializeCommandPreparerForSendingToTheServer(this.consoleReader, this.consoleWriter);
        this.commandSenderToTheServer = ClientInitializer.initializeCommandSenderToTheServer();
        this.outputStringBuilder = ClientInitializer.initializeOutputStringBuilder(history);
    }
    public void start()
    {
        try
        {
            while (true)
            {
                try
                {
                    this.consoleWriter.printlnToTheOutputStream("Введите команду:");
                    String input = this.consoleReader.getNextLine();

                    CommandName commandName = this.getCommandNameFromInputString(input);
                    String[] commandArguments = this.getCommandArgumentsFromInputString(input);




                    CommandDTO commandDTO = this.commandPreparerForSendingToTheServer.prepareCommandDTOForSending(commandName, commandArguments);
                    CommandDTOExtractor commandDTOExtractor = new CommandDTOExtractor(commandDTO);

                    for (CommandDTO cmdDTO: commandDTOExtractor.getCommandDTOList())
                    {
                        CommandExecutionResultDTO commandExecutionResultDTO = this.commandSenderToTheServer.sendCommandDTOToTheServer(cmdDTO);
                        String outputString = this.outputStringBuilder.buildOutputString(commandExecutionResultDTO);
                        history.add(CommandName.valueOf(commandExecutionResultDTO.getCommandName()));
                        if (history.size() > 8)
                            history.removeFirst();
                        this.consoleWriter.printlnToTheOutputStream(outputString);
                    }
                }catch (IllegalArgumentException illegalArgumentException)
                {
                    this.consoleWriter.printlnToTheOutputStream("Неизвестная команда");
                }
            }
        }
        catch (NoSuchElementException noSuchElementException)
        {
            throw noSuchElementException;
          //  this.consoleWriter.printlnToTheOutputStream("Программа завершена из-за нажатия ctrl + D");
        }
    }
    CommandName getCommandNameFromInputString(String inputString)
    {
        String[] tokens = inputString.split(" ");
        return CommandName.valueOf(tokens[0].trim());
    }
    String[] getCommandArgumentsFromInputString(String inputString)
    {
        String[] tokens = inputString.split(" ");
        String[] commandArguments = Arrays.copyOfRange(tokens, 1, tokens.length);
        for (int i = 0; i < commandArguments.length; i++)
            commandArguments[i] = commandArguments[i].trim();
        return commandArguments;
    }
}
