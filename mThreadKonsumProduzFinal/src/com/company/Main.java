package com.company;


import java.io.IOException;

public class Main
{

    public static void main(String[] args) throws IOException
    {
        Pipo pi = new Pipo();
        int bla = 0;
        Lager l = new Lager();
        Konsument k = new Konsument(pi);
        Produzent p = new Produzent(pi);

        p.start();
        k.start();

        while (true)
        {
            try {bla = System.in.read();} catch (Exception e) {}
            switch (bla)
            {
                case 'r':
                    p.resume();
                    break;
                case 's':
                    p.suspend();
                    break;
            }
        }
    }
}
