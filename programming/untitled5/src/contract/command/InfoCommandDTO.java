package contract.command;

import contract.command.CommandDTO;

public class InfoCommandDTO implements CommandDTO {
    @Override
    public String getCommandName() {
        return "info";
    }
}
