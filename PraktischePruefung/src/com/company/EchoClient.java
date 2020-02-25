package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EchoClient
{

    public static void main(String[] args) throws IOException
    {
        int port=0;
        Scanner s = new Scanner(System.in); //Zur Eingabe des Ports
        System.out.println("Geben Sie den gewünschten Port an:");
        while(port==0)
        {
            try{port = s.nextInt();}catch(InputMismatchException e ){System.out.println("False Eingabe");System.exit(1);}
            //Sichere Abfrage nach dem Port. Nur bei Eingabe einer Zahl wird fortgefahren
        }
        String hostname = "localhost";
        try (Socket echoSocket = new Socket(hostname, port);
             PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)))
        {
            String userInput = null;
            while ((userInput = stdIn.readLine()) != null)
            {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
                if (userInput.equals("bye") || userInput.equals("quit"))
                {
                    in.close();                 //Streams und Socket werden geschlossen
                    out.close();                //Client meldet sich vom Server ab
                    echoSocket.close();
                    break;
                }
            }
        } catch (UnknownHostException e)
        {
            System.err.println("Don't know about host: " + hostname);
            System.exit(1);
        } catch (IOException e)
        {
            System.err.println("Couldn't get I/O for " + "the connection to: " + hostname);
            System.exit(1);
        }
    }
}
