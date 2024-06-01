package client.commanddtobuilders.concrete;

import client.commanddtobuilders.CommandDTOBuilder;
import client.util.StringIterator;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.RegisterCommandDTO;

public class RegisterCommandDTOBuilder implements CommandDTOBuilder {
    @Override
    public CommandDTO buildCommandDTO(String[] commandArguments) {
        return new RegisterCommandDTO();
    }

    @Override
    public CommandDTO buildCommandDTOFromScript(String[] fileStrings, StringIterator stringIterator, String commandArgument) {
        return new RegisterCommandDTO();
    }
}