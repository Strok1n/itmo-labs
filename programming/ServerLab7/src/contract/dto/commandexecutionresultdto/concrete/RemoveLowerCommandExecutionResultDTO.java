package contract.dto.commandexecutionresultdto.concrete;

import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public class RemoveLowerCommandExecutionResultDTO implements CommandExecutionResultDTO {
    private String commandExecutionResultMessage;

    public RemoveLowerCommandExecutionResultDTO(String commandExecutionResultMessage)
    {
        this.commandExecutionResultMessage = commandExecutionResultMessage;
    }


    @Override
    public String getCommandName() {
        return "remove_lower";
    }

    public String getCommandExecutionResultMessage() {
        return commandExecutionResultMessage;
    }
}
