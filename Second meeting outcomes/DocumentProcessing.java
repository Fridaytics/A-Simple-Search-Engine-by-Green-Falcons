import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

interface DocumentProcessing {
	static  HashTable filter = new HashTable(401);
	
	public static void setupFilter(File f) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			String line= br.readLine();
		    while (line!=null){
		    	filter.insert(line);
		    	line= br.readLine();
		    }
		    br.close();
		} catch (IOException ee) {
			System.out.println(ee.getMessage());
		}
	}
	
}
