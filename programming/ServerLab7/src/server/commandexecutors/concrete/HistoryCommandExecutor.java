package server.commandexecutors.concrete;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.HistoryCommandExecutionResultDTO;
import server.commandexecutors.CommandExecutor;

public class HistoryCommandExecutor implements CommandExecutor {
    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO) {
        return new HistoryCommandExecutionResultDTO();
    }
}
