package base;

public class Discipline
{
    private String name; // != null, != ""
    private Integer labsCount; //!= null
	
	public Discipline(String name, Integer labsCount)
	{
		this.name = name;
		this.labsCount = labsCount;
	}
}