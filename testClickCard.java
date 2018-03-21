import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
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
		
		giveBack.setOnAction(e -> {
			//TODO
		});
		
		Canvas layout = new Canvas(1200, 900);
		Group root = new Group();
		root.getChildren().addAll(card1, card2, card3, card4, card5, giveBack, layout);
		Scene scene = new Scene(root);
		window.setScene(scene);
		window.show();
	}
	
	
	/*
	 * dealCards() : Simulates the hand being delt.
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
		
		card2.setId("card2");
		card2.setLayoutX(70);
		card2.setLayoutY(800);
		card2.setMinHeight(50);
		card2.setMinWidth(50);
		
		card3.setId("card3");
		card3.setLayoutX(130);
		card3.setLayoutY(800);
		card3.setMinHeight(50);
		card3.setMinWidth(50);
		
		card4.setId("card4");
		card4.setLayoutX(190);
		card4.setLayoutY(800);
		card4.setMinHeight(50);
		card4.setMinWidth(50);
		
		card5.setId("card5");
		card5.setLayoutX(250);
		card5.setLayoutY(800);
		card5.setMinHeight(50);
		card5.setMinWidth(50);
		
		return;
	}

}
