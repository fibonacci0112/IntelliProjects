package com.company;

/**
 * Created by Nico on 20.06.2017.
 */
public class Hop implements Runnable
{
    public void run()
    {
        while (true)
        {
            System.out.println("Hop");
            for (int i = 0; i < 3000000; i++){}
        }
    }
}
