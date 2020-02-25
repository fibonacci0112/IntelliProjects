package uebung;


/**
 * Created by Nico on 02.05.2017.
 */
public class Person
{
    protected String familienname;
    protected String vorname;
    protected String geburtsdatum;

    public Person(String familienname, String vorname, String geburtsdatum)
    {
        this.familienname = familienname;
        this.vorname = vorname;
        this.geburtsdatum = geburtsdatum;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "familienname='" + familienname + '\'' +
                ", vorname='" + vorname + '\'' +
                ", geburtsdatum=" + geburtsdatum +
                '}';
    }
}
