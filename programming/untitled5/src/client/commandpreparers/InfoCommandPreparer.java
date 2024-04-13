package client.commandpreparers;

import contract.command.CommandDTO;
import contract.command.InfoCommandDTO;

public class InfoCommandPreparer implements CommandPreparer{
    @Override
    public CommandDTO prepareCommand(String[] arguments) {
        return new InfoCommandDTO();
    }
}
