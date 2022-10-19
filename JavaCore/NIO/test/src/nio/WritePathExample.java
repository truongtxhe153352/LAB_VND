/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nio;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
/**
 *
 * @author truon
 */
public class WritePathExample {
    public static void main(String[] args) {
        Path path = Paths.get("D:\\hanoijava.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("utf8"), CREATE, APPEND)){
            for (int i = 0; i < 10; i++) {
             writer.write("nguyên văn " + i + "\r\n");
            } 
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
