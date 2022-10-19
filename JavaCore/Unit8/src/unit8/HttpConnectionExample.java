/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit8;

import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.ProcessBuilder.Redirect.Type.APPEND;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author truon
 */
public class HttpConnectionExample {

    public static void main(String[] args) throws MalformedURLException, IOException {
        URL url = new URL("https://fap.fpt.edu.vn/");
        URLConnection connection = url.openConnection();
        Path path = Paths.get("test.html");
        int read;
        byte[] bytes = new byte[100];
        try (InputStream inputStream = connection.getInputStream();
                OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } finally {
            Desktop.getDesktop().open(path.toFile());
        }
    }
}
