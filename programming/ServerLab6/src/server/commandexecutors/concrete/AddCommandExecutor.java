package server.commandexecutors.concrete;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.AddCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.AddCommandExecutionResultDTO;
import server.CollectionManager;
import server.business.Coordinates;
import server.business.Difficulty;
import server.business.Discipline;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;
import server.util.wrappers.AddCommandDTOAfterDatabaseWrapper;

import java.time.LocalDate;

public class AddCommandExecutor implements CommandExecutor {

    final private CollectionManager collectionManager;


    public AddCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO){



        AddCommandDTOAfterDatabaseWrapper wrapper = (AddCommandDTOAfterDatabaseWrapper) commandDTO;

        if (!wrapper.isDatabaseOperationDone())
            return new AddCommandExecutionResultDTO(wrapper.getMessage());


        commandDTO = wrapper.getCommandDTO();
        AddCommandDTO addCommandDTO = (AddCommandDTO) commandDTO;

        LabWork labWork = new LabWork(
               wrapper.getLabWorkGeneratedId(),
                addCommandDTO.getLabWorkName(),
                new Coordinates(addCommandDTO.getLabWorkCoordinatesX(),
                        addCommandDTO.getLabWorkCoordinatesY()),
                LocalDate.now(),
                addCommandDTO.getMinimalPoint(),
                addCommandDTO.getTunedInWorks(),
                Difficulty.valueOf(addCommandDTO.getDifficulty()),
                new Discipline(addCommandDTO.getDisciplineName(),
                        addCommandDTO.getDisciplineLabsCount())
        );

        boolean flag = this.collectionManager.addLabWorkToTheCollection(labWork);
        String commandExecutionResultMessage;

        if (flag)
            commandExecutionResultMessage = "Лабораторная работа успешно добавлена в коллекцию";
        else
            commandExecutionResultMessage = "Лабораторная работа не добавлена в коллекцию";
        return new AddCommandExecutionResultDTO(commandExecutionResultMessage);
    }
}
