package cryptohelper.data;

import cryptohelper.service.DBController;
import cryptohelper.service.QueryResult;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Logger;

public class Soluzione {

    private static final Logger LOG = Logger.getLogger(Soluzione.class.getName());
    private int id;
    private String nome;
    private UserInfo stud;
    private boolean isValida;
    private Mappatura mappatura;

    public Soluzione(String nome, UserInfo stud) {
        this.id = 0;
        this.nome = nome;
        this.stud = stud;
        isValida = false;
        mappatura = new Mappatura();
        char[] invmappa = {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'};
        mappatura.setMappaInversa(invmappa);
    }

    public Soluzione(int id, String nome, UserInfo stud, boolean isValida, Mappatura mappatura) {
        this.id = id;
        this.nome = nome;
        this.stud = stud;
        this.isValida = isValida;
        this.mappatura = mappatura;
    }

    //Salva Soluzione nel db. Restituisce TRUE se l'oparazione va a buon fine
    public boolean salva() {
        boolean result = false;
        DBController dbc = DBController.getInstance();
        String queryInsert = qinsert();
        String querryUpdate = qupdate();
        try {
            //un nuovo messaggo
            if (this.id == 0) {
                int newID = dbc.executeUpdateAndReturnKey(queryInsert);
                //se newID = -1 allora è stato un errore nel inserimento nel db;
                if (newID != -1) {
                    return successInsert(newID);
                }
                if (newID == -1 && this.id != 0) {
                    System.out.println(false);
                    //errore nel inserimento
                    return false;
                }
                //aggiornamento di una soluzione
            } else {
                result = updateSoluzione(dbc, querryUpdate);
            }
        } catch (SQLException ex) {
            LOG.severe(ex.getMessage());
        }
        return result;
    }

    public static Soluzione load(int id) {
        String query = "SELECT * "
                + " FROM Soluzione"
                + " WHERE ID = " + id;
        QueryResult qr = null;
        Soluzione temp = null;
        try {
            qr = DBController.getInstance().executeQuery(query);
            while (qr.next()) {
                //System.out.println(qr.toString());
                //UserInfo u = UserInfo.getUserInfo(qr.getInt("ID_UTENTE"));
                //System.out.println("is valida " + qr.getBoolean("IS_VALIDA"));
                temp = new Soluzione(qr.getInt("ID"), qr.getString("NOME_SOLUZIONE"), UserInfo.getUserInfo(qr.getInt("ID_UTENTE")), qr.getBoolean("IS_VALIDA"), getMap(qr));
                ///System.out.println("SOLUZIONE load" + temp.toString());
            }
        } catch (SQLException ex) {
            LOG.severe(ex.getMessage());
        } catch (Exception ex) {
            LOG.severe(ex.getMessage());
        }
        //System.out.println("SOLUZIONE load" + temp.toString());
        return temp;
    }

    private static Mappatura getMap(QueryResult qr) throws Exception {
        Mappatura map = new Mappatura();
        //System.out.println("MAAAAAP1" + map.toString());
        String mapString = qr.getString("MAPPATURA");
        char[] inverseMap = new char[26];
        for (int i = 0; i < inverseMap.length; i++) {
            inverseMap[i] = mapString.charAt(i);
        }
        map.setMappaInversa(inverseMap);
        //System.out.println("MAAAAAP2" + map.toString());
        return map;
    }

    private boolean updateSoluzione(DBController dbc, String querryUpdate) throws SQLException {
        boolean result;
        result = dbc.executeUpdate(querryUpdate);
        System.out.println("INFO DATA:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "Aggiornato: " + this.toString());
        return result;
    }

    private boolean successInsert(int newID) {
        this.id = newID;
        System.out.println("INFO DATA:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Aggiunto con successo " + this.toString());
        System.out.println(true);
        return true;
    }

    private String qupdate() {
        String mappaturaStr = mappaturaUpdatetoString();
        String querryUpdate = "UPDATE SOLUZIONE"
                + " SET MAPPATURA = '" + mappaturaStr
                + "',"
                + " IS_VALIDA = '" + isValida
                + "'"
                + " WHERE ID = " + this.getId();
        return querryUpdate;
    }

    private String mappaturaUpdatetoString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getMappatura().getMappaInversa().length; i++) {
            builder.append(getMappatura().getMappaInversa()[i]);
        }
        return builder.toString();
    }

    private String qinsert() {
        String mappaturaStr = mappaturaUpdatetoString();
        String queryInsert = "INSERT INTO SOLUZIONE(ID_UTENTE, NOME_SOLUZIONE, MAPPATURA, IS_VALIDA)"
                + "VALUES("
                + stud.getId()
                + ",'"
                + nome
                + "','"
                + mappaturaStr
                + "','"
                + isValida
                + "')";
        return queryInsert;
    }

    @Override
    public String toString() {
        return "Soluzione{" + "id=" + id + ", nome=" + nome + ", stud=" + stud + ", isValida=" + isValida + ", mappatura=" + Arrays.toString(mappatura.getMappaInversa()) + '}';
    }

    void aggiorna(char ch1, char ch2) {
        mappatura.getMappaInversa()[ch1 - 65] = ch2;
        System.out.println("Mappatura Sol " + Arrays.toString(mappatura.getMappaInversa()));
    }

    //METODI GETTER
    public int getId() {
        return id;
    }

    public boolean isValida() {
        return isValida;
    }

    public String getNome() {
        return nome;
    }

    public UserInfo getStud() {
        return stud;
    }

    public boolean isIsValida() {
        return isValida;
    }

    public Mappatura getMappatura() {
        return mappatura;
    }

    //METODI SETTER
    public void setId(int id) {
        this.id = id;
    }

    public void setValida(boolean valida) {
        this.isValida = valida;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setStud(UserInfo stud) {
        this.stud = stud;
    }

    public void setIsValida(boolean isValida) {
        this.isValida = isValida;
    }

    public void setMappatura(Mappatura mappatura) {
        this.mappatura = mappatura;
    }
}
