import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.GroupLayout.Alignment;

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
	Scene titleScene;
	Scene pokerScene;
	Image cardImage = new Image("cardImgs/cheetah-card.gif");
	String borderpane_style = "-fx-background-color: #FFFFFF;";
	String  vbox_style = "-fx-border-color: black;\n" +
			"-fx-border-insets: 10;\n" +
			"-fx-border-width: 5;\n" +
			"-fx-border-style: groove;\n" +
			"-fx-background-color: #FFFFFF;";
	Group root = new Group();
	Group titleRoot = new Group();
	Canvas pokerCanvas = new Canvas(1200, 900);
	GraphicsContext gc = pokerCanvas.getGraphicsContext2D();
	Canvas titleCanvas = new Canvas(1200, 900);
	GraphicsContext titleGc = titleCanvas.getGraphicsContext2D();
	ListView<String> listView = new ListView<>();
	Text giveBackText = new Text(10, 575, "To Select Multiple Cards Hold CTRL and click the desired cards then press 'Give Back Cards'");
	
	// storing a list of card names along with the number of cards
	String listOfCards = "";
	int numberOfCards = 0;
	Text pTitle = new Text();
	Text playerWin = new Text();
	Text giveBackWarning = new Text(10, 550,"You may only give back up to 4 cards if you have an Ace, 3 elsewise");
	ObservableList<String> cards;
	int numOfGames = 0;
	

    public static void main(String[] args) 
    {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception 
	{	
		this.pTitle.setVisible(false);
		this.giveBackWarning.setVisible(false);
		this.giveBackText.setVisible(false);
		this.window = primaryStage;
        this.window.setTitle("Poker Fanatic!");
        
        Button playGame = new Button("Play Game!");
        Button playButton = new Button("Start Poker!");
        Button showWin = new Button("See who Won!");
        Button giveBack = new Button("Give Back Cards");
        Button flipCard = new Button("Flip Cards");
        
        // creating screen for title screen.
        this.titleGc.setFill(Color.GREEN);
        this.titleGc.fillRoundRect(0, 0, 1200, 900, 0, 0);
        // create title Text.
        setUpTitleScene();

        
        playGame.setVisible(true);
        playGame.setOnAction(e -> {
        	playGame.setVisible(false);
        	//this.pTitle.setVisible(false);
        	this.window.setScene(pokerScene);
        });

        this.gc.setFill(Color.GREEN);
        this.gc.fillRoundRect(0, 0, 1200, 900, 0, 0);
        
        playButton.setId("playButton");
        playButton.setMinWidth(40);
        playButton.setMinHeight(40);
        playButton.setLayoutY(350);  
        /*
         * When "Play Poker" is pressed.
         */
        playButton.setOnAction(e -> {
        	root.getChildren().remove(playerWin);
        	this.playerWin.setVisible(false);
        	launchPoker();
            Collections.shuffle(theDeck);
        	dealCards();
        	playButton.setVisible(false);
        	giveBack.setVisible(true);
        	this.giveBackText.setVisible(true);
        });
        
        
        giveBack.setVisible(false);
        giveBack.setId("giveBack");
        giveBack.setLayoutY(400);
        giveBack.setMinHeight(40);
        giveBack.setMinWidth(80);
        
        /*
         * When "Give Back Cards" is pressed.
         * need to make initiateGiveBack notify a user when they attempt to give back more than 3 cards with
         * no Ace.. then they need to be given another chance to give back the cards they wanted to..
         */
        giveBack.setOnAction(e -> 
        {
    		buttonClicked();
    		// Check for an incorrect hand, i.e. where number of cards is equal to 5, or when numOfCards equals 4 and the user does not have an ace.
    		// So the !(not) enveloping the player searchHand calls reads as, if the player does not have any type of ace in their hand but they selected 4 cards to return.
    		if(this.numberOfCards == 5 || ((this.numberOfCards == 4 && !((this.player.searchHand("Ace", "Clubs") != 5)
					|| (this.player.searchHand("Ace", "Hearts") != 5)
					|| (this.player.searchHand("Ace", "Spades") != 5)
					|| (this.player.searchHand("Ace", "Diamonds") != 5)))))
    		{
    			this.giveBackWarning.setVisible(true);
    		}
    		else
            {
    			buttonClicked();
    			giveBack.setVisible(false);
            	initiateGiveBack(0);
            	flipCard.setVisible(true);
            }
        });
        

        flipCard.setVisible(false);
        //flipCard.setText();
        flipCard.setId("flipCard");
        flipCard.setLayoutY(400);
        flipCard.setMinHeight(40);
        flipCard.setMinWidth(80);
        
        /*
         * Assuming, cards have been selected correctly on "give back"
         * flipCard ACTION: initiateGiveBack for Player, Computer,
         * Flip Computer final Cards, clean up messages.
         * 
         */
        flipCard.setOnAction(e -> {
        	//giveBack.setVisible(false);
        	initiateGiveBack(1);
        	flipCards();
        	giveBack.setVisible(false);
        	giveBackText.setVisible(false);
        	showWin.setVisible(true);
			this.giveBackWarning.setVisible(false);
			flipCard.setVisible(false);
        });
        
        showWin.setVisible(false);
        showWin.setId("showWin");
        showWin.setLayoutY(400);
        showWin.setMinHeight(40);
        showWin.setMinWidth(80);
        
        /*
         * When "Show who won!" is pressed.
         */
        showWin.setOnAction(e -> {
        	showWin.setVisible(false);
        	printWinner();
        	playButton.setVisible(true);
        	// how to reset the listView so that a brand new game could be played.
        });
       
        // Title Scene
        this.titleRoot.getChildren().addAll(titleCanvas, playGame);
        this.titleRoot.autoSizeChildrenProperty().setValue(true);
        this.titleScene = new Scene(titleRoot);
        // Poker Scene
        this.root.getChildren().addAll(pokerCanvas, playButton, giveBack, giveBackText, showWin, giveBackWarning, flipCard);
        this.root.autoSizeChildrenProperty().setValue(true);
        this.pokerScene = new Scene(root);
        
		// Adding the CSS sheet
		URL url = this.getClass().getResource("Poker.css");
		if (url == null) {
			System.out.println("Resource not found. Aborting.");
			System.exit(-1);
		}
		String css = url.toExternalForm(); 
		this.pokerScene.getStylesheets().add(css);
		this.window.setMaxHeight(900);
		this.window.setMaxWidth(1200);
		this.titleScene.getStylesheets().add(css);
		
		// add scene to window.
        this.window.setScene(this.titleScene);
        this.gc.setFill(Color.GREEN);
        this.window.show();
		return;
	}
	
	/*
	 * setUpTitleScene: Trying to bundle all things that encapsulate decorating the title scene within this function to clear clutter from start()
	 * 
	 */
	private void setUpTitleScene()
	{
		this.pTitle.setFill(Color.BLACK);
		this.pTitle.setVisible(true);
		this.pTitle.setId("pTitle");
		this.pTitle.setLayoutX(400);
		this.pTitle.setLayoutY(400);
		this.pTitle.setText("Poker Fanatic");

		this.titleRoot.getChildren().add(pTitle);
		
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
        this.gc.drawImage(cardImage, 540, 350, 150, 200);
		// j is used for spacing for Computer Card's X Coordinate
		int j = 1040;
		// m is used for spacing for Player Card's X Coordinate
		int m = 10;
		// aligns the view in horizontal
		this.listView.setOrientation(Orientation.HORIZONTAL);
		
		for(int i = 0; i < 5; i++)
		{
			this.player.setCard(theDeck.get(i));

			// Adding name to cards to list view, this can later be used to give back
			// String names of cards in order to feed into player.java's search hand method.
			this.listView.getItems().add((player.hand.get(i).getRank() + " " 
					+ player.hand.get(i).getSuit()));	
			m = m + 160;
		}
		for(int i = 0; i < 5; i++)
		{
			this.theDeck.remove(0);
		}
		// Drawing opponents hand.
		for(int i = 0; i < 5; i++)
		{
	        this.gc.drawImage(cardImage, j, 8, 150, 200);
	        j = j - 155;
			this.opponent.setCard(theDeck.get(i));
		}
		for(int i = 0; i < 5; i++)
		{
			this.theDeck.remove(0);
		}
		// Multiple Cards can be selected
		this.listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		this.listView.setPadding(new Insets(10,10,10,10));
		this.listView.setPrefSize(550, 200);
		this.listView.setLayoutX(10);
		this.listView.setLayoutY(600);
		this.root.getChildren().add(listView);
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
		this.listOfCards = "";
		this.numberOfCards = 0;
		this.cards = listView.getSelectionModel().getSelectedItems();
		
		for(String m: this.cards)
		{
			this.listOfCards += m + "\n";
			this.numberOfCards++;
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
	public boolean initiateGiveBack(int person)
	{
		boolean turnDone = false;
		boolean tempTurn = true;
		this.root.getChildren().remove(listView);
		if(person == 0)
		{
			Scanner keyboard = new Scanner(this.listOfCards);
			int numOfCards = this.numberOfCards;
			
			//Testing numOfCards
			//System.out.println(numOfCards);
			
				if(numOfCards == 5)
				{
					// prompt user
					this.giveBackWarning.setVisible(true);
					buttonClicked();
					tempTurn = false;
				}
			
			if(numOfCards == 4 && ((this.player.searchHand("Ace", "Clubs") != 5)
					|| (this.player.searchHand("Ace", "Hearts") != 5)
					|| (this.player.searchHand("Ace", "Spades") != 5)
					|| (this.player.searchHand("Ace", "Diamonds") != 5)))
			{

				// Ace is present within the Persons hand
				for(int i = 0; i < 4; i++)
				{
					String rank = keyboard.next();
					String suit = keyboard.next();
					int cNum = this.player.searchHand(rank, suit);
					this.player.giveBack(cNum);
					this.listView.getItems().remove(cNum);
				}
				for(int i = 0; i < numOfCards; i++)
				{
					String rank = theDeck.get(i).getRank();
					String suit = theDeck.get(i).getSuit();
					Card newCard = new Card(suit, rank);
					this.player.setCard(newCard);
					this.listView.getItems().add(rank + " " + suit);
				}
				for(int i = 0; i < numOfCards; i++)
				{
					this.theDeck.remove(0);
				}
				turnDone = true;
			}
			if(numOfCards < 4 && numOfCards != 0)
			{
				for(int i = 0; i < numOfCards; i++)
				{
					String rank = keyboard.next();
					String suit = keyboard.next();
					int cNum = player.searchHand(rank, suit);
					this.player.giveBack(cNum);
					// Seeing if the item gets removed from the list.
					this.listView.getItems().remove(cNum);
				}
				for(int i = 0; i < numOfCards; i++)
				{
					String rank = this.theDeck.get(i).getRank();
					String suit = this.theDeck.get(i).getSuit();
					Card newCard = new Card(suit, rank);
					this.player.setCard(newCard);
					this.listView.getItems().add(rank + " " + suit);
				}
				for(int i = 0; i < numOfCards; i++)
				{
					this.theDeck.remove(0);
				}
				turnDone = true;
			}
			else if(numOfCards == 0)
			{
				turnDone = true;
			}
			
			keyboard.close();
		}
		else if(person == 1)
		{
			// opponent goes here.
			int numOfCards = opponent.think(0);
			for(int i = 0; i < numOfCards; i++)
			{
				this.opponent.setCard(this.theDeck.get(i));
			}
			for(int i = 0; i < numOfCards; i++)
			{
				this.theDeck.remove(0);
			}
			turnDone = true;
		}
		
		this.root.getChildren().add(this.listView);
		return turnDone;
	}
	
	private void flipCards()
	{
		// Flip the computers hand.
		int j = 1040;

		for(int i = 0; i < 5; i++)
		{
			// Draw Over Computer Cards With It's Cards.
			this.gc.setFill(Color.WHITE);
			this.gc.fillRoundRect(j, 8, 150, 200, 8, 8);
			this.gc.setFill(Color.BLACK);
			this.gc.fillText((opponent.hand.get(i).getRank() + " " + opponent.hand.get(i).getSuit()), (j+10), 100);
			j = j - 155;
		}	
		this.player.calcHand();
		this.opponent.calcHand();
		this.gc.fillText(("Total Player Points: " +player.winPoints), 600, 700);
		this.gc.fillText(("Total Computer Points: " + opponent.winPoints), 175, 150);
		this.gc.fillText(("Highest Ranked Player Card: " + player.highCard), 600, 750);
		this.gc.fillText(("Highest Ranked Computer Card: " + opponent.highCard), 175, 200);
			
		return;
	}
	
	private void launchPoker()
	{
		// Poker Variables
		if(this.numOfGames > 0)
		{
			this.listView.setEditable(true);
			this.listView.getItems().remove(0);
			this.listView.getItems().remove(0);
			this.listView.getItems().remove(0);
			this.listView.getItems().remove(0);
			this.listView.getItems().remove(0);
		}
		this.deck = new Deck();
		this.theDeck = deck.getDeck();
		this.player = new Player();
		this.opponent = new Opponent();
		this.numOfGames++;
		// Trying to re-instantiate a players hand.
		// the listView is populated before this method.. so when i create a new listView its removing the list
		// before buttonClicked() can establish what is inside the hand.


	}
	
	private void printWinner()
	{
		this.playerWin.setLayoutX(500);
		this.playerWin.setLayoutY(450);
		this.playerWin.setVisible(false);
		this.root.getChildren().add(playerWin);
		this.gc.setFill(Color.GREEN);
		this.gc.fillRoundRect(0, 0, 1200, 900, 0, 0);
		this.gc.setFill(Color.BLACK);
		this.root.getChildren().remove(listView);
		
		/*
		 * Theoretical Error!! Player needs to win if their pair's beat other pairs.
		 */
        // Player Won.
        if(this.player.winPoints > this.opponent.winPoints) // Flat out wins because the winpoints is higher.
        {
        	this.playerWin.setText("Player Won");
        }
        else if(this.opponent.winPoints > this.player.winPoints) // Flat out wins because the winpoints is higher.
        {
        	this.playerWin.setText("Computer Won!");
        }
        else if(this.opponent.winPoints == this.player.winPoints) // Needs to be delved into.
        {
        	// isPairHigher, Checks to see if player wins by rank of hand combo.
        	if(!(isPairHigher())) 
        	{
        		if(this.opponent.highCard > this.player.highCard)
        		{
        			this.playerWin.setText("Computer Won with High Card!");
        		}
        		else if(player.highCard > opponent.highCard)
        		{
        			this.playerWin.setText("Player Won with High Card!");
        		}
        		else
        		{
        			this.playerWin.setText("Tie Game!!");
        		}
        	}
        }
        this.playerWin.setVisible(true);
        return;
	}
	
	
	/*
	 * isPairHigher: This method with aid printWinner with running a check on each players hand
	 * to see when win points are equal if ranks are higher in who ever's hand.
	 */
	private boolean isPairHigher()
	{
		boolean hadHigherRank = false;
		// OnePair
		if(player.onePair && opponent.onePair && !(player.fullHouse) && !(opponent.fullHouse))
		{
			if(player.onePair() > opponent.onePair())
			{
				hadHigherRank = true;
				this.playerWin.setText("Player Won with Higher Ranked Cards!");
			}
			else if(opponent.onePair() > player.onePair())
			{
				hadHigherRank = true;
				this.playerWin.setText("Computer Won with Higher Ranked Cards!");
			}
		}
		// TwoPair
		if(player.twoPair && opponent.twoPair)
		{
			if(player.twoPair() > opponent.twoPair())
			{
				hadHigherRank = true;
				this.playerWin.setText("Player Won with Higher Ranked Cards!");
			}
			else if(opponent.twoPair() > player.twoPair())
			{
				hadHigherRank = true;
				this.playerWin.setText("Computer Won with Higher Ranked Cards!");
			}
		}
		// ThreeOfKind
		if(player.threeOfKind && opponent.threeOfKind && !(player.fullHouse) && !(opponent.fullHouse))
		{
			if(player.threeOfKind() > opponent.threeOfKind())
			{
				hadHigherRank = true;
				this.playerWin.setText("Player Won with Higher Ranked Cards!");
			}
			else if(opponent.threeOfKind() > player.threeOfKind())
			{
				hadHigherRank = true;
				this.playerWin.setText("Computer Won with Higher Ranked Cards!");
			}
		}
		// FourOfKind
		if(player.fourOfKind && opponent.fourOfKind)
		{
			if(player.fourOfKind() > opponent.fourOfKind())
			{
				hadHigherRank = true;
				this.playerWin.setText("Player Won with Higher Ranked Cards!");
			}
			else if(opponent.fourOfKind() > player.fourOfKind())
			{
				hadHigherRank = true;
				this.playerWin.setText("Computer Won with Higher Ranked Cards!");
			}
		}
		// fullHouse Checks the three of kind first, then onePair
		if(player.fullHouse && opponent.fullHouse)
		{
			// Check three of kind. No way to get past this unless i add "wild cards"
			if(player.threeOfKind() > opponent.threeOfKind())
			{
				hadHigherRank = true;
				this.playerWin.setText("Player Won with Higher Ranked Cards!");
			}
			else if(opponent.threeOfKind() > player.threeOfKind())
			{
				hadHigherRank = true;
				this.playerWin.setText("Computer Won with Higher Ranked Cards!");
			}
		}
		// flush This works with current code, winpoints are equal so high card is checked.
		// Straight this works with current code, winpoints are equal so high card is checked.
		// straightFlush this works with current code, winpoints are equal so high card is checked.
		// royalFlush, this would result in tie because all cards are same just different suit which isnt
		// looked at for who won.
		return hadHigherRank;
	}

}