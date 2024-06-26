package server.commandexecutors.concrete;

import contract.dto.CommandExecutionResultDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.HistoryCommandExecutionResultDTO;
import server.commandexecutors.CommandExecutor;

public class HistoryCommandExecutor extends CommandExecutor {
    @Override
    public CommandExecutionResultDTOWrapper execute(CommandDTO commandDTO) {
        return new CommandExecutionResultDTOWrapper(new HistoryCommandExecutionResultDTO(), true);
    }
}
