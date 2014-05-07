package cryptohelper.com;

import cryptohelper.data.QueryResult;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
import cryptohelper.service.DBController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
public class COMController {
    private Studente studente;
    private GUIController gc;
    private DBController dbc;

    
    public COMController() {
        dbc = DBController.getInstance();
        studente = new Studente();
    }
    
    public boolean authenticate(String usr, String pwd){
        studente.setNickanme(usr);
        studente.setPassword(pwd);
        return studente.authenticate();
    }
    
    //TO-DO  getDestinatari -> getDestinatari(id studente che ha gia concordato sistema di cifratura con vari destinatari))
    public ArrayList<UserInfo> getDestinatari(/*int idStudente*/){
        String query = "SELECT * FROM STUDENTI";
        QueryResult qr = null;
        ArrayList<UserInfo> uInfo = new ArrayList<>();
        try {
            qr = dbc.executeQuery(query);
            //System.out.println(qr.toString());
            while(qr.next()){
                UserInfo temp = new UserInfo(qr.getInt("id"),qr.getString("nome"),qr.getString("cognome"));
                uInfo.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("test");
            Logger.getLogger(COMController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        System.out.println("test2");
        return uInfo;
    }
    /*
    public boolean salvaMessaggioBozza(String titolo, String dest, String corpoMsg){
    UserInfo ui = new UserInfo()
    messaggio.setDestinatario();
    return false;
    }*/
    public Studente getStudente() {
        return studente;
    }

    public void setStudente(Studente studente) {
        this.studente = studente;
    }
    
    
    
    
    
    
}
