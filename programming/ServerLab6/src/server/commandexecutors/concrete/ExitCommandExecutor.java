package server.commandexecutors.concrete;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.AddCommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.ExitCommandExecutionResultDTO;
import server.commandexecutors.CommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;
import server.util.wrappers.AddCommandDTOAfterDatabaseWrapper;

public class  ExitCommandExecutor implements CommandExecutor {
    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO) {
        CommandDTOAfterDatabaseWrapper wrapper = (CommandDTOAfterDatabaseWrapper) commandDTO;
        if (!wrapper.isDatabaseOperationDone())
            return new AddCommandExecutionResultDTO(wrapper.getMessage());


        commandDTO = wrapper.getCommandDTO();
        return new ExitCommandExecutionResultDTO();
    }
}
