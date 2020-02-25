package com.example.uebung1;

/**
 * Created by Nico on 21.10.2016.
 */
public class Uebung1
{
    public static void main(String[] args)
    {
        Arrayelement a = new Arrayelement();
        byte[] b = {1,1,2,2,3,3};
        byte[] c = a.haeufigstesElem(b);

        for (byte i : c)
        {
            System.out.println(i);
        }
    }
}
