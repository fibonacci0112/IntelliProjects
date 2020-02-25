package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application; //semaphor
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class TrafficLight extends Application
{

    private Button red;
    private Button yellow;
    private Button green;
    private StackPane sp;
    private Canvas canv;
    private GraphicsContext gc;
    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        canv = new Canvas(30, 90);
        gc = canv.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canv.getWidth(), canv.getHeight());
        sp = new StackPane();
        primaryStage.setWidth(200);
        primaryStage.setHeight(250);
        primaryStage.setTitle("Ampel");
        red = new Button("rot");
        yellow = new Button("blink");
        green = new Button("grün");
        sp.getChildren().addAll(red, yellow, green, canv);
        sp.setAlignment(red, Pos.BOTTOM_LEFT);
        sp.setAlignment(yellow, Pos.BOTTOM_CENTER);
        sp.setAlignment(green, Pos.BOTTOM_RIGHT);
        sp.setAlignment(canv, Pos.CENTER);
        Controller c = new Controller(this);
        red.setOnAction(c);
        yellow.setOnAction(c);
        green.setOnAction(c);
        primaryStage.setScene(new Scene(sp));
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event ->
        {
            if (gc.getFill() == Color.BLACK)
            {
                gc.setFill(Color.YELLOW);
                gc.fillOval(1, 30, 28, 28);
            } else
            {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, canv.getWidth(), canv.getHeight());
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        this.red();
        primaryStage.show();
    }

    public void red()
    {
        timeline.stop();
        if (gc.getFill() == Color.GOLD)
        {
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
            pauseTransition.setOnFinished(event ->
            {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, canv.getWidth(), canv.getHeight());
                gc.setFill(Color.RED);
                gc.fillOval(1, 0, 28, 28);
            });
            pauseTransition.play();
        } else
        {
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, canv.getWidth(), canv.getHeight());
            gc.setFill(Color.RED);
            gc.fillOval(1, 0, 28, 28);
        }
    }

    public void yellow()
    {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canv.getWidth(), canv.getHeight());
        gc.setFill(Color.YELLOW);
        gc.fillOval(1, 30, 28, 28);
        gc.setFill(Color.GOLD);

    }

    public void green()
    {
        timeline.stop();
        if (gc.getFill() == Color.RED)
        {
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
            pauseTransition.setOnFinished(event ->
            {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, canv.getWidth(), canv.getHeight());
                gc.setFill(Color.GREEN);
                gc.fillOval(1, 60, 28, 28);
            });
            pauseTransition.play();
        } else
        {
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, canv.getWidth(), canv.getHeight());
            gc.setFill(Color.GREEN);
            gc.fillOval(1, 60, 28, 28);
        }

    }

    public void redYellow()
    {
        red();
        gc.setFill(Color.YELLOW);
        gc.fillOval(1, 30, 28, 28);
        gc.setFill(Color.RED);
    }

    public void blink()
    {
        timeline.play();
        gc.setFill(Color.YELLOW);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
