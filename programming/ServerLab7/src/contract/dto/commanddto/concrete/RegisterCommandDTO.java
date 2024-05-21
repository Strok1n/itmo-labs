package contract.dto.commanddto.concrete;

import contract.dto.commanddto.CommandDTO;

public class RegisterCommandDTO implements CommandDTO {
    @Override
    public String getCommandName() {
        return "register";
    }
}