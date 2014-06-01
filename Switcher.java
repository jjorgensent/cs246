import javafx.application.Platform;
import javafx.scene.Button;


public class Switcher extends Thread {
	
	@Override
	public void run() {
		Platform.runLater(new Runnable () {
			@Override
			public void run() {
		Button btn1 = Boldness_1.getInstance().getStartButton();
		Button btn2 = Boldness_1.getInstance().getStopButton();
		Button btn3 = new Button();
		btn3 = btn1;
		btn1 = btn2;
		btn2 = btn3;
			}
		});
		Boldness_1.getInstance().done();
	}

}