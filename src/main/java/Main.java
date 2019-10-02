import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException {
		Application application = new Application();
		application.start();
	}
	
	public static void restart () throws IOException {
		Application application = new Application();
		application.start();
	}

}
