package client.serverresponsehandler;

import contract.commandexecutionresult.AddCommandExecutionResultDTO;
import contract.commandexecutionresult.CommandExecutionResultDTO;

public class AddCommandServerResponseHandler implements ServerResponseHandler{
    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        AddCommandExecutionResultDTO addCommandExecutionResultDTO
                = (AddCommandExecutionResultDTO) commandExecutionResultDTO;
        return addCommandExecutionResultDTO.getCommandExecutionResultMessage();
    }
}
