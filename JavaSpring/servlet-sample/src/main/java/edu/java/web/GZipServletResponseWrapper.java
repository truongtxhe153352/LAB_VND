package edu.java.web;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class GZipServletResponseWrapper extends HttpServletResponseWrapper {
    private GZipServletOutputStream stream = null;
    private PrintWriter writer = null;

    public GZipServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public void close() throws IOException {
        if (writer != null && stream != null) {
            writer.close();
            stream.close();
        }
    }

    @Override
    public void flushBuffer() throws IOException {
        if (writer != null) writer.flush();
        stream.flush();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (stream != null) return stream;
        stream = new GZipServletOutputStream(getResponse().getOutputStream());
        return stream;
    }

    public PrintWriter getWriter() throws IOException {
        if (writer == null) {
            stream = (GZipServletOutputStream) getOutputStream();
            String encoding = getResponse().getCharacterEncoding();
            writer = new PrintWriter(new OutputStreamWriter(stream, encoding));
        }
        return writer;
    }
}
