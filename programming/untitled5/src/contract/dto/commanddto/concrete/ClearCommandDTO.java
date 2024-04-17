package contract.dto.commanddto.concrete;

import contract.dto.commanddto.CommandDTO;

public class ClearCommandDTO implements CommandDTO {
    @Override
    public String getCommandName() {
        return "clear";
    }
}
