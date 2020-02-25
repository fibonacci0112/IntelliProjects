package sample;
import javafx.application.Application;
import javafx.stage.Stage;

public class PieChartTest extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
         new Controller();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
