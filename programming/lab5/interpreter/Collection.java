package interpreter;

import base.*;
import java.util.*;

public class Collection
{
	private java.util.Collection<LabWork> baseCollection;
	private java.time.ZonedDateTime collectionInitializationDate;
	
	public java.util.Collection<LabWork> getBaseCollection()
	{
		return this.baseCollection;
	}

	public void setBaseCollection(java.util.Collection<LabWork> baseCollection)
	{
		this.baseCollection = baseCollection;
	}
	
	public java.time.ZonedDateTime getCollectionInitializationDate()
	{
		return this.collectionInitializationDate;
	}
	
	public void setCollectionInitializationDate(java.time.ZonedDateTime date)
	{
		this.collectionInitializationDate = date;
	}
	
}