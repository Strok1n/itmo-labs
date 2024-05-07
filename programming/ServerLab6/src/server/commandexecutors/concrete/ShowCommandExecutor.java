package server.commandexecutors.concrete;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.ShowCommandExecutionResultDTO;
import server.CollectionManager;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;

import java.util.ArrayList;
import java.util.List;

public class ShowCommandExecutor implements CommandExecutor {


    final private CollectionManager collectionManager;

    public ShowCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO) {

        List<String> collectionElements = new ArrayList<>();

        for (LabWork labWork : collectionManager.getCollectionCopy())
            collectionElements.add(labWork.toString());

        return new ShowCommandExecutionResultDTO(collectionElements);
    }

}
