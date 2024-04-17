package server.commandexecutors;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public interface CommandExecutor {
    CommandExecutionResultDTO execute(CommandDTO commandDTO);
}
