import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
 


public class GuiTest extends Application implements EventHandler<ActionEvent>
{  
    Image cardImage = new Image("cheetah-card.gif");
 
	Poker game = new Poker();
    
    
	
    public static void main(String[] args) 
    {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		// TODO Auto-generated method stub
        primaryStage.setTitle("Poker Fanatic!");
        Group root = new Group();
        Canvas canvas = new Canvas(1200, 900);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        drawBoard(gc);
        dealCard(gc);
        
        // assemble players hand
        // Player Hand
        Button Card1 = new Button();
        Card1.setText("Card1");
        Card1.setAlignment(Pos.BOTTOM_LEFT);
        Button card2 = new Button();
        Button card3 = new Button();
        Button card4 = new Button();
        Button card5 = new Button();
        root.getChildren().add(Card1);
 
        
        
        
 
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        gc.setFill(Color.GREEN);
        primaryStage.show();
		return;
	}
	
	@Override
	public void handle(ActionEvent event)
	{
		// this is what happens when buttons are clicked.
		// if(event.getSource()==card1) {
			// num of cards to give back + 1.
		//}
		return;
	}
	
	public void drawBoard(GraphicsContext gc)
	{
        gc.setFill(Color.GREEN);
        gc.fillRoundRect(0, 0, 1200, 900, 0, 0);


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
        gc.drawImage(cardImage, 1040, 8, 150, 200);
        gc.drawImage(cardImage, 885, 8, 150, 200);
        gc.drawImage(cardImage, 730, 8, 150, 200);
        gc.drawImage(cardImage, 575, 8, 150, 200);
        gc.drawImage(cardImage, 420, 8, 150, 200);
        

	}

}