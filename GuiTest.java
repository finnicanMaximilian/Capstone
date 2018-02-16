import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
 


public class GuiTest extends Application implements EventHandler<ActionEvent>
{  
	// Poker Variables
	Deck deck = new Deck();
	ArrayList<Card> theDeck = deck.getDeck();
	Player player = new Player();
	Opponent opponent = new Opponent();

	// GUI Variables
	Stage window;
	Scene scene;
	Image cardImage = new Image("cheetah-card.gif");
	String borderpane_style = "-fx-background-color: #FFFFFF;";
	String  vbox_style = "-fx-border-color: black;\n" +
			"-fx-border-insets: 10;\n" +
			"-fx-border-width: 5;\n" +
			"-fx-border-style: groove;\n" +
			"-fx-background-color: #FFFFFF;";
	Group root = new Group();
	Canvas canvas = new Canvas(1200, 900);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	ListView<String> listView = new ListView<>();


	
    public static void main(String[] args) 
    {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		window = primaryStage;
        window.setTitle("Poker Fanatic!");

        gc.setFill(Color.GREEN);
        gc.fillRoundRect(0, 0, 1200, 900, 0, 0);
        Collections.shuffle(theDeck);
        
        Button playButton = new Button("Play Poker!");
        playButton.setId("playButton");
        playButton.setMinWidth(40);
        playButton.setMinHeight(40);
        root.getChildren().addAll(canvas, playButton);
        playButton.setOnAction(e -> {
        	dealCards();
        	createButtons();
        });
        
        
        // create scene with root.
        scene = new Scene(root);
        
		// Adding the CSS sheet
		URL url = this.getClass().getResource("Poker.css");
		if (url == null) {
			System.out.println("Resource not found. Aborting.");
			System.exit(-1);
		}
		String css = url.toExternalForm(); 
		scene.getStylesheets().add(css);
		
		// add scene to window.
        window.setScene(scene);
        gc.setFill(Color.GREEN);
        window.show();
		return;
	}
	
	@Override
	public void handle(ActionEvent event)
	{

		return;
	}
	
	/*
	 * dealCards(): for creation sake only make this accommodate two players.
	 */
	public void dealCards()
	{
		int j = 1040;
		int m = 10;
		listView.setOrientation(Orientation.HORIZONTAL);
		for(int i = 0; i < 5; i++)
		{
			player.setCard(theDeck.get(i));
			gc.setFill(Color.WHITE);
			gc.fillRoundRect(m, 630, 150, 200, 5, 5);
			gc.setFill(Color.BLACK);
			gc.fillText((player.hand.get(i).getRank() + " " 
					+ player.hand.get(i).getSuit()), (m + 10), 650);

			// Adding name to cards to list view, this can later be used to give back
			// String names of cards in order to feed into player.java's search hand method.
			listView.getItems().add((player.hand.get(i).getRank() + " " 
					+ player.hand.get(i).getSuit()));

			
			m = m + 160;
		}
		for(int i = 0; i < 5; i++)
		{
			theDeck.remove(0);
		}
		for(int i = 0; i < 5; i++)
		{
	        gc.drawImage(cardImage, j, 8, 150, 200);
	        j = j - 155;
			opponent.setCard(theDeck.get(i));
		}
		for(int i = 0; i < 5; i++)
		{
			theDeck.remove(0);
		}
		// ListView customization
		listView.setPadding(new Insets(10,10,10,10));
		listView.setPrefSize(200, 150);
		listView.setLayoutX(200);
		listView.setLayoutY(100);
		//listView.setMinHeight(150);
		//listView.setMinWidth(800);
		root.getChildren().add(listView);
	}
	
	private void createButtons()
	{
		int m = 10;
		String button_style = "-fx-background-color: transparent;";
		Button b1 = new Button();
		b1.setId("Card1");
		b1.setMinHeight(200);
		b1.setMinWidth(150);
		b1.setLayoutX(m);
		b1.setLayoutY(630);
		//b1.setStyle(button_style);
		root.getChildren().add(b1);
		Button b2 = new Button();
		b2.setId("Card2");
		b2.setMinHeight(200);
		b2.setMinWidth(150);
		b2.setLayoutX(m + 160);
		b2.setLayoutY(630);
		b2.setStyle(button_style);
		root.getChildren().add(b2);
		Button b3 = new Button();
		b3.setId("Card3");
		b3.setMinHeight(200);
		b3.setMinWidth(150);
		b3.setLayoutX(m + 160 + 160);
		b3.setLayoutY(630);
		b3.setStyle(button_style);
		root.getChildren().add(b3);
		Button b4 = new Button();
		b4.setId("Card4");
		b4.setMinHeight(200);
		b4.setMinWidth(150);
		b4.setLayoutX(m + 160 + 160 + 160);
		b4.setLayoutY(630);
		b4.setStyle(button_style);
		root.getChildren().add(b4);
		Button b5 = new Button();
		b5.setId("Card5");
		b5.setMinHeight(200);
		b5.setMinWidth(150);
		b5.setLayoutX(m + 160 + 160 + 160 + 160);
		b5.setLayoutY(630);
		b5.setStyle(button_style);
		root.getChildren().add(b5);


		
		return;
	}
	
	public void drawBoard(GraphicsContext gc)
	{


        // Picture of Deck
        gc.drawImage(cardImage, 540, 350, 150, 200);
        
        
        // Making the title of the game.
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(15, 15, 350, 180, 5, 5);
        gc.setFill(Color.BLACK);
        gc.strokeText("Poker Fanatic", 140, 110);


	}
	
	
	/*
	 * In progress, trying to figure out a way to deal cards out.
	 * 
	 */
	public void dealCard(GraphicsContext gc)
	{
		// Computer Hand

        gc.drawImage(cardImage, 885, 8, 150, 200);
        gc.drawImage(cardImage, 730, 8, 150, 200);
        gc.drawImage(cardImage, 575, 8, 150, 200);
        gc.drawImage(cardImage, 420, 8, 150, 200);
        
        
        
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(10, 630, 150, 200, 5, 5);
        gc.fillRoundRect(170, 630, 150, 200, 5, 5);
        gc.fillRoundRect(330, 630, 150, 200, 5, 5);
        gc.fillRoundRect(490, 630, 150, 200, 5, 5);
        gc.fillRoundRect(650, 630, 150, 200, 5, 5);
       
	}

}