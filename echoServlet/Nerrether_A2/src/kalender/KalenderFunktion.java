package kalender;
/** 
 * Beschreibung: Algorithmen fuer Kalenderfunktionen. Insbesondere Schaltjahrregel, Tagesnummer
 * Diese Algorithmen sind zusammengetragen aus verschiedenen Quellen.
 * 
 * Bitte veraendern Sie diese Datei nicht! 
 * Falls Sie zusaetzliche Funktionen benoetigen, legen Sie eine eigene java-Datei an!
 * @author bannert
 */

public class KalenderFunktion
{
/**
 =========================================================================
 * Funktion:    istSchaltjahr( int jahr )
 * Parameter:    jahr (int)    : das Jahr, das auf Schaltjahr ueberprueft wird
 * Rueckgabe:    (boolean)     : true, wenn das Jahr ein Schaltjahr ist
 *                             : false,  wenn das Jahr nicht Schaltjahr ist
 * Beschreibung: jedes 4.te Jahr ist Schaltjahr,
 * (wenn sich das Jahr durch 4 ohne Rest teilen laesst, ist es Schaltjahr )
 * Ausnahme davon jedes 100.te Jahr ist kein Schaltjahr
 * (wenn sich das Jahr durch 100 ohne Rest teilen laesst, ist es kein Schaltjahr)
 * Ausnahme davon jedes 400.ter Jahr    ist wieder Schaltjahr
 * (wenn sich das Jahr durch 400 ohne Rest teilen laesst, ist es Schaltjahr)
 * =========================================================================
 */
public boolean istSchaltjahr(int jahr)
 {
    return ((jahr%4 == 0 )&&((jahr%100 != 0 )||( jahr%400 == 0 )));
 }

/**
 =========================================================================
 * Funktion:    schaltjahr(int jahr)
 * Parameter:    jahr (int) : das Jahr, das auf Schaltjahr ueberprueft wird
 * Rueckgabe:    (int)      :     1 = true, wenn das Jahr ein Schaltjahr ist
 *                          :     0 = false,  wenn das Jahr nicht Schaltjahr ist
 * Beschreibung: siehe --> istSchaltjahr(long jahr)
 * =========================================================================
 */
 public int schaltjahr(int jahr)
 {
    if(istSchaltjahr(jahr)) return 1;
    else return 0;
 }

/**
 =========================================================================
 * Funktion:    tagesnummer(int tag, int monat, int jahr)
 * Parameter:    tag (long)       : Tag
 *               monat (long)     : Monat
 *               jahr (long)      : Jahr
 * Rueckgabe:    n (int)         : Tagesnummer rel. zum Jahresanfang
 *                               (1=1.1.,2=2.1.,...365/366=31.12)
 * Beschreibung: Algorithmus von Robertson
 * =========================================================================
 */
 public int tagesnummer(int tag, int monat, int jahr)
 {
   int d, e;
   d = (monat+10)/13;
   e = tag + (611*(monat+2))/20 - 2*d - 91;
   return (e + schaltjahr (jahr) * d );
 }

/**
 =========================================================================
 * Funktion:    wochentag_im_jahr(int jahr, int n)
 * Parameter:    jahr (long)    : Jahr
 *                n (int)       : Tagesnummer rel. zum Jahresanfang
 *                              (1=1.1.,2=2.1.,...365/366=31.12)
 * Rueckgabe:    (int) : Wochentag (0=So, 1=Mo,..., 6=Sa)
 * Beschreibung: Algorithmus von Zeller
 * =========================================================================
 */
 public int wochentag_im_jahr(int jahr, int n)
 {
   int j, c;
   j = (jahr-1) % 100;
   c = (jahr-1) / 100;
   return (28+j+n+(j/4)+(c/4)+5*c) % 7;
 }
 
	/**
	 * =========================================================================
	 * Funktion: ostersonntag(int jahr) 
	 * Parameter: jahr (int) : Jahr 
	 * Rueckgabe: (int) : Tagesnummer des Ostersonntag rel. zum Jahresanfang Beschreibung:
	 * Algorithmus "Computus ecclesiasticus" 325 n.Chr. wurde Ostern auf den
	 * Sonntag nach dem ersten Fruehlingsvollmond festgelegt. Damit liegt Ostern
	 * zwischen dem 22. Maerz und dem 25. April.
	 * =========================================================================
	 */
	 public int ostersonntag(int jahr) {
		int gz, jhd, ksj, korr, so, epakte, n;

		gz = (jahr % 19) + 1;
		jhd = jahr / 100 + 1;
		ksj = (3 * jhd) / 4 - 12;
		korr = (8 * jhd + 5) / 25 - 5;
		so = (5 * jahr) / 4 - ksj - 10;
		epakte = (11 * gz + 20 + korr - ksj) % 30;
		if ((epakte == 25 && gz > 11) || epakte == 24)
			epakte++;
		n = 44 - epakte;
		if (n < 21)
			n = n + 30;
		n = n + 7 - (so + n) % 7;
		n += schaltjahr(jahr);
		return n + 59;
	}
} // end of class