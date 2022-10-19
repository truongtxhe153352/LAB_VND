/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nio;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

/**
 *
 * @author truon
 */
public class RandomAccessFileExample {
    public static void main(String[] args) {
        Path path = Paths.get("D:\\hanoijava3.txt");
        ByteBuffer buffer = ByteBuffer.allocate(14);
        try (FileChannel fc = FileChannel.open(path, READ, WRITE)){
            fc.read(buffer);
            System.out.println(buffer.array());
            fc.position(0);
            byte[] bytes = "Trần ".getBytes();
            fc.write(ByteBuffer.wrap(bytes));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
