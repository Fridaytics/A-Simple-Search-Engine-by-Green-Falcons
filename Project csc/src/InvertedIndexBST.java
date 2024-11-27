
class InvertedIndexBST {
    private InvertedIndexNode root;
    // Insert a word with the sentence ID into the BST
    public void insert(String word, int sentenceId) {
        root = insertRec(root, word, sentenceId);
    }
    // Recursively look for a place to insert
    private InvertedIndexNode insertRec(InvertedIndexNode root, String word, int sentenceId) {
        if (root == null) {
            return new InvertedIndexNode(word, sentenceId);
        }// compareTo function returns if equal | returns >0 if greater | returns <0 if smaller LEXIOGRAPHICALLY (order in dictionary)
        if (word.compareTo(root.word) < 0) {
            root.left = insertRec(root.left, word, sentenceId);
        } else if (word.compareTo(root.word) > 0) {
            root.right = insertRec(root.right, word, sentenceId);
        } else {
            root.addId(sentenceId); // If word already exists, add the sentence ID. if sentence ID already exists, increment frequency.
        }
        return root;
    }

    // Search for a word in the BST and return the sentence IDs
    public InvertedIndexNode search(String word) {
    	InvertedIndexNode result = searchRec(root, word);
        return (result != null) ? result : null;
    }
    // Recursively search for word
    private InvertedIndexNode searchRec(InvertedIndexNode root, String word) {
        if (root == null || root.word.equals(word)) {
            return root;
        }
        if (word.compareTo(root.word) < 0) {
            return searchRec(root.left, word);
        }
        return searchRec(root.right, word);
    }
    
   //FOR TESTING PURPOSES
   public void display() {
        display(this.root); // Start from the root
   }

  private void display(InvertedIndexNode root) {
       if (root == null) {
           return; // Base case: do nothing for null nodes
       }
       display(root.left); // Recursively display the left subtree
        System.out.println(root.word + " -> "); // Print the word and its sentence IDs
       root.sentenceIds.findFirst();
       while(root.sentenceIds.current!=null) {
       	System.out.println(root.sentenceIds.retrieve().getDocId());
       	root.sentenceIds.findNext();
       }
       display(root.right); // Recursively display the right subtree
   }
}
