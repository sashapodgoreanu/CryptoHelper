package cryptohelper.com;

import cryptohelper.data.Messaggio;
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
        try {
            return dbc.getDestinatari();
        } catch (SQLException ex) {
            Logger.getLogger(COMController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return null;
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
