
/*


Here's what I haven't implemented: 
opening other GUIs on threads (ConcurrentModel,java), 
opening other GUIs with this: public void start(Stage primaryStage),
check list to see if name is in it, if not, then add it to the ListView (threadList1)
*/

/*
For all you comrades (fellow students), who sought out this exploration and found it,
it is almost completed. Please learn from the hours and hours of research represented
here...it would be a shame to take from this exploration and not learn anything from it
*/

import javafx.application.Platform;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import java.util.Observable;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Boldness_1 extends Application {
    Thread thread;
	ListView<String> threadList1; 
	ListView<String> threadList2;
	TextField runnableTextField;

	private static Boldness_1 instance;
	private Stage primaryStage;

	public static Boldness_1 getInstance() {
		return instance;
	}

	public boolean threadList2Contains(Thread thisThread) {
		return threadList2.getItems().contains(thisThread.getName());
	}

	public boolean shouldCurrentThreadBeRunning() {
		return threadList2Contains(Thread.currentThread());
	}

	public Stage getStage() {
		return this.primaryStage;
	}

	public void done() {		
		String name = Boldness_1.getInstance().getThreadName();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
		//System.out.println("Counter Thread Finished.");

				Boldness_1.getInstance().removeThread(name);
				//System.out.println("Counter Thread Finished.");
			}
		});
	}

	public String getThreadName() {
		return Thread.currentThread().getName();
	}
	public void removeThread(String name) {
		threadList2.getItems().remove(name);
		System.out.println(name + " has finished.");
	}

	public Button startButton() {
		Button btn1 = new Button();
		btn1.setText("Start");
 
      	btn1.setOnAction(new EventHandler<ActionEvent>() {
        @Override
			public void handle(ActionEvent event) {

				String runnableName = threadList1.getSelectionModel().getSelectedItem();
				try {
					Runnable r = (Runnable)Class.forName(runnableName).newInstance();
					thread = new Thread(r);
					thread.start();
					thread.setName(runnableName + " " + thread.getName());
					threadList2.getItems().add(thread.getName());
				} catch (Exception e) {
					System.out.println("Runnable Error. Cannot Run.");
				}
         }
	   });
        
        return btn1;
	}

	public Button stopButton() {

		Button btn2 = new Button();
		btn2.setText("Stop and Remove");
 
      	btn2.setOnAction(new EventHandler<ActionEvent>() {
		    @Override	
			public void handle(ActionEvent event) {

				String threadToStop = threadList2.getSelectionModel().getSelectedItem();
				System.out.println("You just killed the thread: " + threadToStop);
				threadList2.getItems().remove(threadToStop);
      		}
	 	});

		return btn2;
	}

	public void handleTextFieldInput() {
		runnableTextField.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Get the text of the box
				String runnableName = runnableTextField.getText();
				if (!threadList1.getItems().contains(runnableName)) {
				try {
					// I just need to check to see if it's a class
					// It will throw an exception if it isn't a class
					Class.forName(runnableName).newInstance();
				
					// if it doesn't exist, 
					// the exception will throw before it reaches these lines below

					// put the text into the list
					threadList1.getItems().add(runnableName);
					
					// clears the text field
					runnableTextField.clear();
				} catch (Exception e) {
					// The actual error is too annoying to display
					System.out.println("Runnable Error. Does Not Exist.");// + e.getMessage());
         		//e.printStackTrace();
				}
			}
			}
		});
	}

    @Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;

		instance = this;

		// Sets up the grid
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		// how large is the whole scene?
		Scene scene = new Scene(grid, 600, 600);
		primaryStage.setScene(scene);

		// I decided to have a "Help" class that can't be removed from the user
//		ArrayList<String> classNames = new ArrayList<String>();
//		classNames.add("Help");

		// Title of the page initially set here
		primaryStage.setTitle("Glory");

		// Input runnables label
		Label enterRunnable = new Label("Enter Runnable: ");
		grid.add(enterRunnable, 0, 0);

		// Text Field to input Runnables
		runnableTextField = new TextField();
		grid.add(runnableTextField, 1, 0);

		Label listRunnables = new Label("Runnables");
		grid.add(listRunnables, 0, 1);
		// This is the left side List of Runnables
		threadList1 = new ListView<String>();
		ObservableList<String> items1 = FXCollections.observableArrayList ();//classNames);
		threadList1.setItems(items1);

		Label listRunning = new Label("Running");
		grid.add(listRunning, 1, 1);
		// This is the right side List of Runnables that are running
		threadList2 = new ListView<String>();
		ObservableList<String> items2 = FXCollections.observableArrayList ();
		threadList2.setItems(items2);

		// adds the threadLists to the grid
		grid.add(threadList1, 0, 2);
		grid.add(threadList2, 1, 2);


		// Start button
		Button btn1 = startButton();
        StackPane root = new StackPane();
        root.getChildren().add(btn1);
		grid.add(btn1, 0, 4);

		// Stop and Remove Button
		Button btn2 = stopButton();
	   StackPane root1 = new StackPane();
    	root1.getChildren().add(btn2);
		grid.add(btn2, 1, 4);

		// get the Text from the input field and send it the the threadList
		handleTextFieldInput();

		primaryStage.setScene(scene);
		primaryStage.show();
   }
  
   public static void main(String[] args) {
       launch(args);
   }
}