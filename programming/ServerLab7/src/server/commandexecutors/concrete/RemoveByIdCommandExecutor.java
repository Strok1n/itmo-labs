package server.commandexecutors.concrete;

import contract.dto.CommandExecutionResultDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.RemoveByIdCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RemoveByIdCommandExecutionResultDTO;
import server.CollectionManager;
import server.commandexecutors.CommandExecutor;

public class RemoveByIdCommandExecutor extends CommandExecutor {

    final private CollectionManager collectionManager;


    public RemoveByIdCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTOWrapper execute(CommandDTO commandDTO){
        RemoveByIdCommandDTO removeByIdCommandDTO = (RemoveByIdCommandDTO) commandDTO;
        return new  CommandExecutionResultDTOWrapper(new RemoveByIdCommandExecutionResultDTO(this.collectionManager.removeById(removeByIdCommandDTO.getId()),
                removeByIdCommandDTO.getId()), true);
    }
}
