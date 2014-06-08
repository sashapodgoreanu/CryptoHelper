package cryptohelper.data;

import cryptohelper.service.DBController;
import java.sql.SQLException;
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
        char[] invmappa = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        mappatura.setMappaInversa(invmappa);
    }

    public Mappatura getMappatura() {
        return mappatura;
    }

    public void setMappatura(Mappatura mappatura) {
        this.mappatura = mappatura;
    }

    //METODI GETTER
    public int getId() {
        return id;
    }

    public boolean isValida() {
        return isValida;
    }

    //METODI SETTER
    public void setId(int id) {
        this.id = id;
    }

    public void setValida(boolean valida) {
        this.isValida = valida;
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
                + " SET MAPPATURA = '"+ mappaturaStr
                + "',"
                + " SET IS_VALIDA = '"+ isValida
                + "',"
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


}
