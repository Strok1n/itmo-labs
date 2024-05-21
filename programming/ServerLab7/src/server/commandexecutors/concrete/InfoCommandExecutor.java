package server.commandexecutors.concrete;

import contract.dto.CommandExecutionResultDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.InfoCommandExecutionResultDTO;
import server.CollectionManager;
import server.commandexecutors.CommandExecutor;

public class InfoCommandExecutor extends CommandExecutor {

    final private CollectionManager collectionManager;

    public InfoCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTOWrapper execute(CommandDTO commandDTO) {

        return new CommandExecutionResultDTOWrapper(new InfoCommandExecutionResultDTO(
                this.collectionManager.getTypeOfTheCollection(),
                this.collectionManager.getCollectionInitializationDateTime(),
                this.collectionManager.getSizeOfTheCollection()
        ),true);
    }
}
