package com.company;

public class Main
{
    public static void main(String[] args)
    {
        Einzahl e1 = new Einzahl();
        Einzahl e2 = new Einzahl();
        Einzahl e3 = new Einzahl();
        e1.start();
        e2.start();
        e3.start();
        while (e3.isAlive())
        {}
        System.out.println("hallo");
    }
}
