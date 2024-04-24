package contract.dto.commandexecutionresultdto.concrete;

import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public class ExitCommandExecutionResultDTO implements CommandExecutionResultDTO {
    @Override
    public String getCommandName() {
        return "exit";
    }
}
