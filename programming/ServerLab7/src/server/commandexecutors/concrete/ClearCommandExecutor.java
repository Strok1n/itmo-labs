package server.commandexecutors.concrete;

import contract.dto.CommandExecutionResultDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.ClearCommandExecutionResultDTO;
import server.CollectionManager;
import server.commandexecutors.CommandExecutor;

public class ClearCommandExecutor extends CommandExecutor {
    final private CollectionManager collectionManager;


    public ClearCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }
    @Override
    public CommandExecutionResultDTOWrapper execute(CommandDTO commandDTO)
    {
        this.collectionManager.clear();
        return new CommandExecutionResultDTOWrapper(
                new ClearCommandExecutionResultDTO(),true);
    }
}
