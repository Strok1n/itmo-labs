package server.commandexecutors.concrete;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.RemoveByIdCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RemoveByIdCommandExecutionResultDTO;
import server.CollectionManager;
import server.commandexecutors.CommandExecutor;

public class RemoveByIdCommandExecutor implements CommandExecutor {

    final private CollectionManager collectionManager;


    public RemoveByIdCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO){
        RemoveByIdCommandDTO removeByIdCommandDTO = (RemoveByIdCommandDTO) commandDTO;
        return new RemoveByIdCommandExecutionResultDTO(this.collectionManager.removeById(removeByIdCommandDTO.getId()),
                removeByIdCommandDTO.getId());
    }
}
