import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
	public class Read_documents { 
		
	
	public static void Load(String fileName) {
		String dataset = null; 
		
	
	try {
		File f = new File(fileName);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line= br.readLine();
        while (line!=null){      			//loop until an empty line
        	line.replaceAll("[^a-z0-9]", "");
            dataset= line;       			//change the dataset every time
            System.out.println(dataset);	//print all dataset
            line= br.readLine(); 			//move to the next line
        }
	
		String s= dataset.substring(0, dataset.indexOf(",")); 
		int id = Integer.parseInt(s.trim());
		String content = dataset.substring(dataset.indexOf(",") +  1).trim(); }
	
	catch(Exception e) {
		System.out.println("End of file");
	}
	}
public static void main(String[]args) {
	Load("dataset.csv");

}

}