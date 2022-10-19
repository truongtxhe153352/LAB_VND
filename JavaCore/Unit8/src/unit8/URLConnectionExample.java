/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit8;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;

/**
 *
 * @author truon
 */
public class URLConnectionExample {
    
    public static void main(String[] args) throws MalformedURLException, IOException {
        URL url = new URL("https://coccoc.vn/");
        URLConnection connection = url.openConnection();
        InputStream stream = connection.getInputStream();
        
        int read;
        byte[] bytes = new byte[100];
        while ((read = stream.read(bytes)) != -1) {            
            System.out.println(new String(bytes, 0, read));
        }
    }
}
