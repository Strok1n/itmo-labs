package contract.dto.commandexecutionresultdto.concrete;

import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public class RegisterCommandExecutionResultDTO implements CommandExecutionResultDTO {

    private String commandExecutionResultMessage;

    public RegisterCommandExecutionResultDTO(String commandExecutionResultMessage)
    {
        this.commandExecutionResultMessage = commandExecutionResultMessage;
    }
    @Override
    public String getCommandName() {
        return "register";
    }

    public String getCommandExecutionResultMessage() {
        return commandExecutionResultMessage;
    }
}
