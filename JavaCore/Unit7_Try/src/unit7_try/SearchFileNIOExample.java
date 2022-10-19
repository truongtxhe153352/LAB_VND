/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit7_try;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.security.util.Length;

/**
 *
 * @author truon
 */
public class SearchFileNIOExample {

    /**
     * @param args the command line arguments //
     */
           public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("D:\\Try_Text\\hanoijava.txt");
        FileInputStream fileInput = null;
        FileChannel fileChannel = null;

        try {
            fileInput = new FileInputStream(file);
            fileChannel = fileInput.getChannel();

            long size = fileChannel.size();
            ByteBuffer buff = ByteBuffer.allocate((int)size);
            fileChannel.read(buff);
            buff.rewind();
            
            System.out.println(new String(buff.array(), "utf8"));
        } finally {
            if(fileChannel != null) fileChannel.close();
            if(fileInput != null) fileInput.close();
        }
    }
    }

