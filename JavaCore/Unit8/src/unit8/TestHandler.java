/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit8;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 *
 * @author truon
 */
public class TestHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String message = "<html><h2>This is the response</h2></html>";
        httpExchange.sendResponseHeaders(200, message.length());
        try {
            OutputStream out = httpExchange.getResponseBody();
            out.write(message.getBytes());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws IOException {
        TestHandler testHandler = new TestHandler();
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/test", testHandler);
        server.setExecutor(Executors.newFixedThreadPool(5));
        server.start();
    }
}
