//classe Studente

package cryptohelper.bean;

public class Studente
{
    private int id;
    private String nome;
    private String cognome;
    private String nickname;
    private String password;
    
    //COSTRUTTORE
    public Studente(int id, String nome, String cognome, String nickname, String password){
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
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
}
