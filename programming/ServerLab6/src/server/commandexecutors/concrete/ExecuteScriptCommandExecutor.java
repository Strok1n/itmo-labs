package server.commandexecutors.concrete;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.ExecuteScriptCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.ExecuteScriptCommandExecutionResultDTO;
import server.commandexecutors.CommandExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExecuteScriptCommandExecutor implements CommandExecutor {

    final private Map<String, CommandExecutor> commandExecutors;

    public ExecuteScriptCommandExecutor(Map<String, CommandExecutor> commandExecutors)
    {
        this.commandExecutors = commandExecutors;
    }

    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO)
    {
        ExecuteScriptCommandDTO executeScriptCommandDTO = (ExecuteScriptCommandDTO) commandDTO;

        List<CommandExecutionResultDTO> commandExecutionResultDTOList = new ArrayList<>();

        for (CommandDTO commandDTO1: executeScriptCommandDTO.getCommandDTOList()){
            commandExecutionResultDTOList
                    .add(
                            this.commandExecutors.get(commandDTO1.getCommandName()).execute(
                    commandDTO1)
                    );
        }

        return new ExecuteScriptCommandExecutionResultDTO(
                commandExecutionResultDTOList
        );
    }
}
