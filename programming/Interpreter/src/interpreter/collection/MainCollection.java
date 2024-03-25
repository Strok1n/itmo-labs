package interpreter.collection;

import util.OtherUtilities;
import trash.ToXmlAble;

import java.time.ZonedDateTime;
import java.util.Collection;

public class MainCollection<T extends ToXmlAble>
{
    private Collection<T> container;
    private ZonedDateTime collectionInitializationDate;



    public java.util.Collection<T> getContainer()
    {
        return this.container;
    }

    public void setContainer(java.util.Collection<T> container)
    {
        this.container = container;
    }

    public java.time.ZonedDateTime getCollectionInitializationDate()
    {
        return this.collectionInitializationDate;
    }

    public void setCollectionInitializationDate(java.time.ZonedDateTime date)
    {
        this.collectionInitializationDate = date;
    }

    public String toXml() throws IllegalAccessException {
        StringBuilder s = new StringBuilder("""
                <?xml version="1.0" encoding="utf-8"?>
                <Collection>
                <InitializationDate>""");
        s.append(collectionInitializationDate);
        s.append("""
                </InitializationDate>
                <LabWorks>
                """);
        for (T t : this.container)
        {
           // s.append("\t<").append(t.getClass().getSimpleName()).append(">\n");
               s.append(OtherUtilities.objectToXml(t));
           // s.append("\t</").append(t.getClass().getSimpleName()).append(">");
        }
        s.append("""
                </LabWorks>
                </Collection>
                """);
        return s.toString();
    }
}
