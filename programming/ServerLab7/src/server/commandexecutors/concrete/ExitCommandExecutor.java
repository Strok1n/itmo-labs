package server.commandexecutors.concrete;

import contract.dto.CommandExecutionResultDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.ExitCommandExecutionResultDTO;
import server.commandexecutors.CommandExecutor;

public class ExitCommandExecutor extends CommandExecutor {
    @Override
    public CommandExecutionResultDTOWrapper execute(CommandDTO commandDTO) {
        return new CommandExecutionResultDTOWrapper(new ExitCommandExecutionResultDTO(),true);
    }
}
