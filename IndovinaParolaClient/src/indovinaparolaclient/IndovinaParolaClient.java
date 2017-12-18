/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinaparolaclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author AlessandroAchille
 */
public class IndovinaParolaClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
       Scanner sc = new Scanner(System.in);
       System.out.println("Inserisci indirizzo ip server:");
       String indIP=sc.next();
       System.out.println("Inserisci porta server:");
       int porta=sc.nextInt();
       Socket clientSocket=new Socket(InetAddress.getByName(indIP), porta);
       System.out.println("Connessione Stabilita!");
       BufferedReader inputServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
       PrintStream outputServer = new PrintStream(clientSocket.getOutputStream());
       String messaggioIngresso = inputServer.readLine();
       do{
           if (messaggioIngresso.equals("Giocatore: ")){
                System.out.println("Inserisci nome Giocatore: ");
                String nomeGiocatore = sc.next();
                outputServer.println("NomeGiocatore: "+nomeGiocatore);
           }else{
               System.out.println("Messaggio non gesitto in questo contesto");
               System.out.println(messaggioIngresso);
           }
           messaggioIngresso=inputServer.readLine();
       } while(!messaggioIngresso.equals("Start!"));
       do{
            messaggioIngresso=inputServer.readLine();
            System.out.println(messaggioIngresso);
            System.out.println("Inserisci una lettera");
            String lettera = sc.next();
            lettera=lettera.toUpperCase();
            outputServer.println(lettera.charAt(0));
            messaggioIngresso=inputServer.readLine();
       }while(messaggioIngresso.equals("Ritenta!"));
       System.out.println("Hai indovinato la parola");
       messaggioIngresso=inputServer.readLine();
       System.out.println(messaggioIngresso);
       clientSocket.close();
    }
    
}
