package sample;

/**
 * Created by Nico on 21.06.2017.
 */
public class Konsument extends Thread
{
    int zahl;

    public void run()
    {
        try
        {
            while (true)
            {

                Vektor v = new Vektor();
                zahl = v.getVektor();
                if (zahl == -1)
                {
                    //hier muss thread warten
                } else
                {
                    for (int i = 0; i < zahl; i++)
                    {
                        System.out.print("*");
                    }
                    System.out.println();
                }
                Thread.sleep(1000);
            }
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}

