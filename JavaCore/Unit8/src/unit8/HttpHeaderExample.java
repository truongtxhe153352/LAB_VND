/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit8;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 *
 * @author truon
 */
public class HttpHeaderExample {
    public static void main(String[] args) throws MalformedURLException, IOException {
        URL url = new URL("http://www.coccoc.vn/");
        URLConnection connection = (HttpURLConnection)url.openConnection();
        System.out.println("http method: " + connection);
       Map<String, List<String>> headers = connection.getHeaderFields();
        BiConsumer<String, List<String>> header = (key, values)->{
            System.out.println("Key: " + key + "; Values: " + values);
        };
        headers.forEach(header);
    }
}
