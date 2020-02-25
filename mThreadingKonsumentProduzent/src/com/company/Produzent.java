package com.company;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Nico on 21.06.2017.
 */
public class Produzent extends Thread
{
    Random r = new Random();
    public boolean stop = false;

    public void run()
    {
        try
        {
            while (true)
            {
                while (stop)
                {
                    wait();
                }
                System.out.println("pr");
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

