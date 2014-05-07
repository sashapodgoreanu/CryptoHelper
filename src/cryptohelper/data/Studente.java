//classe Studente
package cryptohelper.data;

import cryptohelper.service.DBController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Studente implements Model {

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

    public Studente() {
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

    //Salva i dati di un nuovo utente (studente) sul db
    public boolean salva() {
        DBController dbc = DBController.getInstance();
        int result = -1;
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
            result = dbc.executeUpdateAndReturnKey(querry);
            //se result == -1 è stato un errore nel executeUpdateAndReturnKey(querry)
            if (result != -1) {
                this.setId(result);
                System.out.println("INFO:"+this.getClass()+"."+ Thread.currentThread().getStackTrace()[1].getMethodName()+": Aggiunto con successo "+this.toString());
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Studente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (result > -1 ? true : false);
    }

    //Controlla le credenziali dell'utente e lo autentica nel sistema
    public boolean authenticate() {
        DBController dbc = DBController.getInstance();
        try {
            QueryResult qr = dbc.executeQuery("SELECT * FROM Studenti");
            while (qr.next()) {
                if (this.nickname.equalsIgnoreCase(qr.getString("nickname")) && this.password.equals(qr.getString("password"))) {
                    this.nome = qr.getString("nome");
                    this.cognome = qr.getString("cognome");
                    this.id = qr.getInt("id");
                    return true;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Studente.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Studente{" + "id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", nickname=" + nickname + ", password=" + password + '}';
    }

}
