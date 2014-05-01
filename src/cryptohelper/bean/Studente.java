//classe Studente
package cryptohelper.bean;

import cryptohelper.service.DBController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Studente {

    private int id;
    private String nome;
    private String cognome;
    private String nickname;
    private String password;

    //COSTRUTTORE
    public Studente(String nome, String cognome, String nickname, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.nickname = nickname;
        this.password = password;
    }

    public Studente(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
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

    public String getNickanme() {
        return nickname;
    }

    public String getPassword() {
        return password;
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

    public void setNickanme(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean salva() {
        boolean result = false;
        //"ID AUTO_INCREMENT - NON FORNIRE UN VALORE"
        String querry = "INSERT INTO STUDENTI(NOME,COGNOME,PASSWORD,NICKNAME) "
                + "VALUES('"
                + this.nome
                + "','"
                + this.cognome
                + "','"
                + this.password
                + "','"
                + this.nickname
                + "')";
        try {
            result = DBController.execute(querry);
        } catch (SQLException ex) {
            Logger.getLogger(Studente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
