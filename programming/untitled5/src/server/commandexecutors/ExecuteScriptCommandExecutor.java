package server.commandexecutors;

import contract.command.CommandDTO;
import contract.command.ExecuteScriptCommandDTO;
import contract.commandexecutionresult.CommandExecutionResultDTO;
import contract.commandexecutionresult.ExecuteScriptCommandExecutionResultDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExecuteScriptCommandExecutor implements CommandExecutor{

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
