package contract.dto.commanddto.concrete;

import contract.dto.commanddto.CommandDTO;

public class SumOfTunedInWorksCommandDTO implements CommandDTO {
    @Override
    public String getCommandName() {
        return "sum_of_tuned_in_works";
    }
}
