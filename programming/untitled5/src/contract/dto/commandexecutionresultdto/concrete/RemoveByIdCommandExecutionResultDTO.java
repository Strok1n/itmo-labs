package contract.dto.commandexecutionresultdto.concrete;

import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public class RemoveByIdCommandExecutionResultDTO implements CommandExecutionResultDTO
{
    private boolean commandExecutionResult;
    private int id;

    public RemoveByIdCommandExecutionResultDTO(boolean commandExecutionResult, int id) {
        this.commandExecutionResult = commandExecutionResult;
        this.id = id;
    }

    public boolean getCommandExecutionResult() {
        return commandExecutionResult;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getCommandName() {
        return "remove_by_id";
    }
}
