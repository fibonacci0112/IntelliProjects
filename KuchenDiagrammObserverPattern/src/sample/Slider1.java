package sample;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Observable;
import java.util.Observer;

public class Slider1 extends Stage implements Observer
{

    private Slider slider;
    private Model model;

public Slider1(Model model)
{
    this.model=model;
    slider = new Slider();
    slider.setMin(0);
    slider.setMax(100);
    slider.setValue(40);
    slider.setShowTickMarks(true);
    slider.setMajorTickUnit(50);
    slider.setMinorTickCount(5);
    slider.setBlockIncrement(10);
    this.setTitle("Param1");
    VBox vbox = new VBox(slider);
    Scene scene = new Scene(vbox, 400, 200);
    this.setScene(scene);
    this.setHeight(100);
    this.setWidth(300);
    this.show();
}

    public Slider getSlider()
    {
        return slider;
    }

    //public void setSlider(Slider slider)
    {
       // this.slider = slider;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        slider.setValue(model.getPar1());
    }

}
