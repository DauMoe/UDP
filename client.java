package bitch;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lehuy
 */
public class client {
    DatagramSocket client;
    DatagramPacket reqPacket, respPacket;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    ByteArrayOutputStream baos;
    ByteArrayInputStream bais;
    
    public client() {
        try {
            client = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public <T> void send(T s) {
        try {
            oos = null;
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(s);
            byte[] reqData = baos.toByteArray();
            reqPacket = new DatagramPacket(reqData, reqData.length, InetAddress.getByName("localhost"), 8080);
            client.send(reqPacket);
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public <T> T receive() {
        T s = null;
        try {
            byte[] respData = new byte[1024];
            respPacket = new DatagramPacket(respData, respData.length);
            client.receive(respPacket);
            bais = new ByteArrayInputStream(respData);
            ois = new ObjectInputStream(bais);
            try {
                s = (T) ois.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public void close() {
        client.close();
    }
    
    public static void main(String[] args) {
        client c = new client();
        Student s = new Student("B17DCAT123", "Ahihi", 4.6f, 3.6f, 1.8f);
        c.send(s);
        String str = c.receive().toString();
        System.out.println(str);
        c.close();
    }
}
