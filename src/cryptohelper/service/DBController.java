//Classe Singleton DBController
//Crea e gestisce il DB, esegue le query
package cryptohelper.service;

import cryptohelper.data.QueryResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import util.temp.ExperimentSaveObject.Persona;

public class DBController {

    //debug
    private Log log;

    private static DBController instance;
    private static Connection conn;
    private static Statement st;
    private static ResultSet rs;
    private static final String dBurl = "jdbc:derby://localhost:1527/CHDB";
    private static final String dBusr = "rossoblu";
    private static final String dBpwd = "12345";

    private DBController() {
        this.log = LogFactory.getLog(DBController.class);
        try {
            registerDB();
        } catch (SQLException ex) {
            log.fatal(this.getClass() + ":" + ex.getMessage());
        }
    }

    public static DBController getInstance() {
        if (instance == null) {
            instance = new DBController();
        }
        return instance;
    }

    private void registerDB() throws SQLException {
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        } catch (SQLException ex) {
            log.fatal(this.getClass() + ":" + ex.getMessage());
        }
    }

    //Apre la connessione al db
    private void connect() throws SQLException {
        try {
            conn = DriverManager.getConnection(dBurl, dBusr, dBpwd);
            st = conn.createStatement();
        } catch (SQLException e) {
            log.fatal(this.getClass() + ":" + e.getMessage());
        }
        //System.out.println("Connesso a:" + dBurl);
    }

    //Chiude la connessione al db
    private void disconnect() throws SQLException {
        st.close();
        conn.close();
        //System.out.println("Disconnesso!");
    }

    //Crea le tabelle del database
    public void createTables() throws SQLException {
        //connect();
        //Query tabella Studenti
        String queryStudenti = "CREATE TABLE Studenti"
                + "("
                + "ID INTEGER not null primary key "
                + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                + "Nome VARCHAR(32),"
                + "Cognome VARCHAR(32),"
                + "Password VARCHAR(32),"
                + "Nickname VARCHAR(32)"
                + ")";
        //Query tabella Messaggi
        String queryMessaggi = "CREATE TABLE Messaggi"
                + "("
                + "ID INTEGER not null primary key "
                + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                + "ID_mittente INTEGER,"
                + "ID_destinatario INTEGER,"
                + "FOREIGN KEY(ID_MITTENTE) REFERENCES STUDENTI(ID),"
                + "FOREIGN KEY(ID_DESTINATARIO) REFERENCES STUDENTI(ID),"
                + "Testo VARCHAR(2048),"
                + "TestoCifrato VARCHAR(2048),"
                + "Lingua VARCHAR(32),"
                + "Titolo VARCHAR(32),"
                + "Bozza VARCHAR(5),"
                + "Letto VARCHAR(5)"
                + ")";
        //Query tabella SistemiCifratura
        String querySistemiCifratura = "CREATE TABLE SistemiCifratura"
                + "("
                + "ID INTEGER not null primary key "
                + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                + "NOME VARCHAR(32),"
                + "Metodo VARCHAR(256),"
                + "Chiave VARCHAR(256),"
                + "Creatore INTEGER"
                + ")";
        String querySDCPartners = "CREATE TABLE SDCPartners"
                + "("
                + "ID_CREATORE INTEGER not null,"
                + "ID_PARTNER INTEGER not null,"
                + "ID_SDC INTEGER not null,"
                + "Stato_Proposta VARCHAR(16),"
                + "PRIMARY KEY(ID_CREATORE, ID_PARTNER),"
                + "FOREIGN KEY(ID_CREATORE) REFERENCES STUDENTI(ID),"
                + "FOREIGN KEY(ID_PARTNER) REFERENCES STUDENTI(ID),"
                + "FOREIGN KEY(ID_SDC) REFERENCES SistemiCifratura(ID)"
                + ")";
        String queryAlberoIpotesi = "CREATE TABLE AlberoIpotesi"
                + "("
                + "ID INTEGER not null primary key "
                + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                + "JAVAOBJECT BLOB"
                + ")";
        connect();
        //Non bisogna piu comentare le tabele per far funzionare il Test
        try {
            //drop di tutte le tabelle esistenti
            if (isTableExist("SDCPartners")) {
                st.execute("DROP TABLE SDCPartners");
                System.out.println("INFO SERVICE:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Tabella SDCPartners eliminata!");
            }
            if (isTableExist("MessaggiInviati")) {
                st.execute("DROP TABLE MessaggiInviati");
                System.out.println("INFO SERVICE:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Tabella MessaggiInviati eliminata!");
            }
            if (isTableExist("SistemiCifratura")) {
                st.execute("DROP TABLE SistemiCifratura");
                System.out.println("INFO SERVICE:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Tabella SistemiCifratura eliminata!");
            }
            if (isTableExist("Messaggi")) {
                st.execute("DROP TABLE Messaggi");
                System.out.println("INFO SERVICE:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Tabella Messaggi eliminata!");
            }
            if (isTableExist("Studenti")) {
                st.execute("DROP TABLE Studenti");
                System.out.println("INFO SERVICE:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Tabella Studenti eliminata!");
            }
            if (isTableExist("AlberoIpotesi")) {
                st.execute("DROP TABLE AlberoIpotesi");
                System.out.println("INFO AlberoIpotesi:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Tabella Studenti eliminata!");
            }

            //creazione tabelle
            st.executeUpdate(queryStudenti);
            System.out.println("INFO SERVICE:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Tabella Studenti creata!");
            st.executeUpdate(queryMessaggi);
            System.out.println("INFO SERVICE:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Tabella Messaggi creata!");
            st.executeUpdate(querySistemiCifratura);
            System.out.println("INFO SERVICE:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Tabella SistemiCifratura creata!");
            st.executeUpdate(querySDCPartners);
            System.out.println("INFO SERVICE:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Tabella SDCPartners creata!");
            st.executeUpdate(queryAlberoIpotesi);
            System.out.println("INFO SERVICE:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Tabella AlberoIpotesi creata!");
        } catch (SQLException e) {
            log.fatal(this.getClass() + ":" + e.getMessage());
        } finally {
            disconnect();
        }

    }

    private boolean isTableExist(String sTablename) throws SQLException {
        DatabaseMetaData dbmd = conn.getMetaData();
        rs = dbmd.getTables(null, null, sTablename.toUpperCase(), null);
        if (rs.next()) {
            System.out.println("INFO:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + " Table " + rs.getString("TABLE_NAME") + "already exists !!");
            return true;
        } else {
            System.out.println("INFO:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + " creating table: " + sTablename);
            return false;
        }
    }

    //Per inserimenti
    public boolean executeUpdate(String query) throws SQLException {
        connect();
        int result = 0;
        try {
            result = st.executeUpdate(query);
            System.out.println("INFO SERVICE:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "Query eseguita correttamente! " + query);
        } catch (SQLException e) {
            log.fatal(this.getClass() + ":" + e.getMessage());
        } finally {
            disconnect();
        }
        return (result == 1);
    }

    public QueryResult executeQuery(String querry) throws SQLException {
        ArrayList<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> riga = null;

        connect();
        try {
            rs = st.executeQuery(querry);
            ResultSetMetaData metaData = rs.getMetaData();
            Integer mt = metaData.getColumnCount();
            while (rs.next()) {
                riga = new HashMap<>();
                for (int i = 1; i <= mt; i++) {
                    riga.put(metaData.getColumnName(i).toLowerCase(), rs.getObject(i));
                }
                resultList.add(riga);
            }
        } catch (SQLException e) {
            log.fatal(this.getClass() + ":" + e.getMessage());
        } finally {
            disconnect();
        }
        System.out.println("INFO SERVICE:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Success");
        return new QueryResult(resultList);
    }

    public int executeUpdateAndReturnKey(String querry) throws SQLException {
        connect();
        int result = -1;
        try {
            st.executeUpdate(querry, Statement.RETURN_GENERATED_KEYS);
            rs = st.getGeneratedKeys();
            System.out.println("INFO SERVICE:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Query: " + querry + " - eseguita correttamente!");
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            log.fatal(this.getClass() + ":" + e.getMessage());
        } finally {
            disconnect();
        }
        return result;
    }

    /*
    public boolean saveObject(Object javaObject, String query) throws SQLException, IOException {
        connect();
        int result = 0;
        try {
            PreparedStatement ps = null;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(javaObject);
            oos.flush();
            oos.close();
            bos.close();
            byte[] data = bos.toByteArray();
            ps = this.getPreparedStatement(query);
            ps.setObject(1, data);
            result = ps.executeUpdate();
        } catch (SQLException | IOException e) {
            log.fatal(this.getClass() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return (result == 1);
    }

    public Persona getObject(String query) throws SQLException, IOException, ClassNotFoundException {
        connect();
        Persona rmObj = null;
        PreparedStatement ps = null;
        ps = this.getPreparedStatement(query);
        rs = ps.executeQuery();
        try {
            while (rs.next()) {
                ByteArrayInputStream bais;
                ObjectInputStream ins;
                bais = new ByteArrayInputStream(rs.getBytes("javaObject"));
                ins = new ObjectInputStream(bais);
                rmObj = (Persona) ins.readObject();
                ins.close();
            }
        } catch (SQLException | IOException | ClassNotFoundException sQLException) {
            log.fatal(this.getClass() + ":" + sQLException.getMessage());
        } finally {
            disconnect();
        }
        return rmObj;
    }

    private PreparedStatement getPreparedStatement(String query) throws SQLException {
        return conn.prepareStatement(query);
    }*/
}
