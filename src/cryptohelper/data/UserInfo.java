//Classe di supporto UserInfo
package cryptohelper.data;

import cryptohelper.service.DBController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserInfo {

    private int id;
    private String nome;
    private String cognome;

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

    public static UserInfo getUserInfo(int id) {
        String query = "SELECT * FROM STUDENTI WHERE ID =" + id;
        QueryResult qr = null;
        UserInfo temp = new UserInfo();
        try {
            qr = DBController.getInstance().executeQuery(query);
            while (qr.next()) {
                temp.setId(qr.getInt("ID"));
                temp.setNome(qr.getString("NOME"));
                temp.setCognome(qr.getString("COGNOME"));
                System.out.println("UserInfo: " + temp.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(SistemaCifratura.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(SistemaCifratura.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return temp;
    }

    @Override
    public String toString() {
        return "UserInfo{" + "id=" + id + ", nome=" + nome + ", cognome=" + cognome + '}';
    }

}
