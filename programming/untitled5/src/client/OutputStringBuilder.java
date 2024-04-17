package client;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import client.util.ClientInitializer;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import java.util.Map;
public class OutputStringBuilder
{
    private final Map<String, CommandExecutionResultHandler> commandExecutionResultDTOHandlers;

    public OutputStringBuilder()
    {
        this.commandExecutionResultDTOHandlers = ClientInitializer.initializeCommandExecutionResultDTOHandlers();
    }

    public String buildOutputString(CommandExecutionResultDTO commandExecutionResultDTO)
    {
        return this.commandExecutionResultDTOHandlers.get(commandExecutionResultDTO.getCommandName())
                .handleServerResponse(commandExecutionResultDTO);
    }
}
