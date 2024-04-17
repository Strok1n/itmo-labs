package contract.dto.commanddto.concrete;

import contract.dto.commanddto.CommandDTO;

public class HelpCommandDTO implements CommandDTO {
    @Override
    public String getCommandName() {
        return "help";
    }
}
