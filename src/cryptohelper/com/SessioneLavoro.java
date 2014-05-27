package cryptohelper.com;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import cryptohelper.data.AlberoIpotesi;
import cryptohelper.data.Messaggio;
import cryptohelper.data.QueryResult;
import cryptohelper.data.Soluzione;
import cryptohelper.data.UserInfo;
import cryptohelper.interfaces.MessaggioIntercettato;
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
    UserInfo autore;
    String ultimaModifica;
    AlberoIpotesi alberoIpotesi;
    MessaggioIntercettato messaggioIntercettato;
    Soluzione soluzione;

    //COSTRUTTORE
    public SessioneLavoro(int id, String nomeSessione, UserInfo autore, MessaggioIntercettato messaggio, AlberoIpotesi albero, Soluzione soluzione) {
        this.idSessione = id;
        this.nomeSessione = nomeSessione;
        this.autore = autore;
        this.messaggioIntercettato = messaggio;
        //TO-DO da rimuovere o da rivedere 
        messaggioIntercettato.setAreaLavoro(messaggioIntercettato.getTestoCifrato().toUpperCase());
        this.alberoIpotesi = albero;
        this.soluzione = soluzione;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.ultimaModifica = (dateFormat.format(date));
    }

    //Salva una sessione nella tabella SESSIONELAVORO del db. Restituisce TRUE se l'oparazione va a buon fine
    public boolean salva() {
        XStream xstream = new XStream(new StaxDriver());
        String alberoXML = xstream.toXML(this.alberoIpotesi);
        String messaggioIntercettatoXML = xstream.toXML(this.messaggioIntercettato);

        boolean result = false;
        DBController dbc = DBController.getInstance();
        String queryInsert = "INSERT INTO SessioneLavoro(Id_Utente, Nome_Sessione, Albero_Ipotesi, Messaggio_Intercettato, Ultima_Modifica)"
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
        String queryUpdate = "UPDATE SessioneLavoro"
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
            System.out.println("id_sessione: " + newID);
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
        return "Sessione{" + "id=" + idSessione + "Nome=" + nomeSessione + ", utente=" + autore + ", messaggio=" + messaggioIntercettato + ", albero= " + alberoIpotesi + ", soluzione= " + soluzione + ", modifica= " + ultimaModifica +"}";
    }

    //Preleva l'elenco delle sessioni inviati dallo studente indicato
    public static ArrayList<SessioneLavoro> caricaSessioni(int idStudente) {
        XStream xstream = new XStream(new StaxDriver());
        String query = "SELECT * FROM SessioneLavoro WHERE ID_Utente = " + idStudente;
        QueryResult qr;
        ArrayList<SessioneLavoro> sessioni = new ArrayList<>();
        try {
            qr = DBController.getInstance().executeQuery(query);
            while (qr.next()) {
                UserInfo autore = UserInfo.getUserInfo(idStudente); //preleva dati dell'utente in base all'id
                //preleva il messaggio e lo converte da xml a oggetto Java
                MessaggioIntercettato msg = (MessaggioIntercettato) xstream.fromXML(qr.getString("Messaggio_intercettato"));
                AlberoIpotesi albero = (AlberoIpotesi) xstream.fromXML(qr.getString("ALBERO_IPOTESI"));
                SessioneLavoro temp = new SessioneLavoro(qr.getInt("ID"), qr.getString("Nome_Sessione"), autore, msg, albero, null);
                sessioni.add(temp);
            }
        } catch (Exception ex) {
            Logger.getLogger(COMController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return sessioni;
    }

    public boolean effetuaSostituzione(char ch1, char ch2) {

        alberoIpotesi.display();
        System.out.println(this.getClass() + ": effetuaSostituzione da " + ch1 + "  " + ch2);
        System.out.println(this.getClass() + "messaggioIntercettato.getAreaLavoro(): " + messaggioIntercettato.getAreaLavoro());
        ;

        messaggioIntercettato.setAreaLavoro(alberoIpotesi.effettuaSostituzione(ch1, ch2, messaggioIntercettato.getAreaLavoro()));

        return false;
    }

    //METODI GETTER
    public UserInfo getUtente() {
        return autore;
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

    public MessaggioIntercettato getMessaggioIntercettato() {
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
        this.autore = utente;
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