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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
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
import java.util.ArrayList;
import java.util.Arrays;

public class Boldness extends Application {
    
    @Override
public void start(Stage primaryStage) {

GridPane grid = new GridPane();
grid.setAlignment(Pos.TOP_CENTER);
grid.setHgap(10);
grid.setVgap(10);
grid.setPadding(new Insets(25, 25, 25, 25));

Scene scene = new Scene(grid, 600, 600);
primaryStage.setScene(scene);

Button btn1 = new Button();
btn1.setText("Start");
 
      btn1.setOnAction(new EventHandler<ActionEvent>() {


         @Override
public void handle(ActionEvent event) {
             System.out.println("Thread Started");
         }
});
        
        StackPane root = new StackPane();
        root.getChildren().add(btn1);

grid.add(btn1, 0, 4);


Button btn2 = new Button();
btn2.setText("Remove");
 
      btn2.setOnAction(new EventHandler<ActionEvent>() {


         @Override
public void handle(ActionEvent event) {
             System.out.println("Thread Ended");
         }
});
        
        StackPane root1 = new StackPane();
        root1.getChildren().add(btn2);


grid.add(btn2, 1, 4);

// primaryStage.setScene(new Scene(root, 300, 250));
 // primaryStage.show();


primaryStage.setTitle("Glory");

ArrayList<String> listName = new ArrayList<String>();


Label userName = new Label("Enter Runnable: ");
grid.add(userName, 0, 1);

TextField userTextField = new TextField();
listName.add(userTextField.getOnAction());

grid.add(userTextField, 1, 1);

// userTextField = new TextField();
// grid.add(userTextField, 2, 1);

ListView<String> threadList1 = new ListView<String>();
ObservableList<String> items1 = FXCollections.observableArrayList (listName);
threadList1.setItems(items1);

ListView<String> threadList2 = new ListView<String>();
ObservableList<String> items2 = FXCollections.observableArrayList ();
threadList2.setItems(items2);


// <Text fx:id="actiontarget"
// GridPane.columnIndex="1" GridPane.rowIndex="6"/>

grid.add(threadList1, 0, 2);
grid.add(threadList2, 1, 2);

    primaryStage.setScene(scene);
    primaryStage.show();



    }
    public static void main(String[] args) {
        launch(args);
    }
}

/*
Platform.runLater(new Runnable() {
@Override
public void run() {
stage.setTitle(newTitle);
}
});
*/