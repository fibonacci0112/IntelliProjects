package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer
{
    public static final int PORT = 7777;

    public static void main(String[] args) throws IOException
    {
        boolean laufweiter = true;
        while (laufweiter)           //nachdem sich ein Client abmeldet wird geprüft ob der Server den nächsten Client annehmen soll
        {
            ServerSocket serverSocket = null;
            try
            {
                serverSocket = new ServerSocket(PORT);
            } catch (IOException e)
            {
                System.err.println("Could not listen on port: " + PORT);
                System.exit(1);
            }
            Socket clientSocket = null;
            try
            {
                System.out.println("Listening on port " + PORT + " ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e)
            {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
            {

                //Rechtschreibprüfung startet hier

                String korrigiert = inputLine;

                while(korrigiert.contains("  ")) //es wird überprüft ob String mehrere leerzeichen hintereinander hat
                {
                    korrigiert = korrigiert.replaceAll("  ", " "); //Alle doppelten leerzeichen werden durch einzelne ersetzt
                }

                out.println(korrigiert); //text wird an Client gesendet

                if (inputLine.equals("bye"))
                {
                    break;                          //beendet die verbindung aber wartet dann auf neue Verbindung
                } else if (inputLine.equals("quit"))
                {
                    laufweiter = false;             //variable LaufWeiter wird false und damit die dauerschleife die einen
                    break;                           //neuen Client erwartet beendet.
                }
            }
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        }
    }
}

