package server.commandexecutors.concrete;

import contract.dto.CommandExecutionResultDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.PrintAscendingCommandExecutionResultDTO;
import server.CollectionManager;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;

import java.util.List;

public class PrintAscendingCommandExecutor extends CommandExecutor {
    final private CollectionManager collectionManager;

    public PrintAscendingCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTOWrapper execute(CommandDTO commandDTO) {

       List<LabWork> sorted  = this.collectionManager.getCollectionCopy()
                .stream().sorted().toList();
       String output = "";
        for (LabWork l: sorted) {
            output = output.concat(l.toString()).concat("\n");
        }

        return new CommandExecutionResultDTOWrapper(new PrintAscendingCommandExecutionResultDTO(
                output
        ),true);
    }
}
