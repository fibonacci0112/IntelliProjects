package io;

import kalender.Kalender;

public class Ausgabe {

public static void menue(){
	System.out.println("Auswahl:   [1]	Kalender fuer das ganze Jahr");
	System.out.println("Auswahl:   [2]	Ausgabe Monatsblatt");
	System.out.println("Auswahl:   [3]	Kalenderformat auswaehlen ");
	System.out.println("Auswahl:   [4]	Jahresplan");
	System.out.println("Auswahl:   [5]	Jahresplan mit Feiertagen und Events");
	System.out.println("Auswahl:   [0]	Ende des Programms");
	System.out.println();
	System.out.println("Bitte Zahl eingeben.");
}
		
	public static void print(String spezifischeausgabe){
	System.out.println(spezifischeausgabe);
	}
}
