package client.commanddtobuilders;

import client.util.StringIterator;
import contract.command.CommandDTO;
import contract.command.InfoCommandDTO;

public class InfoCommandDTOBuilder implements CommandDTOBuilder {
    @Override
    public CommandDTO buildCommandDTO(String[] commandArguments) {
        return new InfoCommandDTO();
    }

    @Override
    public CommandDTO buildCommandDTOFromScript(String[] fileStrings, StringIterator stringIterator) {
        return null;
    }
}
