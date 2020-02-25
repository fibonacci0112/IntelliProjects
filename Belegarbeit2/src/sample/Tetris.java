package sample;

import java.awt.*;
import java.util.Random;
import java.util.Timer;

/**
 * Created by Nico on 21.12.2016.
 */
public class Tetris
{

    private int farbe;
    private Block block;
    private int level;
    private int linien;
    private Block nächsterBlock;
    private int punkte;
    private int[][] spielfeld;
    private Random zufall;
    public Event SpielfeldGeändert;
    public Event SpielEnde;
    private int geschwindigkeit;


    public int[][] getSpielfeld()
    {
        return spielfeld;
    }

    public int getFarbe()
    {
        return farbe;
    }

    public int getLevel()
    {
        return level;
    }

    public int getLinien()
    {
        return linien;
    }

    public int getPunkte()
    {
        return punkte;
    }

    public Tetris()
    {
        this.level = 1;
        this.linien = 0;
        this.punkte = 0;
        zufall = new Random();
        this.spielfeld = new int[16][10];
        for (int i = 0; i < spielfeld.length; i++)
        {
            for (int j = 0; j < spielfeld[i].length; j++)
            {
                spielfeld[i][j] = 0;
                if (i == 15)
                {
                    spielfeld[i][j] = 2;
                }
            }
        }
        geschwindigkeit = 1000 / level;
        blockEinfügen();

    }

    protected void OnSpielfeldGeändert()
    {
        if (SpielfeldGeändert != null)
        {
            //SpielfeldGeändert(this, new EventArgs());
        }
    }

    protected void OnSpielEnde()
    {
        if (SpielEnde != null)
        {
            //SpielEnde(this, new EventArgs());
        }
    }

    public void blockEinfügen()
    {
        block = new Block(zufall.nextInt(5));

        for (int i = 0; i < block.getBlockfeld().length; i++)
        {
            for (int j = 0; j < block.getBlockfeld()[i].length; j++)
            {

                if (block.getBlockfeld()[i][j] == 1)
                {
                    if (spielfeld[i][j + 4] == 2)
                    {
                        //spiel beenden
                    }
                    spielfeld[i][j + 4] = block.getBlockfeld()[i][j];
                }
            }
        }
    }

    public void blockfest()
    {
        for (int i = 0; i < spielfeld.length; i++)
        {
            for (int j = 0; j < spielfeld[i].length; j++)
            {
                if (spielfeld[i][j] == 1)
                    spielfeld[i][j] = 2;
            }
        }
        blockEinfügen();
    }

    public void blockNachUnten()
    {
        for (int i = spielfeld.length - 2; i >= 0; i--)              //fehler beheben (unterste reihe in block verschiebt sich wenn platz ist um 1)
        {
            for (int j = 0; j < spielfeld[i].length; j++)
            {
                if (spielfeld[i][j] == 1)
                {
                    if (spielfeld[i + 1][j] == 2)
                    {
                        blockfest();
                        i = -1;
                        break;
                    }
                    spielfeld[i + 1][j] = 1;
                    spielfeld[i][j] = 0;
                }
            }
            if (i == -1)
            {
                break;
            }
        }
    }

    public void blockZurSeite(int richtung)
    {
        for (int i = 0; i < spielfeld.length; i++)              //fehler beheben (unterste reihe in block verschiebt sich wenn platz ist um 1)
        {
            for (int j = 0; j < spielfeld[i].length; j++)
            {
                if (spielfeld[i][j] == 1)
                {
                    if (spielfeld[i + 1][j] == 2)
                    {
                        blockfest();
                        i = -1;

                    }
                    spielfeld[i][j + richtung] = 1;
                    spielfeld[i][j] = 0;
                }
            }
            if (i == -1)
            {
                break;
            }
        }

    }


}


