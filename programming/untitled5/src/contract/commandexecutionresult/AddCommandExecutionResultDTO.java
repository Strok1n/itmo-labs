package contract.commandexecutionresult;

public class AddCommandExecutionResultDTO implements CommandExecutionResultDTO{

    private String commandExecutionResultMessage;

    public AddCommandExecutionResultDTO(String commandExecutionResultMessage)
    {
        this.commandExecutionResultMessage = commandExecutionResultMessage;
    }
    @Override
    public String getCommandName() {
        return "add";
    }

    public String getCommandExecutionResultMessage() {
        return commandExecutionResultMessage;
    }
}
