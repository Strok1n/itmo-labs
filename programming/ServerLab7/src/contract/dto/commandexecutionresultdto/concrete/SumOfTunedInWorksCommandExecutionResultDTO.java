package contract.dto.commandexecutionresultdto.concrete;

import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public class SumOfTunedInWorksCommandExecutionResultDTO implements CommandExecutionResultDTO {

    private final long sum;

    public SumOfTunedInWorksCommandExecutionResultDTO(long sum) {
        this.sum = sum;
    }

    @Override
    public String getCommandName() {
        return "sum_of_tuned_in_works";
    }

    public long getSum() {
        return sum;
    }
}
