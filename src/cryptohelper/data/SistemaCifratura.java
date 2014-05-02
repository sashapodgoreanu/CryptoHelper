//Classe SistemaCifratura

package cryptohelper.data;

public class SistemaCifratura
{
    private int id;
    private String chiave;
    private String metodo;
    private UserInfo creatore;
    
    //COSTRUTTORE
    public SistemaCifratura(int id, String chiave, String metodo, UserInfo creatore){
       this.id = id;
       this.chiave = chiave;
       this.metodo = metodo;
       this.creatore = creatore;
    }
         
    //METODI GETTER
    public int getId() {
        return id;
    }

    public String getChiave() {
        return chiave;
    }

    public String getMetodo() {
        return metodo;
    }
    
    public UserInfo getCreatore() {
        return creatore;
    }
      
    //METODI SETTER
    public void setId(int id) {
        this.id = id;
    }

    public void setChiave(String chiave) {
        this.chiave = chiave;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
    
     public void setCreatore(UserInfo creatore) {
        this.creatore = creatore;
    }
    
}
