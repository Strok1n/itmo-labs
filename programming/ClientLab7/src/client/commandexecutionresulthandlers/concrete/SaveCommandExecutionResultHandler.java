package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.SaveCommandExecutionResultDTO;

public class SaveCommandExecutionResultHandler implements CommandExecutionResultHandler {

    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        SaveCommandExecutionResultDTO saveCommandExecutionResultDTO
                = (SaveCommandExecutionResultDTO) commandExecutionResultDTO;


        return saveCommandExecutionResultDTO.getMsg();
    }
}
