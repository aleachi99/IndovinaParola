/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinaparolaserver;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rossi.alessandroachi
 */
public class Partita extends Thread {
    private Socket socketClient;
    public Partita(Socket s){
        socketClient=s;
    }
    @Override
    public void run(){
        try {
            PrintStream outputClient=new PrintStream(socketClient.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            BufferedReader inputClient=new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
