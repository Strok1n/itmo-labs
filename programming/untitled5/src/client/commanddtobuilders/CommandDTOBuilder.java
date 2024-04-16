package client.commanddtobuilders;

import client.exceptions.InvalidCommandArgumentsInScriptFileException;
import client.util.StringIterator;
import contract.command.CommandDTO;

public interface CommandDTOBuilder
{
    CommandDTO buildCommandDTO(String[] commandArguments);
    CommandDTO buildCommandDTOFromScript(String[] fileStrings, StringIterator stringIterator, String commandArgument) throws InvalidCommandArgumentsInScriptFileException;
}