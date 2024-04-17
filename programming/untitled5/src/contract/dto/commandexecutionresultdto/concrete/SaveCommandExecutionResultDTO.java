package contract.dto.commandexecutionresultdto.concrete;

import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public class SaveCommandExecutionResultDTO implements CommandExecutionResultDTO {
    @Override
    public String getCommandName() {
        return "save";
    }
}
