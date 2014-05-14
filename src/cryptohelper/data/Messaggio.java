//Classe Messaggio
package cryptohelper.data;

import cryptohelper.com.COMController;
import cryptohelper.service.DBController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Messaggio implements MessaggioDestinatario, MessaggioMittente {

    private Log log = LogFactory.getLog(Messaggio.class);   //per debug
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

    //TEST 
    public Messaggio(int id, String testo, String titolo, boolean bozza, UserInfo mittente, UserInfo destinatario) {
        this.id = id;
        this.testo = testo;
        this.titolo = titolo;
        this.bozza = bozza;
        this.mittente = mittente;
        this.destinatario = destinatario;
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

    @Override
    public void setMittente(UserInfo mittente) {
        this.mittente = mittente;
    }

    @Override
    public void setDestinatario(UserInfo destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public String toString() {
        return "Messaggio{" + "id=" + id + ", testo=" + testo + ", testoCifrato=" + testoCifrato + ", lingua=" + lingua + ", titolo=" + titolo + ", bozza=" + bozza + ", letto=" + letto + ", mittente=" + mittente + ", destinatario=" + destinatario + ", sistemaCifratura=" + sistemaCifratura + '}';
    }

    //Salva un messaggio nella tabella Messaggi del db. Restituisce TRUE se l'oparazione va a buon fine
    @Override
    public boolean salva() {
        boolean result = false;
        DBController dbc = DBController.getInstance();
        String queryInsert = "INSERT INTO Messaggi(Id_Mittente, Id_Destinatario,Testo,TestoCifrato,Lingua,Titolo,Bozza,Letto)"
                + "VALUES("
                + this.getMittente().getId()
                + ","
                + this.getDestinatario().getId()
                + ",'"
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
        String querryUpdate = "UPDATE MESSAGGI"
                + " SET TESTO = '" + this.getTesto()
                + "',"
                + " TESTOCIFRATO = '" + this.getTestoCifrato()
                + "',"
                + " ID_MITTENTE = " + this.getMittente().getId()
                + ","
                + " ID_DESTINATARIO = " + this.getDestinatario().getId()
                + ","
                + " LINGUA = '" + this.getLingua()
                + "',"
                + " TITOLO = '" + this.getTitolo()
                + "',"
                + " BOZZA = '" + this.isBozza()
                + "',"
                + " LETTO = '" + this.isLetto()
                + "'"
                + " WHERE ID = " + this.getId();
        try {
            //un nuovo messaggo
            if (this.id == 0) {
                int newID = dbc.executeUpdateAndReturnKey(queryInsert);
                //se newID = -1 allora è stato un errore nel inserimento nel db;
                if (newID != -1) {
                    this.id = newID;
                    System.out.println("INFO DATA:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Aggiunto con successo " + this.toString());
                    System.out.println(true);
                    return true;

                }
                if (newID == -1 && this.id != 0) {
                    System.out.println(false);
                    //errore nel inserimento
                    return false;
                }
                //aggiornamento di un messaggio
            } else {
                result = dbc.executeUpdate(querryUpdate);
                System.out.println("INFO DATA:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "Aggiornato: " + this.toString());
            }

        } catch (SQLException ex) {
            log.fatal(ex.getMessage());
        }
        return result;
    }

    //Elimina un messaggio dalla tabella messaggi. Restituisce TRUE se l'oparazione va a buon fine
    @Override
    public boolean eliminaMessaggio() {
        DBController dbc = DBController.getInstance();
        boolean result = false;
        String query = "DELETE FROM Messaggi WHERE ID=" + this.getId();
        try {

            result = dbc.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    //Preleva l'elenco dei messaggi destinatario
    public static ArrayList<MessaggioDestinatario> caricaMessaggiDestinatario(int idStudente) {
        System.out.println("STAMPO ID STUDENTE" + idStudente);
        String query = "SELECT * FROM Messaggi WHERE (ID_DESTINATARIO = " + idStudente + ")";
        QueryResult qr = null;
        ArrayList<MessaggioDestinatario> messaggi = new ArrayList<>();
        try {
            qr = DBController.getInstance().executeQuery(query);
            //           System.out.println(qr.toString());
            while (qr.next()) {
                MessaggioDestinatario temp = new Messaggio(qr.getInt("ID"), qr.getString("Testo"), qr.getString("TestoCifrato"),
                        qr.getString("Lingua"), qr.getString("Titolo"), Boolean.parseBoolean(qr.getString("Bozza")), Boolean.parseBoolean(qr.getString("Letto")));
                messaggi.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("test");
            Logger.getLogger(COMController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        System.out.println("test2");
        System.out.println("messaggi ricevuti panelo princ: " + messaggi.toString());
        return messaggi;
    }

    //Preleva l'elenco delle bozze
    public static ArrayList<MessaggioMittente> caricaBozze(int idStudente) {
        String query = "SELECT * FROM Messaggi";
        QueryResult qr = null;
        ArrayList<MessaggioMittente> bozze = new ArrayList<>();
        try {
            qr = DBController.getInstance().executeQuery(query);
            while (qr.next()) {
                MessaggioMittente temp = new Messaggio(qr.getInt("ID"), qr.getString("Testo"), qr.getString("TestoCifrato"),
                        qr.getString("Lingua"), qr.getString("Titolo"), Boolean.parseBoolean(qr.getString("Bozza")), Boolean.parseBoolean(qr.getString("Letto")));
                bozze.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("test");
            Logger.getLogger(COMController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        System.out.println("test2");
        return bozze;
    }
   
    
    //Preleva l'elenco dei messaggi inviati     TODO _ CORREGGERE
    public static ArrayList<MessaggioMittente> caricaMessaggiInviati(int idStudente) {
        String query = "SELECT * FROM Messaggi";
        QueryResult qr = null;
        ArrayList<MessaggioMittente> inviati = new ArrayList<>();
        try {
            qr = DBController.getInstance().executeQuery(query);
            while (qr.next()) {
                MessaggioMittente temp = new Messaggio(qr.getInt("ID"), qr.getString("Testo"), qr.getString("TestoCifrato"),
                        qr.getString("Lingua"), qr.getString("Titolo"), Boolean.parseBoolean(qr.getString("Bozza")), Boolean.parseBoolean(qr.getString("Letto")));
                inviati.add(temp);
            }
        } catch (Exception ex) {
            Logger.getLogger(COMController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return inviati;
    }

    @Override
    public void cifra() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
