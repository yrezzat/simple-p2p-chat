/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package P2PChatApp.Networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Youssef Ezzat
 */
public class MessageSend extends Thread {
    
    ServerSocket server;
    int port;
    WritableGUI gui;
    String username;
    
    public MessageSend(WritableGUI gui, int port,String username){
        this.port = port;
        this.gui = gui;
        this.username=username;
        
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MessageSend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        Socket clientSocket;
        try {
            while((clientSocket = server.accept()) != null){
                InputStream input = clientSocket.getInputStream();
                Scanner scanner = new Scanner(new InputStreamReader(input)); 
                String line = scanner.nextLine();
                if(line != null){
                    gui.write(username+": "+line);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MessageSend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
