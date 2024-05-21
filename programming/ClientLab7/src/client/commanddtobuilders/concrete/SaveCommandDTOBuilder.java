package client.commanddtobuilders.concrete;

import client.commanddtobuilders.CommandDTOBuilder;
import client.exceptions.InvalidCommandArgumentsInScriptFileException;
import client.util.StringIterator;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.SaveCommandDTO;

public class SaveCommandDTOBuilder implements CommandDTOBuilder {
    @Override
    public CommandDTO buildCommandDTO(String[] commandArguments) {
        return new SaveCommandDTO();
    }

    @Override
    public CommandDTO buildCommandDTOFromScript(String[] fileStrings, StringIterator stringIterator, String commandArgument) throws InvalidCommandArgumentsInScriptFileException {
        return new SaveCommandDTO();
    }
}
