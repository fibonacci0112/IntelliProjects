package com.company;

import java.io.*;
import java.net.*;
import java.util.Date;

class Server
{
    public static void main(String argv[])
    {
        try
        {
            ServerSocket welcomeSocket = new ServerSocket(22022);
            while (true)
            {
                Socket connectionSocket = welcomeSocket.accept();
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                outToClient.writeBytes("" + new Date().toString() + '\n');
                connectionSocket.close();
            }
        } catch (Exception e){System.out.println(e.toString());}
    }
}
