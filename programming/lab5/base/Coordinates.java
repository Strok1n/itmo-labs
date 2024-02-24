package base;

import java.lang.reflect.*;

public class Coordinates
{
    private float x;
    private long y; // > -161
	
	public Coordinates(float x, long y)
	{
		this.x = x;
		this.y = y;
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