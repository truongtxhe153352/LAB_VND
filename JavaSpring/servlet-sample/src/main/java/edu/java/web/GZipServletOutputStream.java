package edu.java.web;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class GZipServletOutputStream extends ServletOutputStream {
private GZIPOutputStream stream = null;

public GZipServletOutputStream(OutputStream output) throws IOException {
    stream = new GZIPOutputStream(output);
}

    @Override
    public void close() throws IOException {
        stream.close();
    }

    @Override
    public void flush() throws IOException {
        stream.flush();
    }

    @Override
    public void write(byte b[]) throws IOException {
        stream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        stream.write(b, off, len);
    }

    @Override
    public void write(int b) throws IOException {
        stream.write(b);
    }


    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}
