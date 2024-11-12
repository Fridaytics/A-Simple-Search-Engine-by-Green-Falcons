import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.DoubleStream.DoubleMapMultiConsumer;

import javax.sound.sampled.Line;
public class Document_Porcessing implements DocumentProcessing{ //read the documents data from our dataset in the csv file
        
        public static LinkedList<String> Load(String fileName) { //read the document line by line
            LinkedList<String> list_of_words = new LinkedList<String>();
            String dataset = null;
          	DocumentProcessing.setupFilter(new File ("stop.txt"));
        try { //we use try-catch in reading files: file not found, reaching the end of file, I/O issues to read
            File f = new File(fileName); //file object creation
            BufferedReader br = new BufferedReader(new FileReader(f)); //create a buffered reader to read the file
            String line = br.readLine(); //read the first line of our file
            while (line != null) { //loop until an empty line
                line = line.toLowerCase(); //remove all non-alphanumeric characters
                String word = "";
                for (int i = 0; i < line.length(); i++) {
                    char a = line.charAt(i); //get all of the characters at each line
                    if (Character.isWhitespace(a)) { // check if it is a whitespace 
                        if (!filter.search(word)) { //add it to the linkedlist if it is not empty
                            list_of_words.insert(word);
                            word = ""; //change it to null so we can start the process again
                        }
                    } else {
                        word = word + a; //append the char 
                    }
                }              
                if (!filter.search(word)) { //add the last word if there is one left after the loop
                    list_of_words.insert(word);
                }
                dataset = line; //change the dataset every time so we dont lose the current line
                System.out.println(dataset); //print all dataset
                line = br.readLine(); //move to the next line
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list_of_words;
    }
    public static void main(String[] args) {
 
    	 LinkedList<String> list_of_words =  Load("dataset.csv"); //load the whole data set in the list of words 
        System.out.println("These are all the words in the document: ");
        list_of_words.display();     
 
    }
}
