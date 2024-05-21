package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.ExecuteScriptCommandExecutionResultDTO;

import java.util.Map;

public class ExecuteScriptCommandExecutionResultHandler implements CommandExecutionResultHandler {

    private final Map<String, CommandExecutionResultHandler> serverResponseHandlerMap;

    public ExecuteScriptCommandExecutionResultHandler(Map<String, CommandExecutionResultHandler> serverResponseHandlerMap)
    {
        this.serverResponseHandlerMap = serverResponseHandlerMap;
    }

    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        ExecuteScriptCommandExecutionResultDTO executeScriptCommandExecutionResultDTO =
                (ExecuteScriptCommandExecutionResultDTO) commandExecutionResultDTO;

        String output = "";

        for (CommandExecutionResultDTO commandExecutionResultDTO1:
                executeScriptCommandExecutionResultDTO
                .getCommandExecutionResultDTOList() )
        {
            output = output.concat(
                    this.serverResponseHandlerMap
                            .get(commandExecutionResultDTO1.getCommandName())
                            .handleServerResponse(commandExecutionResultDTO1).concat("\n")
            );
        }


        return output;
    }
}
