package server.commandexecutors.concrete;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.UpdateCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.UpdateCommandExecutionResultDTO;
import server.CollectionManager;
import server.business.Coordinates;
import server.business.Difficulty;
import server.business.Discipline;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;

import java.time.LocalDate;

public class UpdateCommandExecutor implements CommandExecutor {

    final private CollectionManager collectionManager;


    public UpdateCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO){
        UpdateCommandDTO updateCommandDTO = (UpdateCommandDTO) commandDTO;

        if (this.collectionManager.removeById(updateCommandDTO.getId()))
        {
            LabWork labWork = new LabWork(
                    updateCommandDTO.getId(),
                    updateCommandDTO.getLabWorkName(),
                    new Coordinates(updateCommandDTO.getLabWorkCoordinatesX(),
                            updateCommandDTO.getLabWorkCoordinatesY()),
                    LocalDate.now(),
                    updateCommandDTO.getMinimalPoint(),
                    updateCommandDTO.getTunedInWorks(),
                    Difficulty.valueOf(updateCommandDTO.getDifficulty()),
                    new Discipline(updateCommandDTO.getDisciplineName(),
                            updateCommandDTO.getDisciplineLabsCount())
            );
            return new UpdateCommandExecutionResultDTO(this.collectionManager.addLabWorkToTheCollection(labWork), updateCommandDTO.getId());
        }
        else
            return new UpdateCommandExecutionResultDTO(false, updateCommandDTO.getId());
    }
}
