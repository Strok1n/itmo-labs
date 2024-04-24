package client.commanddtobuilders.concrete;

import client.commanddtobuilders.CommandDTOBuilder;
import client.util.StringIterator;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.ExitCommandDTO;
import contract.dto.commanddto.concrete.PrintFieldDescendingDifficultyCommandDTO;

public class PrintFieldDescendingDifficultyCommandDTOBuilder implements CommandDTOBuilder {
    @Override
    public CommandDTO buildCommandDTO(String[] commandArguments) {
        return new PrintFieldDescendingDifficultyCommandDTO();
    }

    @Override
    public CommandDTO buildCommandDTOFromScript(String[] fileStrings, StringIterator stringIterator, String commandArgument) {
        return new PrintFieldDescendingDifficultyCommandDTO();
    }
}
