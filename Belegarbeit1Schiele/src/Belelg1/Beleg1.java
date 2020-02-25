package Belelg1;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.util.Arrays.asList;

/**
 * Die Klasse Beleg1 dient als Menuefuehrung, um dem Benutzer die Funktionen der PolynomKlasse darzustellen
 *
 * @author Nicolai Schiele
 */
public class Beleg1
{
    public static void main(String[] args)
    {
        int menu = -1;
        Scanner s = new Scanner(System.in);
        Polynom[] speicher = new Polynom[10];
        int speicherzähler = 0;
        Random r = new Random();

        do
        {
            if (menu != 9)
            {
                System.out.println("Bitte wählen Sie eine der Folgenden Funktionen");
                System.out.println("");
                System.out.println("\t\t 1. Polynom erzeugen");
                System.out.println("\t\t 2. Polynom verändern");
                System.out.println("\t\t 3. Funktionswert an der Stelle X");
                System.out.println("\t\t 4. Addition");
                System.out.println("\t\t 5. Subtraktion");
                System.out.println("\t\t 6. Ableitung");
                System.out.println("\t\t 7. Polynomdivision mit (x-a)");
                System.out.println("\t\t 8. Multiplikation");
                System.out.println("\t\t 9. Alle Polynome Ausgeben");
                System.out.println("\t\t 10. Testbeispiel 1");
                System.out.println("\t\t 11. Testbeispiel 2");
                System.out.println("\t\t 12. Testbeispiel 3");
                System.out.println("\t\t 0. Programm beenden");
                System.out.print("\t\t:");
            }
            try
            {
                menu = s.nextInt();
                switch (menu)
                {
                    case 1:
                        if (speicherzähler < 10)
                        {
                            speicher[speicherzähler] = new Polynom();
                            speicher[speicherzähler].eingabe();
                            speicherzähler++;
                        } else
                        {
                            System.out.println("Polynom memory is full");
                        }
                        break;
                    case 2:
                        System.out.print("Bitte geben Sie das zu verändernde Polynom an: ");
                        speicher[(s.nextInt() - 1)].polynomVerändern();

                        break;
                    case 3:
                        System.out.print("Bitte geben Sie das zu verwendende Polynom den gesuchten Funktionswert an: ");
                        System.out.println("Der Wert an dieser Stelle ist: " + speicher[s.nextInt() - 1].funktionsWert(s.nextFloat()));
                        break;
                    case 4:
                        System.out.print("Bitte geben Sie die zu addierenden Polynome an: ");
                        speicher[s.nextInt() - 1].addition(speicher[s.nextInt() - 1]);
                        break;
                    case 5:
                        System.out.print("Bitte geben Sie die zu subtrahierenden Polynome an: ");
                        speicher[s.nextInt() - 1].differenz(speicher[s.nextInt() - 1]);
                        break;
                    case 6:
                        System.out.print("Bitte geben Sie das Abzuleitende Polynom an: ");
                        int b = s.nextInt() - 1;
                        if (!speicher[b].ableiten())
                        {
                            speicher[b] = null;
                            System.out.println("Polynom ist leer und wurde gelöscht");
                        }
                        break;
                    case 7:
                        int a;
                        System.out.print("Bitte geben Sie das zu dividierende Polynom an: ");
                        a = s.nextInt();
                        System.out.print("Bitte geben Sie a aus dem zu dividierenden Teil (x-a) an: ");
                        speicher[a - 1].polynomdivision(s.nextInt());
                        break;
                    case 8:
                        System.out.print("Bitte geben Sie die zu multiplizierenden Polynome an: ");
                        speicher[s.nextInt() - 1].multiplikation(speicher[s.nextInt() - 1]);
                        break;
                    case 9:
                        for (int i = 0; i < speicher.length; i++)
                        {
                            if (speicher[i] == null)
                            {
                                System.out.println((i + 1) + ". Speicherort ist leer");
                                continue;
                            }
                            System.out.print((i + 1) + ". ");
                            speicher[i].ausgabe();
                        }
                        System.out.println("Wählen Sie eine Funktion(zurück zum Menü mit 13):");
                        break;
                    case 10:
                        System.out.println("Bitte geben Sie 3 Polynome ein: ");
                        for (int i = 0; i < 3; i++)
                        {
                            speicher[i] = new Polynom();
                            speicher[i].eingabe();
                        }
                        System.out.println("Die restlichen Speicherplätze werden mit zufallspolynomen gefüllt");
                        for (int i = 3; i < 10; i++)
                        {
                            speicher[i] = new Polynom(new ArrayList<>(asList(r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat())));
                            speicher[i].ausgabe();
                        }
                        System.out.println("weiter mit 0");
                        s.next();
                        break;
                    case 11:
                        if (speicher[4] == null)
                        {
                            System.out.println("1 Polynom wird erzeugt: ");
                            speicher[4] = new Polynom(new ArrayList<>(asList(r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat())));

                        }
                        speicher[4].ausgabe();
                        System.out.print("Bitte geben Sie den Funktionswert zur Berechnung an: ");
                        System.out.println("Der Funktionswert an der Stelle 4 ist: " + speicher[4].funktionsWert(s.nextInt()));
                        System.out.print("Die erste Ableitung ergibt ein ");
                        speicher[4].ableiten();
                        System.out.print("Die Division des Abgeleiteten Polynoms mit (x-2) ergibt ein ");
                        speicher[4].polynomdivision(2);
                        System.out.println("weiter mit 0");
                        s.next();
                        break;
                    case 12:
                        if (speicher[5] == null || speicher[6] == null)
                        {
                            speicher[5] = new Polynom(new ArrayList<>(asList(r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat())));
                            speicher[6] = new Polynom(new ArrayList<>(asList(r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat(), r.nextInt() / r.nextInt(1000000) * r.nextFloat())));
                            System.out.println("2 Polynome werden erzeugt: ");
                        }
                        speicher[5].ausgabe();
                        speicher[6].ausgabe();
                        speicher[5].addition(speicher[6]);
                        speicher[5].differenz(speicher[6]);
                        speicher[5].multiplikation(speicher[6]);
                        System.out.println("weiter mit 0");
                        s.next();
                        break;
                    default:
                        break;
                }


            } catch (Exception e)
            {
                System.out.println("Felher bei der Eingabe!");
                s.next();
            }


        } while (menu != 0);
    }
}
