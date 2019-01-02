package client;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GUI7 {

	public Client7 client7;
	private Stage stage = new Stage();
	private Scene scene;
	private BorderPane borderpane = new BorderPane();
	private HBox TopBox = new HBox(10);
	private HBox BottomBox = new HBox(10);
	public VBox LeftBox = new VBox(10);
	private VBox RightBox = new VBox(10);
	public TextArea chatarea = new TextArea();
	public TextArea usersarea = new TextArea();
	public TextField textfield = new TextField();
	private Button SendButton = new Button("Send!");
	public Button CloseButton = new Button("Quit chat!");
	private Button EmojiButton = new Button("Emote!");
	private Label UsersLabel = new Label("Users Online");
	private Canvas canvas = new Canvas(500, 52);
	private GraphicsContext gc = canvas.getGraphicsContext2D();
	public String GUIname = null;
	private EmoticonBox emoticonbox;
	public boolean emoticonbool = true;

	public GUI7() {

		LoginUI.display(this);
		client7 = new Client7(this);

		stage.setOnCloseRequest(e -> {
			e.consume();
			closegui();
		});

		EmojiButton.setOnAction(e -> {
			if (emoticonbool) {
				emoticonbox = new EmoticonBox(this);
			} else {
				e.consume();
			}
		});

		CloseButton.setOnAction(e -> {
			closegui();
		});

		SendButton.setOnAction(e -> {
			client7.Send(textfield.getText() + "\n");
			textfield.clear();
		});

		chatarea.setEditable(false);
		chatarea.setWrapText(true);
		
		usersarea.setEditable(false);
	

		textfield.setPromptText("Be kind!");

		Image image = new Image("client/Immagine.png",100,40,false,false);
		
		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
		gc.setFont(theFont);
		gc.fillText("TCFChat2018!", 60, 50);
		gc.strokeText("TCFChat2018!", 60, 50);
		gc.drawImage(image, 390, 10);
		

		TopBox.getChildren().addAll(canvas);
		LeftBox.getChildren().addAll(UsersLabel, usersarea);
		RightBox.getChildren().addAll(CloseButton);
		BottomBox.getChildren().addAll(textfield, SendButton, EmojiButton);

		borderpane.setPadding(new Insets(10, 10, 10, 10));

		borderpane.setTop(TopBox);
		borderpane.setLeft(LeftBox);
		borderpane.setCenter(chatarea);
		borderpane.setRight(RightBox);
		borderpane.setBottom(BottomBox);
		
	

		BorderPane.setMargin(TopBox, new Insets(10, 10, 10, 10));
		BorderPane.setMargin(LeftBox, new Insets(10, 10, 10, 10));
		BorderPane.setMargin(RightBox, new Insets(10, 10, 10, 10));
		BorderPane.setMargin(BottomBox, new Insets(10, 10, 10, 10));
		BorderPane.setMargin(borderpane.getCenter(), new Insets(10, 10, 10, 10));

		scene = new Scene(borderpane);
		
		stage.setMinWidth(1000);
		stage.setMinHeight(500);
		stage.setScene(scene);
		stage.setTitle(GUIname);
		stage.show();

		if (GUIname == null) {
			closegui();
		}

	}

	public void closegui() {
		if(GUIname != null) {
		try {

			client7.sout.write("quit \n".getBytes());
			client7.Nameout.write("quit \n".getBytes());
		} catch (IOException e) {
			
		}
		}
		
		if (!emoticonbool) {
			emoticonbox.stage.close();
		}
		
		stage.close();
	}
	
	
	
	
	
	
}
