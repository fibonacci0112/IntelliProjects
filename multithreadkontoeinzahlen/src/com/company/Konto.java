package com.company;

/**
 * Created by Nico on 20.06.2017.
 */

public class Konto
{
    private static volatile float kontostand = 0;

    public void einzahlen(float geld, String name)
    {
        synchronized (this)
        {
            kontostand += geld;
        }
            System.out.println(name + ": " + geld);
            System.out.println("neuer Kontostand: " + kontostand);
    }
}
