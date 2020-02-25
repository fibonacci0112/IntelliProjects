package com.company;

import java.util.Random;

/**
 * Created by Nico on 27.06.2017.
 */
public class Produzent extends Thread
{
    private Lager lager;
    private Random r;
    private Pipo p;

    public Produzent(Pipo p)
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
                p.put(r.nextInt(60));
                Thread.sleep(1000 + r.nextInt(500));
            }
        } catch (Exception e) {}
    }
}
