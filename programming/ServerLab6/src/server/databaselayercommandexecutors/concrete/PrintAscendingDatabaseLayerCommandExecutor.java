package server.databaselayercommandexecutors.concrete;

import contract.dto.CommandDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.PrintAscendingCommandExecutionResultDTO;
import server.CollectionManager;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;
import server.databaselayercommandexecutors.DatabaseLayerCommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;

import java.sql.Connection;
import java.util.List;

public class PrintAscendingDatabaseLayerCommandExecutor  extends DatabaseLayerCommandExecutor {



    public PrintAscendingDatabaseLayerCommandExecutor(Connection connection)
    {
        super(connection);
    }


    @Override
    public CommandDTOAfterDatabaseWrapper execute(CommandDTO commandDTO) {
        System.out.println("##$$");

        CommandDTO simpleDTO = ((CommandDTOWrapper) commandDTO).getCommandDTO();
        Integer userId = this.authorize((CommandDTOWrapper) commandDTO);
        if (userId == null)
            return new CommandDTOAfterDatabaseWrapper(simpleDTO,  false,
                    "Пользователь не зарегистрирован в базе данных");
        return new CommandDTOAfterDatabaseWrapper(simpleDTO, true);
    }
}