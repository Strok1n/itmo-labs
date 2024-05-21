package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.PrintAscendingCommandExecutionResultDTO;

public class PrintAscendingCommandExecutionResultHandler implements CommandExecutionResultHandler {
    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO)
    {
        PrintAscendingCommandExecutionResultDTO dto = (PrintAscendingCommandExecutionResultDTO) commandExecutionResultDTO;
        return dto.getOutput();
    }
}
