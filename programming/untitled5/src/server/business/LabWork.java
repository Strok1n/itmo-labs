package server.business;

public class LabWork {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double minimalPoint; //Поле не может быть null, Значение поля должно быть больше 0
    private long tunedInWorks;
    private Difficulty difficulty; //Поле может быть null
    private Discipline discipline; //Поле может быть null

    public LabWork(int id,
                   String name,
                   Coordinates coordinates,
                   java.time.LocalDateTime creationDate,
                   Double minimalPoint,
                   long tunedInWorks,
                   Difficulty difficulty,
                   Discipline discipline
                   )
    {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.tunedInWorks = tunedInWorks;
        this.difficulty = difficulty;
        this.discipline = discipline;
    }


    public int getId() {
        return id;
    }
}
