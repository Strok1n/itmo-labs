package server.databaselayercommandexecutors.concrete;

import contract.dto.CommandDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.PrintFieldDescendingDifficultyCommandExecutionResultDTO;
import server.CollectionManager;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;
import server.databaselayercommandexecutors.DatabaseLayerCommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;

public class PrintFieldDescendingDifficultyDatabaseLayerCommandExecutor extends DatabaseLayerCommandExecutor {




    public PrintFieldDescendingDifficultyDatabaseLayerCommandExecutor(Connection connection)
    {
        super(connection);
    }

    @Override
    public CommandDTOAfterDatabaseWrapper execute(CommandDTO commandDTO) {
        CommandDTO simpleDTO = ((CommandDTOWrapper) commandDTO).getCommandDTO();
        Integer userId = this.authorize((CommandDTOWrapper) commandDTO);
        if (userId == null)
            return new CommandDTOAfterDatabaseWrapper(simpleDTO,  false,
                    "Пользователь не зарегистрирован в базе данных");
        return new CommandDTOAfterDatabaseWrapper(simpleDTO, true);
    }
}