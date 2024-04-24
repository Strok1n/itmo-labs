package server.collection;

import java.util.ArrayList;
import java.util.Iterator;

public class MyIterator<K> implements Iterator<K> {
    private int i;
    private ArrayList<K> list;
    int currentI;
    HashNode<K> current;

    private ArrayList<HashNode<K>> bucketArray;

//    public MyIterator(ArrayList<K> list)
//    {
//        this.list = list;
//    }
    public MyIterator(ArrayList<HashNode<K>> bucketArray)
    {
        i = 0;
        currentI = 0;
        this.bucketArray = bucketArray;
    }

    @Override
    public boolean hasNext() {

        if (current == null)
            return false;
        if (current.next != null)
            return true;
        int j = currentI + 1;
        while (bucketArray.get(j) == null)
        {
            j++;
            if (j >= bucketArray.size())
                return false;

        }
        return true;

    }

    public void nextRef()
    {
        for (int j = 0; j < i; j++) {
            current = current.next;
        }
        i = 0;
    }

    @Override
    public K next() {
        System.out.println("#");
        nextRef();
        if (current.next != null)
        {
            i++;
            return current.next.key;
        }else
        {
            currentI++;
            while (bucketArray.get(currentI) == null)
            {
                currentI++;
                if (currentI>= bucketArray.size())
                    return null;
            }
            return bucketArray.get(currentI).key;
        }

    }
}
