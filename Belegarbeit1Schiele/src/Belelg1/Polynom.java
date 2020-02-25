package Belelg1;

import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;

/**
 * Die Klasse Polynom beinhaltet Felder und Methoden zum erzeugen, verarbeiten und Darstellen
 * von bis zu 6 stelligen Polynome
 *
 * @author Nicolai Schiele
 */

public class Polynom
{
    private ArrayList<Float> koeffizienten;
    private int addsub = 1;


    /**
     * Erzeugt eine Koeffizientenliste der Laenge 0
     */
    public Polynom()
    {
        this.koeffizienten = new ArrayList<>();
    }

    /**
     * ArrayList Parameter zum uebergeben fester Koeffizienten
     *
     * @param koeffizienten
     */
    public Polynom(ArrayList<Float> koeffizienten)
    {
        this.koeffizienten = new ArrayList<>();
        for (int i = 0; i < koeffizienten.size(); i++)
        {
            this.koeffizienten.add(koeffizienten.get(i));
        }
    }


    /**
     * Die Eingabe Methode laesst den Benutzer die Koeffizienten per Tastatur eingeben
     */
    public void eingabe()
    {
        System.out.print("Bitte geben Sie bis zu 7 Koeffizienten an(mit Leerzeichen getrennt): ");
        Scanner s = new Scanner(System.in);
        String eingabe = s.nextLine();
        char[] charArray = eingabe.toCharArray();
        String wert = "";
        for (int i = 0; i < charArray.length; i++)
        {
            if (koeffizienten.size() > 6)
            {
                System.out.println("nur die ersten 7 Koeffizienten wurden verwendet");
                break;
            }
            if (charArray[i] != ' ')
            {
                wert = wert.concat(Character.toString(charArray[i]));
            } else if (wert != "")
            {
                koeffizienten.add(Float.parseFloat(wert));
                wert = "";
            }
        }
        try
        {
            koeffizienten.add(Float.parseFloat(wert));
        } catch (NumberFormatException e)
        {
            System.out.println("Fehler bei der Eingabe.");
        }
        ausgabe();
    }


    /**
     * Die Ausgabe Methode Bringt das Polynom in eine fuer den Benutzer leserliche Darstellung und gibt dies aus
     */
    public void ausgabe()
    {
        int grad = -1;
        String ausgabe = "";
        for (int i = 0; i < koeffizienten.size(); i++)
        {
            if (!(this.koeffizienten.get(i) < 0.0001 && this.koeffizienten.get(i) > -0.0001))
            {
                if (koeffizienten.size() - i == 1)
                {
                    ausgabe = ausgabe.concat(String.format("%+.2f ", koeffizienten.get(i)));
                } else
                {
                    ausgabe = ausgabe.concat(String.format("%+.2fx^%d ", koeffizienten.get(i), koeffizienten.size() - i - 1));
                }
            }
            if (ausgabe != "")
            {
                grad++;
            }
        }
        if (grad == -1)
        {
            System.out.println("Alle Koeffizienten sind 0");

        } else
        {
            System.out.println("Polynom " + grad + ". Grades mit folgenden Koeffizienten: " + ausgabe);
        }
    }

    /**
     * Laesst den Benutzer die Koeffizienten eines bereits bestehenden Polynoms veraendern
     */
    public void polynomVerändern()
    {
        koeffizienten.clear();
        eingabe();
    }

    /**
     * Liefert den Funktionswert eines vom Benutzer definierten X Wertes
     *
     * @param eingabe
     * @return ergebnis
     */
    public String funktionsWert(float eingabe)
    {
        float ergebnis = 0;
        for (int i = koeffizienten.size(); i > 0; i--)
        {
            ergebnis += koeffizienten.get(i - 1) * Math.pow(eingabe, koeffizienten.size() - i);
        }
        return String.valueOf(ergebnis);
    }

