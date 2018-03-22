import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;



/*
 * Author: Maximilian Finnican
 * Version: 3/21/2018
 * Purpose: testClickCard is being made to simulate what it would be like to click multiple buttons then to notice to which buttons were clicked.
 * Basic Operation = Return Strings of the cards being clicked.
 * Next Operation = Allow a user to "unclick" a card before they press giveBackCards.
 */
public class testClickCard extends Application{
	Button card1;
	Button card2;
	Button card3;
	Button card4;
	Button card5;
	Button giveBack;
	
	Boolean card1Clicked = false;
	Boolean card2Clicked = false;
	Boolean card3Clicked = false;
	Boolean card4Clicked = false;
	Boolean card5Clicked = false;
	
	Stage window;
	// Required to run program.
	public static void main(String[] args)
	{
		// Launch is an Application function.
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		window = primaryStage;
		window.setTitle("Card Sand Box");
		card1 = new Button();
		card2 = new Button();
		card3 = new Button();
		card4 = new Button();
		card5 = new Button();
		giveBack = new Button("Give Back Cards");
		
		organizeLayout();
		
		dealCards();
		
		final EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						if(event.getSource() == card1) {
							if(card1Clicked) {
								card1Clicked = false;
							} else {
								card1Clicked = true;
							}
						}
						else if(event.getSource() == card2) {
							if(card2Clicked) {
								card2Clicked = false;
							} else {
								card2Clicked = true;
							}
						}
						else if(event.getSource() == card3) {
							if(card3Clicked) {
								card3Clicked = false;
							} else {
								card3Clicked = true;
							}
						}
						else if(event.getSource() == card4) {
							if(card4Clicked) {
								card4Clicked = false;
							} else {
								card4Clicked = true;
							}
						}
						else if(event.getSource() == card5) {
							if(card5Clicked) {
								card5Clicked = false;
							} else {
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
		
		giveBack.setOnAction(e -> {
			//TODO
			System.out.println(cardsClicked());

		});
		
		Canvas layout = new Canvas(1200, 900);
		Group root = new Group();
		root.getChildren().addAll(layout, card1, card2, card3, card4, card5, giveBack);
		

		Scene scene = new Scene(root);
		//scene.addEventHandler(MouseEvent.MOUSE_CLICKED, myHandler);
		window.setScene(scene);
		window.show();
	}
	
	
	/*
	 * cardsClicked() : scans the players cards to see which booleans are true then created a list of clicked cards.
	 */
	private String cardsClicked()
	{
		String listOfCards = "";
		if(card1Clicked)
		{
			listOfCards += "Card1\n";
		}
		if(card2Clicked)
		{
			listOfCards += "Card2\n";
		}
		if(card3Clicked)
		{
			listOfCards += "Card3\n";
		}
		if(card4Clicked)
		{
			listOfCards += "Card4\n";
		}
		if(card5Clicked)
		{
			listOfCards += "Card5\n";
		}
		return listOfCards;
	}
	
	/*
	 * dealCards() : Simulates the hand being dealt.
	 */
	private void dealCards()
	{
		card1.setText("Ace Clubs");
		card2.setText("Ace Clubs");
		card3.setText("Ace Clubs");
		card4.setText("Ace Clubs");
		card5.setText("Ace Clubs");
		return;
	}
	
	/*
	 * oranizeLayout() : this is just helping with spacing of everything / setting the id to the buttons.
	 */
	private void organizeLayout()
	{
		card1.setId("card1");
		card1.setLayoutX(10);
		card1.setLayoutY(800);
		card1.setMinHeight(50);
		card1.setMinWidth(50);
		card1.setVisible(true);

		
		card2.setId("card2");
		card2.setLayoutX(70);
		card2.setLayoutY(800);
		card2.setMinHeight(50);
		card2.setMinWidth(50);
		card2.setVisible(true);
		
		card3.setId("card3");
		card3.setLayoutX(130);
		card3.setLayoutY(800);
		card3.setMinHeight(50);
		card3.setMinWidth(50);
		card3.setVisible(true);
		
		card4.setId("card4");
		card4.setLayoutX(190);
		card4.setLayoutY(800);
		card4.setMinHeight(50);
		card4.setMinWidth(50);
		card4.setVisible(true);
		
		card5.setId("card5");
		card5.setLayoutX(250);
		card5.setLayoutY(800);
		card5.setMinHeight(50);
		card5.setMinWidth(50);
		card5.setVisible(true);
		
		giveBack.setId("GiveBack");
		giveBack.setVisible(true);
		return;
	}

}
