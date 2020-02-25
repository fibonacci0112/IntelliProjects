package com.company;

public class Main {

    public static void main(String[] args)
    {

        double a=100;

        for(int i = 0; i<10; i++)
        {

            System.out.println(a*Math.pow((1+0.03),i));
        }
    }
}
