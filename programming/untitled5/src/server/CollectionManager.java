package server;

import server.business.LabWork;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class CollectionManager {

    private String collectionFileName;

    private LocalDate collectionInitializationDateTime;
    private Set<LabWork> labWorks;




    public CollectionManager(Set<LabWork> set,
                             LocalDate collectionInitializationDateTime,
                             String collectionFileName)
    {
        this.labWorks = set;
        this.collectionInitializationDateTime = collectionInitializationDateTime;
        this.collectionFileName = collectionFileName;
    }

    public boolean addLabWorkToTheCollection(LabWork labWork)
    {
        return this.labWorks.add(labWork);
    }

    private int maxIdOfTheCollection()
    {
        int maxid= 0;
        for (LabWork l : labWorks) {
            if (l.getId() > maxid)
                maxid = l.getId();
        }
        return maxid;
//       return this.labWorks.stream().max(new Comparator<LabWork>() {
//            @Override
//            public int compare(LabWork o1, LabWork o2) {
//                return o1.getId() - o2.getId();
//            }
//        }).get().getId();
    }

    public int generateId()
    {
        if (this.labWorks.isEmpty())
            return 1;
        return this.maxIdOfTheCollection() + 1;
    }

    public Set<LabWork> getCollection() {
        return labWorks;
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

    public LocalDate getCollectionInitializationDateTime() {
        return collectionInitializationDateTime;
    }

    public int getSizeOfTheCollection()
    {
        return this.labWorks.size();
    }

    public String getCollectionFileName() {
        return collectionFileName;
    }
    public String getCollectionFileName2() {
        return "C:\\Users\\1\\Desktop\\scripts\\collection.xml";
    }

    public void setCollectionFileName(String collectionFileName) {
        this.collectionFileName = collectionFileName;
    }
}
