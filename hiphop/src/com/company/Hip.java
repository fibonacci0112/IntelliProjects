package com.company;

/**
 * Created by Nico on 20.06.2017.
 */
public class Hip extends Thread
{
    public void run()
    {
        while (true)
        {
            System.out.println("Hip");
            for (int i = 0; i < 1000; i++) ;
        }
    }
}
