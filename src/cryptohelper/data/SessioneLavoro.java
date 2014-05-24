package cryptohelper.data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import cryptohelper.com.COMController;
import cryptohelper.service.DBController;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessioneLavoro {

    int idSessione;
    String nomeSessione;
    UserInfo utente;
    String ultimaModifica;
    AlberoIpotesi alberoIpotesi;
    Messaggio messaggioIntercettato;
    // soluzione

    //COSTRUTTORE I
    public SessioneLavoro(int id, String nome, UserInfo studente, AlberoIpotesi albero, Messaggio messaggio /* soluzione*/) {

        idSessione = id;
        nomeSessione = nome;
        utente = studente;
        messaggioIntercettato = messaggio;
        alberoIpotesi = albero;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        ultimaModifica = (dateFormat.format(date));
    }

    //Salva una sessione nella tabella SESSIONELAVORO del db. Restituisce TRUE se l'oparazione va a buon fine
    public boolean salva() {
        XStream xstream = new XStream(new StaxDriver());
        String alberoXML = xstream.toXML(this.alberoIpotesi);
        String messaggioIntercettatoXML = xstream.toXML(this.messaggioIntercettato);

        boolean result = false;
        DBController dbc = DBController.getInstance();
        String queryInsert = "INSERT INTO SessioneLavoro(Id_Utente, Nome_Sessione, ALBERO_IPOTESI, Messaggio_Intercettato, Ultima_Modifica)"
                + "VALUES("
                + this.getUtente().getId()
                + ",'"
                + this.getNomeSessione()
                + "','"
                + alberoXML
                + "','"
                + messaggioIntercettatoXML
                + "','"
                + this.getUltimaModifica()
                + "')";
        String querryUpdate = "UPDATE SessioneLavoro"
                + " Id_Utente = '" + this.getUtente().getId()
                + "','"
                + " Id_Messaggio_Intercettato = '" + this.getMessaggioIntercettato().getId()
                + "','"
                + " Ultima_Modifica = '" + this.getUltimaModifica()
                + "',"
                + " WHERE ID = " + this.getIdSessione();
        try {
            //una nuova sessione
   //         if (this.getIdSessione() == 0) {
                int newID = dbc.executeUpdateAndReturnKey(queryInsert);
                System.out.println("id_sessione: "+newID);
                //se newID = -1 allora è stato un errore nel inserimento nel db;
                if (newID != -1) {
                    this.idSessione = newID;
                    System.out.println("AGGIUNGO SESSIONE");
                    System.out.println("INFO DATA:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Aggiunto con successo " + this.toString());
                    System.out.println(true);
                    return true;

                }
    /*            if (newID == -1 && this.idSessione != 0) {
                    System.out.println(false);
                    System.out.println("ERRORE NEL INSERIMENTO");
                    //errore nel inserimento
                    return false;
                }
                //aggiornamento di un messaggio
            } else {
                result = dbc.executeUpdate(querryUpdate);
                System.out.println("AGGIORNO");
                System.out.println("INFO DATA:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "Aggiornato: " + this.toString());
            }
*/
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    //Elimina un messaggio dalla tabella messaggi. Restituisce TRUE se l'oparazione va a buon fine
    public boolean elimina() {
        DBController dbc = DBController.getInstance();
        boolean result = false;
        String query = "DELETE FROM SessioneLavoro WHERE ID=" + this.getIdSessione();
        try {
            result = dbc.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Sessione{" + "id=" + idSessione + ", utente=" + utente + ", Titolo=" + nomeSessione + ", modifica=" + ultimaModifica + '}';
    }
    
     //Preleva l'elenco delle sessioni inviati dallo studente indicato
    public static ArrayList<SessioneLavoro> caricaSessioni(int idStudente) {
        String query = "SELECT * FROM SessioneLavoro WHERE ID_Utente = " + idStudente;
        QueryResult qr = null;
        ArrayList<SessioneLavoro> sessioni = new ArrayList<>();
        try {
            qr = DBController.getInstance().executeQuery(query);
            while (qr.next()) {
                UserInfo user = UserInfo.getUserInfo(idStudente);
                // ATTENZIONE!!!!!  DA RIVEDERE I CAMPI CHE HO MESSO A NULL
                SessioneLavoro temp = new SessioneLavoro(qr.getInt("ID"), qr.getString("nome_Sessione"), null, null, null);
                sessioni.add(temp);
                //      SessioneLavoro temp = new SessioneLavoro(qr.getInt("ID"), qr.getInt("id_utente"), qr.getInt("id_albero"),
                //                 qr.getInt("id_messaggio_intercettato"), qr.getString("ultima_modifica"));
                //       sessioni.add(temp);
            }
        } catch (Exception ex) {
            Logger.getLogger(COMController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return sessioni;
    }
   

    //METODI GETTER
    public UserInfo getUtente() {
        return utente;
    }

    public int getIdSessione() {
        return idSessione;
    }

    public String getNomeSessione() {
        return nomeSessione;
    }

    public String getUltimaModifica() {
        return ultimaModifica;
    }

    public Messaggio getMessaggioIntercettato() {
        return messaggioIntercettato;
    }

    public void setAlberoIpotesi(AlberoIpotesi alberoIpotesi) {
        this.alberoIpotesi = alberoIpotesi;
    }

    public AlberoIpotesi getAlberoIpotesi() {
        return alberoIpotesi;
    }

    //METODI SETTER
    public void setUtente(UserInfo utente) {
        this.utente = utente;
    }

    public void setIdSessione(int idSessione) {
        this.idSessione = idSessione;
    }

    public void setNomeSessione(String nome) {
        this.nomeSessione = nome;
    }

    public void setUltimaModifica(String ultimaModifica) {
        this.ultimaModifica = ultimaModifica;
    }

    public void setMessaggioIntercettato(Messaggio messaggioIntercettato) {
        this.messaggioIntercettato = messaggioIntercettato;
    }

}