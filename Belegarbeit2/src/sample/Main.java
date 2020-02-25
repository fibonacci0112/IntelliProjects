package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application
{

    private GraphicsContext gc;
    private Tetris tetris;


    @Override
    public void start(Stage s) throws Exception
    {
        s.setTitle("Tetris");
        Group root = new Group();
        Canvas canvas = new Canvas(300, 450);
        gc = canvas.getGraphicsContext2D();
        tetris = new Tetris();
        timerStart(s);
        maleInSpielfeld(s);
        root.getChildren().add(canvas);
        s.setScene(new Scene(root));
        s.show();


    }

    private void maleInSpielfeld(final Stage s)
    {
        s.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode() == KeyCode.UP)
                {
                    //rotieren
                }

                if (keyEvent.getCode() == KeyCode.LEFT)
                {
                    tetris.blockZurSeite(-1);

                }

                if (keyEvent.getCode() == KeyCode.RIGHT)
                {
                    tetris.blockZurSeite(1);

                }

                if (keyEvent.getCode() == KeyCode.DOWN)
                {
                    tetris.blockNachUnten();

                }
                draw(s);
            }
        });
    }


    public void draw(Stage s)
    {
        gc.clearRect(0,0,500,500);
        for (int i = 0; i < tetris.getSpielfeld().length; i++)
        {
            for (int j = 0; j < tetris.getSpielfeld()[i].length; j++)
            {
                if(tetris.getSpielfeld()[i][j] == 1)
                {
                    gc.clearRect((j * 30) + 1, (i * 30) + 1, 28, 28);
                    gc.setFill(Color.RED);
                    gc.fillRect((j * 30) + 1, (i * 30) + 1, 28, 28);
                }
                if(tetris.getSpielfeld()[i][j] == 2)
                {
                    gc.setFill(Color.BLUE);
                    gc.fillRect((j * 30) + 1, (i * 30) + 1, 28, 28);
                }
                System.out.print(tetris.getSpielfeld()[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    public void timerStart(final Stage s)
    {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000/tetris.getLevel()), new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent ae)
            {
                draw(s);
                tetris.blockNachUnten();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
