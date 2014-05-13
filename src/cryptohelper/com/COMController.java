package cryptohelper.com;

import cryptohelper.data.QueryResult;
import cryptohelper.data.SistemaCifratura;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
import cryptohelper.service.DBController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class COMController {
    private Studente studente;
    private GUIController gc;

    
    public COMController() {
        studente = new Studente();
    }
    
    /**
     * Autentifica utilizzatoreSistema
     * @param usr - username
     * @param pwd - password
     * @return false se usr o pwd errato
     */
    public boolean authenticate(String usr, String pwd){
        studente.setNickanme(usr);
        studente.setPassword(pwd);
        return studente.authenticate();
    }
    
    //Preleva i destinatari a cui è possibile inviare dei messaggi (destinatari con cui si è concordato un SDC)
    public ArrayList<UserInfo> getDestinatari(UserInfo st){
        String query = "SELECT * FROM STUDENTI";
        QueryResult qr = null;
        ArrayList<UserInfo> uInfo = new ArrayList<>();
        try {
            qr = DBController.getInstance().executeQuery(query);
            while(qr.next()){
                UserInfo temp = new UserInfo(qr.getInt("id"),qr.getString("nome"),qr.getString("cognome"));
                uInfo.add(temp);
            }
              } catch (SQLException ex) {
            Logger.getLogger(COMController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (Exception ex) {
            System.out.println("test2");
            Logger.getLogger(COMController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        System.out.println("test2");
        return uInfo;
    }
    
    public ArrayList<UserInfo> getDestinatari(){
        String query = "SELECT * FROM STUDENTI";
        QueryResult qr = null;
        ArrayList<UserInfo> uInfo = new ArrayList<>();
        try {
            qr = DBController.getInstance().executeQuery(query);
            while(qr.next()){
                UserInfo temp = new UserInfo(qr.getInt("id"),qr.getString("nome"),qr.getString("cognome"));
                uInfo.add(temp);
            }
              } catch (SQLException ex) {
            Logger.getLogger(COMController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (Exception ex) {
            System.out.println("test2");
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

    
    public boolean inviaProposta(UserInfo utilizzatoreSistema, UserInfo partner, SistemaCifratura sdc) {
        return false;
    }
    
    
    
    
    
    
}
