package server.commandexecutors;

import contract.command.CommandDTO;
import contract.commandexecutionresult.CommandExecutionResultDTO;
import contract.commandexecutionresult.InfoCommandExecutionResultDTO;
import server.CollectionManager;

public class InfoCommandExecutor implements CommandExecutor{

    final private CollectionManager collectionManager;

    public InfoCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO) {

        return new InfoCommandExecutionResultDTO(
                this.collectionManager.getTypeOfTheCollection(),
                this.collectionManager.getCollectionInitializationDateTime(),
                this.collectionManager.getSizeOfTheCollection()
        );
    }
}
