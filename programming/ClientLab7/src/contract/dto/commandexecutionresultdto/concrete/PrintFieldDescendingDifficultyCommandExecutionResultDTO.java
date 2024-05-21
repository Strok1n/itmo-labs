package contract.dto.commandexecutionresultdto.concrete;

import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public class PrintFieldDescendingDifficultyCommandExecutionResultDTO implements CommandExecutionResultDTO {
    private final String output;

    public PrintFieldDescendingDifficultyCommandExecutionResultDTO(String output) {
        this.output = output;
    }


    @Override
    public String getCommandName() {
        return "print_field_descending_difficulty";
    }


    public String getOutput() {
        return output;
    }
}
