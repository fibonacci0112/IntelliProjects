package sample;

import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Nico on 03.07.2017.
 */
public class PieChartStage extends Stage implements Observer
{

    private PieChart pi;
    private PieChart.Data slice1;
    private PieChart.Data slice2;
    private PieChart.Data slice3;
    Model model;


    public PieChartStage(Model model)
    {
        this.setTitle("Pie Chart");
        pi = new PieChart();
        this.model=model;
        slice1 = new PieChart.Data("Param1", model.getPar1());
        slice2 = new PieChart.Data("Param2", model.getPar2());
        slice3 = new PieChart.Data("Param3", model.getPar3());
        pi.getData().add(slice1);
        pi.getData().add(slice2);
        pi.getData().add(slice3);
        VBox vbox = new VBox(pi);
        Scene scene = new Scene(vbox, 400, 200);
        this.setScene(scene);
        this.setHeight(500);
        this.setWidth(500);
        this.show();
    }

    @Override
    public void update(Observable o, Object arg)
    {
        slice1.setPieValue(model.getPar1());
        slice2.setPieValue(model.getPar2());
        slice3.setPieValue(model.getPar3());
    }
}
