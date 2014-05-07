/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TestInterfaces;

/**
 *
 * @author st116628
 */
public class TestMessaggio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MessaggioDestinatario md = new Messaggio("giulio","pighi");
        Messaggio mm1 = new Messaggio("sasha","podgo");
        md.stampaDestinatario();
        mm1.stampaDestinatario();
    }
    
}
