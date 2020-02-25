package sample;

import com.thoughtworks.xstream.mapper.Mapper;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.w3c.dom.events.KeyboardEvent;

public class Main extends Application {

    int x=0;
    int y=0;


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        primaryStage.setTitle("new test");
        primaryStage.setScene(new Scene(root, 600 , 500));

        Canvas c = new Canvas(500,500);
        root.getChildren().add(c);



        GraphicsContext gc = c.getGraphicsContext2D();


        primaryStage.show();

        c.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
        {
            gc.clearRect(0,0,c.getHeight(),c.getWidth());
            gc.setFill( Color.RED );
            gc.fillRect(x,y,18,18);
            gc.fillRect(x,y+20,18,18);
            gc.fillRect(x+20,y,18,18);
            gc.fillRect(x+20,y+20,18,18);
            y+=40;
            if(y+40>c.getHeight())
            {
                y=0;
                x+=40;
            }

        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
