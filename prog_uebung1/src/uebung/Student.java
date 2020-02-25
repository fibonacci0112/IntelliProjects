package uebung;

import java.util.Arrays;

/**
 * Created by Nico on 02.05.2017.
 */
public class Student extends Person implements Studierende
{
    private int matrikelnummer;
    private String seminarGruppe;
    private Fach[] faecher = new Fach[4];

    public Student(String familienname, String vorname, String geburtsdatum, int matrikelnummer, String seminarGruppe, Fach[]faecher)
    {
        super(familienname, vorname, geburtsdatum);
        this.matrikelnummer = matrikelnummer;
        this.seminarGruppe = seminarGruppe;
        for (int i = 0; i <faecher.length ; i++)
        {
            this.faecher[i]=faecher[i];
        }
    }

    public Student()
    {
        super("","","");
        this.matrikelnummer =0;
        this.seminarGruppe = "";
        for (int i = 0; i <faecher.length ; i++)
        {
            this.faecher[i]= new Fach();
        }
    }

    public void setMatrikelnummer(int matrikelnummer)
    {
        this.matrikelnummer = matrikelnummer;
    }

    public void setSeminarGruppe(String seminarGruppe)
    {
        this.seminarGruppe = seminarGruppe;
    }

    public void setFaecher(Fach[] faecher)
    {
        this.faecher = faecher;
    }

    public void setFach(Fach f, int index)
    {
        this.faecher[index]=f;
    }

    public int getMatrikelnummer()
    {
        return matrikelnummer;
    }

    public String getSeminarGruppe()
    {
        return seminarGruppe;
    }

    public Fach[] getFaecher()
    {
        return faecher;
    }

    @Override
    public String toString()
    {
        return "Student: " + " \nFamilienname:\t " + super.familienname + "\nVorname:\t\t " + super.vorname + "\nGeburtsdatum:\t " + super.geburtsdatum +
                "\nmatrikelnummer:\t " + matrikelnummer + "\nseminarGruppe:\t " + seminarGruppe + "\nfaecher:\t\t " + Arrays.toString(faecher);
    }
}
