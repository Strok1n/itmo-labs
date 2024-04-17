package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commandexecutionresultdto.concrete.AddCommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public class AddCommandExecutionResultHandler implements CommandExecutionResultHandler {
    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        AddCommandExecutionResultDTO addCommandExecutionResultDTO
                = (AddCommandExecutionResultDTO) commandExecutionResultDTO;
        return addCommandExecutionResultDTO.getCommandExecutionResultMessage();
    }
}
