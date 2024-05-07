package server.collection;

class HashNode<K> {
    K key;
    final int hashCode;

    // Reference to next node
    HashNode<K> next;


    public HashNode(K key, int hashCode)
    {
        this.key = key;
        this.hashCode = hashCode;
    }
}