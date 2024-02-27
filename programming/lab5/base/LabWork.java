package base;

import java.lang.reflect.*;

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
	
	public LabWork() {
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
	
	public int getId()
	{
		return this.id;
	}
	
	public void setCreationDate(java.time.ZonedDateTime creationDate)
	{
		this.creationDate = creationDate;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	

	@Override
	public int compareTo(LabWork other)
	{
		return (other.id - this.id);
	}
	
	@Override
	public String toString()
	{
		String s = "{\n";
		try
		{
			for (Field field : this.getClass().getDeclaredFields())
				s += "\t\"" + field.getName() + "\": " + field.get(this) + ",\n";
		}
		catch(Exception exception)
		{
		}
		return s + "}";
		
		//return "{\n" +  
		//"\t\"id\": " + this.name + "\n" + 
		////"\t\"name\": " + this.name + "\n" + 
		//"\t\"coordinates\": " + this.coordinates + "\n" + 
		//"}";
	}
}