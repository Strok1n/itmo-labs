package client.commandexecutionresulthandlers;

import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public interface CommandExecutionResultHandler {

    String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO);

}
