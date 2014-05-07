//Classe Singleton DBController
//Crea e gestisce il DB, esegue le query
package cryptohelper.service;

import cryptohelper.data.Messaggio;
import cryptohelper.data.QueryResult;
import cryptohelper.data.UserInfo;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBController {

    //debug
    private Log log = LogFactory.getLog(DBController.class);

    private static DBController instance;
    private static Connection conn;
    private static Statement st;
    private static ResultSet rs;
    private static final String dBurl = "jdbc:derby://localhost:1527/CHDB";
    private static final String dBusr = "rossoblu";
    private static final String dBpwd = "12345";

    private DBController() {
        try {
            registerDB();
        } catch (SQLException ex) {
            log.fatal(ex.getMessage());
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
            log.fatal(ex.getMessage());
        }
    }

    //Apre la connessione al db
    private void connect() throws SQLException {
        try {
            conn = DriverManager.getConnection(dBurl, dBusr, dBpwd);
            st = conn.createStatement();
        } catch (SQLException e) {
            log.fatal(e.getMessage());
        }
        System.out.println("Connesso a:" + dBurl);
    }

    //Chiude la connessione al db
    private void disconnect() throws SQLException {
        st.close();
        conn.close();
        System.out.println("Disconnesso!");
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
                + "NICKNAME VARCHAR(32)"
                + ")";
        //Query tabella Messaggi
        String queryMessaggi = "CREATE TABLE Messaggi"
                + "("
                + "ID INTEGER not null primary key "
                + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
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
                + "Metodo VARCHAR(256),"
                + "Chiave VARCHAR(256),"
                + "Creatore INTEGER"
                + ")";
        String querySDCPartners = "CREATE TABLE SDCPartners"
                + "("
                + "ID_CREATORE INTEGER not null,"
                + "ID_PARTNER INTEGER not null,"
                + "ID_SDC INTEGER not null,"
                + "STATO_PROPOSTA VARCHAR(16),"
                + "PRIMARY KEY(ID_CREATORE, ID_PARTNER, ID_SDC),"
                + "FOREIGN KEY(ID_CREATORE) REFERENCES STUDENTI(ID),"
                + "FOREIGN KEY(ID_PARTNER) REFERENCES STUDENTI(ID),"
                + "FOREIGN KEY(ID_SDC) REFERENCES SistemiCifratura(ID)"
                + ")";
        String queryMessaggiInviati = "CREATE TABLE MessaggiInviati"
                + "("
                + "ID_MITTENTE INTEGER not null,"
                + "ID_DESTINATARIO INTEGER not null,"
                + "ID_MESSAGGIO INTEGER not null,"
                + "PRIMARY KEY(ID_MITTENTE, ID_DESTINATARIO, ID_MESSAGGIO),"
                + "FOREIGN KEY(ID_MITTENTE) REFERENCES STUDENTI(ID),"
                + "FOREIGN KEY(ID_DESTINATARIO) REFERENCES STUDENTI(ID),"
                + "FOREIGN KEY(ID_MESSAGGIO) REFERENCES Messaggi(ID)"
                + ")";
        connect();
        //Non bisogna piu comentare le tabele per far funzionare il Test
        try {
            //drop di tutte le tabelle esistenti
            if (isTableExist("MessaggiInviati")) {
                st.execute("DROP TABLE MessaggiInviati");
                System.out.println("Tabella MessaggiInviati eliminata!");
            }
            if (isTableExist("SDCPartners")) {
                st.execute("DROP TABLE SDCPartners");
                System.out.println("Tabella SDCPartners eliminata!");
            }
            if (isTableExist("SistemiCifratura")) {
                st.execute("DROP TABLE SistemiCifratura");
                System.out.println("Tabella SistemiCifratura eliminata!");
            }
            if (isTableExist("Messaggi")) {
                st.execute("DROP TABLE Messaggi");
                System.out.println("Tabella Messaggi eliminata!");
            }
            if (isTableExist("Studenti")) {
                st.execute("DROP TABLE Studenti");
                System.out.println("Tabella Studenti eliminata!");
            }
            //creazione tabelle
            st.executeUpdate(queryStudenti);
            System.out.println("Tabella Studenti creata!");
            st.executeUpdate(queryMessaggi);
            System.out.println("Tabella Messaggi creata!");
            st.executeUpdate(querySistemiCifratura);
            System.out.println("Tabella SistemiCifratura creata!");
            st.executeUpdate(querySDCPartners);
            System.out.println("Tabella SDCPartners creata!");
            st.executeUpdate(queryMessaggiInviati);
            System.out.println("Tabella MessaggiInviati creata!");
        } catch (SQLException e) {
            log.fatal(e.getMessage());
        } finally {
            disconnect();
        }

    }

//Per inserimenti
    public boolean executeUpdate(String query) throws SQLException {
        connect();
        try {
            st.executeUpdate(query);
            System.out.println("Query eseguita correttamente!");
        } catch (SQLException e) {
            log.fatal(e.getMessage());
        } finally {
            disconnect();
        }
        return false;
    }

    public QueryResult executeQuery(String querry) throws SQLException {
        ArrayList<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        Map<String, Object> riga = null;

        connect();
        try {
            rs = st.executeQuery(querry);
            ResultSetMetaData metaData = rs.getMetaData();
            Integer mt = metaData.getColumnCount();
            while (rs.next()) {
                riga = new HashMap<String, Object>();
                for (int i = 1; i <= mt; i++) {
                    riga.put(metaData.getColumnName(i).toLowerCase(), rs.getObject(i));
                }
                resultList.add(riga);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }
        return new QueryResult(resultList);
    }

    public ResultSet executeQuery1(String querry) throws SQLException {

        ResultSet resultSet = null;
        connect();
        try {
            resultSet = st.executeQuery(querry);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }
        return resultSet;
    }

    public int executeUpdateAndReturnKey(String querry) throws SQLException {
        connect();
        int result = -1;
        try {
            st.executeUpdate(querry, Statement.RETURN_GENERATED_KEYS);
            rs = st.getGeneratedKeys();
            System.out.println("Query: " + querry + " - eseguita correttamente!");
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            log.fatal(e.getMessage());
        } finally {
            disconnect();
        }
        return result;
    }

    //Preleva il messaggio indicato come parametro dal db
    public Messaggio getMessaggio(int id) throws SQLException {
        connect();
        try {
            rs = st.executeQuery("SELECT * FROM Messaggi");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Messaggio result = null;
        while (rs.next()) {
            if (Integer.parseInt(rs.getString("ID")) == id) {
                result = new Messaggio(Integer.parseInt(rs.getString("ID")), rs.getString("Testo"),
                        rs.getString("TestoCifrato"), rs.getString("Lingua"), rs.getString("Titolo"),
                        Boolean.parseBoolean(rs.getString("Bozza")), Boolean.parseBoolean(rs.getString("Letto")));
            }
        }
        disconnect();
        return result;
    }

    public ArrayList<UserInfo> getDestinatari() throws SQLException {
        connect();

        ArrayList<UserInfo> ui = new ArrayList<>();
        String querry = "SELECT * FROM STUDENTI";
        try {
            rs = st.executeQuery(querry);
            while (rs.next()) {
                UserInfo tempU = new UserInfo(rs.getInt("id"), rs.getString("nome"), rs.getString("cognome"));
                ui.add(tempU);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }
        System.out.println("Extracted:" + ui.toString());
        return ui;
    }

    public UserInfo getUserInfo(int id) throws SQLException {
        connect();
        UserInfo result = new UserInfo();
        String querry = "SELECT * FROM STUDENTI";
        try {
            rs = st.executeQuery(querry);
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    result.setNome(rs.getString("Nome"));
                    result.setCognome(rs.getString("Cognome"));
                    System.out.println("getUserInfo" + result.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }
        return result;
    }

    public void fillUserInfo(UserInfo ui) throws SQLException {
        connect();
        UserInfo result = new UserInfo();
        String querry = "SELECT * FROM STUDENTI";
        try {
            rs = st.executeQuery(querry);
            while (rs.next()) {
                if (rs.getInt("id") == ui.getId()) {
                    ui.setNome(rs.getString("Nome"));
                    ui.setCognome(rs.getString("Cognome"));
                    System.out.println("fillUserInfo" + result.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }
    }

    public boolean update(String querry) throws SQLException {
        connect();
        int edit = 0;
        try {
            edit = st.executeUpdate(querry);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }
        if (edit == 1) {
            System.out.println("Record edited with success");
        } else {
            System.out.println("Record NOT EDITED with success");
        }
        return (edit == 1 ? true : false);
    }

    private boolean isTableExist(String sTablename) throws SQLException {
        DatabaseMetaData dbmd = conn.getMetaData();
        ResultSet rs = dbmd.getTables(null, null, sTablename.toUpperCase(), null);
        if (rs.next()) {
            System.out.println("Table " + rs.getString("TABLE_NAME") + "already exists !!");
            return true;
        } else {
            System.out.println("creating table: " + sTablename);
            return false;
        }
    }
}
