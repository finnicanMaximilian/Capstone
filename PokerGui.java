import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
 


public class PokerGui extends Application implements EventHandler<ActionEvent>
{  
	// Poker Variables
	Deck deck;
	ArrayList<Card> theDeck;
	Player player;
	Opponent opponent;

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
	Text giveBackText = new Text(10, 575, "To Select Multiple Cards Hold CTRL and click the desired cards then press 'Give Back Cards'");
	
	// storing a list of card names along with the number of cards
	String listOfCards = "";
	int numberOfCards = 0;
	Text playerWin = new Text();
	

	
    public static void main(String[] args) 
    {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		giveBackText.setVisible(false);
		window = primaryStage;
        window.setTitle("Poker Fanatic!");
        Button playButton = new Button("Play Poker!");
        Button showWin = new Button("See who Won!");
        Button giveBack = new Button("Give Back Cards");

        gc.setFill(Color.GREEN);
        gc.fillRoundRect(0, 0, 1200, 900, 0, 0);
        

        showWin.setVisible(false);
        showWin.setId("showWin");
        showWin.setLayoutY(400);
        showWin.setMinHeight(40);
        showWin.setMinWidth(80);
        showWin.setOnAction(e -> {
        	showWin.setVisible(false);
        	printWinner();
        	playButton.setVisible(true);
        	// how to reset the listView so that a brand new game could be played.
        	this.listView = new ListView<>();
        });

        giveBack.setVisible(false);
        giveBack.setId("giveBack");
        giveBack.setLayoutY(400);
        giveBack.setMinHeight(40);
        giveBack.setMinWidth(80);
        giveBack.setOnAction(e -> 
        {
        	buttonClicked();
        	initiateGiveBack(0);
        	initiateGiveBack(1);
        	// Testing to see if the initiateGiveBack is changing the players cards in hand.
        	// for(int i = 0; i < 5; i++) System.out.println("Player Card " + i + ":" + player.hand.get(i).getRank() + " " + player.hand.get(i).getSuit());
        	announceWinner();
        	giveBack.setVisible(false);
        	giveBackText.setVisible(false);
        	showWin.setVisible(true);
        });

        playButton.setId("playButton");
        playButton.setMinWidth(40);
        playButton.setMinHeight(40);
        playButton.setLayoutY(350);

        playButton.setOnAction(e -> {
        	playerWin.setVisible(false);
        	launchPoker();
            Collections.shuffle(theDeck);
        	dealCards();
        	playButton.setVisible(false);
        	giveBack.setVisible(true);
        	giveBackText.setVisible(true);
        });

        root.getChildren().addAll(canvas, playButton, giveBack, giveBackText, showWin);
        
        
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
        // Picture of Deck
        gc.drawImage(cardImage, 540, 350, 150, 200);
		// j is used for spacing for Computer Card's X Coordinate
		int j = 1040;
		// m is used for spacing for Player Card's X Coordinate
		int m = 10;
		// aligns the view in horizontal
		listView.setOrientation(Orientation.HORIZONTAL);
		for(int i = 0; i < 5; i++)
		{
			player.setCard(theDeck.get(i));

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
		// Drawing opponents hand.
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
		// Multiple Cards can be selected
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listView.setPadding(new Insets(10,10,10,10));
		listView.setPrefSize(550, 200);
		listView.setLayoutX(10);
		listView.setLayoutY(600);
		//listView.setMinHeight(150);
		//listView.setMinWidth(800);
		root.getChildren().add(listView);
	}
	
	public void drawBoard(GraphicsContext gc)
	{
        // Making the title of the game.
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(15, 15, 350, 180, 5, 5);
        gc.setFill(Color.BLACK);
        gc.strokeText("Poker Fanatic", 140, 110);
	}
	
	/*
	 * Works with GiveBack to get strings from the listview.
	 */
	private void buttonClicked()
	{
		listOfCards = "";
		ObservableList<String> cards;
		cards = listView.getSelectionModel().getSelectedItems();
		
		for(String m: cards)
		{
			listOfCards += m + "\n";
			numberOfCards++;
		}
		//System.out.println(message);
		return;
	}
	
	
	
	/*
	 * inititateGiveBack: This method would initiate the Giving back phase, 
	 * instructing the player on what to do, in return giving back the cards
	 * to the player and removing them from the deck.
	 * 0 == Player
	 * 1 == Computer
	 * 
	 * initiateGiveBack takes in the names of the cards that the user selects to remove from his/her hand
	 * then needs to re initialize those elements of the listView.
	 */
	public void initiateGiveBack(int person)
	{
		root.getChildren().remove(listView);
		if(person == 0)
		{
			//System.out.println("How many cards would you like to give back?");
			Scanner keyboard = new Scanner(listOfCards);
			int numOfCards = this.numberOfCards;
			if(numOfCards == 4 && ((player.searchHand("Ace", "Clubs") != 5)
					|| (player.searchHand("Ace", "Hearts") != 5)
					|| (player.searchHand("Ace", "Spades") != 5)
					|| (player.searchHand("Ace", "Diamonds") != 5)))
			{

				// Ace is present within the Persons hand
				for(int i = 0; i < 4; i++)
				{
					String rank = keyboard.next();
					String suit = keyboard.next();
					int cNum = player.searchHand(rank, suit);
					player.giveBack(cNum);
					// Seeing if the item gets removed from the list.
					listView.getItems().remove(cNum);
					
					
					
				}

				//System.out.println("\n\nGiving Back Cards....");
				for(int i = 0; i < numOfCards; i++)
				{
					String rank = theDeck.get(i).getRank();
					String suit = theDeck.get(i).getSuit();
					Card newCard = new Card(suit, rank);
					player.setCard(newCard);
					listView.getItems().add(rank + " " + suit);
				}
				for(int i = 0; i < numOfCards; i++)
				{
					theDeck.remove(0);
				}
			}
			else if(numOfCards < 4 && numOfCards != 0)
			{
//				System.out.println("Please type the Rank and Suit of the card you would like to give back\n"
//						+ "			Then press ENTER after each card.");
				for(int i = 0; i < numOfCards; i++)
				{
					String rank = keyboard.next();
					String suit = keyboard.next();
					int cNum = player.searchHand(rank, suit);
					player.giveBack(cNum);
					// Seeing if the item gets removed from the list.
					listView.getItems().remove(cNum);
				}
				for(int i = 0; i < numOfCards; i++)
				{
					String rank = theDeck.get(i).getRank();
					String suit = theDeck.get(i).getSuit();
					Card newCard = new Card(suit, rank);
					player.setCard(newCard);
					listView.getItems().add(rank + " " + suit);
				}
				for(int i = 0; i < numOfCards; i++)
				{
					theDeck.remove(0);
				}
			}
			else if(numOfCards == 0)
			{
				return;
			}
			else
			{
				// TODO Make this message Appear in GUI
				System.out.println("I'm Sorry but if you do not possess an Ace then you may not"
						+ "give back that many cards please type the number again");
			}


		}
		else if(person == 1)
		{
			// opponent goes here.
			int numOfCards = opponent.think(0);
			for(int i = 0; i < numOfCards; i++)
			{
				opponent.setCard(theDeck.get(i));
			}
			for(int i = 0; i < numOfCards; i++)
			{
				theDeck.remove(0);
			}

		}
		else
		{
			System.out.println("Wrong number entered, please enter 0 for player and 1 for opponent.");
		}
		root.getChildren().add(listView);
		return;
	}
	
	
	private void announceWinner()
	{
		// Flip the computers hand.
		int j = 1040;

		for(int i = 0; i < 5; i++)
		{
			// Draw Over Computer Cards With It's Cards.
			gc.setFill(Color.WHITE);
			gc.fillRoundRect(j, 8, 150, 200, 8, 8);
			gc.setFill(Color.BLACK);
			gc.fillText((opponent.hand.get(i).getRank() + " " + opponent.hand.get(i).getSuit()), (j+10), 100);
			j = j - 155;
		}	
		player.calcHand();
		opponent.calcHand();
		gc.fillText(("Total Player Points: " +player.winPoints), 600, 700);
		gc.fillText(("Total Computer Points: " + opponent.winPoints), 175, 150);
		gc.fillText(("Highest Ranked Player Card: " + player.highCard), 600, 750);
		gc.fillText(("Highest Ranked Computer Card: " + opponent.highCard), 175, 200);
			
		return;
	}
	
	public void launchPoker()
	{
		// Poker Variables
		this.deck = new Deck();
		this.theDeck = deck.getDeck();
		this.player = new Player();
		this.opponent = new Opponent();
	}
	
	public void printWinner()
	{

		playerWin.setLayoutX(500);
		playerWin.setLayoutY(450);
		playerWin.setVisible(false);

		root.getChildren().add(playerWin);
        gc.setFill(Color.GREEN);
        gc.fillRoundRect(0, 0, 1200, 900, 0, 0);
        gc.setFill(Color.BLACK);
        root.getChildren().remove(listView);
        // Player Won.
        if(player.winPoints > opponent.winPoints)
        {
        	playerWin.setText("Player Won");
        }
        else if(opponent.winPoints > player.winPoints)
        {
        	playerWin.setText("Computer Won!");
        }
        else if(opponent.winPoints == player.winPoints)
        {
        	if(opponent.highCard > player.highCard)
        	{
        		playerWin.setText("Computer Won with High Card!");
        	}
        	else if(player.highCard > opponent.highCard)
        	{
        		playerWin.setText("Player Won with High Card!");
        	}
        	else
        	{
        		playerWin.setText("Tie Game!!");
        	}
        }
     	playerWin.setVisible(true);
        	
        return;
	}

}