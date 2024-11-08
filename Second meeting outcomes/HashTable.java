
class HashTable {

    private Node[] table;
    private int size;

    // Constructor to initialize the hash table
    public HashTable(int size) {
        this.size = size;
        table = new Node[size];
    }

    // Hash function to compute the index of a key
    private int hash(String key) {
        return Math.abs(key.hashCode()) % size;
    }
    public static int hashing(String str) {
		String collector = "";
		int add = 0;
		for(int i = 0; i<str.length()&&i<2 ;i++) {
			collector += (int)(str.charAt(i));
			add += (int)(str.charAt(str.length()-i-1));
		}
		collector +=add;
		return (int) (Integer.parseInt(collector));	
	}
    // Insert key-value pair into the hash table
    public void insert(String key) {
        int index = hash(key);
        Node newNode = new Node(key);

        // If there's no collision, insert the new node directly
        if (table[index] == null) {
            table[index] = newNode;
        } else {
            // Collision occurred, add the new node at the end of the linked list
            Node current = table[index];
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Search for a key and return its value
    public boolean search(String key) {
        int index = hash(key);
        Node current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false; // Return false if the key is not found
    }
    // Display for testing purposes
    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print("Index " + i + ": ");
            Node current = table[i];
            while (current != null) {
                System.out.print("(" + current.key+ ") -> ");
                current = current.next;
            }
            System.out.println("None");
        }
    }
 }
