package server;

import server.business.LabWork;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class CollectionManager {

    private Set<LabWork> labWorks;
    private ZonedDateTime collectionInitializationDateTime;

    public CollectionManager(Set<LabWork> set)
    {
        this.labWorks = set;
        this.collectionInitializationDateTime = ZonedDateTime.now();
    }

    public boolean addLabWorkToTheCollection(LabWork labWork)
    {
        return this.labWorks.add(labWork);
    }

    private int maxIdOfTheCollection()
    {
       return this.labWorks.stream().max(new Comparator<LabWork>() {
            @Override
            public int compare(LabWork o1, LabWork o2) {
                return o1.getId() - o2.getId();
            }
        }).get().getId();
    }

    public int generateId()
    {
        if (this.labWorks.isEmpty())
            return 1;
        return this.maxIdOfTheCollection() + 1;
    }

    public Set<LabWork> getCollectionCopy()
    {
        return new HashSet<>(this.labWorks);
    }

    public void clear()
    {
        this.labWorks.clear();
    }

    public boolean removeById(int id)
    {
        return this.labWorks.removeIf((labWork -> (labWork.getId() == id)));
    }
    public String getTypeOfTheCollection()
    {
        return labWorks.getClass().getSimpleName();
    }

    public ZonedDateTime getCollectionInitializationDateTime() {
        return collectionInitializationDateTime;
    }

    public int getSizeOfTheCollection()
    {
        return this.labWorks.size();
    }
}
