//Classe SistemaCifratura
package cryptohelper.data;

import cryptohelper.interfaces.CalcolatoreMappatura;
import cryptohelper.service.DBController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SistemaCifratura {

    private int id;
    private String nome;
    private String chiave;
    private String metodo;
    private UserInfo creatore;
    private Mappatura map;
    private CalcolatoreMappatura cm;

    //COSTRUTTORE
    public SistemaCifratura(UserInfo creatore) {
        this.creatore = creatore;
    }

    public SistemaCifratura(int id, String nome, String chiave, String metodo, UserInfo creatore) {
        this.id = id;
        this.nome = nome;
        this.chiave = chiave;
        this.metodo = metodo;
        this.creatore = creatore;
        map = create(metodo, chiave);
    }

    public SistemaCifratura(String chiave, String metodo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SistemaCifratura(int id, String nome, String chiave, String metodo) {
        this.id = id;
        this.nome = nome;
        this.chiave = chiave;
        this.metodo = metodo;
        map = create(metodo, chiave);
    }

    public static void caricaSistemiCifratura(Studente st) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static SistemaCifratura load(UserInfo u1, UserInfo u2) {
        String query = "SELECT * "
                + " FROM SDCPARTNERS"
                + " WHERE (ID_CREATORE = " + u1.getId()
                + " AND ID_PARTNER = " + u2.getId()
                + ") OR (ID_CREATORE = " + u2.getId()
                + " AND ID_PARTNER = " + u1.getId()
                + ") AND STATO_PROPOSTA = 'accettato'";
        QueryResult qr = null;
        SistemaCifratura temp = null;
        try {
            qr = DBController.getInstance().executeQuery(query);
            while (qr.next()) {
                temp = getSistemaCifratura(qr.getInt("ID_SDC"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SistemaCifratura.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(SistemaCifratura.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return temp;
    }

    public String prova(String testo) {
        return Cifratore.cifraMonoalfabetica(map, testo);
    }
    
    public Mappatura create(String metodo, String chiave) {
        this.metodo = metodo;
        this.chiave = chiave;
        cm = CalcolatoreMappatura.create(metodo);
        map = cm.calcola(chiave);
        return map;
    }

    public boolean salva() {
        boolean result = false;
        DBController dbc = DBController.getInstance();
        //querry per salvare la prima volta
        String queryInsert = "INSERT INTO SISTEMICIFRATURA(NOME, METODO, CHIAVE, CREATORE)"
                + " VALUES('"
                + this.nome
                + "','"
                + this.metodo
                + "','"
                + this.chiave
                + "',"
                + this.creatore.getId()
                + ")";
        //querry per update sdc
        String querryUpdate = "UPDATE SISTEMICIFRATURA"
                + " SET CHIAVE = '" + this.getChiave()
                + "',"
                + " NOME = '" + this.getNome()
                + "' WHERE ID = " + this.getId();
        try {
            //un nuovo SDC
            if (this.id == 0) {
                int newID = dbc.executeUpdateAndReturnKey(queryInsert);
                //se newID = -1 allora è stato un errore nel inserimento nel db;
                if (newID != -1) {
                    this.id = newID;
                    System.out.println("INFO DATA:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Aggiunto con successo " + this.toString());
                    System.out.println(true);
                    return true;

                }
                if (newID == -1 && this.id != 0) {
                    System.out.println(false);
                    //errore nel inserimento
                    return false;
                }
                //aggiornamento di UN SDC
            } else {
                result = dbc.executeUpdate(querryUpdate);
                System.out.println("INFO DATA:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "Aggiornato: " + this.toString());
            }

        } catch (SQLException ex) {

        }
        return result;
    }

    /**
     * Il metodo verifica se la chiave introdota per il metodo e valida
     *
     * @param metodo di cifratura
     * @param chiave per la cifratura
     * @return true se valid
     */
    public boolean valid(String metodo, String chiave) {
        if (metodo == null || chiave == null) {
            return false;
        }
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

    public static ArrayList<SistemaCifratura> caricaSistemiCifratura(UserInfo stud) {
        String query = "SELECT * FROM SISTEMICIFRATURA";
        QueryResult qr = null;
        ArrayList<SistemaCifratura> sdcs = new ArrayList<>();
        try {
            qr = DBController.getInstance().executeQuery(query);
            while (qr.next()) {
                if (qr.getInt("creatore") == stud.getId()) {
                    SistemaCifratura temp = new SistemaCifratura(qr.getInt("id"), qr.getString("nome"), qr.getString("chiave"), qr.getString("metodo"), stud);
                    System.out.println("caricaSistemiCifratura: "+temp.toString());
                    sdcs.add(temp);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SistemaCifratura.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(SistemaCifratura.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return sdcs;
    }
    
    public static SistemaCifratura getSistemaCifratura(int id){
        String query = "SELECT * FROM SISTEMICIFRATURA WHERE ID ="+id;
        QueryResult qr = null;
        SistemaCifratura temp = null;
        try {
            qr = DBController.getInstance().executeQuery(query);
            while (qr.next()) {
                    temp = new SistemaCifratura(qr.getInt("id"), qr.getString("nome"), qr.getString("chiave"), qr.getString("metodo"));
                    System.out.println("SistemaCifratura: "+temp.toString());               
            }
        } catch (SQLException ex) {
            Logger.getLogger(SistemaCifratura.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(SistemaCifratura.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return temp;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "SistemaCifratura{" + "id=" + id + ", chiave=" + chiave + ", metodo=" + metodo + ", creatore=" + creatore + ", map=" + map + ", cm=" + cm + '}';
    }

}
