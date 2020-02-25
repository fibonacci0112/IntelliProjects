package com.example.uebung1;

/**
 * Created by Nico on 21.10.2016.
 */
public class Vektor
{
    private int dimension;
    private float[] komponenten;
    private boolean istZeilenvektor = true;

    public Vektor( float[] komponenten, boolean istZeilenvektor)
    {
        this.dimension = komponenten.length;
        this.komponenten = new float[dimension];
        for (int i = 0; i < this.dimension; i++)
        {
            this.komponenten[i] = komponenten[i];
        }
        this.istZeilenvektor = istZeilenvektor;
    }

    public int getDimension()
    {
        return dimension;
    }

    public float[] getKomponenten()
    {
        return komponenten;
    }

    public boolean istZeilenvektor()
    {
        return istZeilenvektor;
    }



    public void transponiere()
    {
        this.istZeilenvektor = !istZeilenvektor;
    }

    public float skalarProdukt(Vektor V)
    {
        if(this.istZeilenvektor!=V.istZeilenvektor && this.dimension==V.dimension)
        {
            float ergebnis=0;

            for (int i = 0; i < this.komponenten.length; i++)
            {
                ergebnis += this.komponenten[i]*V.getKomponenten()[i];
            }

            return ergebnis;
        }
        System.out.println("Fehler");
        return 0;
    }

    public String toString()
    {
       return "who cares";
    }


}
