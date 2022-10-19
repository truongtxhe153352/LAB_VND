package Java_Collection2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConcurrencyExample {
   public static void addData(List list) throws InterruptedException {
       while (true){
         double random = (Math.random()*100);
         list.add(random);
           System.out.println("add new data " + random);
           Thread.sleep(100L);
       }
   }

   public static void printData(List list){
       while (true){
           try {
               System.out.println("=========");
               list.forEach(value-> System.out.println("value = " + value));
           } catch (Exception e){
               e.printStackTrace();
               System.exit(1);
           }
       }
   }
    public static void main(String[] args) {
        List list = new ArrayList<>();
       new Thread(()-> {
           try {
               addData(list);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }).start();

        
    }
}
