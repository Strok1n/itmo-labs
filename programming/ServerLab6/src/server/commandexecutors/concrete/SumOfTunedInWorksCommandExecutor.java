package server.commandexecutors.concrete;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.AddCommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.SumOfTunedInWorksCommandExecutionResultDTO;
import server.CollectionManager;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;

public class SumOfTunedInWorksCommandExecutor implements CommandExecutor {
    final private CollectionManager collectionManager;

    public SumOfTunedInWorksCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO) {
        CommandDTOAfterDatabaseWrapper wrapper = (CommandDTOAfterDatabaseWrapper) commandDTO;
        if (!wrapper.isDatabaseOperationDone())
            return new AddCommandExecutionResultDTO(wrapper.getMessage());
        commandDTO = wrapper.getCommandDTO();


        long sum = 0;

        for (LabWork l: this.collectionManager.getCollectionCopy()) {
            sum += l.getTunedInWorks();
        }

        return new SumOfTunedInWorksCommandExecutionResultDTO(
                sum
        );
    }
}
