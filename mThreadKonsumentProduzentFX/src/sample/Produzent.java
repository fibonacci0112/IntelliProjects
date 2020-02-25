package sample;

import java.util.Random;

/**
 * Created by Nico on 21.06.2017.
 */
public class Produzent extends Thread
{
    Random r = new Random();



    public void run()
    {
        try
        {
            while (true)
            {
                    Vektor v = new Vektor();
                    v.setVektor(r.nextInt(60));
                    Thread.sleep(1000);
            }
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}

