/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit6;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author truon
 */
public class SimpleThreadSample {

    public static void main(String[] args) {

        new Thread(
                () -> {
                    Arrays.stream(args).forEach(ele
                            -> {
                        try {
                            Thread.sleep(1000);
                            System.out.println(ele);
                            // );
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SimpleThreadSample.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                }
        ).start();
    }
}
