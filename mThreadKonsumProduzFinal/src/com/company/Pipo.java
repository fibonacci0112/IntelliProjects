package com.company;

import java.io.*;

/**
 * Created by Nico on 05.07.2017.
 */
public class Pipo
{
    private DataOutputStream out;
    private DataInputStream in;
    public Pipo() throws IOException
    {
        PipedInputStream snk = new PipedInputStream();
        PipedOutputStream src = new PipedOutputStream();
        src.connect(snk);
        out = new DataOutputStream(src);
        in = new DataInputStream(snk);
    }

    public void put(int value) throws IOException
    {
        out.writeInt(value);
    }

    public int get() throws IOException
    {
        return in.readInt();
    }

    public void close() throws IOException
    {
        out.close();
    }
}
