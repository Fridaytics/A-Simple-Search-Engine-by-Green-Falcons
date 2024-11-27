import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
interface DocumentProcessing {
	public static CoalescedHashTable filter = new CoalescedHashTable(571);
	public static void setupFilter(File f) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line= br.readLine();
		    while (line!=null){
		    	filter.insert(line);
		    	line= br.readLine();
		    }
		    br.close();
		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		}
	}
	public static boolean filterStopword(String word) {
		return filter.contains(word);
	}
}
