package client.serverresponsehandler;

import contract.commandexecutionresult.CommandExecutionResultDTO;

public interface ServerResponseHandler{

    String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO);

}
