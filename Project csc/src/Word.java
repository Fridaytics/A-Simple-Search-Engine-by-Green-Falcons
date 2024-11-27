 					//this is when doing inverted index
					//do not add duplicate words therefore
					//you need to search if exists do not add, else add
public class Word {
	private String text;
	private LinkedList<Integer>ids;
	
	public Word(String text, LinkedList<Integer> ids) {
		this.text = text;
		this.ids = new LinkedList<Integer>();
	}
	public boolean searchIfExists(Integer id) {
		if(ids.empty()) return false; 		//check if empty
		ids.findFirst(); 					//start from the beginging 
		while(!ids.last()) { 				//loop through the whole list
			if(ids.retrieve().equals(id)) {//check every id in the list and compare with our input
				return true; 				//if found output true
			}
			ids.findNext();					 //move to the next item in the list
		}
		if(ids.retrieve().equals(id)) { //check for the last item since we did !ids.last earlier
			return true; //because we cannot reach the last item due to the condition in while
		}
		return false;
	}
	public void addId(int id) { //add the id in the list if there is no copy of it in the list
		if(!searchIfExists(id)) //so search if it's not in the list add it 
			ids.insert(id);    //otherwise do nothing or do not add

	}
	public String getText() {
		return text;
	}
	public LinkedList<Integer> getIds(){
		return ids;
	}
	public String toString() {
		return "Word: " + text + ", Document IDs: " +ids;
	
	}
	
}
