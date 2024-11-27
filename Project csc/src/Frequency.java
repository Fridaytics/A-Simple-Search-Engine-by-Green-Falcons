// Class to store documentId and frequency
public class Frequency {
	private int documentId;
	private int frequency;
	public Frequency(int documentId){
		this.documentId=documentId;
		frequency = 1;
	}
	// Increments the frequency by +1
	public void increment() {
		frequency++;
	}
	// Getters
	public int getDocId() {
		return documentId;
	}
	public int getFrequency() {
		return frequency;
	}
	// Setter
	public void setFrequency(int frequency) {
		this.frequency=frequency;
	}
	public String toString() {
		return "Document ID:"+documentId+" Frequency:"+frequency;
	}
}
