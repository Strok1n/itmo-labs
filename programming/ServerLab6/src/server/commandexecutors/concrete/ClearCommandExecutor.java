package server.commandexecutors.concrete;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.AddCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.AddCommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.ClearCommandExecutionResultDTO;
import server.CollectionManager;
import server.commandexecutors.CommandExecutor;
import server.util.wrappers.AddCommandDTOAfterDatabaseWrapper;
import server.util.wrappers.ClearCommandDTOAfterDatabaseWrapper;

public class ClearCommandExecutor implements CommandExecutor {
    final private CollectionManager collectionManager;


    public ClearCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }
    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO)
    {
        ClearCommandDTOAfterDatabaseWrapper wrapper = (ClearCommandDTOAfterDatabaseWrapper) commandDTO;

        for (int id: wrapper.getIds()) {
            this.collectionManager.removeById(id);
        }

        return new AddCommandExecutionResultDTO(
                wrapper.getMessage()
        );
    }
}
