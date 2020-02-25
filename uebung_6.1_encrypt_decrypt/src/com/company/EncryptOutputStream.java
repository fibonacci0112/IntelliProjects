package com.company;

import java.io.FilterOutputStream;
import java.io.OutputStream;

/**
 * Created by Nico on 10.07.2017.
 */
public class EncryptOutputStream extends FilterOutputStream
{
    /**
     * Creates an output stream filter built on top of the specified
     * underlying output stream.
     *
     * @param out the underlying output stream to be assigned to
     *            the field <tt>this.out</tt> for later use, or
     *            <code>null</code> if this instance is to be
     *            created without an underlying stream.
     */

    private OutputStream out;
    public EncryptOutputStream(OutputStream out)
    {
        super(out);
        this.out=out;
    }

    public void write(byte b) throws Exception
    {
        out.write(b);
    }
}
