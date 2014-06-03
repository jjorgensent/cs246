import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.security.SecureRandom;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.math.BigInteger;
import javafx.scene.layout.GridPane;

public class Switcher extends Thread {
	int i;
	@Override
	public void run() {
		String[] titleList = {"Hello Java FX World!", "How Are You Doing?", "I'm Fine, Thanks For Asking.", "It's Fun Being a Box on Your Machine!", "Well, Goodbye World!"};
		String name = Thread.currentThread().getName();
		Stage stage = Boldness_1.getInstance().getStage();

		Platform.runLater(new Runnable () {
			@Override
			public void run() {
				Thread.currentThread().setName(name);
				while (i < titleList.length) {

			  	String newTitle = titleList[i];
			  	stage.setTitle(newTitle);
				i = i + 1;
				try {
			  	Thread.sleep(500);
			  	} catch (Exception ex) {
			  	System.out.println(ex.getMessage());
			  }
			  if (!Boldness_1.getInstance().shouldCurrentThreadBeRunning()) {
			  	stage.setTitle("Glory");

			  	break;
			  }
			   }
			}
			});

		Boldness_1.getInstance().done();
		}

	}
	