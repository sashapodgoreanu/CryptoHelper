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
public class Messaggio implements MessaggioDestinatario, MessaggioMittente {

    private String mittente;
    private String destinatario;

    public Messaggio(String mittente, String destinatario) {
        this.mittente = mittente;
        this.destinatario = destinatario;
    }

    @Override
    public void stampaDestinatario() {
        if(this instanceof MessaggioDestinatario ){
            System.out.println("sono un messaggio Destinatario "+ mittente+" "+destinatario);
        } else if (this instanceof Messaggio) {
            System.out.println("sono un messaggio normale "+ mittente+" "+destinatario);
        }
    }

    @Override
    public void StampaMittente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
