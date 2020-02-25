package io;

import java.util.Scanner;

public class Eingabe {

	private static Scanner scan = new Scanner(System.in);

	public static String einlesen() {
		return scan.next();
	}

	public static int monat() {
		try {
			int auswahlMonat = Integer.parseInt(Eingabe.einlesen());
			if (auswahlMonat <= 12 && auswahlMonat > 0) {
				return auswahlMonat;
			}
		} catch (Exception e) {
		}
		Ausgabe.print("Fehler bei der Eingabe\n");
		return 0;

	}

	public static int jahr() {
		try {
			int auswahlJahr = Integer.parseInt(Eingabe.einlesen());
			if (auswahlJahr <= 9999 && auswahlJahr > 1581) {
				return auswahlJahr;
			}
		} catch (Exception e) {
		}
		Ausgabe.print("Fehler bei der Eingabe\n");
		return 0;

	}
}
