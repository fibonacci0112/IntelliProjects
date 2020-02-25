package com.company;


import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        int bla = 0;
        Konsument k = new Konsument();
        Produzent p = new Produzent();
        p.start();
        k.start();

        while (true)
        {
         try{   bla = System.in.read();}catch(Exception e){}
                switch (bla)
                {
                    case 'r':
                        p.stop=false;
                        break;
                    case 's':
                        p.stop=true;
                        break;
                }

        }
    }

}
