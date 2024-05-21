package contract.dto.commanddto.concrete;

import contract.dto.commanddto.CommandDTO;

public class SaveCommandDTO implements CommandDTO {
    @Override
    public String getCommandName() {
        return "save";
    }
}
