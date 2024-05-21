package contract.dto.commanddto.concrete;

import contract.dto.commanddto.CommandDTO;

public class ShowCommandDTO implements CommandDTO {
    @Override
    public String getCommandName() {
        return "show";
    }
}
