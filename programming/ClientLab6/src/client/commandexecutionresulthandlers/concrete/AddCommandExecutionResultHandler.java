package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.AddCommandExecutionResultDTO;

public class AddCommandExecutionResultHandler implements CommandExecutionResultHandler {
    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        AddCommandExecutionResultDTO addCommandExecutionResultDTO
                = (AddCommandExecutionResultDTO) commandExecutionResultDTO;
        return addCommandExecutionResultDTO.getCommandExecutionResultMessage();
    }
}
