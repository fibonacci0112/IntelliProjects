package com.company;

public class Main
{

    public static void main(String[] args)
    {
        Hip h1 = new Hip();
        Hop h2 = new Hop();


        System.out.println(h1.getName());
        System.out.println(h1.getPriority());
        System.out.println(h2.getName());
        System.out.println(h2.getPriority());
        h1.start();
        h2.start();

    }
}
