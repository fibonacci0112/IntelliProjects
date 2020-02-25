package sample;


/**
 * Created by user on 31.03.16.
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class MoveIt extends Application
{

    private List<MovingEllipse> ovals = new ArrayList<MovingEllipse>();
    private Group group = new Group();  //root node for the play window
    private final Random random = new Random();
    private final Button bt = new Button("Restart");
    private final Pane pane = new Pane(group);


    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        final BorderPane borderPane;
        final Scene scene;

        primaryStage.setTitle("Ellipse & AnimationTimer Example");
        //the first moving circle
        pane.setOnMouseClicked(event ->
        {
            for (int i = 0; i < 100; i++)
            {
                generate(Color.YELLOW, event.getSceneX(), event.getSceneY() - bt.getHeight(), 10.0, false);
            }
        });
        //generate(Color.GREENYELLOW, 150.0, 150.0, 17.0, true); //the second moving circle
        //create a pane for a group with all moving objects
        pane.setPrefSize(500, 500);
        pane.setStyle("-fx-background-color: blue;");
        //create a restart button
        bt.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                ovals.clear();              //clear List with references
                group.getChildren().clear();//clear all moving objects
                //generate(Color.RED, 100.0, 100.0, 35.0, true); //the new first moving circle
            }
        });
        //create the main window lauout
        borderPane = new BorderPane();
        borderPane.setTop(bt);
        borderPane.setCenter(pane);
        scene = new Scene(borderPane, 500, 500, Color.ANTIQUEWHITE);
        primaryStage.setScene(scene);
        //set pane autoresisable
        scene.widthProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth)
            {
                System.out.println("Width: " + newSceneWidth);
                pane.setPrefWidth(scene.getWidth());
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight)
            {
                System.out.println("Height: " + newSceneHeight);
                pane.setPrefHeight(scene.getHeight());
            }
        });
        primaryStage.show();

        new AnimationTimer()
        { //animate all circles
            @Override
            public void handle(long now)
            {
                for (MovingEllipse e : ovals)
                {
                    e.getEllipse().setCenterX(e.getEllipse().getCenterX() + e.getStepX());
                    e.getEllipse().setCenterY(e.getEllipse().getCenterY() + e.getStepY() + e.getGravity());
                    if ((e.getEllipse().getCenterX() + (e.getEllipse().getRadiusX())) > pane.getWidth() || (e.getEllipse().getCenterX() - (e.getEllipse().getRadiusX())) < 0)
                    {
                        e.setStepX(e.getStepX() * -0.9);
                    }
                    if ((e.getEllipse().getCenterY() - (e.getEllipse().getRadiusY())) < 0)
                    {
                        e.setStepY(e.getStepY() * -0.9);
                    }
                    if (e.getEllipse().getCenterY() + (e.getEllipse().getRadiusY()) > pane.getHeight())
                    {
                        e.setStepY(0);
                        e.setStepX(0);
                        e.setGravity(0);
                    }


                }
                try
                {
                    Thread.sleep(10);
                } catch (InterruptedException e)
                {
                }
            }
        }.start();
    }

    private void generate(Color c, Double x, Double y, Double radius, boolean clickable)
    {

        Ellipse localCircle = new Ellipse(x, y, radius, radius);
        localCircle.setStrokeWidth(3);
        localCircle.setStroke(Color.BLACK);
        localCircle.setFill(c);
        if (clickable)
        {  //add event handler
            pane.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent e)
                { //create one more moving circle
                    for (int i = 0; i < 100; i++)
                    {
                        generate(Color.YELLOW, e.getSceneX(), e.getSceneY() - bt.getHeight(), 10.0, false);
                    }

                }
            });
        }
        ovals.add(new MovingEllipse(localCircle, random.nextDouble() - 0.5, random.nextDouble() - 0.5));
        group.getChildren().add(localCircle); //add obect to the group

    }
}
