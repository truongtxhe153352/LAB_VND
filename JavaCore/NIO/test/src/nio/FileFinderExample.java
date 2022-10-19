/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nio;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Matcher;

/**
 *
 * @author truon
 */
public class FileFinderExample {

    // 
    static class Finder extends SimpleFileVisitor<Path> {

        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.{txt}");

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (matcher.matches(file.getFileName())) {
                System.out.println("found: " + file);
            }
            return CONTINUE;
            //To change body of generated methods, choose Tools | Templates.
        }

    }

    public static void main(String[] args) throws IOException {
        Finder finder = new Finder();
        Path path = Paths.get("D:\\Try_Text");
        Files.walkFileTree(path, finder);
    }
}
