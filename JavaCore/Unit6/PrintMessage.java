/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit6;

import java.sql.Time;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author truon
 */
public class PrintMessage implements Runnable {

    private String message;

    public PrintMessage(String message) {
        this.message = message;
    }

    public PrintMessage() {
    }

    @Override
    public void run() {
        String[] elenents = message.split(" ");
        Arrays.stream(elenents).forEach(ele -> {
            // Phương thức currentThread() trả về một tham chiếu của thread hiện đang thực thi.
            System.out.println(Thread.currentThread().getName() + " print" + ele);
            try {
                //  TimeUnit.SECONDS.sleep(1);
                Thread.sleep((int) (Math.random() * 3) * 1000);
            } catch (InterruptedException ex) {
                //  Logger.getLogger(PrintMessage.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
//        PrintMessage message = new PrintMessage("Say hello to everyone");
//        new Thread(message).start();
//        new Thread(message).start();
        new Thread(new PrintMessage("Say hello to everyone")).start();
        new Thread(new PrintMessage("Say hello to everyone")).start();
    }
}
