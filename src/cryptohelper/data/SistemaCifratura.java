//Classe SistemaCifratura
package cryptohelper.data;

public class SistemaCifratura {

    private int id;
    private String chiave;
    private String metodo;
    private UserInfo creatore;
    private Mappatura mp;
    private CalcolatoreMappatura cm;

    //COSTRUTTORE
    public SistemaCifratura(int id, String chiave, String metodo, UserInfo creatore, CalcolatoreMappatura cm) {
        this.id = id;
        this.chiave = chiave;
        this.metodo = metodo;
        this.creatore = creatore;
    }

    public SistemaCifratura(UserInfo creatore) {
        this.creatore = creatore;
    }
    

    public SistemaCifratura(String chiave, String metodo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SistemaCifratura(String queryresult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void caricaSistemiCifratura(Studente st) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static SistemaCifratura load(int id1, int id2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String prova(String testo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void calcolaMappatura() {
        
    }
    
    public Mappatura create(String metodo, String chiave){
       throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChiave() {
        return chiave;
    }

    public void setChiave(String chiave) {
        this.chiave = chiave;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public UserInfo getCreatore() {
        return creatore;
    }

    public void setCreatore(UserInfo creatore) {
        this.creatore = creatore;
    }

    public Mappatura getMp() {
        return mp;
    }

    public void setMp(Mappatura mp) {
        this.mp = mp;
    }

    public boolean valid(String metodo, String chiave) {
        System.out.println(chiave.length());
        if (chiave.length() > 26)
            return false;
        char[] alfabeto = new char[26];
        for (int i = 0; i < chiave.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (alfabeto[j] == chiave.charAt(i))
                    return false;
            }
            alfabeto[i] = chiave.charAt(i);
        }
        for (int i = 0; i < chiave.length(); i++) {
           System.out.println(alfabeto[i]);
        }
        
        return true;
    }
    
    
}
