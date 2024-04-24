package server.collection;

// A node of chains
class HashNode<K> {
    K key;
    final int hashCode;

    // Reference to next node
    HashNode<K> next;

    // Constructor
    public HashNode(K key, int hashCode)
    {
        this.key = key;
        this.hashCode = hashCode;
    }
}