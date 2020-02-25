package com.company;


import java.util.Random;

/**
 * Created by Nico on 27.06.2017.
 */
public class Konsument extends Thread
{
    private Lager lager;
    private Random r;
    private Pipo p;

    public Konsument(Pipo p)
    {
        this.p = p;
        r = new Random();

    }

    public void run()
    {
        try
        {
            while (true)
            {
                int zahl = p.get();
                for (int i = 0; i < zahl; i++)
                {
                    System.out.print("*");
                }
                System.out.println();
                Thread.sleep(1000 + r.nextInt(500));
            }
        } catch (Exception e) {System.out.println(e.toString());}
    }
}
