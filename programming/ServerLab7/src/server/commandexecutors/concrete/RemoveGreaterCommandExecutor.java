package server.commandexecutors.concrete;

import contract.dto.CommandExecutionResultDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.RemoveGreaterCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RemoveGreaterCommandExecutionResultDTO;
import server.CollectionManager;
import server.business.Coordinates;
import server.business.Difficulty;
import server.business.Discipline;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;

import java.time.LocalDate;

public class RemoveGreaterCommandExecutor extends CommandExecutor {

    final private CollectionManager collectionManager;

    public RemoveGreaterCommandExecutor(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTOWrapper execute(CommandDTO commandDTO) {
        RemoveGreaterCommandDTO removeGreaterCommandDTO = (RemoveGreaterCommandDTO) commandDTO;

        LabWork labWork = new LabWork(
                this.collectionManager.generateId(),
                removeGreaterCommandDTO.getLabWorkName(),
                new Coordinates(removeGreaterCommandDTO.getLabWorkCoordinatesX(),
                        removeGreaterCommandDTO.getLabWorkCoordinatesY()),
                LocalDate.now(),
                removeGreaterCommandDTO.getMinimalPoint(),
                removeGreaterCommandDTO.getTunedInWorks(),
                Difficulty.valueOf(removeGreaterCommandDTO.getDifficulty()),
                new Discipline(removeGreaterCommandDTO.getDisciplineName(),
                        removeGreaterCommandDTO.getDisciplineLabsCount())
        );

        int size1 = this.collectionManager.getSizeOfTheCollection();

        this.collectionManager.getCollection().removeIf(
                labWork1 -> labWork.compareTo(labWork1) < 0
        );

        int size2 = this.collectionManager.getSizeOfTheCollection();

        String commandExecutionResultMessage = "Из коллекции было удалено " + (size1 - size2) + " элементов";

        return new CommandExecutionResultDTOWrapper(
                new RemoveGreaterCommandExecutionResultDTO(commandExecutionResultMessage), true);


    }

}
