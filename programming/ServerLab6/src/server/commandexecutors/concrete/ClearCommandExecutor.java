package server.commandexecutors.concrete;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.ClearCommandExecutionResultDTO;
import server.CollectionManager;
import server.commandexecutors.CommandExecutor;

public class ClearCommandExecutor implements CommandExecutor {
    final private CollectionManager collectionManager;


    public ClearCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }
    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO)
    {
        this.collectionManager.clear();
        return new ClearCommandExecutionResultDTO();
    }
}
