package com.company;

import java.util.ArrayList;

/**
 * Created by Nico on 27.06.2017.
 */
public class Lager
{
    private ArrayList<Integer> lager = new ArrayList();
    private boolean leer;


    public synchronized void produce(int zahl) throws InterruptedException
    {
        this.lager.add(zahl);
        leer = false;
        notify();
    }

    public synchronized int consume() throws InterruptedException
    {
        while (leer)
        {
            wait();
        }

        int i = this.lager.get(0);
        lager.remove(0);
        if (lager.size() == 0)
        {
            leer = true;
        }
        return i;
    }
}
