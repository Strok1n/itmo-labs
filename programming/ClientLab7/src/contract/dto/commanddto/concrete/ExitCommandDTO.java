package contract.dto.commanddto.concrete;

import contract.dto.commanddto.CommandDTO;

public class ExitCommandDTO implements CommandDTO {
    @Override
    public String getCommandName() {
        return "exit";
    }

}
