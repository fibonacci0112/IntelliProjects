package com.company;

public class Main
{

    public static void main(String[] args)
    {
        Hip h1 = new Hip();
        Hop h2 = new Hop();
        Thread t1 = new Thread(h1);
        Thread t2 = new Thread(h2);

        System.out.println(t1.getName());
        System.out.println(t1.getPriority());
        System.out.println(t2.getName());
        System.out.println(t2.getPriority());
        t1.start();
        t2.start();
    }
}
