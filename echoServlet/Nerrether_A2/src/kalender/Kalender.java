package kalender;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

import feiertage.EventFinder;
import io.Ausgabe;
import io.Eingabe;
import jahresplaner.Jahresplaner;

public class Kalender implements IKalender
{

    public int istMontag = 0;

    private KalenderFunktion kalfunk = new KalenderFunktion();

    @Override
    public String getMonatsblatt(int jahr, int monat)
    {

        int verschiebung = kalfunk.wochentag_im_jahr(jahr, kalfunk.tagesnummer(1, monat, jahr));
        verschiebung -= istMontag;

        if (verschiebung == -1)
        {
            verschiebung = 6;
        }
        String kopfzeile = getKopfzeileMonatsblatt(jahr, monat);
        String monatstag = "";
        int[] monatsLaenge = {31, 28 + kalfunk.schaltjahr(jahr), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        for (int i = 0; i < verschiebung; i++)
        {
            monatstag += "\t";
        }

        for (int i = 1; i <= monatsLaenge[monat - 1]; i++)
        {
            if (i <= 9)
            {
                monatstag += "0";
            }
            monatstag += i + "\t";

            verschiebung++;

            if (verschiebung % 7 == 0)
            {
                verschiebung = 0;
                monatstag += "\n";
            }

        }
        return kopfzeile + "\n" + monatstag;
    }

    @Override
    public String getKopfzeileMonatsblatt(int jahr, int monat)
    {

        String[] monate = {"Januar", "Februar", "Maerz", "April", "Mai", "Juni", "Juli", "August", "September", "Okober", "November", "Dezember"};
        String[] tage = {"So", "Mo", "Di", "Mi", "Do", "Fr", "Sa"};
        String ausgabeTage = "";
        for (int i = 0; i < tage.length; i++)
        {
            ausgabeTage += tage[(i + istMontag) % 7] + "\t";
        }
        return "*******************" + monate[monat - 1] + " " + jahr + "*******************\n" + ausgabeTage;

    }
}
