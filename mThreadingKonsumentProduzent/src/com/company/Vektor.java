package com.company;

import java.util.ArrayList;

/**
 * Created by Nico on 21.06.2017.
 */
public class Vektor
{
    public static ArrayList<Integer> vektor = new ArrayList<>();
    private boolean leer = true;

    public synchronized void setVektor(int zahl) throws InterruptedException
    {
        vektor.add(zahl);
        leer=false;
        System.out.println(leer);
    }

    public synchronized int getVektor() throws InterruptedException
    {
        while(leer)
        {

            //wait();
        }
        int i = vektor.get(0);
        vektor.remove(0);
        if(vektor.size()==0)
        {
            leer=true;
        }
        return i;
    }

    public ArrayList getList()
    {
        return vektor;
    }
}
