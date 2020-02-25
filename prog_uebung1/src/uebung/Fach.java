package uebung;

/**
 * Created by Nico on 02.05.2017.
 */
public class Fach
{
    private int semester;
    private String kursName;
    private String studiengang;

    public Fach(int semester, String kursName, String studiengang)
    {
        if(semester>0&&semester<7) this.semester = semester;
        else System.out.println("falsche Semester Zahl");
        this.kursName = kursName;
        this.studiengang = studiengang;
    }

    public Fach()
    {
        this.semester = 0;
        this.kursName = "";
        this.studiengang = "";
    }

    public Fach(Fach f)
    {
        this.semester = f.getSemester();
        this.kursName = f.getKursName();
        this.studiengang = f.getStudiengang();
    }

    public int getSemester()
    {
        return semester;
    }

    public String getKursName()
    {
        return kursName;
    }

    public String getStudiengang()
    {
        return studiengang;
    }

    public void setSemester(int semester)
    {
        if(semester>0&&semester<7) this.semester = semester;
        else System.out.println("falsche Semester Zahl");
    }

    public void setKursName(String kursName)
    {
        this.kursName = kursName;
    }

    public void setStudiengang(String studiengang)
    {
        this.studiengang = studiengang;
    }

    @Override
    public String toString()
    {
        return "\nFach:" +
                "\nsemester:\t" + semester +
                "\nkursName:\t" + kursName +
                "\nstudiengang:\t" + studiengang;
    }
}
