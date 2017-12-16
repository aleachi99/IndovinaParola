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
    private PrintStream outputClient;
    private BufferedReader inputClient;
    private String nomeGiocatore;
    private Parola parola;
    public Partita(Socket s){
        socketClient=s;
        parola=new Parola();
    }
    @Override
    public void run(){
        try {
            outputClient=new PrintStream(socketClient.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            inputClient=new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        }
        outputClient.println("Giocatore: ");
        String inClient="";
        do{
            try {
                inClient = inputClient.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while(!inClient.startsWith("NomeGiocatore: "));
        nomeGiocatore=inClient.substring(15);
        outputClient.println(parola.getParolaIncognita());
        do{
            try {
                char letteraInput = inputClient.readLine().charAt(0);
                parola.checkLettera(letteraInput);
            } catch (IOException ex) {
                Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (parola.isParolaIndovinata()){
                outputClient.println("Hai Vinto!");
                outputClient.println("Parola: "+parola.getParolaIncognita());
            }else{
                outputClient.println("Ritenta!");
                outputClient.println("Lettere Indovinate: "+parola.getParolaIncognita());
            }
        }while(!parola.isParolaIndovinata());
        try {
            socketClient.close();
        } catch (IOException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
