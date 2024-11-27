
public class Ranking {
	static LinkedList<Frequency> rankedRetrival(Node<Frequency> list) {
		Node<Frequency> head = mergeSort(list);
		LinkedList<Frequency> linkedList = new LinkedList<>();
		int i =1;
		while (head!=null) {
			System.out.println("Rank = "+i+" | "+head.data);
			linkedList.addNode(head);
			head=head.next;
			i++;
		}
		return linkedList;
	}
	
	// Main function to perform Merge Sort and return a new sorted list
    public static Node<Frequency> mergeSort(Node<Frequency> head) {
        if (head == null || head.next == null) {
            return head; // If list is empty or has only one node
        }

        // Split the list into two halves
        Node<Frequency> middle = getMiddle(head);
        Node<Frequency> rightHead = middle.next;
        middle.next = null; // Break the list into two halves

        // Recursively sort both halves
        Node<Frequency> leftSorted = mergeSort(head);
        Node<Frequency> rightSorted = mergeSort(rightHead);

        // Merge the two sorted halves and return the new sorted list
        return merge(leftSorted, rightSorted);
    }

    // Function to find the middle of the list using the slow and fast pointer technique
    private static Node<Frequency> getMiddle(Node<Frequency> head) {
        if (head == null) return head;

        Node<Frequency> slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    // Function to merge two sorted linked lists into one sorted linked list
    private static Node<Frequency> merge(Node<Frequency> left, Node<Frequency> right) {
    	Node<Frequency> tmp = new Node<Frequency>(new Frequency(0)); // Temporary node to hold the result
    	Node<Frequency> current = tmp;
        // Merge the two lists
        while (left != null && right != null) {
            if (left.data.getFrequency() >= right.data.getFrequency()) {
                current.next = new Node<Frequency>(new Frequency(left.data.getDocId())); // Create new node for left
                current.next.data.setFrequency(left.data.getFrequency());
                left = left.next;
            } else {
                current.next = new Node<Frequency>(new Frequency(right.data.getDocId())); // Create new node for 
                current.next.data.setFrequency(right.data.getFrequency());
                right = right.next;
            }
            current = current.next;
        }
        // Append remaining nodes from left or right
        while (left != null) {
            current.next = new Node<Frequency>(new Frequency(left.data.getDocId())); // Create new node for left
            current.next.data.setFrequency(left.data.getFrequency());
            left = left.next;
            current = current.next;
        }
        while (right != null) {
            current.next = new Node<Frequency>(new Frequency(right.data.getDocId())); // Create new node for right
            current.next.data.setFrequency(right.data.getFrequency());
            right = right.next;
            current = current.next;
        }
        return tmp.next; // Return the new sorted list
    }
}