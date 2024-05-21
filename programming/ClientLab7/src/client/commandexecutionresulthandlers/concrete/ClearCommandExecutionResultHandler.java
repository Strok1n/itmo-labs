package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public class ClearCommandExecutionResultHandler implements CommandExecutionResultHandler {
    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        return "Коллекция очищена";
    }
}
