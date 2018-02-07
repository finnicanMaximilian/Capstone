

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
 


public class GuiTest extends Application 
{  

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
        
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        gc.setFill(Color.GREEN);
        primaryStage.show();
		return;
	}
	
	public void drawBoard(GraphicsContext gc)
	{
        gc.setFill(Color.GREEN);
        gc.fillRoundRect(0, 0, 1200, 900, 0, 0);
        Image cardImage = new Image("cheetah-card.gif");
        gc.drawImage(cardImage, 1040, 8, 150, 200);
        gc.drawImage(cardImage, 890, 8, 150, 200);
        gc.drawImage(cardImage, 735, 8, 150, 200);
        gc.drawImage(cardImage, 580, 8, 150, 200);
        gc.drawImage(cardImage, 425, 8, 150, 200);
        
        gc.drawImage(cardImage, 540, 350, 150, 200);


	}

}
