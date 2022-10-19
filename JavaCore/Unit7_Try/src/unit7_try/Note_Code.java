/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit7_try;

import java.io.Serializable;

/**
 *
 * @author truon
 */
public class Note_Code {
//        int[] values = {120, 105, 110, 32, 99, 104, -61, -96, 111};
//        byte [] bytes = new byte[10];
//        for (int i = 0; i < bytes.length; i++) {
//            bytes[i] = (byte).values[i];
//        }
//        System.out.println(new String(bytes, "utf8"));

    // 3
//         File file = new File("C:\\Temp");
//        if (file.isDirectory()) {
//            System.out.println("This is folder!");
//        } else {
//            System.out.println("This is file");
//        }
    //     System.out.println("This is " + (file.isFile()?"file":"folder") + "!");
//     File file = new File("C:\\Users\\truon\\Downloads\\Giay_to_cong_ty_VNDIRECT");
//        // System.out.println("This is " + (file.isFile() ? "file" : "folder") + "!");
//        File[] files = file.listFiles();
//        for (File f : files) {
//            System.out.println(f.getName() + " : " + f.length() + "bytes");
//       }
//     private static long getSize(File file){
//        if (file.isFile()) {
//            return file.length();
//        }
//        File[] files = file.listFiles();
//        int length = 0;
//        for (int i = 0; i < files.length; i++) {
//            if (files[i].isDirectory()) {
//                length += getSize(files[i]);
//                continue;
//            }
//            length += files[i].length();
//        }
//        return length;
//    }
//    public static void main(String[] args) {
//        // TODO code application logic here
//       File file = new File("C:\\Users\\truon\\Downloads\\Giay_to_cong_ty_VNDIRECT");
//        System.out.println("Size: " + getSize(file)/(1024*1024) + " MB");
//    }
    //        if (file.isFile()) {
//            return file.length();
//        }
//
//        AtomicLong length = new AtomicLong(0);
//        Arrays.stream(file.listFiles()).forEach(f -> {
//            length.getAndSet(length.longValue()
//                    + (f.isDirectory() ? getSize(f) : f.length()));
//        });
//        return length.longValue();
//    }
//
//    public static void main(String[] args) {
//        // TODO code application logic here
//        File file = new File("C:\\Temp");
//        System.out.println("Size: " + getSize(file)/(1024*1024) + "MB");
//    }
    //   public static void main(String[] args) {
//        File file = new File("D://hanoijava.txt");
//        try (InputStream inputStream = new FileInputStream(file)){
//            byte[] bytes = new byte[16];
//            inputStream.read(bytes);
//            System.out.println(new String(bytes));
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//       public static void main(String[] args) {
//        File file = new File("D://hanoijava.txt");
//        try (InputStream inputStream = new FileInputStream(file)){
//            byte[] bytes = new byte[4 *1024];
//            int read = -1;
//            StringBuilder builder = new StringBuilder();
//            while ((read = inputStream.read(bytes)) != -1) {                
//                builder.append(new String(bytes, 0, read));
//            }
//            System.out.println("[" + builder + "]");
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//    public static void main(String[] args) {
//       File file = new File("D:\\hanoijava.txt");
//       // Doc ghi du lieu ngau nhien
//        try (RandomAccessFile randomAccess = new RandomAccessFile(file, "rw")){
//            // di chuyen den bat ki vi tri nao cua file
//            randomAccess.seek(8);
//            byte[] bytes = new byte[4*102];
//            int read = randomAccess.read(bytes);
//            System.out.println(new String(bytes, 0, read, "utf8"));
//            
//            randomAccess.seek(file.length());
//            randomAccess.write("\r\n".getBytes());
//            randomAccess.writeChars("Hello Co Can");
//            
//            // 
//            Desktop.getDesktop().open(file);
//        } catch (Exception e) {
//            System.out.println("error here: " + e);
//        }
//     public static void main(String[] args) {
//       File file = new File("D:\\hanoijava.txt");
//       // Doc ghi du lieu ngau nhien
//        try (FileWriter writer = new FileWriter(file, true)){
//            writer.write("\r\n");
//            writer.write("Tran Thi B");
//            Desktop.getDesktop().open(file);
//        } catch (Exception e) {
//        }
//    }
//     public static void main(String[] args) throws FileNotFoundException, IOException {
//       File file = new File("D:\\hanoijava.txt");
//// giu noi dung dang ton tai va noi them du lieu vao file
//       try (FileWriter writer = new FileWriter(file, true)){
//            writer.write("\r\n");
//            writer.write("Tran Thi B");
//            Desktop.getDesktop().open(file);
//        } catch (Exception e) {
//            System.out.println("error: " + e);
//        }
//        FileReader reader = new FileReader(file);
//        reader = new FileReader(file);
//        char[] buffer = new char[4*1024];
//        int read = -1;
//        StringBuilder builder = new StringBuilder();
//        while ((read = reader.read(buffer)) != -1) {  
//            // 
//            builder.append(buffer, 0, read);
//        }
//        System.out.println(builder);
//    }
//public class PrintMessage implements Runnable, Serializable {
//
//    /**
//     * @param args the command line arguments //
//     */
//    private String message;
//
//    public PrintMessage() {
//    }
//
//    public PrintMessage(String message) {
//        this.message = message;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    @Override
//    public void run() {
////        String[] elements = message.split(" ");
////        Arrays.stream(elements).forEach(ele -> {
////            System.out.println(Thread.currentThread().getName() + "print" + ele);
////            try {
////                TimeUnit.SECONDS.sleep(1);
////            } catch (InterruptedException ex) {
////                Logger.getLogger(PrintMessage.class.getName()).log(Level.SEVERE, null, ex);
////            }
////        });
//    }
//
//    public static void main(String[] args) throws IOException {
//        File folder = new File("D:\\");
//        ObjectOutputStream output = null;
//        FileOutputStream fileOutput = new FileOutputStream(new File(folder, "hanoijava2"));
//        output = new ObjectOutputStream(fileOutput);
//        PrintMessage printMessage = new PrintMessage();
//        output.writeObject(new PrintMessage("Can Noi Roi"));
//    }
    
    
    
    
    
//   public class PrintMessage implements Runnable, Serializable {
//
//    /**
//     * @param args the command line arguments //
//     */
//    private String message;
//
//    public PrintMessage() {
//    }
//
//    public PrintMessage(String message) {
//        this.message = message;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    @Override
//    public void run() {
////        String[] elements = message.split(" ");
////        Arrays.stream(elements).forEach(ele -> {
////            System.out.println(Thread.currentThread().getName() + "print" + ele);
////            try {
////                TimeUnit.SECONDS.sleep(1);
////            } catch (InterruptedException ex) {
////                Logger.getLogger(PrintMessage.class.getName()).log(Level.SEVERE, null, ex);
////            }
////        });
//    }
//
//    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//        File folder = new File("D:\\");
//        ObjectInputStream input = null;
//        input = new ObjectInputStream(new FileInputStream(new File(folder, "hanoijava2")));
//        Object obj = input.readObject();
//        Method method = obj.getClass().getMethod("run", new Class[0]);
//        method.invoke(obj, new Object[0]);
//        input.close();
//    }
//
//}
    
    
    
    
//    public static void main(String[] args) throws FileNotFoundException, IOException {
//        File file = new File("D:\\Try_Text\\hanoijava.txt");
//        FileInputStream fileInput = null;
//        FileChannel fileChannel = null;
//
//        try {
//            fileInput = new FileInputStream(file);
//            fileChannel = fileInput.getChannel();
//
//            long size = fileChannel.size();
//            ByteBuffer buff = ByteBuffer.allocate((int)size);
//            fileChannel.read(buff);
//            buff.rewind();
//            
//            System.out.println(new String(buff.array(), "utf8"));
//        } finally {
//            if(fileChannel != null) fileChannel.close();
//            if(fileInput != null) fileInput.close();
//        }
//    }
    
    
//    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
//        File file = new File("D:\\hanoijava.txt");
//        RandomAccessFile raf = new RandomAccessFile(file, "rw");
//        
//        FileChannel channel = raf.getChannel();
//        FileLock lock = channel.tryLock(0, Long.MAX_VALUE, false);
//        
//        Desktop.getDesktop().edit(file);
//        Thread.sleep(15*1000l);
//        lock.release();
//    }
    
    
    
    
//    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
//        File sourceFile = new File("D:\\hanoijava.txt");
//        File desFile = new File("D:\\hanoijava3.txt");
//        FileChannel srcChannel = null;
//        FileChannel desChannel = null;
//
//        srcChannel = new FileInputStream(sourceFile).getChannel();
//        desChannel = new FileOutputStream(desFile).getChannel();
//        srcChannel.transferTo(0, srcChannel.size(), desChannel);
//
//        Desktop.getDesktop().open(sourceFile.getParentFile());
//    }
    
    
    
//     public static void main(String[] args) throws FileNotFoundException, IOException {
//        Charset charset = Charset.forName("utf-8");
//        Pattern pattern = Pattern.compile("s\\S", Pattern.CASE_INSENSITIVE);
//        
//        File file = new File("D:\\hanoijava.txt");
//        FileInputStream stream = new FileInputStream(file);
//        FileChannel channel = stream.getChannel();
//        ByteBuffer bytes = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
//        CharBuffer chars = charset.decode(bytes);
//        Matcher matcher = pattern.matcher(chars);
//        if (matcher.find()) {
//            System.out.println("Found at: " + Integer.toString(matcher.start()));
//            System.out.println("Value: " + chars.subSequence(matcher.start(), matcher.end()) + "");
//        } else {
//            System.out.println("Not found");
//        }
}

