/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit6;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 *
 * @author truon
 */
public class PrintNumbers implements Runnable {
    
    private Integer number = new Integer(1);
    
    @Override
    public void run() {
        Thread current = Thread.currentThread();
        while (true) {
            number++;
            System.out.println(current.getName() + " number is\"" + number + "\"");
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(PrintNumbers.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (number % 10 == 0) {
                break;
            }
        }
        System.out.println(current.getName() + " is stopped!");
    }
    
    public static void main(String[] args) throws Exception {
        PrintNumbers numbers = new PrintNumbers();
//        Callable<Object> value = Executors.callable(numbers);
//        System.out.println("main say hello");
//        value.call();
//        System.out.println("main say goobye");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        IntStream.range(0, 6).forEach(i -> executor.submit(numbers));
    }
}
