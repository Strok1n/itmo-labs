package base;

public class LabWork implements Comparable<LabWork>
{
	
	private Integer id; // != null, > 0, unique, auto generation
    private String name; // != null, != ""
    private Coordinates coordinates; // != null
    private java.time.ZonedDateTime creationDate; // != null, auto generation
    private Integer minimalPoint; // != null, > 0
    private long tunedInWorks;
    private Difficulty difficulty; // != null
    private Discipline discipline; // != null
	
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
	
	
	
	@Override
	public int compareTo(LabWork other)
	{
		return (other.id - this.id);
	}
	
	@Override
	public String toString()
	{
		return this.name + this.difficulty.toString();
	}
}