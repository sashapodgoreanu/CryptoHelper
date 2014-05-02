package cryptohelper.com;

import cryptohelper.data.Messaggio;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
import java.util.ArrayList;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
public class COMController {
    private Studente studente;
    private Messaggio messaggio;
    private GUIController gc;

    
    public COMController() {
        studente = new Studente();
        messaggio = new Messaggio();
    }
    
    public boolean authenticate(String usr, String pwd){
        studente.setNickanme(usr);
        studente.setPassword(pwd);
        return studente.authenticate();
    }
    
    public ArrayList<UserInfo> getDestinatari(){
        
        return null;
    }
    
    /*
    public boolean salvaMessaggioBozza(String titolo, String dest, String corpoMsg){
        UserInfo ui = new UserInfo()
        messaggio.setDestinatario();
        return false;
    }*/
    
    
    
    
    
}
