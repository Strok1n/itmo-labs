package server.commandexecutors.concrete;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.ExitCommandExecutionResultDTO;
import server.commandexecutors.CommandExecutor;

public class ExitCommandExecutor implements CommandExecutor {
    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO) {
        return new ExitCommandExecutionResultDTO();
    }
}