    /**
     * Addiert this mit dem uebergebenen Polynom p und gibt das ergebnis aus
     *
     * @param p: Polynom das mit this addiert wird
     */
    public void addition(Polynom p)
    {
        Polynom ergebnis = new Polynom();
        String addsubausgabe = "addition";

        if (this.koeffizienten.size() < p.koeffizienten.size())                                                         //prüft welches Polynom länger ist und speichert es in this
        {
            ergebnis.koeffizienten = new ArrayList<>(this.koeffizienten);
            this.koeffizienten = new ArrayList<>(p.koeffizienten);
            p.koeffizienten = new ArrayList<>(ergebnis.koeffizienten);
        }
        int j = this.koeffizienten.size() - p.koeffizienten.size();
        for (int i = 0; i < j; i++)                                                                                     //füllt p mit nullen
        {
            p.koeffizienten.add(0, 0f);
        }
        ergebnis.koeffizienten.clear();
        for (int i = 0; i < p.koeffizienten.size(); i++)
        {
            ergebnis.koeffizienten.add(i, this.koeffizienten.get(i) + (addsub * p.koeffizienten.get(i)));
        }

        while (p.koeffizienten.get(0) < 0.0001 && p.koeffizienten.get(0) > -0.0001)                            //entfernt die Höchsten Nullstellen
        {
            p.koeffizienten.remove(0);
        }
        if (addsub == -1)
        {
            addsubausgabe = "subtraktion";
        }
        System.out.print(String.format("Die %s der Polynome ergibt folgendes ", addsubausgabe));
        ergebnis.ausgabe();
        addsub = 1;
    }

    /**
     * Ruft die Methode Addition auf und aendert die Variable addsub zu -1 so dass aus der Addition eine Subtraktion wird
     *
     * @param p: : Polynom das mit this subtrahiert wird
     */
    public void differenz(Polynom p)
    {
        addsub = -1;
        addition(p);
    }


    /**
     * Führt die erste Ableitung von this aus wenn Koeffizienten vorhanden sind
     *
     * @return boolean: Prüft ob nicht alle Koeffizienten 0 sind
     */
    public boolean ableiten()
    {
        if (koeffizienten.size() == 0)                                                                                  //schauen ob irrelevant
        {
            return false;
        }

        int exponent = koeffizienten.size();
        for (int i = 0; i < this.koeffizienten.size(); i++)
        {
            koeffizienten.set(i, koeffizienten.get(i) * (exponent - 1));
            exponent--;
        }
        koeffizienten.remove(koeffizienten.size() - 1);

        ausgabe();
        return true;
    }


    /**
     * Führt eine Polynomdivision der Art P / (x-a) durch
     *
     * @param a: Wert an der Stelle P / (x-a)
     */
    public void polynomdivision(int a)
    {
        if (koeffizienten.size() != 0)
        {
            Polynom ergebnis = new Polynom();
            float hilf = 0;
            for (int i = 0; i < this.koeffizienten.size(); i++)
            {
                ergebnis.koeffizienten.add(i, this.koeffizienten.get(i) + hilf);
                hilf = ergebnis.koeffizienten.get(i) * a;
            }
            if (ergebnis.koeffizienten.get(this.koeffizienten.size() - 1) != 0)
            {
                hilf = ergebnis.koeffizienten.get(this.koeffizienten.size() - 1);
            } else
            {
                hilf = 0;
            }
            ergebnis.koeffizienten.remove(this.koeffizienten.size() - 1);

            ergebnis.ausgabe();
            System.out.println("Rest: " + hilf);
        }
    }

    /**
     * Multipliziert zwei Polynome
     *
     * @param p: das mit this zu multiplizierende Polynom
     */
    public void multiplikation(Polynom p)
    {
        /*if ((this.koeffizienten.size() + p.koeffizienten.size() - 1) > 7)
        {
            System.out.println("Ergebnispolynom hätte höheren Grad als 6");
        }*/
        Polynom ergebnis = new Polynom();

        while (ergebnis.koeffizienten.size() < (this.koeffizienten.size() + p.koeffizienten.size() - 1))
        {
            ergebnis.koeffizienten.add(0f);
        }

        for (int i = 0; i < this.koeffizienten.size(); i++)
        {

            for (int j = 0; j < p.koeffizienten.size(); j++)
            {
                ergebnis.koeffizienten.set(i + j, ergebnis.koeffizienten.get(i + j) + (this.koeffizienten.get(i) * p.koeffizienten.get(j)));
            }
        }
        System.out.print("bei der Multiplikation der Polynome entstand folgendes Polynom: ");
        ergebnis.ausgabe();

    }
}