package feiertage;

import java.util.HashMap;
import java.util.Scanner;

import kalender.KalenderFunktion;

public class EventFinder
{
    Scanner readTXT;
    private KalenderFunktion kf = new KalenderFunktion();

    // in diese HashMap werden alle Events/ Feiertage eingetragen
    private HashMap<Integer, String> ereignisHashMap = new HashMap<>();

    public static int startId = 1;
    int hashID;

    public EventFinder(int jahr)
    {
        ladeEventHashMap(jahr);
        setOsterTage(jahr);
        setAdvent(jahr);
        setBus();
        setMutterTag(jahr);
    }

    /**
     * Die Events werden aus Dateien gelesen und in die HashMap
     * geladen. Einige Feiertage werden algorithmisch berechnet.
     * Die HashMap-Entrys bestehen aus Key (Integer) und Value (String)
     *
     * @param jahr
     * @return HashMap<Integer , String> mit Events (= Feiertage)
     */
    private void ladeEventHashMap(int jahr)
    {
        startId = 0;
        readTXT = new Scanner(getClass().getResourceAsStream("Event.txt"));

        while (readTXT.hasNext())
        {
            String value = readTXT.nextLine();
            String[] teileEvent = value.split(";");
            String[] teileDatum = teileEvent[0].split("\\.");

            int tagesnummerFeiertag = kf.tagesnummer(Integer.parseInt(teileDatum[0]), Integer.parseInt(teileDatum[1]), jahr);

            hashID = Integer.parseInt(startId + "" + String.format("%1$03d", tagesnummerFeiertag));

            startId++;
            ereignisHashMap.put(hashID, teileEvent[1]);
        }
    }

    /**
     * gibt eine mit Feiertagen und Events geladene HashMap f�r
     * das angegebene Jahr zur�ck
     *
     * @param jahr (int)
     * @return HashMap<Integer  ,  String>
     */
    public HashMap<Integer, String> getEreignisHashMap(int jahr)
    {
        return ereignisHashMap;
    }

    /**
     * Berechnung der Adventssonntage
     *
     * @param faktor 3 = der 1. Advent liegt 21 Tage zur�ck,
     *               2 = der 2. Advent liegt 14 Tage zur�ck,
     *               1 = der 3. Advent liegt 7 Tage zur�ck,
     *               0 = der 4. Advent liegt am Sonntag vor dem 24.12.,
     * @param jahr   (int)
     * @return int Tagesnummer
     */

    public int getAdvent(int faktor, int jahr)
    {


        return 0;
    }

    private void setOsterTage(int jahr)
    {
        int osterSonn = kf.ostersonntag(jahr);
        hashID = Integer.parseInt(startId + "" + String.format("%1$03d", osterSonn));
        startId++;
        ereignisHashMap.put(hashID, "Ostersonntag");

        readTXT = new Scanner(getClass().getResourceAsStream("Ostern.txt"));

        while (readTXT.hasNext())
        {
            String value = readTXT.nextLine();
            String[] teileEvent = value.split(";");

            int ergebnis = Integer.parseInt(teileEvent[0]);

            hashID = Integer.parseInt(startId + "" + String.format("%1$03d", osterSonn + ergebnis));
            startId++;
            ereignisHashMap.put(hashID, teileEvent[1]);
        }
    }

    private void setAdvent(int jahr)
    {
        int weihnacht = kf.tagesnummer(24, 12, jahr);
        int advent = weihnacht - kf.wochentag_im_jahr(jahr, weihnacht);

        for (int i = 0; i < 4; i++)
        {
            hashID = Integer.parseInt(startId + "" + String.format("%1$03d", advent));
            startId++;
            ereignisHashMap.put(hashID, 4 - i + ". Advent");
            advent -= 7;
        }
        advent += 3;

        hashID = Integer.parseInt(startId + "" + String.format("%1$03d", advent));
        startId++;
        ereignisHashMap.put(hashID, "Busz und Bettag");

    }

    private void setMutterTag(int jahr)
    {
        int wochentagErsterMai = kf.wochentag_im_jahr(jahr, kf.tagesnummer(1, 5, jahr));
        if (wochentagErsterMai == 0) {wochentagErsterMai = 7;}
        int mutter = kf.tagesnummer(1, 5, jahr) + (14 - wochentagErsterMai);

        if (kf.ostersonntag(jahr) + 49 == mutter) {mutter -= 7;}

        hashID = Integer.parseInt(startId + "" + String.format("%1$03d", mutter));
        startId++;
        ereignisHashMap.put(hashID, "Muttertag");
    }

    private void setBus()
    {

    }


}
