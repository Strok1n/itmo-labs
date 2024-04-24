package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commanddto.concrete.PrintFieldDescendingDifficultyCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.PrintFieldDescendingDifficultyCommandExecutionResultDTO;

public class PrintFieldDescendingDifficultyCommandExecutionResultHandler implements CommandExecutionResultHandler {
    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        PrintFieldDescendingDifficultyCommandExecutionResultDTO dto = (PrintFieldDescendingDifficultyCommandExecutionResultDTO) commandExecutionResultDTO;
        return dto.getOutput();
    }
}
