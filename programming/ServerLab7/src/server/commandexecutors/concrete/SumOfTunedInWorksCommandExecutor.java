package server.commandexecutors.concrete;

import contract.dto.CommandExecutionResultDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.SumOfTunedInWorksCommandExecutionResultDTO;
import server.CollectionManager;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;

public class SumOfTunedInWorksCommandExecutor extends CommandExecutor {
    final private CollectionManager collectionManager;

    public SumOfTunedInWorksCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTOWrapper execute(CommandDTO commandDTO) {
        long sum = 0;

        for (LabWork l: this.collectionManager.getCollectionCopy()) {
            sum += l.getTunedInWorks();
        }

        return new CommandExecutionResultDTOWrapper(new SumOfTunedInWorksCommandExecutionResultDTO(
                sum), true);
    }
}
