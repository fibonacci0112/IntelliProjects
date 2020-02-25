package sample;

public class Controller
{

    Model model = new Model(69,12,31);
    PieChartStage pcs = new PieChartStage(model);
    Slider1 s1 = new Slider1(model);
    Slider2 s2 = new Slider2(model);


    public Controller()
    {
        model.addObserver(s1);
        model.addObserver(s2);
        model.addObserver(pcs);
        s1.getSlider().valueProperty().addListener((observable, oldValue, newValue) ->
        {
            model.setPar1(newValue.doubleValue());
            model.changeSomething();
        });

        s2.getSlider().valueProperty().addListener((observable, oldValue, newValue) ->
        {
            model.setPar2(newValue.doubleValue());
            model.changeSomething();
        });
    }
}



