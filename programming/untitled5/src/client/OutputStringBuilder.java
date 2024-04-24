package client;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import client.util.ClientInitializer;
import contract.CommandName;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

import java.util.ArrayDeque;
import java.util.Map;
public class OutputStringBuilder
{
    private final Map<String, CommandExecutionResultHandler> commandExecutionResultDTOHandlers;

    public OutputStringBuilder(ArrayDeque<CommandName> history)
    {
        this.commandExecutionResultDTOHandlers = ClientInitializer.initializeCommandExecutionResultDTOHandlers(history);
    }

    public String buildOutputString(CommandExecutionResultDTO commandExecutionResultDTO)
    {
        return this.commandExecutionResultDTOHandlers.get(commandExecutionResultDTO.getCommandName())
                .handleServerResponse(commandExecutionResultDTO);
    }
}
