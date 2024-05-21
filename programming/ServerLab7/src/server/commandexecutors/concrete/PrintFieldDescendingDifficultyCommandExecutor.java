package server.commandexecutors.concrete;

import contract.dto.CommandExecutionResultDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.PrintFieldDescendingDifficultyCommandExecutionResultDTO;
import server.CollectionManager;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;

import java.util.Comparator;
import java.util.List;

public class PrintFieldDescendingDifficultyCommandExecutor extends CommandExecutor {

    final private CollectionManager collectionManager;

    public PrintFieldDescendingDifficultyCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTOWrapper execute(CommandDTO commandDTO) {

        List<LabWork> sorted  = this.collectionManager.getCollectionCopy()
                .stream().sorted(
                        new Comparator<LabWork>() {
                            @Override
                            public int compare(LabWork o1, LabWork o2) {
                                return o2.getDifficulty().compareTo(
                                        o1.getDifficulty());
                            }
                        }
                ).toList();
        String output = "";
        for (LabWork l: sorted) {
            output = output.concat(l.getDifficulty().toString()).concat("\n");
        }

        return new CommandExecutionResultDTOWrapper(new PrintFieldDescendingDifficultyCommandExecutionResultDTO(
                output
        ), true);
    }
}
