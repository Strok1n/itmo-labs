package server.collection;

import java.util.*;
import java.util.function.Predicate;

public class MyHashSet<K> implements Set<K> {



    private ArrayList<HashNode<K>> bucketArray;



    private int numBuckets;

    // Current size of array list
    private int size;

    public MyHashSet()
    {
        bucketArray = new ArrayList<>();
        numBuckets = 10;
        size = 0;


        for (int i = 0; i < numBuckets; i++)
            bucketArray.add(null);
    }

    public int size() { return size; }
    public boolean isEmpty() { return size() == 0; }
    private final int hashCode (K key) {
        return Objects.hashCode(key);
    }


    private int getBucketIndex(K key)
    {
        int hashCode = hashCode(key);
        int index = hashCode % numBuckets;
        // key.hashCode() could be negative.
        index = index < 0 ? index * -1 : index;
        return index;
    }


    public boolean remove(Object key1)
    {
        K key = (K) key1;

        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);

        HashNode<K> head = bucketArray.get(bucketIndex);

        HashNode<K> prev = null;
        while (head != null) {
            if (head.key.equals(key) && hashCode == head.hashCode)
                break;

            prev = head;
            head = head.next;
        }


        if (head == null)
            return false;

        size--;

        if (prev != null)
            prev.next = head.next;
        else
            bucketArray.set(bucketIndex, head.next);
        return true;
    }


    public boolean add(K key)
    {

        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        HashNode<K> head = bucketArray.get(bucketIndex);


        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode) {
                return false;
            }
            head = head.next;
        }
        size++;
        head = bucketArray.get(bucketIndex);
        HashNode<K> newNode
                = new HashNode<K>(key, hashCode);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);

        if ((1.0 * size) / numBuckets >= 0.7) {
            ArrayList<HashNode<K> > temp = bucketArray;
            bucketArray = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            size = 0;
            for (int i = 0; i < numBuckets; i++)
                bucketArray.add(null);

            for (HashNode<K> headNode : temp) {
                while (headNode != null) {
                    add(headNode.key);
                    headNode = headNode.next;
                }
            }
        }
        return true;
    }



    @Override
    public boolean removeIf(Predicate<? super K> filter) {
        // return Set.super.removeIf(filter);
        for (HashNode<K> node : bucketArray) {
            while (node != null)
            {
                if (filter.test(node.key))
                    this.remove(node);
                node = node.next;
            }
        }
        return true;
    }



    @Override
    public Iterator<K> iterator() {
        Iterator<K> iterator = new MyIterator<>(bucketArray);
//        ArrayList<K> arrayList = new ArrayList<>();
//        for (HashNode<K> node : bucketArray) {
//            while (node != null)
//            {
//                arrayList.add(node.key);
//                node = node.next;
//            }
//        }
        return iterator;
    }










    @Override
    public boolean contains(Object o) {
        return false;
    }




    @Override
    public Object[] toArray() {
        return new Object[0];
    }
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends K> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }
    @Override
    public void clear() {

    }




}
