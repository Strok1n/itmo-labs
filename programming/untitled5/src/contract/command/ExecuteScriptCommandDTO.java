package contract.command;

import java.util.List;

public class ExecuteScriptCommandDTO implements CommandDTO{

    private List<CommandDTO> commandDTOList;

    public ExecuteScriptCommandDTO(List<CommandDTO> commandDTOList)
    {
        this.commandDTOList = commandDTOList;
    }

    @Override
    public String getCommandName() {
        return "execute_script";
    }

    public List<CommandDTO> getCommandDTOList() {
        return commandDTOList;
    }
}
