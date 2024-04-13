package client.commandpreparers;

import client.ConsoleReader;
import client.ConsoleWriter;
import client.util.LabWorkFieldValuesGetter;
import contract.command.AddCommandDTO;
import contract.command.CommandDTO;

public class AddCommandPreparer implements CommandPreparer
{
    private ConsoleReader consoleReader;
    private ConsoleWriter consoleWriter;

    private final LabWorkFieldValuesGetter labWorkFieldValuesGetter;

    public AddCommandPreparer(ConsoleReader consoleReader, ConsoleWriter consoleWriter)
    {
        this.labWorkFieldValuesGetter = new LabWorkFieldValuesGetter(consoleReader, consoleWriter);
    }

    @Override
    public CommandDTO prepareCommand(String[] arguments)
    {
        String labWorkName = labWorkFieldValuesGetter.getLabWorkName();
        int labWorkCoordinatesX = labWorkFieldValuesGetter.getLabWorkCoordinatesX();
        Integer labWorkCoordinatesY = labWorkFieldValuesGetter.getLabWorkCoordinatesY();
        Double minimalPoint = labWorkFieldValuesGetter.getLabWorkMinimalPoint();
        long tunedInWorks = labWorkFieldValuesGetter.getLabWorkTunedInWorks();
        String difficulty = labWorkFieldValuesGetter.getLabWorkDifficulty();
        String disciplineName = labWorkFieldValuesGetter.getLabWorkDisciplineName();
        int disciplineLabsCount = labWorkFieldValuesGetter.getLabWorkDisciplineLabsCount();

        return new AddCommandDTO(labWorkName,
                labWorkCoordinatesX,
                labWorkCoordinatesY,
                minimalPoint,
                tunedInWorks,
                difficulty,
                disciplineName,
                disciplineLabsCount);
    }









}
