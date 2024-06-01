package server.commandexecutors.concrete;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.AddCommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.InfoCommandExecutionResultDTO;
import server.CollectionManager;
import server.commandexecutors.CommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;

public class InfoCommandExecutor implements CommandExecutor {

    final private CollectionManager collectionManager;

    public InfoCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO) {
        CommandDTOAfterDatabaseWrapper wrapper = (CommandDTOAfterDatabaseWrapper) commandDTO;
        if (!wrapper.isDatabaseOperationDone())
            return new AddCommandExecutionResultDTO(wrapper.getMessage());
        commandDTO = wrapper.getCommandDTO();

        return new InfoCommandExecutionResultDTO(
                this.collectionManager.getTypeOfTheCollection(),
                this.collectionManager.getCollectionInitializationDateTime(),
                this.collectionManager.getSizeOfTheCollection()
        );
    }
}
