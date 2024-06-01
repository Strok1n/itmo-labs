package server.commandexecutors.concrete;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.AddCommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.HistoryCommandExecutionResultDTO;
import server.commandexecutors.CommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;

public class HistoryCommandExecutor implements CommandExecutor {
    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO) {
        CommandDTOAfterDatabaseWrapper wrapper = (CommandDTOAfterDatabaseWrapper) commandDTO;
        if (!wrapper.isDatabaseOperationDone())
            return new AddCommandExecutionResultDTO(wrapper.getMessage());
        commandDTO = wrapper.getCommandDTO();


        return new HistoryCommandExecutionResultDTO();
    }
}
