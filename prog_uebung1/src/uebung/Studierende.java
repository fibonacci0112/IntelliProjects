package uebung;

/**
 * Created by Nico on 03.05.2017.
 */
public interface Studierende
{
    public void setMatrikelnummer(int matrikelnummer);

    public void setSeminarGruppe(String seminarGruppe);

    public void setFaecher(Fach[] faecher);

    public void setFach(Fach f, int index);

    public int getMatrikelnummer();

    public String getSeminarGruppe();

    public Fach[] getFaecher();
}
