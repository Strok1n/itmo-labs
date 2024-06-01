package server.commandexecutors.concrete;

import contract.dto.CommandDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.RemoveByIdCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.AddCommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RemoveByIdCommandExecutionResultDTO;
import server.CollectionManager;
import server.commandexecutors.CommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;

public class RemoveByIdCommandExecutor implements CommandExecutor {

    final private CollectionManager collectionManager;


    public RemoveByIdCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO){
        CommandDTOAfterDatabaseWrapper wrapper = (CommandDTOAfterDatabaseWrapper) commandDTO;
        if (!wrapper.isDatabaseOperationDone())
            return new AddCommandExecutionResultDTO(
                    wrapper.getMessage()
            );

        commandDTO = wrapper.getCommandDTO();

        RemoveByIdCommandDTO removeByIdCommandDTO = (RemoveByIdCommandDTO) (commandDTO);
        this.collectionManager.removeById(removeByIdCommandDTO.getId());
        return new RemoveByIdCommandExecutionResultDTO(true,
                removeByIdCommandDTO.getId());
    }
}
