/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit8;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author truon
 */
public class DatagramClientExample {

    //DatagramSocket socket;
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        byte[] bytes = new byte[100];
        bytes = "Client say Xin Chao".getBytes();
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, 4445);
        socket.send(packet);
        packet = new DatagramPacket(bytes,bytes.length);
        socket.receive(packet);
        
        System.out.println("From Server: " + new String(packet.getData(), 0, packet.getLength()));
        socket.close();
    }
}
