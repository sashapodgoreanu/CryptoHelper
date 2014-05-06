//Classe di supporto UserInfo
package cryptohelper.data;

import cryptohelper.service.DBController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserInfo
{
    private int id;
    private String nome;
    private String cognome;

    public UserInfo(int id) {
        this.id = id;
        DBController db = DBController.getInstance();
        try {
            db.fillUserInfo(this);
        } catch (SQLException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UserInfo() {
    }

    public UserInfo(int id, String nome, String cognome) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
    }
    
    
    
    
        
    //METODI GETTER
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }
    
    //METODI SETTER
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String toString() {
        return "UserInfo{" + "id=" + id + ", nome=" + nome + ", cognome=" + cognome + '}';
    }
    
    

}
