package start;

import io.Ausgabe;
import io.Eingabe;
import jahresplaner.Jahresplaner;
import kalender.Kalender;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AuswahlMenue implements IAuswahlMenue
{
    Kalender k = new Kalender();
    @Override
    public void zeigeMonat(int jahr, int monat)
    {
        Ausgabe.print(k.getMonatsblatt(jahr, monat));
    }

    @Override
    public void zeigeJahr(int jahr)
    {

        for (int i = 1; i <= 12; i++)
        {
            Ausgabe.print(k.getMonatsblatt(jahr, i));
        }

    }

    @Override

    public int liesMonat()
    {
        return Eingabe.monat();

    }

    @Override
    public int liesJahr()
    {
        return Eingabe.jahr();
    }

    @Override
    public void auswahlMenue()
    {
        String scannerAuswahl = "";
        while (!scannerAuswahl.equals("0"))
        {
            Ausgabe.menue();
            scannerAuswahl = Eingabe.einlesen();

            switch (scannerAuswahl)
            {

                case "1":
                    zeigeJahr(eingabeJahreszahl());

                    break;

                case "2":
                    int jahresergebnis = eingabeJahreszahl();

                    int monatsergebnis;
                    do
                    {
                        Ausgabe.print("Bitte eine Monatszahl eingeben:");
                        monatsergebnis = liesMonat();
                    } while (monatsergebnis == 0);

                    zeigeMonat(jahresergebnis, monatsergebnis);

                    break;

                case "3":
                    Ausgabe.print("Bitte waehle die Kalenderformation aus\namerikanisch [0]\neuropaeisch [1]");
                    try
                    {
                        int auswahlFormat = Integer.parseInt(Eingabe.einlesen());
                        if (auswahlFormat == 1 || auswahlFormat == 0)
                        {
                            k.istMontag = auswahlFormat;
                            break;
                        }
                    } catch (Exception e)
                    {

                    }
                    Ausgabe.print("Fehler bei der Eingabe\n");

                    break;

                case "4":
                    jahresplan(0);
                    break;

                case "5":
                    jahresplan(1);
                    break;

                case "0":
                    Ausgabe.print("Auf wiedersehen!");
                    break;

                default:
                    Ausgabe.print("Bitte nur 1, 2, 3, 4, 5 oder 0 eingeben\n");
                    break;
            }
        }
    }

    public int eingabeJahreszahl()
    {
        int jahresauswahl;
        do
        {
            Ausgabe.print("Bitte eine Jahreszahl zwischen 1582 und 9999 eingeben:");
            jahresauswahl = liesJahr();
        } while (jahresauswahl == 0);
        return jahresauswahl;
    }

    private void jahresplan(int feiertage)
    {
        Jahresplaner japl;
        int jahr = eingabeJahreszahl();
        Ausgabe.print("Bitte nacheinander den Anfangsmonat und Endmonat eingeben: ");
        int von = liesMonat();
        int bis = liesMonat();


        japl = new Jahresplaner(jahr);
        String ausgabe = japl.gibJahresplan(von, bis, jahr, feiertage);
        Ausgabe.print(ausgabe);
        Ausgabe.print("Soll gespeichert werden?(j/n) ");
        if (Eingabe.einlesen().equals("j"))
        {
            Ausgabe.print("Bitte gib einen Dateinamen ein(0 wenn nicht): ");
            String name = Eingabe.einlesen();
            if (name.equals("0")) {name = "";}
            speichern(jahr, ausgabe, name, "events_");
        }
    }

    private void speichern(int jahr, String jahresPlan, String fileName, String events)
    {
        try
        {
            String updatedText = jahresPlan.replaceAll("\n", System.lineSeparator());
            if (fileName.equals("")) { fileName += "jahresplan_" + events + jahr + ".txt"; }

            FileWriter fw = new FileWriter(fileName + ".txt");
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(updatedText);
            bw.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
