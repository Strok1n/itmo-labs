package client.commanddtobuilders.concrete;

import client.commanddtobuilders.CommandDTOBuilder;
import client.exceptions.InvalidCommandArgumentsInScriptFileException;
import client.util.StringIterator;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.ClearCommandDTO;

public class ClearCommandDTOBuilder implements CommandDTOBuilder {
    @Override
    public CommandDTO buildCommandDTO(String[] commandArguments) {
        return new ClearCommandDTO();
    }

    @Override
    public CommandDTO buildCommandDTOFromScript(String[] fileStrings, StringIterator stringIterator, String commandArgument) throws InvalidCommandArgumentsInScriptFileException {
        return new ClearCommandDTO();
    }
}
