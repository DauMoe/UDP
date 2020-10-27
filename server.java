package bitch;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
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
public class server {
    DatagramSocket server;
    DatagramPacket reqPacket, respPacket;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    ByteArrayOutputStream baos;
    ByteArrayInputStream bais;
    
    int bufferSize;
    public server() {
        try {
            bufferSize = 1024;
            server = new DatagramSocket(8080);
        } catch (SocketException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public <T> void send(T s) {
        try {
            oos = null;
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(s);
            byte[] reqData = baos.toByteArray();
            reqPacket = new DatagramPacket(reqData, reqData.length, respPacket.getSocketAddress());
            server.send(reqPacket);
        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Student receive() {
        Student s = null;
        try {
            byte[] respData = new byte[1024];
            respPacket = new DatagramPacket(respData, respData.length);
            server.receive(respPacket);
            bais = new ByteArrayInputStream(respData);
            ois = new ObjectInputStream(bais);
            try {
                s = (Student)ois.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public void close() {
        server.close();
    }
    
    public void listening() {
        System.out.println("Listening....");
        while(true) {
            Student s = receive();
            float tb = (float)((s.getTiengAnh()+s.getTin()+s.getToan())/3);
            if (tb<5) {
                send("Khong qua");
            } else {
                send("Qua");
            }
        }
    }
    
    public static void main(String[] args) {
        server s = new server();
        s.listening();
    }
}
