package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RemoveGreaterCommandExecutionResultDTO;

public class RemoveGreaterCommandExecutionResultHandler implements CommandExecutionResultHandler {
    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        RemoveGreaterCommandExecutionResultDTO dto = (RemoveGreaterCommandExecutionResultDTO) commandExecutionResultDTO;
        return dto.getCommandExecutionResultMessage();
    }
}
