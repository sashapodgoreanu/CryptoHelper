package cryptohelper.com;

import cryptohelper.data.Studente;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
public class COMController {
    private Studente studente;
    private GUIController gc;

    
    public COMController() {
        studente = new Studente();
    }
    
    public boolean authenticate(String usr, String pwd){

        studente.setNickanme(usr);
        studente.setPassword(pwd);
        return studente.authenticate();
    }
    
    
    
    
    
}
