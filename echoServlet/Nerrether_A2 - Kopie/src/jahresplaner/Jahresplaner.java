package jahresplaner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

import feiertage.EventFinder;
import kalender.KalenderFunktion;

public class Jahresplaner
{
    private int columnwidth = 35;
    private int feiertagflag = 0;
    private KalenderFunktion kalfunk;
    private EventFinder ef;
    private HashMap feiertage;

    public Jahresplaner(int jahr)
    {
        kalfunk = new KalenderFunktion();
        ef = new EventFinder(jahr);
        feiertage = ef.getEreignisHashMap(jahr);
    }

    /**
     * der angegebene Monat f?r den Jahresplan wird zusammengebaut in der Form
     * Mai 2017
     * Mo|01|            |121
     * Di|02|            |122
     * Mi|03|            |123
     * usw.
     * Di|30|            |150
     * Mi|31|            |151
     * Jede Zeile wird als String in einer LinkedList<String> abgespeichert
     *
     * @param monat (int) -> 1= jan bis 12= dez
     * @return LinkedList<String> der "Monatsplan" - Container mit allen Zeilen des Monats
     */

    public LinkedList<String> baueMonat(int monat, int jahr)
    {
        LinkedList<String> monatsListe = new LinkedList();

        String[] monate = {"Januar", "Februar", "Maerz", "April", "Mai", "Juni", "Juli", "August", "September", "Okober", "November", "Dezember"};

        String[] tage = {"So", "Mo", "Di", "Mi", "Do", "Fr", "Sa"};

        int[] monatsLaenge = {31, 28 + kalfunk.schaltjahr(jahr), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        monatsListe.add(rightpad(monate[monat], columnwidth));

        String feiertag = "";
        for (int i = 0; i < 31; i++)
        {
            int wochentag = kalfunk.wochentag_im_jahr(jahr, kalfunk.tagesnummer(i + 1, monat + 1, jahr));

            if (i < monatsLaenge[monat])
            {

                if (feiertagflag != 0)
                {
                    feiertag = Objects.toString(getAlleFeiertage(i + 1, monat + 1, jahr), "");
                }

                if (feiertag != "" && kalfunk.tagesnummer(i + 1, monat + 1, jahr) == 60 && !kalfunk.istSchaltjahr(jahr))
                {
                    feiertag = "";
                }
                if (feiertag.length() > columnwidth / 2)
                {
                    feiertag = feiertag.substring(0, columnwidth / 2 - 3) + "...";
                }

                String listEntry = String.format("| %1$02d | " + rightpad(feiertag, columnwidth / 2) + " |%2$03d", i + 1, kalfunk.tagesnummer(i + 1, monat + 1, jahr));
                monatsListe.add(rightpad(tage[wochentag] + listEntry, columnwidth));

            } else
            {
                monatsListe.add(rightpad("", columnwidth));
            }

        }

        return monatsListe;

    }

    /**
     * Der Jahresplan f?r die angegebenen Monate wird als String zur?ck gegeben.
     * Hinweis zur Implementierung:
     * Die Monatspl?ne der angegebenen Monate werden in einer
     * Container-Klasse LinkedList <LinkedList<String>> der "Planliste" zusammengefasst.
     * Damit erh?lt man eine 2-dimensionale Datenstruktur.
     * Um den String zusammenzubauen, wird die Datenstruktur so durchlaufen, dass jeweils
     * die ersten Zeilen aller Monate nebeneinander ausgegeben werden.
     * Der zur?ckgegebene String sollte folgendes Format haben:
     * Januar 2017            Februar 2017            Maerz 2017
     * So|01|            |1    Mi|01|            |32    Mi|01|            |60
     * Mo|02|            |2    Do|02|            |33    Do|02|            |61
     * Di|03|            |3    Fr|03|            |34    Fr|03|            |62
     * Mi|04|            |4    Sa|04|            |35    Sa|04|            |63
     * Do|05|            |5    So|05|            |36    So|05|            |64
     * Fr|06|            |6    Mo|06|            |37    Mo|06|            |65
     * usw.
     *
     * @param von       (int) - 1= jan bis 12= dez
     * @param bis       (int) - 1= jan bis 12= dez
     * @param jahr      (int)
     * @param feiertage (int)  1 mit Feiertagen / 0 ohne Feiertage
     * @return String - der Jahresplan
     */
    public String gibJahresplan(int von, int bis, int jahr, int feiertage)
    {
        feiertagflag = feiertage;
        LinkedList<String> fertigesJahr = new LinkedList();
        von--;

        for (int j = von; j < bis; j++)
        {
            fertigesJahr.addAll(baueMonat(j, jahr));
        }

        String ausgabeJahr = "";

        for (int t = 0; t < 32; t++)
        {
            for (int m = 0; m < bis - von; m++)
            {
                ausgabeJahr += fertigesJahr.get(m * 32 + t);
            }
            ausgabeJahr += "\n";
        }

        return ausgabeJahr;
    }

    public String getAlleFeiertage(int tag, int monat, int jahr)
    {
        String a = "";
        for (int i = 0; i <= feiertage.size(); i++)
        {
            if (feiertage.containsKey(i * 1000 + kalfunk.tagesnummer(tag, monat, jahr)))
            {
                a += feiertage.get(i * 1000 + kalfunk.tagesnummer(tag, monat, jahr)) + ",";
            }
        }
        return a;
    }

    private String rightpad(String text, int length)
    {
        return String.format("%-" + length + "." + length + "s", text);
    }
}
