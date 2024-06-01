package server.databaselayercommandexecutors.concrete;

import contract.dto.CommandDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.ExecuteScriptCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.ExecuteScriptCommandExecutionResultDTO;
import server.commandexecutors.CommandExecutor;
import server.databaselayercommandexecutors.DatabaseLayerCommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExecuteScriptDatabaseLayerCommandExecutor extends DatabaseLayerCommandExecutor {

    public ExecuteScriptDatabaseLayerCommandExecutor(Connection connection)
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