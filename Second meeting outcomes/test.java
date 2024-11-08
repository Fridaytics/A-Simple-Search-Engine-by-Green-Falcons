import java.io.File;

public class test implements DocumentProcessing {

	public static void main(String[] args) {
		File stopwords = new File("stop.txt");
		DocumentProcessing.setupFilter(stopwords);
		DocumentProcessing.filter.display();
		
	}

}
