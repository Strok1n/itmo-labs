package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RegisterCommandExecutionResultDTO;

public class RegisterCommandExecutionResultHandler implements CommandExecutionResultHandler {
    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        RegisterCommandExecutionResultDTO registerCommandExecutionResultDTO
                = (RegisterCommandExecutionResultDTO) commandExecutionResultDTO;
        return registerCommandExecutionResultDTO.getCommandExecutionResultMessage();
    }
}