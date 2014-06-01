import java.io.BufferedReader;
import java.io.FileReader;
import javafx.application.Platform;

public class Loader implements Runnable {

@Override
public void run() {

Platform.runLater(new Runnable() { 
	@Override
	public void run() {
		try{
		BufferedReader reader = new BufferedReader(new FileReader("classes.txt"));
		String line = null;
		while ((line = reader.readLine()) != null) {
					System.out.println(line + " has been loaded");

					String runnableName = line;
				if (!Boldness_1.getInstance().threadList1.getItems().contains(runnableName)) {
				try {

					// I just need to check to see if it's a class
					// It will throw an exception if it isn't a class
					Class.forName(runnableName).newInstance();
				
					// if it doesn't exist, 
					// the exception will throw before it reaches these lines below

					// put the text into the list
					Boldness_1.getInstance().threadList1.getItems().add(runnableName);
					System.out.println(line + " has been loaded");

				} catch (Exception e) {
					// The actual error is too annoying to display
					System.out.println("Runnable Error. " + runnableName + " does Not Exist.");// + e.getMessage());
         		//e.printStackTrace();
				}
			}
		}
	} catch (Exception ex) {
	System.out.println("Exception thrown: " + ex.getMessage());
}
}	

});
Boldness_1.getInstance().done();
}
}