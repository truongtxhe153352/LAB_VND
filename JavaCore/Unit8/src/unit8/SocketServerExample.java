/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author truon
 */
public class SocketServerExample {

    public SocketServerExample(int serverPort) throws IOException {
        ServerSocket serverSock = new ServerSocket(serverPort);
        System.out.println("SERVER Listening...");
        while (true) {
            Socket socket = serverSock.accept();
            Thread th = new Thread() {
                @Override
                public void run() {
                    try (DataInputStream input = new DataInputStream(socket.getInputStream());
                            DataOutputStream output = new DataOutputStream (socket.getOutputStream())) {
                        System.out.println("Client Say: " + input.readUTF());
                        output.writeUTF("I'm a socket server!");
                    } catch (Exception e) {
                    }
                }
            };
            th.start();
        }
    }

    public static void main(String[] args) throws IOException {
        new SocketServerExample(9245);
        
    }
}
