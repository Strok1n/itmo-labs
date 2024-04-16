package contract.commandexecutionresult;

import contract.command.CommandDTO;

import java.util.List;

public class ExecuteScriptCommandExecutionResultDTO implements CommandExecutionResultDTO{
    private List<CommandExecutionResultDTO> commandExecutionResultDTOList;

    public ExecuteScriptCommandExecutionResultDTO(List<CommandExecutionResultDTO> commandExecutionResultDTOList)
    {
        this.commandExecutionResultDTOList = commandExecutionResultDTOList;
    }

    @Override
    public String getCommandName() {
        return "execute_script";
    }

    public List<CommandExecutionResultDTO> getCommandExecutionResultDTOList() {
        return commandExecutionResultDTOList;
    }
}
