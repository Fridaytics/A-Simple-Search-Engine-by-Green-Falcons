
import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JFrame;

public class Menu {
    public static void main(String[] args) {
        // Initialize necessary components
        DocumentProcessing.setupFilter(new File("stop.txt"));
        Index index = new Index();
        Document_Processing dp = new Document_Processing(DocumentProcessing.filter, index);

        // Load documents
        JFrame parentFrame = new JFrame();
        parentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        CSVFileSelector frame = new CSVFileSelector(parentFrame);
        frame.setVisible(true);
        dp.Load(frame.getSelectedFile());
        frame.dispose();
        parentFrame.dispose();
        System.out.println("Document successfully loaded!");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
        	try {
	            System.out.println("\n========== Search Engine Menu ==========");
	            System.out.println("1. Boolean Retrieval");
	            System.out.println("2. Ranked Retrieval");
	            System.out.println("3. Indexed Documents");
	            System.out.println("4. Indexed Tokens/Vocabs");
	            System.out.println("5. Search for word");
	            System.out.println("6. EXIT");
	            System.out.print("Choose an option: ");
	
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline
	            switch (choice) {
	                case 1:{
	                    // Perform Boolean Retrieval
	                	int pointer = 0;
	                	String collector = "";
	                	String word = null;
	                	LinkedList<Frequency> list1 = null,list2 = null;
	                	InvertedIndexNode search =null;
	                	while(true){
	                		System.out.print("Enter first word for Boolean Retrieval: ");
	                		word = scanner.next();
	                		search = index.invertedIndexBST.search(word);
	                		if(search==null) {
	                			System.out.println("Could not find word, try another one");
	                		}else {
	                    		list1 = search.sentenceIds;
	                    		break;
	                		}
	                	}
	                	collector+=word;
	                    boolean continuing = true;
	                    while(continuing) {
	                    	System.out.println("What boolean operation would you like to use?\n 1.OR \n 2.AND \n 3.NOT(without) \n 4.EXIT");
	                    	pointer = scanner.nextInt();
	                    	switch(pointer) {
	                    		case 1:
	                    			collector+= " OR ";
	                    			while(true) {
	                    				System.out.print("Enter word for Boolean Retrieval: ");
	                    				word = scanner.next();
	                    				search = index.invertedIndexBST.search(word);
	                    				if(search==null) {
	                    					System.out.println("Could not find word, try another one");
	                    				}else {
	                                		list2 = index.invertedIndexBST.search(word).sentenceIds;
	                    					break;
	                    				}
	                    			}
	                    			collector= "(" + collector + word + ")";
	                    			System.out.println(collector);
	                    			list1 = QueryProcessing.orQuery(list1, list2);
	                    			list1.findFirst();
	                    			while(list1.current!=null) {
	                    				System.out.println("Document ID:"+list1.retrieve().getDocId());
	                    				list1.findNext();
	                    			}
	                    			break;
	                    		case 2:{
	                    			collector+=" AND ";
		                    			while(true) {
		                    				System.out.print("Enter word for Boolean Retrieval: ");
		                    				word = scanner.next();
		                    				search = index.invertedIndexBST.search(word);
		                    				if(search==null) {
		                    					System.out.println("Could not find word, try another one");
		                    				}else {
		                                		list2 = index.invertedIndexBST.search(word).sentenceIds;
		                    					break;
		                    				}
		                    			}
		                    			list2 = QueryProcessing.andQuery(list1, list2);
		                    			if(list2.getHead()==null) {
		                    				System.out.println("Could not find any shared elements in "+"(" + collector + word + ")");
		                    				continuing=false;
		                    				break;
		                    			}else {
		                        			collector= "(" + collector + word + ")";
		                        			System.out.println(collector);
		                    				list1 = list2;
		                    				list1.findFirst();
		                        			while(list1.current!=null) {
		                        				System.out.println("Document ID:"+list1.retrieve().getDocId());
		                        				list1.findNext();
		                        			}
	                    			}
		                    			break;
	                    		}
	                    		case 3:
	                    			collector+= " NOT(without) ";
	                    			while(true) {
	                    				System.out.print("Enter word for Boolean Retrieval: ");
	                    				word = scanner.next();
	                    				search = index.invertedIndexBST.search(word);
	                    				if(search==null) {
	                    					System.out.println("Could not find word, try another one");
	                    				}else {
	                                		list2 = index.invertedIndexBST.search(word).sentenceIds;
	                    					break;
	                    				}
	                    			}
	                    			collector= "(" + collector + word + ")";
	                    			System.out.println(collector);
	                    			list1 = QueryProcessing.notQuery(list1, list2);
	                    			list1.findFirst();
	                    			while(list1.current!=null) {
	                    				System.out.println("Document ID:"+list1.retrieve().getDocId());
	                    				list1.findNext();
	                    			}
	                    			break;     		
	                    		case 4:
	                    			continuing = false;
	                    			break;
	                    	}
	                    }
	                    break;
	                }
	                case 2:
	                    // Perform Ranked Retrieval
	                	int pointer;
	                    String word;
	                    String collector="";
	                    InvertedIndexNode search;
	                    LinkedList<Frequency>list1=null, list2=null;
	                    while(true){
	                		System.out.print("Enter first word for Ranked Retrieval: ");
	                		word = scanner.next();
	                		search = index.invertedIndexBST.search(word);
	                		if(search==null) {
	                			System.out.println("Could not find word, try another one");
	                		}else {
	                    		list1 = search.sentenceIds;
	                    		break;
	                		}
	                	}
	                    collector+=word;
	                    while(true) {
		                    collector+=" OR ";
		                    while(true) {
		        				System.out.print("Enter another word for Ranked Retrieval: ");
		        				word = scanner.next();
		        				search = index.invertedIndexBST.search(word);
		        				if(search==null) {
		        					System.out.println("Could not find word, try another one");
		        				}else {
		                    		list2 = index.invertedIndexBST.search(word).sentenceIds;
		        					break;
		        				}
		        			}
		                    collector= "(" + collector + word + ")";
	            			System.out.println(collector);
		        			list1 = QueryProcessing.orQuery(list1, list2);
		            		list1 = Ranking.rankedRetrival(list1.getHead());
		            		System.out.println("Continue? \n1.Yes\n2.No");
		            		pointer = scanner.nextInt();
		            		if(pointer==2) {
		            			break;
		            		}		
	                    }
	                    break;
	
	                case 3:
	                    // Show Indexed Documents
	                    System.out.println("Indexed Documents:" +index.getTotalDocuments());
	                    break;
	
	                case 4:
	                    // Show Indexed Tokens
	                    System.out.println("Indexed Tokens:"+index.getTotalTokens());
	                    System.out.println("Vocabulary (Unique Words): " + index.getUniqueWords());
	                    break;
	                case 5:
	                	while(true) {
	                		System.out.println("Please input word you would like to search for:");
	                		word = scanner.nextLine();
	                		InvertedIndexNode node=index.invertedIndexBST.search(word);
	                		if(node==null) {
	                			System.out.println("Could not find word, try another one");
	                		}else {
	                			System.out.println(node);
	                			break;
	                		}  		
	                	}
	                	break;
	                case 6:
	                    // Exit
	                    System.out.println("Exiting the program. Goodbye!");
	                    running = false;
	                    break;
	
	                default:
	                    System.out.println("Invalid choice. Please select a valid option.");
	                    break;
	            }
            }catch(InputMismatchException e) {
            	System.out.println("Wrong input!");
            	scanner.next();
            }
        }

        scanner.close();
    }
}
