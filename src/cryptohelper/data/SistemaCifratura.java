//Classe SistemaCifratura
package cryptohelper.data;

public class SistemaCifratura {

    private int id;
    private String chiave;
    private String metodo;
    private UserInfo creatore;
    private Mappatura map;
    private CalcolatoreMappatura cm;

    //COSTRUTTORE
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
        return Cifratore.cifraMonoalfabetica(map, testo);
    }
    /*
    public String cifra(String testo) {
        
    }
*/

    //TO -DO SALVA 
    public Mappatura create(String metodo, String chiave) {
        this.metodo = metodo;
        this.chiave = chiave;
        //this.id = salva();
        cm = CalcolatoreMappatura.create(metodo);
        map = cm.calcola(chiave);
        return map;
    }
    
    //TO - DO
    private int salva() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Il metodo verifica se la chiave introdota per il metodo e valida
     *
     * @param metodo di cifratura
     * @param chiave per la cifratura
     * @return true se valid
     */
    public boolean valid(String metodo, String chiave) {
        chiave = chiave.toLowerCase();
        System.out.println(chiave.length());
        if (metodo.equals("parola chiave")) {
            if (!chiave.matches("[a-zA-Z]*")) {
                return false;
            }
            if (chiave.length() != 26) {
                return false;
            }
            char[] alfabeto = new char[26];
            for (int i = 0; i < chiave.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (alfabeto[j] == chiave.charAt(i)) {
                        return false;
                    }
                }
                alfabeto[i] = chiave.charAt(i);
            }
            return true;
        }
        return false;
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
        return map;
    }

    public void setMp(Mappatura map) {
        this.map = map;
    }

    
}
