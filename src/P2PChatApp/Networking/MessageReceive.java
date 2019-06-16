/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package P2PChatApp.Networking;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Youssef Ezzat
 */
public class MessageReceive extends Thread {

    String message, hostname,username;
    int port;
    
    public MessageReceive() {
    }

    public MessageReceive(String message, String hostname, int port,String username) {
        this.message = message;
        this.hostname = hostname;
        this.port = port;
        this.username=username;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(hostname, port);
            socket.getOutputStream().write(message.getBytes());
            DataOutputStream output=new DataOutputStream(socket.getOutputStream());
            output.writeUTF(username+": "+message);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(MessageReceive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
