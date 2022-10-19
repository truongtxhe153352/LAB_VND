/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author truon
 */
public class PathExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Path path = Paths.get("D:\\Unit7_Try");
//        System.out.println("This is: " + (Files.isDirectory(path) ? "folder" : "file") + "!");
//        System.out.println(Files.exists(path));
//        // lặp lại thư mục
//        try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)){
//            for (Path p : directoryStream) {
//                System.out.println(p.getFileName());
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }

// Hiển thị mình folder(3)
//        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
//            @Override
//            public boolean accept(Path entry) throws IOException {
//                return Files.isDirectory(entry);
//            }
//        };
//
//        Path path = Paths.get("D:\\Try_text");
//        System.out.println("This is: " + (Files.isDirectory(path) ? "folder" : "file") + "!");
//        System.out.println(Files.exists(path));
//        // lặp lại thư mục  (path, filter); Hiển thị mình folder(path, filter)
//        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path, "*.{java,txt}")) {
//            for (Path p : directoryStream) {
//                System.out.println(p.getFileName());
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
        Path path = Paths.get("D:\\");
        Path path2 = path.resolve("hanoijava.txt");

        Charset charset = Charset.forName("UTF-8");
        try (BufferedReader reader = Files.newBufferedReader(path2, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
