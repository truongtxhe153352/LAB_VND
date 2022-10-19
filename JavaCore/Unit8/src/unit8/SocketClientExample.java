/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author truon
 */
public class SocketClientExample {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9245);
        System.out.println("CLIENT start sending...");
        try (DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
            output.writeUTF("Hello Server");
            System.out.println("Server Say: " + input.readUTF());
        } finally {
            socket.close();
        }
    }
}
