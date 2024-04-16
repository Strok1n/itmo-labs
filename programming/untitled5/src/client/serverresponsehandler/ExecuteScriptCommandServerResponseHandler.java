package client.serverresponsehandler;

import contract.commandexecutionresult.CommandExecutionResultDTO;
import contract.commandexecutionresult.ExecuteScriptCommandExecutionResultDTO;

import java.util.Map;

public class ExecuteScriptCommandServerResponseHandler implements ServerResponseHandler{

    private final Map<String, ServerResponseHandler> serverResponseHandlerMap;

    public ExecuteScriptCommandServerResponseHandler(Map<String, ServerResponseHandler> serverResponseHandlerMap)
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
