package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ClientStarter7 extends Application {

	private Stage stage = new Stage();
	private Scene scene;
	private GridPane gridpane = new GridPane();
	private TextField textfield = new TextField();
	static TextArea textarea = new TextArea();
	private Button StartButton = new Button("Start Client!");
	private Button CloseButton = new Button("Exit");

	private Label AreaLabel = new Label("History: ");

	static int i = 0;

	public void start(Stage primaryStage) {

		stage.setOnCloseRequest(e -> {
			e.consume();
			closeprog();
		});

		this.CloseButton.setOnAction(e -> {
			closeprog();
		});

		this.StartButton.setOnAction(e -> {

			GUI7 gui = new GUI7();
			textfield.clear();

		});

		textarea.setEditable(false);
		gridpane.setPadding(new Insets(10, 10, 10, 10));

		gridpane.add(StartButton, 0, 0);
		gridpane.add(AreaLabel, 0, 1);
		gridpane.add(textarea, 0, 2);
		gridpane.add(CloseButton, 0, 3);

		scene = new Scene(gridpane);
		stage.setScene(scene);
		stage.setTitle("ClientStarter 7.0");
		stage.show();

	}

	private void closeprog() {
		if (AlertBox.display(2)) {
			stage.close();
			
		}

	}

	public static void main(String[] args) {
		launch();
	}

}
