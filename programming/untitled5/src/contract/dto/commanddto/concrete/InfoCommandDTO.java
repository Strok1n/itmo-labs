package contract.dto.commanddto.concrete;

import contract.dto.commanddto.CommandDTO;

public class InfoCommandDTO implements CommandDTO {
    @Override
    public String getCommandName() {
        return "info";
    }
}
