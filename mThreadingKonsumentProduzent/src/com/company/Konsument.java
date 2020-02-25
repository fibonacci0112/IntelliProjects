package com.company;

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
                for (int i = 0; i < zahl; i++)
                {
                    System.out.print("*");
                }
                System.out.println();
                Thread.sleep(1000);
            }
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}

