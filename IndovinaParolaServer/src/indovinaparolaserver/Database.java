/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinaparolaserver;

/**
 *
 * @author rossi.alessandroachi
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Database {
    private Connection con;
    
    public Database(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Setup the connection with the DB
            con = DriverManager
                    .getConnection("jdbc:mysql://localhost/tecnologia?"
                            + "user=tecnologia&password=password");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getParola(){
        try{
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT COUNT(*) AS numeroRighe FROM dizionario");
            rs.next();
            int numeroParole=rs.getInt("numeroRighe");
            numeroParole=(int)((Math.random()*1000)%numeroParole)+1;
            rs=stmt.executeQuery("SELECT parola FROM dizionario WHERE id="+numeroParole);
            rs.next();
            String parolaScelta=rs.getString("parola");
            System.out.println("Parola Scelta:" + parolaScelta);
            return parolaScelta;
        }catch (Exception exc){
            System.out.println(exc);
            exc.printStackTrace();
            return "";
        }
    }
    public void closeConnection() throws SQLException{
        con.close();
    }
    public void inserisciPartita(String nomeGiocatore, String parola){
        Statement stmt;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO partita (nomeGiocatore, parola) VALUES (\""+nomeGiocatore+"\",\""+parola+"\");");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
