import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.jsoup.Jsoup;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.Map.Entry;



public class Application {
	
	 //create hashmap to store the count of each individual word
	 public Map<String, Integer> countByWord   = new HashMap<String, Integer>();

	//this method begins the application after being called from the Main class
	public void start () throws IOException {
		this.getUrlFromUser();
	}
	
	//this method gets the url from the user and stores it to be used
	public void getUrlFromUser () throws IOException {
		//prompt the user to enter the url and store their response within a string variable
		System.out.println("Enter the url");
		Scanner scanner = new Scanner(System.in);
		String url = scanner.nextLine();				
		scanner.close();
		//take the url and pass it to class method in order to be scraped
		this.scrapeURLForText(url);		
	}
	
	public void scrapeURLForText (String url) throws IOException {
		 //take in user inputed url and get text from webpage
		 String textFromUrl = Jsoup.connect("https://www.quora.com/What-does-throws-IOexception-mean").get().text();
		 //store each individual word from the textFromUrl into an array
		 String arrayOfWords[] = textFromUrl.split(" ");
		 //go through each word in the array either placing them in HashMap or incrementing its count value
		 for(String word: arrayOfWords) {
			 String cleanedWord = this.cleanWord(word);
			 //if the word is alread in the hashmap store its count in the count variable, otherwise make count 0
			 int count = countByWord.containsKey(cleanedWord) ? countByWord.get(cleanedWord) : 0; 
			 //add the word to the hashtable with either a count value incremented by 1
			 countByWord.put(cleanedWord, count+1);
		 }
		 
		 this.sortByCount(countByWord);
//		 System.out.println(countByWord);
		 		 
	}
	
	public String cleanWord (String word) {
		//turn string to lowercase to standardize all text
		String lowerCaseWord = word.toLowerCase();
		//remove any characters that aren't hyphens
		String cleanedWord = lowerCaseWord.replaceAll("[^a-zA-Z&&[^-]]", "");
		return cleanedWord;
	}
	
	
	public Map<String, Integer> sortByCount(Map<String, Integer> unsortedWordByCount) {
		
	    Map<String, Integer> sortedWordByCount = unsortedWordByCount
	    		//store the key/value pairs within a single set object
	    		.entrySet()
	    		//use stream API in order to be able to utilize methods
	    		.stream()
	    		//sort the elements by their values
	    		.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
	    		//convert the data into a linkedhashmap to maintain sorted order of list
	    		.collect( Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
	    		System.out.println(sortedWordByCount);
	    		return sortedWordByCount;
	}
	
	
}
