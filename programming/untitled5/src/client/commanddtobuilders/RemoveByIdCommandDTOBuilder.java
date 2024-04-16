package client.commanddtobuilders;

import client.ConsoleReader;
import client.ConsoleWriter;
import client.exceptions.InvalidCommandArgumentsInScriptFileException;
import client.util.FieldValidators;
import client.util.LabWorkFieldValuesGetter;
import client.util.StringIterator;
import contract.command.CommandDTO;
import contract.command.RemoveByIdCommandDTO;

public class RemoveByIdCommandDTOBuilder implements CommandDTOBuilder{


    private ConsoleReader consoleReader;
    private ConsoleWriter consoleWriter;

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
        return null;
    }
}
