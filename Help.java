import java.io.BufferedReader;
import java.io.FileReader;

public class Help extends Thread {
	@Override
	public void run() {

try {
System.out.println("Local Runnables are:");
BufferedReader reader = new BufferedReader(new FileReader("classes.txt"));
String line = null;
while ((line = reader.readLine()) != null) {
	System.out.println(line);

}
} catch (Exception e) {
	System.out.println(e.getMessage());
}


Boldness_1.getInstance().done();
	}

}