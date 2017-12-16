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
public class Parola {
    private String parola="";
    private char[] parolaIndovinata;
    int numeroCaratteri;
    public Parola(){
        numeroCaratteri=(int)(Math.random()*10%4)+3;
        parolaIndovinata=new char[numeroCaratteri];
        for (int i=0; i<numeroCaratteri; i++){
            String[] caratteri={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
            parola=parola+caratteri[(int)(Math.random()*100%26)];
            parolaIndovinata[i]='?';
        }
        System.out.println(parola);
        System.out.println(parolaIndovinata);
    }
    
    public void checkLettera(char lettera){
        boolean checkLettera[]=new boolean[numeroCaratteri];
        for (int i=0; i<numeroCaratteri; i++){
            if(parola.charAt(i)==lettera){
                parolaIndovinata[i]=lettera;
            }
        }
        for (int i=0; i<numeroCaratteri; i++){
             System.out.print(parolaIndovinata[i]);
        }
    }
    public boolean isParolaIndovinata(){
        boolean terminata=true;
        for (int i=0; i<numeroCaratteri; i++){
             if(parola.charAt(i)!=parolaIndovinata[i]){
                 terminata=false;
             }
        }
        return terminata;
    }
    public String getParola(){
        return parola;
    }
    public String getParolaIncognita(){
        return new String(parolaIndovinata);
    }
}
