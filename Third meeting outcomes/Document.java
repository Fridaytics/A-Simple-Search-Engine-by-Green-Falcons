 						//when a you input a word retrieve the-
						//ids of documents that includes this word-
						//and represent the output in a linkedlistthis is inverted index
						//every document has id and words without stop words
public class Document{
LinkedList<String> words = new LinkedList<>();
int id;

public Document(LinkedList<String> words, int id) {
	this.words = words;
	this.id = id;
}
public int getId() {
	return id;
}
public LinkedList<String> getWords(){
	return words;
}
public boolean isWordAvailable(String w) { //check if the word's already taken from previous document or even the same 
	words.findFirst(); //start from the beginning of the list
	while(!words.last()) {//loop through the whole list except the last one
		if(words.retrieve().equals(w)) { //if we already selected the word in a previous document or even the same one
			return true; //the purpose is to avoid id duplication when doing inverted index
		}
		words.findNext(); //move to the next word
	}
	return words.retrieve().equals(w); //check for the last word in the list 
}

public String toString() {
	return "Document's ID: " + id + ", Doucment's Words: " + words;
}

}