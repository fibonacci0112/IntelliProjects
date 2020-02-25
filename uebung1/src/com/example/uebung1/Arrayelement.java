package com.example.uebung1;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Nico on 21.10.2016.
 */
public class Arrayelement
{

    public byte[] haeufigstesElem(byte[] arr)
    {
        byte[] ergebnis = {0};
        int zaehler = 1;
        int höchsterwert = 0;
        int ergindex = 0;

        Arrays.sort(arr);

        for (int i = 1; i < arr.length; i++)
        {
            if (arr[i] == arr[i - 1])
            {
                zaehler++;
            } else
            {
                zaehler = 1;
            }


            if (zaehler == höchsterwert && höchsterwert != 0) //ergänzt ergebnis array um wert mit gleicher anzahl
            {
                byte[] hilf = new byte[ergindex + 1];

                for (int j = 0; j < ergebnis.length; j++)
                {
                    hilf[j] = ergebnis[j];
                }
                ergebnis = new byte[ergindex + 2];
                for (int j = 0; j < hilf.length; j++)
                {
                    ergebnis[j] = hilf[j];
                }
                ergebnis[ergindex + 1] = arr[i-1];
                ergindex++;
            }

            if (zaehler > höchsterwert || höchsterwert == 0) //neuer höchster wert
            {
                ergindex = 0;
                ergebnis = new byte[ergindex + 1];
                ergebnis[ergindex] = arr[i-1];
                höchsterwert = zaehler;
            }

        }

        if(arr.length==1 || arr.length==ergindex+2) //Ausnahme wenn nur einzelne Werte vorkommen
        {
            return arr;
        }


        return ergebnis;
    }
}
