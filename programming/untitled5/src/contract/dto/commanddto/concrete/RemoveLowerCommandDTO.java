package contract.dto.commanddto.concrete;

import contract.dto.commanddto.CommandDTO;

public class RemoveLowerCommandDTO implements CommandDTO {

    private String labWorkName;
    private int labWorkCoordinatesX;
    private Integer labWorkCoordinatesY;
    private Double minimalPoint;
    private long tunedInWorks;
    private String difficulty;
    private String disciplineName;
    private int disciplineLabsCount;

    public RemoveLowerCommandDTO(String labWorkName,
                                   int labWorkCoordinatesX,
                                   Integer labWorkCoordinatesY,
                                   Double minimalPoint,
                                   long tunedInWorks,
                                   String difficulty,
                                   String disciplineName,
                                   int disciplineLabsCount){
        this.labWorkName = labWorkName;
        this.labWorkCoordinatesX = labWorkCoordinatesX;
        this.labWorkCoordinatesY = labWorkCoordinatesY;
        this.minimalPoint = minimalPoint;
        this.tunedInWorks = tunedInWorks;
        this.difficulty = difficulty;
        this.disciplineName = disciplineName;
        this.disciplineLabsCount = disciplineLabsCount;
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
        return "remove_lower";
    }
}
