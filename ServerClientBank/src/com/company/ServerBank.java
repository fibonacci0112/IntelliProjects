package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerBank implements Runnable
{
    Socket socket;
    boolean clientcontinues = true;

    ServerBank(Socket socket)
    {
        this.socket = socket;
    }

    public static void main(String args[]) throws Exception
    {

        ServerSocket serverSocket = new ServerSocket(6686);

        while (true)
        {
            Socket socket = serverSocket.accept();
            System.out.println("Connected" + socket.toString());
            new Thread(new ServerBank(socket)).start();
        }
    }

    public void run()
    {
        try
        {

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (clientcontinues)
            {
                out.println("Welche Währung wollen Sie eingeben?(DM oder EU)");
                double currencyvalue = 0;
                String extension = "";
                switch (inFromClient.readLine().toUpperCase())
                {
                    case "EU":
                        extension = "DE";
                        currencyvalue = 1.96;
                        break;
                    case "DM":
                        extension = "EU";
                        currencyvalue = 0.51;
                        break;
                    default:
                        out.println("Falsche Eingabe. Beliebige Eingabe zum Fortfahren.");
                        inFromClient.readLine();
                        continue;
                }
                out.println("Welche Wert wollen Sie umrechnen?");
                double result;
                try
                {
                    result = currencyvalue * Double.parseDouble(inFromClient.readLine());
                } catch (NumberFormatException e)
                {
                    out.println("Falsche Eingabe. Beliebige Eingabe zum Fortfahren.");
                    inFromClient.readLine();
                    continue;
                }

                out.println("Wert in " + extension + " " + result + ". Wollen Sie noch eine Eingabe machen?(J oder N)");
                switch (inFromClient.readLine().toUpperCase())
                {
                    case "J":
                        continue;
                    case "N":
                        clientcontinues = false;
                        out.println("done \n");
                        break;
                    default:
                        out.println("Falsche Eingabe. Beliebige Eingabe zum Fortfahren.");
                        inFromClient.readLine();
                        continue;
                }
            }


            socket.close();
        } catch (IOException e)
        {
            System.out.println(e);
        }
    }
}
