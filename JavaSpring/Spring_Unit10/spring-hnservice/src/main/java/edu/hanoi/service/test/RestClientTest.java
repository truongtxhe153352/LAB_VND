package edu.hanoi.service.test;


import org.apache.commons.codec.binary.Base64;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class RestClientTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://localhost:8080/list/users");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("Accept", "Application/Json");

//        String account = "nguoidung:123456";
//        byte[] encodedAuth = Base64.encodeBase64(account.getBytes(StandardCharsets.UTF_8));
//        String basicAuth = "Basic " +  new String(encodedAuth);
//        connection.setRequestProperty("Authorization", basicAuth);


        String account = "username-random47:password-7700742181";
//       String account = "quantri:654321";
        String basicAuth = "Basic " +
                DatatypeConverter.printBase64Binary(account.getBytes());
        connection.setRequestProperty("Authorization", basicAuth);


        InputStream stream = connection.getInputStream();
        byte[] bytes = new byte[4 * 1024];
        int read = -1;
        try {
            while ((read = stream.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, read));
            }
        } finally {
            stream.close();
        }
    }
}
