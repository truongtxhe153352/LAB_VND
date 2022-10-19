package edu.java.web;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.ColorType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@WebServlet(urlPatterns = "/synch", asyncSupported = true)
public class AsynchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final AsyncContext ctx = request.startAsync();
        ctx.setTimeout(60*1000);
        ctx.start(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3*1000L);
                    Writer writer = ctx.getResponse().getWriter();
                    URL obj = new URL("https://www.google.com.vn");
                    HttpURLConnection con = (HttpURLConnection)obj.openConnection();
                    con.setRequestMethod("GET");
                    con.addRequestProperty("Accept-Encoding", "gzip");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                    }
                        writer.write("Hello Async");
                    ctx.complete();

                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }








}
