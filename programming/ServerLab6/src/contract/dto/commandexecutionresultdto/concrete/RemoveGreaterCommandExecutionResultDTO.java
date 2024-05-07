package contract.dto.commandexecutionresultdto.concrete;

import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public class RemoveGreaterCommandExecutionResultDTO implements CommandExecutionResultDTO {

    private String commandExecutionResultMessage;

    public RemoveGreaterCommandExecutionResultDTO(String commandExecutionResultMessage)
    {
        this.commandExecutionResultMessage = commandExecutionResultMessage;
    }

    @Override
    public String getCommandName() {
        return "remove_greater";
    }

    public String getCommandExecutionResultMessage() {
        return commandExecutionResultMessage;
    }
}
