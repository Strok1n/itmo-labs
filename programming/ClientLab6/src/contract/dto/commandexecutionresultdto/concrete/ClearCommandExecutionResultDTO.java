package contract.dto.commandexecutionresultdto.concrete;

import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public class ClearCommandExecutionResultDTO implements CommandExecutionResultDTO {
    @Override
    public String getCommandName() {
        return "clear";
    }
}
