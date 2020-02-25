package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import static java.lang.Math.cos;
import static java.lang.StrictMath.sin;

/**
 * Beinhaltet alle Funktionen der Grafischen Oberfläche
 */
public class Main extends Application
{

    private TableView table = new TableView();
    private Button printb = new Button();
    private Button enterb = new Button();
    private Button loadb = new Button();
    private Button saveb = new Button();
    private Button clearb = new Button();
    private Button deleteb = new Button();
    private Group root = new Group();
    private TextField input = new TextField();
    private Canvas canvas;
    private GraphicsContext gc;
    private Graph g;


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Graphen");
        input.setPrefWidth(500);
        input.setLayoutY(10);
        input.setLayoutX(10);

        printb.setText("Print");
        printb.setLayoutX(310);
        printb.setLayoutY(40);

        deleteb.setText("Delete");
        deleteb.setLayoutX(70);
        deleteb.setLayoutY(40);

        enterb.setText("Enter");
        enterb.setLayoutX(10);
        enterb.setLayoutY(40);

        saveb.setText("Save");
        saveb.setLayoutX(130);
        saveb.setLayoutY(40);

        loadb.setText("Load");
        loadb.setLayoutX(190);
        loadb.setLayoutY(40);

        clearb.setText("Clear");
        clearb.setLayoutX(250);
        clearb.setLayoutY(40);


        canvas = new Canvas(600, 520);
        canvas.setLayoutY(80);
        inputHandler(primaryStage);
        primaryStage.setScene(new Scene(root, 600, 600));
        gc = canvas.getGraphicsContext2D();
        root.getChildren().addAll(printb, enterb, loadb, saveb, clearb, deleteb, input, canvas);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 600, 600);
        g = new Graph();
        primaryStage.show();
    }

    /**
     * Verwaltet die Funktion der Buttons
     * @param s
     */
    private void inputHandler(final Stage s)
    {
        enterb.setOnAction(e ->
        {
            if (g.graphBuilder(input.getText()))
            {
                input.clear();
                if (g.searchpath != null)
                {
                    String out="";
                    for (int i = 0; i < g.finalpath.size(); i++)
                    {
                        out+=(g.finalpath.get(i).name + ", ");
                    }
                    gc.fillText(out,10,10);
                    g.searchpath = null;
                }
            }
        });
        deleteb.setOnAction(e -> g.deleteNode(input.getText()));
        printb.setOnAction(e ->
                {
                    drawGraph(gc);
                }
        );
        saveb.setOnAction(e -> g.writeToFile());
        loadb.setOnAction(e -> g.readFromFile());
        clearb.setOnAction(e -> g = new Graph());
    }


    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * Zeichnet nacheinander Kanten und Knoten in Kreisform
     * @param gc
     */
    private void drawGraph(GraphicsContext gc)
    {
        gc.clearRect(0, 0, 600, 600);
        if(g.nodecount>0)
        {
            int count = 0;
            int x, y;
            int x2, y2;
            int length = 100;
            double angle = 0.0;
            double angle_stepsize = (2 * Math.PI) / (double) (g.nodecount);

            while (angle < 2 * Math.PI)
            {
                for (int i = 0; i < g.nodecount; i++)
                {
                    if (g.joints[count][i] > 0)
                    {
                        x = (int) (length * cos(angle - Math.PI / 3));
                        y = (int) (length * sin(angle - Math.PI / 3));
                        if (i == count)
                        {
                            gc.strokeOval(250 + x, 235 + y, 25, 25);
                            gc.setFill(Color.BLACK);
                            gc.fillText(String.valueOf(g.joints[count][i]), 250 + x, 235 + y);
                        } else
                        {
                            x2 = (int) (length * cos(angle_stepsize * i - Math.PI / 3));
                            y2 = (int) (length * sin(angle_stepsize * i - Math.PI / 3));
                            gc.setFill(Color.BLUE);
                            drawArrow(gc, 265 + x, 265 + y, 265 + x2, 265 + y2, g.joints[count][i]);
                            Transform transform = Transform.translate(0, 0);
                            gc.setTransform(new Affine(transform));
                        }
                    }
                }
                angle += angle_stepsize;
                if (count < g.nodecount) { count++;} else {count = 0;}
            }
            angle = 0;
            count = 0;
            while (angle < 2 * Math.PI)
            {
                x = (int) (length * cos(angle - Math.PI / 3));
                y = (int) (length * sin(angle - Math.PI / 3));
                gc.setFill(Color.RED);
                gc.fillOval(250 + x, 250 + y, 30, 30);
                angle += angle_stepsize;
                if (count < g.nodecount) { count++;} else {count = 0;}
            }
            angle = 0;
            count = 0;
            while (angle < 2 * Math.PI)
            {
                x = (int) (length * cos(angle - Math.PI / 3));
                y = (int) (length * sin(angle - Math.PI / 3));
                gc.setFill(Color.BLACK);
                gc.fillText(g.nodes[count].name, 250 + x + 13, 250 + y + 17);
                angle += angle_stepsize;
                if (count < g.nodecount) { count++;} else {count = 0;}
            }
        }
    }

    /**
     * Zeichnet einen Pfeil in Richtung der Kante und setzt den Wert ein
     * @param gc
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param joint
     */
    void drawArrow(GraphicsContext gc, int x1, int y1, int x2, int y2, int joint) {
        gc.setFill(Color.BLACK);

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);

        Transform transform = Transform.translate(x1, y1);
        transform = transform.createConcatenation(Transform.rotate(Math.toDegrees(angle), 0, 0));
        gc.setTransform(new Affine(transform));

        gc.strokeLine(0, 0, len, 0);
        gc.fillPolygon(new double[]{len, len - 20, len - 20, len}, new double[]{0, -5, 5, 0},
                4);
        gc.fillText(String.valueOf(joint),50,0);
    }
}
