
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author truon
 */
public class CopingAndMovingExample {

    public static void main(String[] args) throws IOException {
        Path source = Paths.get("D:\\Try_Text\\hanoijava.txt");
        Path target = Paths.get("D:\\Try_Text2\\hanoijava1.txt");
// chứa phương thức làm việc với file
    //    Files.copy(source, target, REPLACE_EXISTING);
   //   Files.delete(target);
   Files.createLink(target, source);
    }
}
