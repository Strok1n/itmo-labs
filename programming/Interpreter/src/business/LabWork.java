package business;

import util.OtherUtilities;
import util.ToXmlAble;

import java.util.function.Supplier;

public class LabWork implements Comparable<LabWork>, ToXmlAble, Supplier<LabWork>
{

    private Integer id; // != null, > 0, unique, auto generation
    private String name; // != null, != ""
    private Coordinates coordinates; // != null
    private java.time.ZonedDateTime creationDate; // != null, auto generation
    private Integer minimalPoint; // != null, > 0
    private long tunedInWorks;
    private Difficulty difficulty; // != null
    private Discipline discipline; // != null

    public LabWork()
    {
        this.coordinates = new Coordinates();
        this.discipline = new Discipline();
    }

    public LabWork( int id, String name, Coordinates coordinates, int minimalPoint,
                    long tunedInWorks, Difficulty difficulty, Discipline discipline)
    {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = java.time.ZonedDateTime.now();
        this.minimalPoint = minimalPoint;
        this.tunedInWorks = tunedInWorks;
        this.difficulty = difficulty;
        this.discipline = discipline;
    }

    public final int getId()
    {
        return this.id;
    }

    public final void setCreationDate(java.time.ZonedDateTime creationDate)
    {
        this.creationDate = creationDate;
    }

    public final void setId(int id)
    {
        this.id = id;
    }


    @Override
    public final int compareTo(LabWork other)
    {
        return (this.id - other.id);
    }

    @Override
    public String toString()
    {
        try {
            return OtherUtilities.objectToJson(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
//        String s = "{\n";
//            for (Field field : this.getClass().getDeclaredFields()) {
//                try {
//                    s += "\t\"" + field.getName() + "\": " + field.get(this) + ",\n";
//                } catch (IllegalAccessException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//        return s + "}";
        return null;
    }

    @Override
    public String toXml() {
        return null;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public LabWork get() {
        return this;
    }

    public long getTunedInWorks() {
        return tunedInWorks;
    }
}
