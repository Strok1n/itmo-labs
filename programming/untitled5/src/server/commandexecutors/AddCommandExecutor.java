package server.commandexecutors;

import contract.command.AddCommandDTO;
import contract.command.CommandDTO;
import contract.commandexecutionresult.AddCommandExecutionResultDTO;
import contract.commandexecutionresult.CommandExecutionResultDTO;
import server.CollectionManager;
import server.business.Coordinates;
import server.business.Difficulty;
import server.business.Discipline;
import server.business.LabWork;

import java.time.LocalDateTime;

public class AddCommandExecutor implements CommandExecutor{

    final private CollectionManager collectionManager;


    public AddCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO){
        AddCommandDTO addCommandDTO = (AddCommandDTO) commandDTO;

        LabWork labWork = new LabWork(
                this.collectionManager.generateId(),
                addCommandDTO.getLabWorkName(),
                new Coordinates(addCommandDTO.getLabWorkCoordinatesX(),
                        addCommandDTO.getLabWorkCoordinatesY()),
                LocalDateTime.now(),
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
