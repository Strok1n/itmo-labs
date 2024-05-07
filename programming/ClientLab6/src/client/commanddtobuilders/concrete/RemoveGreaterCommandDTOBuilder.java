package client.commanddtobuilders.concrete;

import client.commanddtobuilders.CommandDTOBuilder;
import client.exceptions.InvalidCommandArgumentsInScriptFileException;
import client.io.ConsoleReader;
import client.io.ConsoleWriter;
import client.util.FieldValidators;
import client.util.LabWorkFieldValuesGetter;
import client.util.StringIterator;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.RemoveGreaterCommandDTO;

public class RemoveGreaterCommandDTOBuilder implements CommandDTOBuilder {

    private final LabWorkFieldValuesGetter labWorkFieldValuesGetter;

    public RemoveGreaterCommandDTOBuilder(ConsoleReader consoleReader, ConsoleWriter consoleWriter)
    {
        this.labWorkFieldValuesGetter = new LabWorkFieldValuesGetter(consoleReader, consoleWriter);
    }

    @Override
    public CommandDTO buildCommandDTO(String[] commandArguments) {


        String labWorkName = labWorkFieldValuesGetter.getLabWorkName();
        int labWorkCoordinatesX = labWorkFieldValuesGetter.getLabWorkCoordinatesX();
        Integer labWorkCoordinatesY = labWorkFieldValuesGetter.getLabWorkCoordinatesY();
        Double minimalPoint = labWorkFieldValuesGetter.getLabWorkMinimalPoint();
        long tunedInWorks = labWorkFieldValuesGetter.getLabWorkTunedInWorks();
        String difficulty = labWorkFieldValuesGetter.getLabWorkDifficulty();
        String disciplineName = labWorkFieldValuesGetter.getLabWorkDisciplineName();
        int disciplineLabsCount = labWorkFieldValuesGetter.getLabWorkDisciplineLabsCount();


        return new RemoveGreaterCommandDTO(labWorkName,
                labWorkCoordinatesX,
                labWorkCoordinatesY,
                minimalPoint,
                tunedInWorks,
                difficulty,
                disciplineName,
                disciplineLabsCount);
    }

    @Override
    public CommandDTO buildCommandDTOFromScript(String[] fileStrings, StringIterator stringIterator, String commandArgument) throws InvalidCommandArgumentsInScriptFileException {

        String labWorkNameString = fileStrings[stringIterator.increment()].trim();
        String labWorkCoordinatesXString = fileStrings[stringIterator.increment()].trim();
        String labWorkCoordinatesYString = fileStrings[stringIterator.increment()].trim();
        String minimalPointString = fileStrings[stringIterator.increment()].trim();
        String tunedInWorksString = fileStrings[stringIterator.increment()].trim();
        String difficultyString = fileStrings[stringIterator.increment()].trim();
        String disciplineNameString = fileStrings[stringIterator.increment()].trim();
        String disciplineLabsCountString = fileStrings[stringIterator.increment()].trim();
        if (
                !FieldValidators.validateLabWorkName(labWorkNameString) ||
                        !FieldValidators.validateLabWorkCoordinatesX(labWorkCoordinatesXString) ||
                        !FieldValidators.validateLabWorkCoordinatesY(labWorkCoordinatesYString) ||
                        !FieldValidators.validateLabWorkMinimalPoint(minimalPointString) ||
                        !FieldValidators.validateLabWorkTunedInWorks(tunedInWorksString) ||
                        !FieldValidators.validateLabWorkDifficulty(difficultyString) ||
                        !FieldValidators.validateLabWorkDisciplineName(disciplineNameString) ||
                        !FieldValidators.validateLabWorkDisciplineLabsCount(disciplineLabsCountString)
        ){
            throw new InvalidCommandArgumentsInScriptFileException();
        }

        String labWorkName = labWorkNameString;
        int labWorkCoordinatesX = Integer.parseInt(labWorkCoordinatesXString);
        Integer labWorkCoordinatesY = Integer.parseInt(labWorkCoordinatesYString);
        Double minimalPoint = Double.parseDouble(minimalPointString);
        long tunedInWorks = Long.parseLong(tunedInWorksString);
        String difficulty = difficultyString;
        String disciplineName = disciplineNameString;
        int disciplineLabsCount = Integer.parseInt(disciplineLabsCountString);

        return new RemoveGreaterCommandDTO(labWorkName,
                labWorkCoordinatesX,
                labWorkCoordinatesY,
                minimalPoint,
                tunedInWorks,
                difficulty,
                disciplineName,
                disciplineLabsCount);
    }
}

