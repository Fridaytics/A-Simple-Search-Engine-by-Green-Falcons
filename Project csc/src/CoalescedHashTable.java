
public class CoalescedHashTable {
    private static class HashNode {
        String value; // The value itself is treated as the key
        int next;     // Pointer to the next entry in the chain

        HashNode(String value) {
            this.value = value;
            this.next = -1;
        }
    }

    private HashNode[] table;
    private int capacity;
    private int size;
    private int freeIndex;

    public CoalescedHashTable(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.table = new HashNode[capacity];
        this.freeIndex = 0;
    }

    // Hashes the string and takes mod capacity
    private int hashFunction(String value) {
        return Math.abs(value.hashCode()) % capacity;
    }

    // Finds the next free index in the table
    private int findFreeIndex() {
        while (freeIndex < capacity && table[freeIndex] != null) {
            freeIndex++;
        }
        if (freeIndex < capacity) {
            return freeIndex;
        } else {
            return -1;
        }
    }

    // Insert a string into the hash table
    public void insert(String value) {
        int index = hashFunction(value);

        // If the slot is empty, insert directly
        if (table[index] == null) {
            table[index] = new HashNode(value);
            size++;
            return;
        }

        // Handle collision using coalesced chaining
        int freeSlot = findFreeIndex();
        if (freeSlot == -1) {
            throw new RuntimeException("HashTable is full");
        }

        // Insert at the free slot and link it
        table[freeSlot] = new HashNode(value);
        int prev = index;
        while (table[prev].next != -1) {
            prev = table[prev].next;
        }
        table[prev].next = freeSlot;
        size++;
    }

    // Search for a value in the hash table
    public boolean contains(String value) {
        int index = hashFunction(value);

        // Traverse the chain to find the value
        while (index != -1 && table[index] != null) {
            if (table[index].value.equals(value)) {
                return true;
            }
            index = table[index].next;
        }
        return false;
    }

    // Display for testing purposes
    public void display() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                System.out.print("Index " + i + ": \"" + table[i].value + "\" -> ");
                int next = table[i].next;
                while (next != -1) {
                    System.out.print("\"" + table[next].value + "\" -> ");
                    next = table[next].next;
                }
                System.out.println("null");
            } else {
                System.out.println("Index " + i + ": null");
            }
        }
    }
}