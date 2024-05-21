package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public class ExitCommandExecutionResultHandler implements CommandExecutionResultHandler {

    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        System.exit(0);
        return "";
    }


}
