package uebung;

import java.util.*;

/**
 * Created by Nico on 02.05.2017.
 */
public class Menu
{
    public static void main(String[] args)
    {
        try
        {
        ArrayList<Student> studenten = new ArrayList<Student>();
        Scanner s = new Scanner(System.in);


            for (int i = 0; i < 3; i++)
            {
                studenten.add(new Student());
                System.out.println("Student " + (i + 1));
                System.out.print("Familienname: ");
                studenten.get(i).familienname = s.next();
                System.out.print("Vorname: ");
                studenten.get(i).vorname = s.next();
                System.out.print("Geburtsdatum: ");
                studenten.get(i).geburtsdatum = s.next();
                System.out.print("Matrikelnummer: ");
                studenten.get(i).setMatrikelnummer(s.nextInt());
                System.out.print("Seminargruppe ");
                studenten.get(i).setSeminarGruppe(s.next());
                int zähler = 0;
                do
                {
                    System.out.print("Fächer: (Semester, Kursname, Studiengang)");
                    studenten.get(i).setFach(new Fach(s.nextInt(), s.next(), s.next()), zähler);
                    System.out.println("mit 0 beenden, mit 1 nächstes Fach eingeben");
                    zähler++;
                } while (s.nextInt() != 0 && zähler < 4);
            }

            for (int i = 0; i < studenten.size(); i++)
            {
                System.out.println(i + ". " + studenten.get(i).toString() + "\n");
            }

            System.out.println("Welches Fach soll gelistet werden?: ");
            String eingabe = s.next();
        for (int i = 0; i < studenten.size(); i++)
        {
            for (int j = 0; j < studenten.get(i).getFaecher().length; j++)
            {
                if(eingabe.equals(studenten.get(i).getFaecher()[j].getKursName()))
                {
                    System.out.println(studenten.get(i).getMatrikelnummer() + " ist in diesem Fach.");
                }
            }
        }
        }catch (Exception e)
        {
            System.out.println("Fehler bei der Eingabe. Programm wird beendet.");
        }
    }
}
