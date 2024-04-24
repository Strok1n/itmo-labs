package contract.dto.commandexecutionresultdto.concrete;

import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

import java.util.ArrayList;

public class PrintAscendingCommandExecutionResultDTO implements CommandExecutionResultDTO {
    private final String output;

    public PrintAscendingCommandExecutionResultDTO(String output) {
        this.output = output;
    }

    @Override
    public String getCommandName() {
        return "print_ascending";
    }


    public String getOutput() {
        return output;
    }
}
