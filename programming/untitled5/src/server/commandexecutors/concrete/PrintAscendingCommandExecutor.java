package server.commandexecutors.concrete;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.AddCommandDTO;
import contract.dto.commanddto.concrete.PrintAscendingCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.InfoCommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.PrintAscendingCommandExecutionResultDTO;
import server.CollectionManager;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;

import java.util.List;

public class PrintAscendingCommandExecutor implements CommandExecutor {
    final private CollectionManager collectionManager;

    public PrintAscendingCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO) {

       List<LabWork> sorted  = this.collectionManager.getCollectionCopy()
                .stream().sorted().toList();
       String output = "";
        for (LabWork l: sorted) {
            output = output.concat(l.toString()).concat("\n");
        }

        return new PrintAscendingCommandExecutionResultDTO(
                output
        );
    }
}
