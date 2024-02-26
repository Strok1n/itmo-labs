package base;

import java.lang.reflect.*;

public class Discipline
{
    private String name; // != null, != ""
    private Integer labsCount; //!= null
	
	public Discipline(){}
	
	public Discipline(String name, Integer labsCount)
	{
		this.name = name;
		this.labsCount = labsCount;
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
	}
}