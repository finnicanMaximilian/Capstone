import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.sun.glass.events.MouseEvent;

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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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
 


public class PokerTwoGui extends Application
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
	Text giveBackText = new Text(10, 575, "To Select Multiple Cards Hold CTRL and click the desired cards then press 'Give Back Cards'");
	
	// storing a list of card names along with the number of cards
	String listOfCards = "";
	int numberOfCards = 0;
	Text playerWin = new Text();
	Text giveBackWarning = new Text(10, 550,"You may only give back up to 4 cards if you have an Ace, 3 elsewise");
	ObservableList<String> cards;
	int numOfGames = 0;
	
    Button card1 = new Button();
    Button card2 = new Button();
    Button card3 = new Button();
    Button card4 = new Button();
    Button card5 = new Button();
    
    boolean card1Clicked = false;
    boolean card2Clicked = false;
    boolean card3Clicked = false;
    boolean card4Clicked = false;
    boolean card5Clicked = false;
    
    String card1Name = "";
    String card2Name = "";
    String card3Name = "";
    String card4Name = "";
    String card5Name = "";
    
    Image twoClubs = new Image("cardImgs/2_of_clubs.png");
    Image tenClubs = new Image("cardImgs/10_of_clubs.png");
    Image tenDiamonds = new Image("cardImgs/10_of_diamonds.png");
    Image tenHearts = new Image("cardImgs/10_of_hearts.png");
    Image tenSpades = new Image("cardImgs/10_of_spades.png");
    Image twoDiamonds = new Image("cardImgs/2_of_diamonds.png");
    Image twoHearts = new Image("cardImgs/2_of_hearts.png");
    Image twoSpades = new Image("cardImgs/2_of_spades.png");
    Image threeClubs = new Image("cardImgs/3_of_clubs.png");
    Image threeDiamonds = new Image("cardImgs/3_of_diamonds.png");
    Image threeHearts = new Image("cardImgs/3_of_hearts.png");
    Image threeSpades = new Image("cardImgs/3_of_spades.png");
    Image fourClubs = new Image("cardImgs/4_of_clubs.png");
    Image fourDiamonds = new Image("cardImgs/4_of_diamonds.png");
    Image fourHearts = new Image("cardImgs/4_of_hearts.png");
    Image fourSpades = new Image("cardImgs/4_of_spades.png");
    Image fiveClubs = new Image("cardImgs/5_of_clubs.png");
    Image fiveDiamonds = new Image("cardImgs/5_of_diamonds.png");
    Image fiveHearts = new Image("cardImgs/5_of_hearts.png");
    Image fiveSpades = new Image("cardImgs/5_of_spades.png");
    Image sixClubs = new Image("cardImgs/6_of_clubs.png");
    Image sixDiamonds = new Image("cardImgs/6_of_diamonds.png");
    Image sixHearts = new Image("cardImgs/6_of_hearts.png");
    Image sixSpades = new Image("cardImgs/6_of_spades.png");
    Image sevenClubs = new Image("cardImgs/7_of_clubs.png");
    Image sevenDiamonds = new Image("cardImgs/7_of_diamonds.png");
    Image sevenHearts = new Image("cardImgs/7_of_hearts.png");
    Image sevenSpades = new Image("cardImgs/7_of_spades.png");
    Image eightClubs = new Image("cardImgs/8_of_clubs.png");
    Image eightDiamonds = new Image("cardImgs/8_of_diamonds.png");
    Image eightHearts = new Image("cardImgs/8_of_hearts.png");
    Image eightSpades = new Image("cardImgs/8_of_spades.png");
    Image nineClubs = new Image("cardImgs/9_of_clubs.png");
    Image nineDiamonds = new Image("cardImgs/9_of_diamonds.png");
    Image nineHearts = new Image("cardImgs/9_of_hearts.png");
    Image nineSpades = new Image("cardImgs/9_of_spades.png");
    Image aceClubs = new Image("cardImgs/ace_of_clubs.png");
    Image aceDiamonds = new Image("cardImgs/ace_of_diamonds.png");
    Image aceHearts = new Image("cardImgs/ace_of_hearts.png");
    Image aceSpades = new Image("cardImgs/ace_of_spades.png");
    Image jackClubs = new Image("cardImgs/jack_of_clubs.png");
    Image jackDiamonds = new Image("cardImgs/jack_of_diamonds.png");
    Image jackHearts = new Image("cardImgs/jack_of_hearts.png");
    Image jackSpades = new Image("cardImgs/jack_of_spades.png");
    Image queenClubs = new Image("cardImgs/queen_of_clubs.png");
    Image queenDiamonds = new Image("cardImgs/queen_of_diamonds.png");
    Image queenHearts = new Image("cardImgs/queen_of_hearts.png");
    Image queenSpades = new Image("cardImgs/queen_of_spades.png");
    Image kingClubs = new Image("cardImgs/king_of_clubs.png");
    Image kingDiamonds = new Image("cardImgs/king_of_diamonds.png");
    Image kingHearts = new Image("cardImgs/king_of_hearts.png");
    Image kingSpades = new Image("cardImgs/king_of_spades.png");
    
    
    public static void main(String[] args) 
    {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		
		final EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event) {
				gc.setFill(Color.ORANGE);
				// TODO Auto-generated method stub
				if(event.getSource() == card1) {
					if(card1Clicked) {
						gc.setFill(Color.GREEN);
						gc.fillRoundRect(5, 625, 156, 220, 8, 8);
						card1Clicked = false;
					} else {
						gc.fillRoundRect(5, 625, 156, 220, 8, 8);
						card1Clicked = true;
					}
				}
				else if(event.getSource() == card2) {
					if(card2Clicked) {
						gc.setFill(Color.GREEN);
						gc.fillRoundRect(165, 625, 156, 220, 8, 8);
						card2Clicked = false;
					} else {
						gc.fillRoundRect(165, 625, 156, 220, 8, 8);
						card2Clicked = true;
					}
				}
				else if(event.getSource() == card3) {
					if(card3Clicked) {
						gc.setFill(Color.GREEN);
						gc.fillRoundRect(325, 625, 156, 220, 8, 8);
						card3Clicked = false;
					} else {
						gc.fillRoundRect(325, 625, 156, 220, 8, 8);
						card3Clicked = true;
					}
				}
				else if(event.getSource() == card4) {
					if(card4Clicked) {
						gc.setFill(Color.GREEN);
						gc.fillRoundRect(485, 625, 156, 220, 8, 8);
						card4Clicked = false;
					} else {
						gc.fillRoundRect(485, 625, 156, 220, 8, 8);
						card4Clicked = true;
					}
				}
				else if(event.getSource() == card5) {
					if(card5Clicked) {
						gc.setFill(Color.GREEN);
						gc.fillRoundRect(645, 625, 156, 220, 8, 8);
						card5Clicked = false;
					} else {
						gc.fillRoundRect(645, 625, 156, 220, 8, 8);
						card5Clicked = true;
					}
				}
			}
		};
		
		card1.setOnAction(myHandler);
		card2.setOnAction(myHandler);
		card3.setOnAction(myHandler);
		card4.setOnAction(myHandler);
		card5.setOnAction(myHandler);
		
		this.giveBackWarning.setVisible(false);
		this.giveBackText.setVisible(false);
		this.window = primaryStage;
		this.window.setMaxHeight(900);
		this.window.setMaxWidth(1200);
        this.window.setTitle("Poker Fanatic!");

        Button playGame = new Button("Play Game!");
        Button playButton = new Button("Play Poker!");
        Button showWin = new Button("See who Won!");
        Button giveBack = new Button("Give Back Cards");
        Button flipCard = new Button("Flip Cards");
        
        // creating screen for title screen.
        this.titleGc.setFill(Color.GREEN);
        this.titleGc.fillRoundRect(0, 0, 1200, 900, 0, 0);
        // create title Text.
        setUpTitleScene();
        

        playGame.setId("playGame");
        playGame.setVisible(true);
        playGame.setLayoutX(550);
        playGame.setLayoutY(500);
        playGame.setMinWidth(40);
        playGame.setMinHeight(40);
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
        	//this.giveBackText.setVisible(true);
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
        	scanPHand();
    		cardsClicked();
    		// Check for an incorrect hand, i.e. where number of cards is equal to 5, or when numOfCards equals 4 and the user does not have an ace.
    		// So the !(not) enveloping the player searchHand calls reads as, if the player does not have any type of ace in their hand.
    		if(this.numberOfCards == 5 || ((this.numberOfCards == 4 && !((this.player.searchHand("Ace", "Clubs") != 5)
					|| (this.player.searchHand("Ace", "Hearts") != 5)
					|| (this.player.searchHand("Ace", "Spades") != 5)
					|| (this.player.searchHand("Ace", "Diamonds") != 5)))))
    		{
    			this.giveBackWarning.setVisible(true);
    		}
    		else
            {
    			cardsClicked();
    			System.out.println(this.listOfCards);
    			giveBack.setVisible(false);
            	initiateGiveBack(0);
            	scanPHand();
        		this.root.getChildren().removeAll(card1, card2, card3, card4, card5);
        		cleanSelectedCards();
            	for(int i = 0; i < 5; i++) {
            		createPCards(player.hand.get(i), i);
            	}
            	flipCard.setVisible(true);
            }
        });
        

        flipCard.setVisible(false);
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
			//will not be needed due to the 	this.giveBackWarning.setVisible(false);
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
        	this.root.getChildren().removeAll(card1, card2, card3, card4, card5);
        	printWinner();
        	playButton.setVisible(true);
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
		this.titleScene.getStylesheets().add(css);
		
		// add scene to window.
        this.window.setScene(this.titleScene);
        this.gc.setFill(Color.GREEN);
        this.window.show();
		return;
	}
	
	/*
	 * setUpTitleScene() : This method adds the game image to the title scene
	 * this method can also be used to customize the title scene in more detail.
	 */
	private void setUpTitleScene()
	{
		this.titleGc.drawImage(new Image("title.png"), 225, 300);
		return;
	}
	
	/*
	 * cleanSelectedCards() : This method is called after giveBack has been clicked in order to clean up the "selected card"
	 * look.
	 */
	private void cleanSelectedCards()
	{
		gc.setFill(Color.GREEN);
		gc.fillRoundRect(5, 625, 156, 220, 8, 8);
		gc.fillRoundRect(165, 625, 156, 220, 8, 8);
		gc.fillRoundRect(325, 625, 156, 220, 8, 8);
		gc.fillRoundRect(485, 625, 156, 220, 8, 8);
		gc.fillRoundRect(645, 625, 156, 220, 8, 8);
	}
	
	/*
	 * scanPHand() : This method scans the players hand then attaches the name of each card to a global variable that is then used with
	 * createPHand().
	 */
	private void scanPHand()
	{
		this.card1Name = (player.hand.get(0).getRank() + " " + player.hand.get(0).getSuit());
		this.card2Name = (player.hand.get(1).getRank() + " " + player.hand.get(1).getSuit());
		this.card3Name = (player.hand.get(2).getRank() + " " + player.hand.get(2).getSuit());
		this.card4Name = (player.hand.get(3).getRank() + " " + player.hand.get(3).getSuit());
		this.card5Name = (player.hand.get(4).getRank() + " " + player.hand.get(4).getSuit());
		return;
	}
	
	/*
	 * dealCards(): for creation sake only make this accommodate two players.
	 * This Inititally draws the cards.
	 */
	public void dealCards()
	{
        // Picture of Deck
        this.gc.drawImage(cardImage, 540, 350, 150, 200);
		// j is used for spacing for Computer Card's X Coordinate
		int j = 1040;
		// m is used for spacing for Player Card's X Coordinate
		int m = 10;
		
		// Set the Players hand.
		for(int i = 0; i < 5; i++)
		{
			// assign to hand
			this.player.setCard(theDeck.get(i));
			// Testing : System.out.println("Cards being set to hand:" + player.hand.get(i).getRank() + " " + player.hand.get(i).getSuit());
			
			// draw cards to canvas
			createPCards(theDeck.get(i), i);
			m = m + 160;
		}

		// remove cards dealt to player
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
	}
	
	public Image findCard(Card card)
	{
		Image cardImg = null;
		String cardName = card.getRank() + card.getSuit();
		switch(cardName) {
		case "2Clubs": cardImg = this.twoClubs; 
		break;
		case "2Diamonds": cardImg = this.twoDiamonds; 
		break;
		case "2Hearts": cardImg = this.twoHearts; 
		break;
		case "2Spades": cardImg = this.twoSpades; 
		break;
		case "3Clubs": cardImg = this.threeClubs; 
		break;
		case "3Diamonds": cardImg = this.threeDiamonds; 
		break;
		case "3Hearts": cardImg = this.threeHearts; 
		break;
		case "3Spades": cardImg = this.threeSpades; 
		break;
		case "4Clubs": cardImg = this.fourClubs; 
		break;
		case "4Diamonds": cardImg = this.fourDiamonds; 
		break;
		case "4Hearts": cardImg = this.fourHearts; 
		break;
		case "4Spades": cardImg = this.fourSpades; 
		break;
		case "5Clubs": cardImg = this.fiveClubs; 
		break;
		case "5Diamonds": cardImg = this.fiveDiamonds; 
		break;
		case "5Hearts": cardImg = this.fiveHearts; 
		break;
		case "5Spades": cardImg = this.fiveSpades; 
		break;
		case "6Clubs": cardImg = this.sixClubs; 
		break;
		case "6Diamonds": cardImg = this.sixDiamonds; 
		break;
		case "6Hearts": cardImg = this.sixHearts; 
		break;
		case "6Spades": cardImg = this.sixSpades; 
		break;
		case "7Clubs": cardImg = this.sevenClubs; 
		break;
		case "7Diamonds": cardImg = this.sevenDiamonds; 
		break;
		case "7Hearts": cardImg = this.sevenHearts; 
		break;
		case "7Spades": cardImg = this.sevenSpades; 
		break;
		case "8Clubs": cardImg = this.eightClubs; 
		break;
		case "8Hearts": cardImg = this.eightHearts; 
		break;
		case "8Spades": cardImg = this.eightSpades; 
		break;
		case "8Diamonds": cardImg = this.eightDiamonds; 
		break;
		case "9Clubs": cardImg = this.nineClubs; 
		break;
		case "9Diamonds": cardImg = this.nineDiamonds; 
		break;
		case "9Hearts": cardImg = this.nineHearts; 
		break;
		case "9Spades": cardImg = this.nineSpades; 
		break;
		case "10Clubs": cardImg = this.tenClubs; 
		break;
		case "10Diamonds": cardImg = this.tenDiamonds; 
		break;
		case "10Hearts": cardImg = this.tenHearts; 
		break;
		case "10Spades": cardImg = this.tenSpades; 
		break;
		case "JackClubs": cardImg = this.jackClubs; 
		break;
		case "JackDiamonds": cardImg = this.jackDiamonds; 
		break;
		case "JackHearts": cardImg = this.jackHearts; 
		break;
		case "JackSpades": cardImg = this.jackSpades; 
		break;
		case "QueenClubs": cardImg = this.queenClubs; 
		break;
		case "QueenDiamonds": cardImg = this.queenDiamonds; 
		break;
		case "QueenHearts": cardImg = this.queenHearts; 
		break;
		case "QueenSpades": cardImg = this.queenSpades; 
		break;
		case "KingClubs": cardImg = this.kingClubs; 
		break;
		case "KingDiamonds": cardImg = this.kingDiamonds; 
		break;
		case "KingHearts": cardImg = this.kingHearts; 
		break;
		case "KingSpades": cardImg = this.kingSpades; 
		break;
		case "AceClubs": cardImg = this.aceClubs; 
		break;
		case "AceDiamonds": cardImg = this.aceDiamonds; 
		break;
		case "AceHearts": cardImg = this.aceHearts; 
		break;
		case "AceSpades": cardImg = this.aceSpades; 
		break;
		}
		
		return cardImg;
	}
	
	
	/*
	 * createCards: Card, and i. This function takes in the card in the players hand along with the index to which card this is in
	 * the players hand. from there the card is passed through the findCard() method which finds the image associated with that card.
	 * then depending on the index of the card, they are all placed on the playing table in correct order.
	 */
	public void createPCards(Card card, int i)
	{
		//TODO
		Image cardImg = findCard(card);
		ImageView img = new ImageView(cardImg);
		img.setFitWidth(130);
		//img.setSmooth(true);
		img.setFitHeight(200);
		//img.setCache(true);
		
		if(i == 0) {
			this.card1.setGraphic(img);
			this.card1.setLayoutX(10);
			this.card1.setLayoutY(630);
			this.root.getChildren().add(card1);
		}
		if(i == 1) {
			this.card2.setGraphic(img);
			this.card2.setLayoutX(170);
			this.card2.setLayoutY(630);
			this.root.getChildren().add(card2);
		}
		if(i == 2) {
			this.card3.setGraphic(img);
			this.card3.setLayoutX(330);
			this.card3.setLayoutY(630);
			this.root.getChildren().add(card3);
		}
		if(i == 3) {
			this.card4.setGraphic(img);
			this.card4.setLayoutX(490);
			this.card4.setLayoutY(630);
			this.root.getChildren().add(card4);
		}
		if(i == 4) {
			this.card5.setGraphic(img);
			this.card5.setLayoutX(650);
			this.card5.setLayoutY(630);
			this.root.getChildren().add(card5);
		}
		return;
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
	 * cardsClicked() : scans the players cards to see which booleans are true then created a list of clicked cards.
	 * the list of cards needs to be rank + space + suit.
	 * using scanPHand will collect the names of each card in a players hand then i can just add to the list the card names.
	 * scan P Hand will be ran before cardsClicked in order to know whats in the players hand first.
	 */
	private void cardsClicked()
	{
		this.numberOfCards = 0;
		this.listOfCards = "";
		//String list = "";
		if(card1Clicked)
		{
			numberOfCards++;
			this.listOfCards += card1Name + "\n";
		}
		if(card2Clicked)
		{
			numberOfCards++;
			this.listOfCards += card2Name + "\n";
		}
		if(card3Clicked)
		{
			numberOfCards++;
			this.listOfCards += card3Name+ "\n";
		}
		if(card4Clicked)
		{
			numberOfCards++;
			this.listOfCards += card4Name+ "\n";
		}
		if(card5Clicked)
		{
			numberOfCards++;
			this.listOfCards += card5Name+ "\n";
		}
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
	 */
	public void initiateGiveBack(int person)
	{
		if(person == 0)
		{
			Scanner keyboard = new Scanner(this.listOfCards);
			int numOfCards = this.numberOfCards;
			
			//Testing numOfCards
			//System.out.println(numOfCards);
			//TODO TESTING CHECK FOR IF CARDCLICKED NEEDS TO BE CALLED AGAIN WHEN USER ENTERS 5 CARDS AS NUMOFCARDS
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
				}
				for(int i = 0; i < numOfCards; i++)
				{
					String rank = theDeck.get(i).getRank();
					String suit = theDeck.get(i).getSuit();
					Card newCard = new Card(suit, rank);
					this.player.setCard(newCard);
				}
				for(int i = 0; i < numOfCards; i++)
				{
					this.theDeck.remove(0);
				}
			}
			if(numOfCards < 4 && numOfCards != 0)
			{
				for(int i = 0; i < numOfCards; i++)
				{
					String rank = keyboard.next();
					String suit = keyboard.next();
					int cNum = player.searchHand(rank, suit);
					this.player.giveBack(cNum);
				}
				for(int i = 0; i < numOfCards; i++)
				{
					String rank = this.theDeck.get(i).getRank();
					String suit = this.theDeck.get(i).getSuit();
					Card newCard = new Card(suit, rank);
					this.player.setCard(newCard);
				}
				for(int i = 0; i < numOfCards; i++)
				{
					this.theDeck.remove(0);
				}

			}
			keyboard.close();
		}
		else if(person == 1)
		{
			// opponent goes here.
			// Making the opponent smarter is done through here. 
			// You can have a list of buttons like easy medium hard then check to see which one is clicked.
			int numOfCards = opponent.think(0);
			for(int i = 0; i < numOfCards; i++)
			{
				this.opponent.setCard(this.theDeck.get(i));
			}
			for(int i = 0; i < numOfCards; i++)
			{
				this.theDeck.remove(0);
			}

		}
		return;
	}
	
	/*
	 * createOHand() : This method will scan the opponents final hand then draw them to the canvas.
	 * Maybe to tackle this problem i should create a third scene as the winning scene. this scnee could be altered in all types of ways possibly. 
	 */
	private void createOHand(Card card, int i)
	{
		this.gc.setFill(Color.GREEN);
		
		//TODO scan the opponent
		Image cardImg = findCard(card);
		ImageView img = new ImageView(cardImg);

		
		if(i == 0) {
			this.gc.fillRoundRect(1040, 8, 150, 200, 10, 10);
			this.gc.drawImage(cardImg, 1040, 8, 150, 200);

		}
		if(i == 1) {
			this.gc.fillRoundRect(885, 8, 150, 200, 10, 10);
			this.gc.drawImage(cardImg, 885, 8, 150, 200);

		}
		if(i == 2) {
			this.gc.fillRoundRect(730, 8, 150, 200, 10, 10);
			this.gc.drawImage(cardImg, 730, 8, 150, 200);

		}
		if(i == 3) {
			this.gc.fillRoundRect(575, 8, 150, 200, 10, 10);
			this.gc.drawImage(cardImg, 575, 8, 150, 200);
	
		}
		if(i == 4) {
			this.gc.fillRoundRect(420, 8, 150, 200, 10, 10);
			this.gc.drawImage(cardImg, 420, 8, 150, 200);

		}
	}
	
	private void flipCards()
	{
		// TODO Change flip cards to show the opponents hand as cards.
		// Flip the computers hand.
		int j = 1040;
		
		
		//TODO This will be replaced with a createOCards() to make the cards inside the opponents hand..
		for(int i = 0; i < 5; i++)
		{
			createOHand(this.opponent.hand.get(i), i);
//			//createOHand();
//			
//			// Draw Over Computer Cards With It's Cards.
//			this.gc.setFill(Color.GREEN);
//			this.gc.fillRoundRect(j, 8, 150, 200, 8, 8);
//			this.gc.setFill(Color.BLACK);
//			this.gc.fillText((opponent.hand.get(i).getRank() + " " + opponent.hand.get(i).getSuit()), (j+10), 100);
//			j = j - 155;
			
		}	
		this.player.calcHand();
		this.opponent.calcHand();
		this.gc.setFill(Color.WHITE);
		this.gc.fillText(("Total Player Points: " + player.winPoints), 900, 700);
		this.gc.fillText(("Total Computer Points: " + opponent.winPoints), 175, 150);
		this.gc.fillText(("Highest Ranked Player Card: " + player.highCard), 900, 750);
		this.gc.fillText(("Highest Ranked Computer Card: " + opponent.highCard), 175, 200);

			
		return;
	}
	
	private void launchPoker()
	{
		// Poker Variables
		this.deck = new Deck();
		this.theDeck = deck.getDeck();
		this.player = new Player();
		this.opponent = new Opponent();
		
		this.card1Clicked = false;
		this.card2Clicked = false;
		this.card3Clicked = false;
		this.card4Clicked = false;
		this.card5Clicked = false;
		    
		this.card1Name = "";
		this.card2Name = "";
		this.card3Name = "";
		this.card4Name = "";
		this.card5Name = "";
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