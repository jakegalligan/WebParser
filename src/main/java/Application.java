import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import org.jsoup.Jsoup;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map.Entry;



public class Application {
	
	 //create hashmap to store the count of each individual word
	 public Map<String, Integer> countByWord   = new HashMap<String, Integer>();

	//this method begins the application after being called from the Main class
	public void start () throws IOException {
		//get the desired url input from user via console
		String url = this.getUrlFromUser();
		//scrape the url for text
		Map<String,Integer> unsortedCountByWords = this.getTextFromUrl(url);
		//sort all words from the url and sort them by number of time they appear
		Map<String,Integer> sortedCountByWords = this.sortByCount(unsortedCountByWords);
		//Go through sorted word list and log first 25 words
		int wordsDesired = 25;
		this.printTopWordsOnWebPage(sortedCountByWords,wordsDesired);
		//restart the application
		Main.restart();
		
		
	}
	
	//this method gets the url from the user and stores it to be used
	public String getUrlFromUser () throws IOException {
		//prompt the user to enter the url and store their response within a string variable
		System.out.println("Enter the url");
		Scanner scanner = new Scanner(System.in);
		String url = scanner.nextLine();				
		return url;
	}
	
	public Map<String,Integer> getTextFromUrl (String url) throws IOException {
		 //take in user inputed url and get text from webpage
		 String textFromUrl = Jsoup.connect(url).get().text();
		 //store each individual word from the textFromUrl into an array
		 String arrayOfWords[] = textFromUrl.split(" ");
		 //go through each word in the array either placing them in HashMap or incrementing its count value
		 for(String word: arrayOfWords) {
			 String cleanedWord = this.cleanWord(word);
			 //add check to make sure no empty strings are being passed
			 if (cleanedWord.length() != 0) {
			 //if the word is alread in the hashmap store its count in the count variable, otherwise make count 0
			 int count = countByWord.containsKey(cleanedWord) ? countByWord.get(cleanedWord) : 0; 
			 //add the word to the hashtable with either a count value incremented by 1
			 countByWord.put(cleanedWord, count+1);
			 }
		 }
		 return countByWord;		 		 
	}
	
	public String cleanWord (String word) {
		//turn string to lowercase to standardize all text
		String lowerCaseWord = word.toLowerCase();
		//remove any characters that aren't hyphens
		String cleanedWord = lowerCaseWord.replaceAll("[^a-zA-Z&&[^-]]", "");
		return cleanedWord;
	}
	
	
	public Map<String, Integer> sortByCount(Map<String, Integer> unsortedCountByWord) {
	    Map<String, Integer> sortedWordByCount = unsortedCountByWord
	    	//store the key/value pairs within a single set object
	    	.entrySet()
	    	//use stream API in order to be able to utilize sorted method
	   		.stream()
	   		//sort the elements based on their value from large
	   		.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
	   		//create a linked map collection with the key/value pairs of each element within the stream. A linkedmap is used in order to maintain order of the map
    		.collect( Collectors.toMap(element -> element.getKey(), element -> element.getValue(), (element1, element2) -> element2, LinkedHashMap::new));
	    	return sortedWordByCount;
	}
	
	public void printTopWordsOnWebPage(Map<String,Integer> wordMap, int wordsDesired) {
		int i = 1;
		//create an iterator based off the entries within the map of most used words
		Iterator<Map.Entry<String,Integer>> entries = wordMap.entrySet().iterator();
		//print the top 25 words in order
		while (i < wordsDesired + 1) {
			Map.Entry<String, Integer> entry = entries.next();
			System.out.println(i+". " + entry.getKey() + ": " + entry.getValue());
			i++;
		}
	}
}
