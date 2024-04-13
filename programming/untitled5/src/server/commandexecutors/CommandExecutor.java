package server.commandexecutors;

import contract.command.CommandDTO;
import contract.commandexecutionresult.CommandExecutionResultDTO;

public interface CommandExecutor {
    CommandExecutionResultDTO execute(CommandDTO commandDTO);
}
