package contract.dto.commanddto.concrete;

import contract.dto.commanddto.CommandDTO;

public class PrintAscendingCommandDTO implements CommandDTO {
    @Override
    public String getCommandName() {
        return "print_ascending";
    }
}
