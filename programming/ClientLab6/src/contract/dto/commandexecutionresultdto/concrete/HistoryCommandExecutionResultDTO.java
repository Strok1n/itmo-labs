package contract.dto.commandexecutionresultdto.concrete;

import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public class HistoryCommandExecutionResultDTO implements CommandExecutionResultDTO {
    @Override
    public String getCommandName() {
        return "history";
    }
}
