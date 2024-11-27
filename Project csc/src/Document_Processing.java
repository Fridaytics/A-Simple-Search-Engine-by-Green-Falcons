import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Document_Processing implements DocumentProcessing {

    private CoalescedHashTable filter;
    private Index index; // Reference to the Index class for document and inverted index handling
    private int documentIdCounter = 0; // Counter to assign unique IDs to each document

    public Document_Processing(CoalescedHashTable filter, Index index) {
        this.filter = filter;
        this.index = index;
    }

    public int getDocumentCount() {
        return documentIdCounter + 1;
    }

    // Load documents, process each line, and populate Index
    public LinkedList<String> Load(File file) {
        LinkedList<String> list_of_words = new LinkedList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                // Skip lines that contain tokens/vocab/topics
                if (line.contains("tokens") || line.contains("vocab") || line.contains("topics")) {
                    continue; // Skip to the next line if it's metadata
                }
                // Skip content before the first comma
                int firstComma = line.indexOf(",");
                if (firstComma == -1 || firstComma == line.length() - 1) continue; // Skip line if no comma found or no content after the comma

                String content = line.substring(firstComma + 1).trim(); // Content after the first comma
                if (content.isEmpty()) {
                    continue; // Skip empty lines or lines without valid content
                }
                String doc = content; // Original document content
                content = content.toLowerCase(); // Convert to lowercase
                String[] wordsArray = content.split("\\s+"); // Split by whitespace

                LinkedList<String> wordsList = new LinkedList<>(); // List to store valid words for the document

                for (String word : wordsArray) {
                    word = word.replaceAll("[^a-zA-Z0-9]", ""); // Remove non-alphanumeric characters

                    if (!word.isEmpty() && !filter.contains(word)) { // Filter out stop words
                        wordsList.insert(word); // Add to document's word list
                        index.invertedIndexBST.insert(word, documentIdCounter);
                        // Check if word exists in the inverted index
                        boolean wordExists = false;
                        if (!index.invertedIndex.empty()) { // Only check if list is not empty
                            index.invertedIndex.findFirst();
                            while (!index.invertedIndex.last()) {
                                Word existingWord = index.invertedIndex.retrieve();
                                if (existingWord.getText().equals(word)) {
                                    existingWord.addId(documentIdCounter); // Add document ID if word exists
                                    wordExists = true;
                                    break;
                                }
                                index.invertedIndex.findNext();
                            }
                            // If we reach the last item, check it as well
                            if (!wordExists && index.invertedIndex.retrieve().getText().equals(word)) {
                                index.invertedIndex.retrieve().addId(documentIdCounter);
                                wordExists = true;
                            }
                        }
                        // If the word was not found, add a new Word instance
                        if (!wordExists) {
                            LinkedList<Integer> ids = new LinkedList<Integer>();
                            ids.insert(documentIdCounter);
                            index.invertedIndex.insert(new Word(word, ids));
                        }
                    }
                }
                // Add the document only if the wordsList is not empty
                if (!wordsList.empty()) {
                    index.add_Document(new Document(wordsList, documentIdCounter++, doc));
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list_of_words;
    }
}
