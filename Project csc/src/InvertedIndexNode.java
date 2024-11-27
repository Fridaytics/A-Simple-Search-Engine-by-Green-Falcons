
public class InvertedIndexNode {
    LinkedList<Frequency> sentenceIds; // Set to store unique sentence IDs
    String word;
    InvertedIndexNode left,right;
    public InvertedIndexNode(String word, int sentenceId) {
        this.word = word;
        this.sentenceIds = new LinkedList<>();
        this.sentenceIds.insert(new Frequency(sentenceId));
        this.left = this.right = null;
    }
    // Method to addId to Frequency LinkedList 
    void addId(int sentenceId) {
    	// Checking if Id already exists
    	if (sentenceIds.getTail().data.getDocId()==sentenceId){
    		// If Id already exists increment frequency of word in said document by 1
    		sentenceIds.getTail().data.increment();
    	}
    	else {
    		// Else add Id to linkedList
    		sentenceIds.add(new Frequency(sentenceId));
    	}
    }
    public String toString() {
    	String collector = "Word: "+word+"\n";
    	Node<Frequency> node = sentenceIds.getHead();
    	while(node!=null) {
    		collector+= node.data.toString()+"\n";
    		node=node.next;
    	}
    	return collector;
    }
}
