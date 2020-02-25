package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main
{
    public static void main(String[] args)
    {
        FileInputStream fiInSt;
        FileOutputStream fiOuSt;
        DecryptInputStream deInSt;
        EncryptOutputStream enOuSt;
        String pathvar = "./testinput.txt";
        String pathvar2 = "./testoutput.txt";
        String pathvar3 = "./testdecrypt.txt";
        File inputFile = new File(pathvar);
        File outputFile = new File(pathvar2);
        File decryptfile = new File(pathvar3);

        byte[] schluessel = new byte[5];
        System.out.print("Schlüssel: ");
        for (byte i = 0; i < schluessel.length; i++)
        {
            schluessel[i]=(byte)(i+50);
            System.out.print((char)schluessel[i] + ", ");
        }
        System.out.println();
        try
        {
            byte[] data = new byte[]{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a',};
            fiOuSt = new FileOutputStream(inputFile);
            fiOuSt.write(data);
            System.out.print("Daten:\t\t\t\t  ");
            for (int i = 0; i < data.length; i++) System.out.print((char)data[i] + ", ");
            System.out.println();
            fiInSt = new FileInputStream(inputFile);
            fiOuSt = new FileOutputStream(outputFile);
            deInSt = new DecryptInputStream(fiInSt);
            enOuSt = new EncryptOutputStream(fiOuSt);

            byte einzelnesbyte;


            System.out.print("Verschlüsselte Daten: ");
            int count=0;
            while ((einzelnesbyte = deInSt.readByte()) != -1)
            {
                einzelnesbyte = (byte) (einzelnesbyte ^ schluessel[count]);
                System.out.print((char)einzelnesbyte + ", ");
                enOuSt.write(einzelnesbyte);
                count++;
                if(count>=schluessel.length) count=0;
            }
            System.out.println();
            deInSt = new DecryptInputStream(new FileInputStream(outputFile));
            enOuSt = new EncryptOutputStream(new FileOutputStream(decryptfile));
            System.out.print("Zurück entschlüsselt: ");
            count=0;
            while ((einzelnesbyte = deInSt.readByte()) != -1)
            {
                einzelnesbyte = (byte) (einzelnesbyte ^ schluessel[count]);
                System.out.print((char)einzelnesbyte + ", ");
                enOuSt.write(einzelnesbyte);
                count++;
                if(count>=schluessel.length) count=0;
            }
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}
