//Classe Messaggio
package cryptohelper.data;

import cryptohelper.service.DBController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Messaggio implements MessaggioDestinatario, MessaggioMittente {

    private int id;
    private String testo;
    private String testoCifrato;
    private String lingua;
    private String titolo;
    private boolean bozza;
    private boolean letto;
    private UserInfo mittente;
    private UserInfo destinatario;
    private SistemaCifratura sistemaCifratura;

    //COSTRUTTORE I
    public Messaggio(int id, String testo, String testoCifrato, String lingua, String titolo, boolean bozza, boolean letto, UserInfo mittente, UserInfo destinatario, SistemaCifratura sc) {
        this.id = id;
        this.testo = testo;
        this.testoCifrato = testoCifrato;
        this.lingua = lingua;
        this.titolo = titolo;
        this.bozza = bozza;
        this.letto = letto;
        this.mittente = mittente;
        this.destinatario = destinatario;
        this.sistemaCifratura = sc;
    }

    //COSTRUTTORE II
    public Messaggio(int id, String testo, String testoCifrato, String lingua, String titolo, boolean bozza, boolean letto) {
        this.id = id;
        this.testo = testo;
        this.testoCifrato = testoCifrato;
        this.lingua = lingua;
        this.titolo = titolo;
        this.bozza = bozza;
        this.letto = letto;
    }

    public Messaggio() {
    }
    
    

    // METODI GETTER
    public int getId() {
        return id;
    }

    @Override
    public String getTesto() {
        return testo;
    }

    @Override
    public String getTestoCifrato() {
        return testoCifrato;
    }

    @Override
    public String getLingua() {
        return lingua;
    }

    @Override
    public String getTitolo() {
        return titolo;
    }

    public SistemaCifratura getSistemaCifratura() {
        return sistemaCifratura;
    }

    public UserInfo getDestinatario() {
        return destinatario;
    }

    public UserInfo getMittente() {
        return mittente;
    }

    @Override
    public boolean isBozza() {
        return bozza;
    }

    @Override
    public boolean isLetto() {
        return letto;
    }

    // METODI SETTER
    public void setId(int id) {
        this.id = id;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public void setTestoCifrato(String testoCifrato) {
        this.testoCifrato = testoCifrato;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setBozza(boolean bozza) {
        this.bozza = bozza;
    }

    public void setLetto(boolean letto) {
        this.letto = letto;
    }

    public void setSistemaCifratura(SistemaCifratura sistemaCifratura) {
        this.sistemaCifratura = sistemaCifratura;
    }

    public void setMittente(UserInfo mittente) {
        this.mittente = mittente;
    }

    public void setDestinatario(UserInfo destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public String toString() {
        return "Messaggio{" + "id=" + id + ", testo=" + testo + ", testoCifrato=" + testoCifrato + ", lingua=" + lingua + ", titolo=" + titolo + ", bozza=" + bozza + ", letto=" + letto + '}';
    }

    //Salva un messaggio nella tabella Messaggi del db. Restituisce TRUE se l'oparazione va a buon fine
    @Override
    public boolean salva() {
        boolean result = false;
        DBController dbc = DBController.getInstance();
        String query = "INSERT INTO Messaggi(Testo,TestoCifrato,Lingua,Titolo,Bozza,Letto)"
                + "VALUES('"
                + this.getTesto()
                + "','"
                + this.getTestoCifrato()
                + "','"
                + this.getLingua()
                + "','"
                + this.getTitolo()
                + "','"
                + this.isBozza()
                + "','"
                + this.isLetto()
                + "')";
        try {
            result = dbc.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    //Elimina un messaggio dalla tabella messaggi. Restituisce TRUE se l'oparazione va a buon fine
    @Override
    public boolean elimina() {
        DBController dbc = DBController.getInstance();
        boolean result = false;
        String query = "DELETE FROM Messaggi WHERE ID=" + this.getId();
        try {
            
            result = dbc.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public void cifra() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
}
