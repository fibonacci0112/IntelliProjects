package com.company;

import java.io.*;
import java.net.*;

class Client1
{
    public static void main(String argv[])
    {
        try
        {
                Socket clientSocket = new Socket("localhost", 22022);
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("FROM SERVER: " + inFromServer.readLine());
                clientSocket.close();

        } catch (Exception e) {System.out.println(e.toString());}
    }
}
