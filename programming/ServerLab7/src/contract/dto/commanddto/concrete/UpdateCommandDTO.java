package contract.dto.commanddto.concrete;

import contract.dto.commanddto.CommandDTO;

public class UpdateCommandDTO implements CommandDTO {

    private final int id;
    private final String labWorkName;
    private final int labWorkCoordinatesX;
    private final Integer labWorkCoordinatesY;
    private final Double minimalPoint;
    private final long tunedInWorks;
    private final String difficulty;
    private final String disciplineName;
    private final int disciplineLabsCount;

    public UpdateCommandDTO(int id,
                            String labWorkName,
                         int labWorkCoordinatesX,
                         Integer labWorkCoordinatesY,
                         Double minimalPoint,
                         long tunedInWorks,
                         String difficulty,
                         String disciplineName,
                         int disciplineLabsCount){
        this.id = id;
        this.labWorkName = labWorkName;
        this.labWorkCoordinatesX = labWorkCoordinatesX;
        this.labWorkCoordinatesY = labWorkCoordinatesY;
        this.minimalPoint = minimalPoint;
        this.tunedInWorks = tunedInWorks;
        this.difficulty = difficulty;
        this.disciplineName = disciplineName;
        this.disciplineLabsCount = disciplineLabsCount;
    }

    public int getId() {
        return id;
    }

    public String getLabWorkName() {
        return labWorkName;
    }

    public int getLabWorkCoordinatesX() {
        return labWorkCoordinatesX;
    }

    public Integer getLabWorkCoordinatesY() {
        return labWorkCoordinatesY;
    }

    public Double getMinimalPoint() {
        return minimalPoint;
    }

    public long getTunedInWorks() {
        return tunedInWorks;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public int getDisciplineLabsCount() {
        return disciplineLabsCount;
    }

    @Override
    public String getCommandName() {
        return "update";
    }

}
