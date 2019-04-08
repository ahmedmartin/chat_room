/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_room;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmed_Martin
 */
public class Chat_room {

    ServerSocket ser_sock;
    Socket sock;
    DataInputStream input;
    PrintStream print;
    
   static ArrayList <chathandel> clients =new ArrayList<>();
    
    public static void main(String[] args) {
        // TODO code application logic here
         new Chat_room();
    }
    
    public Chat_room(){
        try {
            ser_sock =new ServerSocket(5050);
            while (true) {
                sock=ser_sock.accept();
                clients.add(new chathandel(sock));
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Chat_room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private class chathandel extends Thread{
      
        PrintStream print ;
        DataInputStream input;
        
      public chathandel(Socket sock) {
            try {
                print =new PrintStream(sock.getOutputStream());
                input =new DataInputStream(sock.getInputStream());
                start();
            } catch (IOException ex) {
                Logger.getLogger(Chat_room.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
      
      public void run(){
          
          while (true) {
              try {
                  String msg=input.readLine();
                  for(int i=0;i<clients.size();i++){
                        clients.get(i).print.println(msg);
                  }
                  
              } catch (IOException ex) {
                  Logger.getLogger(Chat_room.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
         
          
      }
      
    }
    
}

  
