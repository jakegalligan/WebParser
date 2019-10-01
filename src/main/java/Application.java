import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import org.jsoup.Jsoup;

public class Application {
	
	  //create hashmap to store the count of each individual word
	  public HashMap<String, Integer> countByWord   = new HashMap<String, Integer>();

	//this method begins the application after being called from the Main class
	public void start () throws IOException {
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
		 String arrayOfTextFromUrl[] = textFromUrl.split(" ");
		 for(String word: arrayOfTextFromUrl) {
			 System.out.println(word);
		 }
		 		 
	}	
}
