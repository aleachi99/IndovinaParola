/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinaparolaserver;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author rossi.alessandroachi
 */
public class IndovinaParolaServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5000);
            Partita partita = new Partita(server.accept());
        } catch (IOException ex) {
            Logger.getLogger(IndovinaParolaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
