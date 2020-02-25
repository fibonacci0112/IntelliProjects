package com.company;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Nico on 10.07.2017.
 */
public class Client
{
    public static void main(String argv[])
    {
        boolean clientContinues=true;
        try
        {
            String sentence;
            String answer;
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            Socket clientSocket = new Socket("localhost", 6686);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while(clientContinues)
            {
                answer = inFromServer.readLine();
                System.out.println(answer);
                if (answer.contains("done"))
                {
                    break;
                }
                sentence = inFromUser.readLine();
                outToServer.writeBytes(sentence + '\n');
            }
            clientSocket.close();

        } catch (Exception e) {System.out.println(e.toString());}
    }
}

