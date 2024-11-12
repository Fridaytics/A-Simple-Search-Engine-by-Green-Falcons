
//linkedlist of linkedlists 
//all of the documents 
public class Index {
	LinkedList<Document> whole_Docs; //list of all documemnts
	LinkedList<Word> invertedIndex; //list for inverted index
	
public Index() {
whole_Docs = new LinkedList<Document>();//Initializing 
invertedIndex = new LinkedList<Word>(); //Initializing 
}

public void index(Document d) {
	
}

public void add_Document(Document d) 	{
	whole_Docs.insert(d);
	
}
public void display_Docs() { 
	if(whole_Docs==null) {
		System.out.println("No documents");
		return;
	}
	else if(whole_Docs.empty()) {
		System.out.println("The documents are empty");
	}
	whole_Docs.findFirst(); //point at the first document
	while(!whole_Docs.last()) { //since we are not at the last document do the following
	Document document = whole_Docs.retrieve(); //retrieve every documents id and words
	System.out.println("____________________________________________"); //a line for console display
	System.out.println("Id:" +document.id); //show the id of the document
	document.words.display(); //display the whole words of a document
	whole_Docs.findNext(); //move to the next document
	}
}

}