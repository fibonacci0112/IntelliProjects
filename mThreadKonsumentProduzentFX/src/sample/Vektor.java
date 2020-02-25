package sample;

import java.util.ArrayList;

/**
 * Created by Nico on 21.06.2017.
 */
public class Vektor
{
    public static ArrayList<Integer> vektor = new ArrayList<>();

    public synchronized void setVektor(int zahl)
    {
        vektor.add(zahl);
    }

    public synchronized int getVektor()
    {
        if (vektor.size()==0)
        {
            return -1;
        }
        int i = vektor.get(0);
        vektor.remove(0);
        return i;
    }

    public ArrayList getList()
    {
        return vektor;
    }
}
