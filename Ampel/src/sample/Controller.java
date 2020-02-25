package sample;

import javafx.event.Event;
import javafx.event.EventHandler;

public class Controller implements EventHandler
{
    private TrafficLight view;
    private Model model;

    public Controller(TrafficLight view)
    {
        this.view = view;
        this.model = new Model();
    }

    @Override
    public void handle(Event event)
    {
        try
        {
            if (event.getSource().toString().contains("rot"))
            {
                if (model.isgreen())
                {
                    view.yellow();
                    view.red();
                    model.setIsred(true);
                    model.setIsyellow(false);
                    model.setIsgreen(false);
                }
                if (!model.isred())
                {
                    view.red();
                    model.setIsred(false);
                    model.setIsyellow(false);
                    model.setIsgreen(true);
                }

            } else if (event.getSource().toString().contains("blink"))
            {
                view.blink();
                model.setIsred(false);
                model.setIsyellow(false);
                model.setIsgreen(false);

            } else if (event.getSource().toString().contains("grün"))
            {
                if (model.isred())
                {
                    view.redYellow();
                    view.green();
                    model.setIsred(true);
                    model.setIsyellow(true);
                    model.setIsgreen(false);
                }
                if (!model.isgreen())
                {
                    view.green();
                    model.setIsred(false);
                    model.setIsyellow(false);
                    model.setIsgreen(true);
                }

            }
        } catch (Exception e) {System.out.println(e.toString());}
    }
}

