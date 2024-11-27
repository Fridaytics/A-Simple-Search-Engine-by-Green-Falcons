
public class QueryProcessing {
    // Method for AND query (intersection of sentence IDs), takes Frequency LinkedList for a certain node and gives a Frequency LinkedList with the doc Ids
    public static LinkedList<Frequency> andQuery(LinkedList<Frequency> list1, LinkedList<Frequency> list2) {
        LinkedList<Frequency> result = new LinkedList<>();
            // Using two-pointer technique to find the intersection of lists
        	Node<Frequency> node1=list1.getHead();
        	Node<Frequency> node2=list2.getHead();
            while (node1 != null && node2 != null) {
                int id1 = node1.data.getDocId();
                int id2 = node2.data.getDocId();
                if (id1 == id2) {
                    result.insert(new Frequency(id1));// If IDs match, add to result
                    result.retrieve().setFrequency(node1.data.getFrequency()+node2.data.getFrequency());
                    result.findNext();
                    node1=node1.next;  // Move both pointers forward
                    node2=node2.next;
                } else if (id1 < id2) {
                    node1=node1.next;  // Move pointer in list1 forward
                } else {
                	node2=node2.next;  // Move pointer in list2 forward
                }
            }
        return result;
    }
    // Method for OR query (union of sentence IDs)
    public static LinkedList<Frequency> orQuery(LinkedList<Frequency> list1, LinkedList<Frequency> list2) {
        LinkedList<Frequency> result = new LinkedList<>();
            Node<Frequency> current1 = list1.getHead();
            Node<Frequency> current2 = list2.getHead();
            // Using two-pointer technique to merge lists without duplicates
            while (current1 != null || current2 != null) {
                if (current1 == null) {
                    // Only list2 has more items left
                    result.add(new Frequency(current2.data.getDocId()));
                    result.getTail().data.setFrequency(current2.data.getFrequency());
                    current2 = current2.next;
                } else if (current2 == null) {
                    // Only list1 has more items left
                    result.add(new Frequency(current1.data.getDocId()));
                    result.getTail().data.setFrequency(current1.data.getFrequency());
                    current1 = current1.next;
                } else if (current1.data.getDocId() == current2.data.getDocId()) {
                    // Both lists have the same ID, add only once
                    result.add(new Frequency(current1.data.getDocId()));
                    result.getTail().data.setFrequency(current1.data.getFrequency()+current2.data.getFrequency());
                    current1 = current1.next;
                    current2 = current2.next;
                } else if (current1.data.getDocId() < current2.data.getDocId()) {
                    // Add id1 and move pointer in list1
                    result.add(new Frequency(current1.data.getDocId()));
                    result.getTail().data.setFrequency(current1.data.getFrequency());
                    current1 = current1.next;
                } else {
                    // Add id2 and move pointer in list2
                    result.add(new Frequency(current2.data.getDocId()));
                    result.getTail().data.setFrequency(current2.data.getFrequency());
                    current2 = current2.next;
                }
            }
            return result;
    }
    // Method for NOT query all elemements in list1 that are not in list 2 , takes Frequency LinkedList for a certain node and gives a Frequency LinkedList with the doc Ids
    public static LinkedList<Frequency> notQuery(LinkedList<Frequency> list1, LinkedList<Frequency> list2) {
        LinkedList<Frequency> result = new LinkedList<>();
        Node<Frequency> node1 = list1.getHead();
        Node<Frequency> node2 = list2.getHead();

        // Traverse both lists using two-pointer technique
        while (node1 != null) {
            int id1 = node1.data.getDocId();
            
            if (node2 == null || id1 < node2.data.getDocId()) {
                // If list2 is exhausted or id1 is not in list2, add it to result
                result.add(new Frequency(id1));
                node1 = node1.next; // Move pointer in list1
            } else if (id1 == node2.data.getDocId()) {
                // If IDs match, skip it as it's in list2
                node1 = node1.next; // Move pointer in list1
                node2 = node2.next; // Move pointer in list2
            } else {
                // If id1 > id2, move pointer in list2 forward
                node2 = node2.next;
            }
        }

        return result;
    }

}
