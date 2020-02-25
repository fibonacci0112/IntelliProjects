package sample;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Konsument k = new Konsument();
        Produzent p = new Produzent();
        Vektor v = new Vektor();
        p.start();
        k.start();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
