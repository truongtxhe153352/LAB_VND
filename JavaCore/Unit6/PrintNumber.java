/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit6;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author truon
 */
public class PrintNumber implements Runnable {

    private int number = 1;
    private boolean alive = true;

    public int getNumber() {
        return number;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public synchronized void run() {
        Thread current = Thread.currentThread();
                while (number < 30) {
            number++;
            System.out.println(current.getName() + " number is\"" + number + "\"");
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(PrintNumber.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
        System.out.println(current.getName() + " is stoped!");
    }

    public static void main(String[] args) throws InterruptedException {
        PrintNumber number = new PrintNumber();
//        Thread thread = new Thread(number);
//        //  thread.getName();
//        thread.setName("Hanoi_Thread");
//        // khi tất cả kết thúc chương trình sẽ tự động kết thúc
//        
//        thread.setDaemon(true);
//        thread.start();
//        // chờ thread này chết rồi thread khác mới được chạy
//        // Thread.currentThread().join();
//
////        while (thread.isAlive()) {            
////            if (number.getNumber()%10 == 0) number.setAlive(false);
////            //Thread.sleep(1000);
////            // Tạo độ trễ
////            TimeUnit.SECONDS.sleep(1);
////        }
//      //  Thread.currentThread().join();
//        System.out.println("Main Thread say hello");
//        System.out.println("Main THread say goodbye");

       Thread thread1 = new Thread(number);
       thread1.setName("FSoft_Thread 1");
       thread1.start();
       
       Thread thread2 = new Thread(number);
       thread2.setName("FSoft_Thread 2");
       thread2.start();
    }
}
