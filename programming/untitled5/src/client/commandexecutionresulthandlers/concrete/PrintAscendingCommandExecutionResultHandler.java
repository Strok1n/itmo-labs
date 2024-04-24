package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commanddto.concrete.PrintAscendingCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.PrintAscendingCommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.PrintFieldDescendingDifficultyCommandExecutionResultDTO;

public class PrintAscendingCommandExecutionResultHandler implements CommandExecutionResultHandler {
    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO)
    {
        PrintAscendingCommandExecutionResultDTO dto = (PrintAscendingCommandExecutionResultDTO) commandExecutionResultDTO;
        return dto.getOutput();
    }
}
