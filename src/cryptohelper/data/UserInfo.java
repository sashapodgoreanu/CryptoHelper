//Classe di supporto UserInfo
package cryptohelper.data;

public class UserInfo
{
    private int id;
    private String nome;
    private String cognome;
        
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

}
