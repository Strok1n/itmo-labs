package server.commandexecutors.concrete;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.RemoveGreaterCommandDTO;
import contract.dto.commanddto.concrete.RemoveLowerCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RemoveGreaterCommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RemoveLowerCommandExecutionResultDTO;
import server.CollectionManager;
import server.business.Coordinates;
import server.business.Difficulty;
import server.business.Discipline;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;

import java.time.LocalDate;

public class RemoveLowerCommandExecutor implements CommandExecutor {
    final private CollectionManager collectionManager;

    public RemoveLowerCommandExecutor(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO) {
        RemoveLowerCommandDTO removeLowerCommandDTO = (RemoveLowerCommandDTO) commandDTO;

        LabWork labWork = new LabWork(
                this.collectionManager.generateId(),
                removeLowerCommandDTO.getLabWorkName(),
                new Coordinates(removeLowerCommandDTO.getLabWorkCoordinatesX(),
                        removeLowerCommandDTO.getLabWorkCoordinatesY()),
                LocalDate.now(),
                removeLowerCommandDTO.getMinimalPoint(),
                removeLowerCommandDTO.getTunedInWorks(),
                Difficulty.valueOf(removeLowerCommandDTO.getDifficulty()),
                new Discipline(removeLowerCommandDTO.getDisciplineName(),
                        removeLowerCommandDTO.getDisciplineLabsCount())
        );

        int size1 = this.collectionManager.getSizeOfTheCollection();

        this.collectionManager.getCollection().removeIf(
                labWork1 -> labWork.compareTo(labWork1) > 0
        );

        int size2 = this.collectionManager.getSizeOfTheCollection();

        String commandExecutionResultMessage = "Из коллекции было удалено " + (size1 - size2) + " элементов";

        return new RemoveLowerCommandExecutionResultDTO(commandExecutionResultMessage);


    }
}
