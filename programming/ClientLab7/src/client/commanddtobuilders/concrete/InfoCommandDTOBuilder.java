package client.commanddtobuilders.concrete;

import client.commanddtobuilders.CommandDTOBuilder;
import client.util.StringIterator;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.InfoCommandDTO;

public class InfoCommandDTOBuilder implements CommandDTOBuilder {
    @Override
    public CommandDTO buildCommandDTO(String[] commandArguments) {
        return new InfoCommandDTO();
    }

    @Override
    public CommandDTO buildCommandDTOFromScript(String[] fileStrings, StringIterator stringIterator, String commandArgument) {
        return new InfoCommandDTO();
    }
}
