package com.company;

import java.io.FilterInputStream;
import java.io.InputStream;

/**
 * Created by Nico on 10.07.2017.
 */
public class DecryptInputStream extends FilterInputStream
{
    private InputStream in;

    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    protected DecryptInputStream(InputStream in)
    {
        super(in);
        this.in = in;
    }


    public final byte readByte() throws Exception
    {
        int ch = in.read();
        if (ch < 0)
        {
            return -1;
        }
            return (byte) (ch);
    }
}
