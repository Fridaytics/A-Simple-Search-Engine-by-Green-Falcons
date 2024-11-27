//linkedlist of linkedlists 
//all of the documents 
public class Index {
    LinkedList<Document> whole_Docs; //list of all documents
    LinkedList<Word> invertedIndex; //list for inverted index
    InvertedIndexBST invertedIndexBST; //bst for inverted index
    public Index() {
        whole_Docs = new LinkedList<Document>(); //Initializing 
        invertedIndex = new LinkedList<Word>(); //Initializing 
        invertedIndexBST = new InvertedIndexBST(); //Initializing
    }

    public void index(Document d) {
        add_Document(d);
    }

    public void add_Document(Document d) {
        if (whole_Docs.empty()) {
            whole_Docs.insert(d); // Insert the first document
            return;
        }

        whole_Docs.findFirst();
        while (!whole_Docs.last()) {
            whole_Docs.findNext(); // Traverse to the last document
        }
        whole_Docs.insert(d); // Insert at the end of the list
    }
    public int getTotalTokens() {
        int totalTokens = 0;
        if (whole_Docs == null || whole_Docs.empty()) {
            return totalTokens; // Return 0 if no documents
        }

        whole_Docs.findFirst();
        while (true) {
            Document doc = whole_Docs.retrieve();
            totalTokens += doc.getWords().getSize(); // Add the number of words in the document

            if (whole_Docs.last()) break; // Stop if this is the last document
            whole_Docs.findNext();
        }
        return totalTokens;
    }

    public int getUniqueWords() {
        int uniqueWords = 0;
        if (invertedIndex == null || invertedIndex.empty()) {
            return uniqueWords; // Return 0 if inverted index is empty
        }

        invertedIndex.findFirst();
        while (true) {
            uniqueWords++; // Each entry in the inverted index is a unique word

            if (invertedIndex.last()) break; // Stop if this is the last word
            invertedIndex.findNext();
        }
        return uniqueWords;
    }

    public void display_Docs() {
        if (whole_Docs == null || whole_Docs.empty()) {
            System.out.println("No documents available.");
            return;
        }

        whole_Docs.findFirst(); // Start from the first document
        while (true) {
            Document document = whole_Docs.retrieve(); // Retrieve the current document

            // Skip documents with ID -1 or with content "content"
            if (document.getId() != -1 && !document.getContent().equalsIgnoreCase("content")) {
                System.out.println("____________________________________________");
                System.out.println("Id: " + document.getId());
                System.out.println("Content: " + document.getContent());
            }

            if (whole_Docs.last()) break; // Stop if this is the last document
            whole_Docs.findNext(); // Move to the next document
        }
    }
    public void getDocumentByIndex(int id) {
        if (whole_Docs == null || whole_Docs.empty()) {
            System.out.println("No documents available.");
            return;
        }

        whole_Docs.findFirst(); // Start from the first document
        while (true) {
            Document document = whole_Docs.retrieve(); // Retrieve the current document
            if (document.getId() == id) {
                System.out.println("____________________________________________");
                System.out.println("Id: " + document.getId());
                System.out.println("Content: " + document.getContent());
                return; // Exit the method after finding the document
            }

            if (whole_Docs.last()) break; // Stop if this is the last document
            whole_Docs.findNext(); // Move to the next document
        }

        System.out.println("Document with Id " + id + " not found.");
    }
    public int getTotalDocuments() {
        int count = 0;
        if (whole_Docs == null || whole_Docs.empty()) {
            return count; // Return 0 if no documents
        }

        whole_Docs.findFirst();
        while (true) {
            count++;
            if (whole_Docs.last()) break; // Stop if this is the last document
            whole_Docs.findNext();
        }
        System.out.println(count);
        return count;
    }

}