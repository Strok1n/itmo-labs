package client.commanddtobuilders.concrete;

import client.commanddtobuilders.CommandDTOBuilder;
import client.io.ConsoleReader;
import client.io.ConsoleWriter;
import client.exceptions.InvalidCommandArgumentsInScriptFileException;
import client.util.FieldValidators;
import client.util.LabWorkFieldValuesGetter;
import client.util.StringIterator;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.RemoveByIdCommandDTO;

public class RemoveByIdCommandDTOBuilder implements CommandDTOBuilder {
    private final LabWorkFieldValuesGetter labWorkFieldValuesGetter;

    public RemoveByIdCommandDTOBuilder(ConsoleReader consoleReader, ConsoleWriter consoleWriter)
    {
        this.labWorkFieldValuesGetter = new LabWorkFieldValuesGetter(consoleReader, consoleWriter);
    }
    @Override
    public CommandDTO buildCommandDTO(String[] commandArguments) {
        int id;
        if (!FieldValidators.validateLabWorkId(commandArguments[0]))
            id = labWorkFieldValuesGetter.getLabWorkId();
        else
            id = Integer.parseInt(commandArguments[0]);
        return new RemoveByIdCommandDTO(id);
    }

    @Override
    public CommandDTO buildCommandDTOFromScript(String[] fileStrings, StringIterator stringIterator, String commandArgument) throws InvalidCommandArgumentsInScriptFileException {
        return new RemoveByIdCommandDTO(Integer.parseInt(commandArgument.trim()));
    }
}
