import java.util.Scanner;

public class Application {
	
	//this method begins the application after being called from the Main class
	public void start () {
		//promp the user to enter the url and store their response within a string variable
		System.out.println("Enter the url");
		Scanner scanner = new Scanner(System.in);
		String url = scanner.nextLine();
		
		System.out.println(url);
	}
}
