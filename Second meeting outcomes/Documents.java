 						//when a you input a word retrieve the-
						//ids of documents that includes this word-
						//and represent the output in a linkedlistthis is inverted index
						//every document has id and words without stop words
public class Documents{
LinkedList<String> words = new LinkedList<>();
int id;

public Documents(LinkedList<String> words, int id) {
	this.words = words;
	this.id = id;
}

}