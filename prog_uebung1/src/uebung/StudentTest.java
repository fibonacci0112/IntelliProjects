package uebung;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Nico on 03.05.2017.
 */
public class StudentTest
{
    Fach[] f = new Fach[]{new Fach()};
    Student s = new Student("", "", "", 0, "", f);

    @org.junit.Test
    public void getSetMatrikelnummer() throws Exception
    {
        s.setMatrikelnummer(5);
        assertEquals(5, s.getMatrikelnummer());
    }

    @org.junit.Test
    public void getSetSeminarGruppe() throws Exception
    {
        s.setSeminarGruppe("test");
        assertEquals("test", s.getSeminarGruppe());
    }

    @org.junit.Test
    public void getSetFaecher() throws Exception
    {
        s.setFaecher(f);
        assertEquals(f[0], s.getFaecher()[0]);
    }


    @org.junit.Test
    public void TestToString() throws Exception
    {
        Student expected = new Student("", "", "", 0, "", f);
        assertEquals(expected.toString(), s.toString());
    }

}