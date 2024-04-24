package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RemoveGreaterCommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RemoveLowerCommandExecutionResultDTO;

public class RemoveLowerCommandExecutionResultHandler implements CommandExecutionResultHandler {
    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        RemoveLowerCommandExecutionResultDTO dto = (RemoveLowerCommandExecutionResultDTO) commandExecutionResultDTO;
        return dto.getCommandExecutionResultMessage();
    }
}
