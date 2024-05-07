package client.commanddtobuilders.concrete;


import client.commanddtobuilders.CommandDTOBuilder;
import client.util.StringIterator;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.HelpCommandDTO;

public class HelpCommandDTOBuilder implements CommandDTOBuilder {

    @Override
    public CommandDTO buildCommandDTO(String[] commandArguments) {
        return new HelpCommandDTO();
    }

    @Override
    public CommandDTO buildCommandDTOFromScript(String[] fileStrings, StringIterator stringIterator, String commandArgument) {
        return new HelpCommandDTO();
    }
}
