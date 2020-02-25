package com.company;

import java.util.Random;

/**
 * Created by Nico on 20.06.2017.
 */
public class Einzahl extends Thread
{
    public void run()
    {
        Konto k= new Konto();
        Random r = new Random();
        long time = this.getId()*100;
        while (true)
        {
            k.einzahlen(r.nextFloat(), this.getName());
            try
            {
                Thread.sleep(time);
            } catch (Exception e)
            {
            }
        }
    }
}
