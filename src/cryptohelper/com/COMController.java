package cryptohelper.com;

import cryptohelper.data.Messaggio;
import cryptohelper.data.Proposta;
import cryptohelper.service.QueryResult;
import cryptohelper.data.SistemaCifratura;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
import cryptohelper.service.DBController;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class COMController {

    private static Log log = LogFactory.getLog(Messaggio.class);   //per log

    private Studente studente;
    private static COMController instance;

    private COMController() {
        studente = new Studente();
    }

    public static COMController getInstance() {
        if (instance == null) {
            instance = new COMController();
        }
        return instance;
    }

    /**
     * Autentifica utilizzatoreSistema
     *
     * @param usr - username
     * @param pwd - password
     * @return false se usr o pwd errato
     */
    public boolean authenticate(String usr, String pwd) {
        studente.setNickanme(usr);
        studente.setPassword(pwd);
        return studente.authenticate();
    }

    //Preleva i destinatari a cui è possibile inviare dei messaggi (destinatari con cui si è concordato un SDC)
    public ArrayList<UserInfo> getDestinatari(UserInfo st) {
        String query1 = "SELECT *"
                + " FROM STUDENTI JOIN SDCPARTNERS "
                + " ON(" + st.getId() + " = SDCPARTNERS.ID_CREATORE OR " + st.getId() + " = SDCPARTNERS.ID_PARTNER)"
                + " AND (STUDENTI.ID = SDCPARTNERS.ID_PARTNER OR STUDENTI.ID = SDCPARTNERS.ID_CREATORE)"
                + " AND SDCPARTNERS.STATO_PROPOSTA = 'accettata')";
        String query = "SELECT *"
                + "FROM STUDENTI JOIN SDCPARTNERS "
                + "    ON ( "
                + "        (" + st.getId() + " = SDCPARTNERS.ID_CREATORE OR " + st.getId() + " = SDCPARTNERS.ID_PARTNER)AND(STUDENTI.ID = SDCPARTNERS.ID_PARTNER OR STUDENTI.ID = SDCPARTNERS.ID_CREATORE)"
                + "         "
                + "        AND SDCPARTNERS.STATO_PROPOSTA = 'accettata')"
                + "where id <> " + st.getId();
        QueryResult qr = null;
        ArrayList<UserInfo> uInfo = new ArrayList<>();
        try {
            qr = DBController.getInstance().executeQuery(query);
            while (qr.next()) {
                UserInfo temp = new UserInfo(qr.getInt("id"), qr.getString("nome"), qr.getString("cognome"));
                uInfo.add(temp);
            }
        } catch (SQLException ex) {
            log.fatal(ex.getMessage());
        } catch (Exception ex) {
            log.fatal(ex.getMessage());
        }
        System.out.println(uInfo.toString());
        return uInfo;
    }

    public ArrayList<UserInfo> getDestinatari() {
        String query = "SELECT * FROM STUDENTI";
        QueryResult qr = null;
        ArrayList<UserInfo> uInfo = new ArrayList<>();
        try {
            qr = DBController.getInstance().executeQuery(query);
            while (qr.next()) {
                UserInfo temp = new UserInfo(qr.getInt("id"), qr.getString("nome"), qr.getString("cognome"));
                uInfo.add(temp);
            }
        } catch (SQLException ex) {
            log.fatal(ex.getMessage());
        } catch (Exception ex) {
            log.fatal(ex.getMessage());
        }
        return uInfo;
    }

    public boolean proponiSistemaCifratura(UserInfo utilizzatoreSistema, UserInfo partner, SistemaCifratura sdc) {
        Proposta proposta = new Proposta(sdc, utilizzatoreSistema, partner);
        return proposta.salva();
    }

    public Studente getStudente() {
        return studente;
    }

    public void setStudente(Studente studente) {
        this.studente = studente;
    }
}
