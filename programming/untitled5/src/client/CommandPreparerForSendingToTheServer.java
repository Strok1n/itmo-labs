package client;

import client.commanddtobuilders.CommandDTOBuilder;
import client.io.ConsoleReader;
import client.io.ConsoleWriter;
import client.util.ClientInitializer;
import contract.dto.commanddto.CommandDTO;
import java.util.Map;

public class CommandPreparerForSendingToTheServer
{
    private final Map<String, CommandDTOBuilder> commandDTOBuilders;

    public CommandPreparerForSendingToTheServer(ConsoleReader consoleReader, ConsoleWriter consoleWriter)
    {
        this.commandDTOBuilders = ClientInitializer.initializeCommandDTOBuilders(consoleReader, consoleWriter);
    }

    public CommandDTO prepareCommandDTOForSending(String commandName, String[] commandArguments)
    {
        return commandDTOBuilders.get(commandName).buildCommandDTO(commandArguments);
    }

}
