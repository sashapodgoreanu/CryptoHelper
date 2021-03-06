package cryptohelper.data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import cryptohelper.interfaces.HtmlVisitable;
import cryptohelper.interfaces.HtmlVisitorInterface;
import cryptohelper.interfaces.MessaggioIntercettato;
import cryptohelper.service.DBController;
import cryptohelper.service.QueryResult;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessioneLavoro implements HtmlVisitable {

    private static Log log = LogFactory.getLog(Messaggio.class);   //per log
    int idSessione;
    String nomeSessione;
    UserInfo autore;
    String ultimaModifica;
    AlberoIpotesi alberoIpotesi;
    MessaggioIntercettato messaggioIntercettato;
    Soluzione soluzione;

    //COSTRUTTORE I
    public SessioneLavoro(int id, String nomeSessione, UserInfo autore, MessaggioIntercettato messaggio, AlberoIpotesi albero) {
        this.idSessione = id;
        this.nomeSessione = nomeSessione;
        this.autore = autore;
        this.messaggioIntercettato = messaggio;
        this.alberoIpotesi = albero;
        this.soluzione = new Soluzione(nomeSessione, autore);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.ultimaModifica = (dateFormat.format(date));
    }

    public SessioneLavoro(int idSessione, String nomeSessione, UserInfo autore, AlberoIpotesi alberoIpotesi, MessaggioIntercettato messaggioIntercettato, Soluzione soluzione) {
        this.idSessione = idSessione;
        this.nomeSessione = nomeSessione;
        this.autore = autore;
        this.alberoIpotesi = alberoIpotesi;
        this.messaggioIntercettato = messaggioIntercettato;
        this.soluzione = soluzione;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.ultimaModifica = (dateFormat.format(date));
    }

    //COSTRUTTORE II
    public SessioneLavoro(String nomeSessione, UserInfo autore, MessaggioIntercettato messaggio) {
        this.nomeSessione = nomeSessione;
        this.autore = autore;
        this.messaggioIntercettato = messaggio;
        this.alberoIpotesi = new AlberoIpotesi(messaggio.getTestoCifrato());
        this.soluzione = new Soluzione(nomeSessione, autore);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.ultimaModifica = (dateFormat.format(date));
    }

    //Salva una sessione nella tabella SESSIONELAVORO del db. Restituisce TRUE se l'oparazione va a buon fine
    public boolean salva() {
        if (!soluzione.salva()) {
            return false; //se la soluzione non è stata salvata corettamente
        }
        XStream xstream = new XStream(new StaxDriver());
        String alberoXML = xstream.toXML(this.alberoIpotesi);
        String messaggioIntercettatoXML = xstream.toXML(this.messaggioIntercettato);
        System.out.println(alberoXML);
        boolean result = false;
        DBController dbc = DBController.getInstance();
        String queryInsert = "INSERT INTO SessioneLavoro(Id_Utente, Nome_Sessione, Albero_Ipotesi, Messaggio_Intercettato, ID_Soluzione, Ultima_Modifica)"
                + "VALUES("
                + this.getUtente().getId()
                + ",'"
                + this.getNomeSessione()
                + "','"
                + DBController.escapeForSQL(alberoXML)
                + "','"
                + DBController.escapeForSQL(messaggioIntercettatoXML)
                + "',"
                + this.soluzione.getId()
                + ",'"
                + this.getUltimaModifica()
                + "')";
        String queryUpdate = "UPDATE SessioneLavoro"
                + " Set MESSAGGIO_INTERCETTATO = '" + DBController.escapeForSQL(messaggioIntercettatoXML)
                + "',"
                + " ALBERO_IPOTESI = '" + DBController.escapeForSQL(alberoXML)
                + "',"
                + " Ultima_Modifica = '" + this.getUltimaModifica()
                + "'"
                + " WHERE ID = " + this.getIdSessione();
        try {
            //una nuova sessione
            if (this.getIdSessione() == 0) {
                int newID = dbc.executeUpdateAndReturnKey(queryInsert);
                System.out.println("id_sessione: " + newID);
                //se newID = -1 allora è stato un errore nel inserimento nel db;
                if (newID != -1) {
                    this.idSessione = newID;
                    System.out.println("AGGIUNGO SESSIONE");
                    System.out.println("INFO DATA:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Aggiunto con successo " + this.toString());
                    dbc.comit();
                    return true;
                }
                if (newID == -1 && this.idSessione != 0) {
                    System.out.println(false);
                    System.out.println("ERRORE NEL INSERIMENTO");
                    //errore nel inserimento
                    return false;
                }
                //aggiornamento di una Sessione
            } else {
                result = soluzione.salva() && dbc.executeUpdate(queryUpdate);
                System.out.println("AGGIORNO");
                System.out.println("INFO DATA:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "Aggiornato: " + this.toString());
            }
        } catch (SQLException ex) {
            log.fatal(ex.getMessage());
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
            log.fatal(ex.getMessage());
        }
        return result;
    }

    @Override
    public String toString() {
        return "Sessione{" + "id=" + idSessione + ", Nome=" + nomeSessione + ", utente=" + autore + ", messaggio=" + messaggioIntercettato + ", albero= " + alberoIpotesi + ", soluzione= " + soluzione + ", modifica= " + ultimaModifica + "}";
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
                //System.out.println("**************aAA ");
                UserInfo autore = UserInfo.getUserInfo(idStudente); //preleva dati dell'utente in base all'id
                //preleva il messaggio e lo converte da xml a oggetto Java
                MessaggioIntercettato msg = (MessaggioIntercettato) xstream.fromXML(qr.getString("Messaggio_intercettato"));
                //System.out.println("Area Lavoro: " + msg.getAreaLavoro());
                AlberoIpotesi albero = (AlberoIpotesi) xstream.fromXML(qr.getString("ALBERO_IPOTESI"));
                Soluzione sol = Soluzione.caricaSoluzione(qr.getInt("ID_SOLUZIONE"));
                //System.out.println("**************SOL " + sol.toString());
                SessioneLavoro temp = new SessioneLavoro(qr.getInt("ID"), qr.getString("Nome_Sessione"), autore, albero, msg, sol);
                sessioni.add(temp);
            }
        } catch (Exception ex) {
            log.fatal(ex.getMessage());
        }
        return sessioni;
    }

    /**
     * Il metodo effetua una sostituzione sul arealavoro del
     * MessaggioIntercetatto ed aggiorna la Mappaturapossizione in base alla
     * mossa effetuata
     *
     * @param ch1 carattere cifrato da sostituire
     * @param ch2 presunto carattere in chiaro
     * @return true se la sostituzone ch1 -> ch2 è stata fata gia fatta
     */
    public boolean effetuaSostituzione(char ch1, char ch2) {
        soluzione.aggiorna(ch1, ch2);
        //Tutti i caratteri dentro la MappaturaPosizioni sono lettere Upercase.
        //Una volta fatta effettuaSostituzione(ch1, ch2), ch2 sara un presunto carattere in chiaro qundi deve essere carattere minuscolo
        if (ch2 >= 'A' && ch2 <= 'Z') {
            //trasforma
            ch2 = (char) (ch2 + 32);
            //il carattere '-' coincide con torna allo stato iniziale del carattere cifrato
        } else if (ch2 == '-') {
            ch2 = ch1;
        }
        //verifica della mossa duplicata se gia presente nel'alberoIpotesi
        boolean mossaDuplicata = alberoIpotesi.cerca(ch1, ch2);
        System.out.println(this.getClass() + ": effetuaSostituzione da " + ch1 + "  " + ch2);
        //AQggiorna areaLavoro del messaggioIntercettato con la sostituzione della mossa ch1-> ch2
        messaggioIntercettato.setAreaLavoro(alberoIpotesi.effettuaSostituzione(ch1, ch2, messaggioIntercettato.getAreaLavoro()));
        alberoIpotesi.display();
        return mossaDuplicata;
    }

    public Mossa undo(String arealAvoro) {
        Pair<Mossa, String> pair = alberoIpotesi.undo(arealAvoro);
        arealAvoro = pair.getSecond();
        messaggioIntercettato.setAreaLavoro(arealAvoro);
        Mossa mossa = pair.getFirst();
        soluzione.aggiorna(mossa.getCharacter(), mossa.getInverseChar());
        return mossa;
    }

    @Override
    public void accept(HtmlVisitorInterface visitor) {
        visitor.visit(this);
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

    public UserInfo getAutore() {
        return autore;
    }

    public void setAutore(UserInfo autore) {
        this.autore = autore;
    }

    public Soluzione getSoluzione() {
        return soluzione;
    }

    public void setSoluzione(Soluzione soluzione) {
        this.soluzione = soluzione;
    }


}
