/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.APPEND;

/**
 *
 * @author truon
 */
public class LinkFileExample {

    public static void main(String[] args) {
        Path source = Paths.get("D:\\Try_Text\\hanoijava.txt");
        Path target = Paths.get("D:\\Try_Text2\\hanoijava1.txt");
        Charset charset = Charset.forName("utf8");
        try (BufferedWriter writer = Files.newBufferedWriter(source, charset, APPEND)) {
//            writer.write("\n Java NIO 2 example \r\n");
        } catch (Exception e) {
            System.out.println("e");
        }

        try (BufferedReader reader = Files.newBufferedReader(target, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
        }
    }
}
