package server.commandexecutors.concrete;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.AddCommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.PrintAscendingCommandExecutionResultDTO;
import server.CollectionManager;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;

import java.util.List;

public class PrintAscendingCommandExecutor implements CommandExecutor {
    final private CollectionManager collectionManager;

    public PrintAscendingCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO) {
        CommandDTOAfterDatabaseWrapper wrapper = (CommandDTOAfterDatabaseWrapper) commandDTO;
        if (!wrapper.isDatabaseOperationDone())
            return new AddCommandExecutionResultDTO(wrapper.getMessage());


        commandDTO = wrapper.getCommandDTO();

       List<LabWork> sorted  = this.collectionManager.getCollectionCopy()
                .stream().sorted().toList();
       String output = "";
        for (LabWork l: sorted) {
            output = output.concat(l.toString()).concat("\n");
        }

        System.out.println("#$");

        return new PrintAscendingCommandExecutionResultDTO(
                output
        );
    }
}
