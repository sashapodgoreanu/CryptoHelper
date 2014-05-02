//Classe DBController
//Crea e gestisce il DB, esegue le query
package cryptohelper.service;

import cryptohelper.bean.Messaggio;
import cryptohelper.bean.Studente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBController {

    private static Connection conn;
    private static Statement st;
    private static ResultSet rs;
    private static final String dBurl = "jdbc:derby://localhost:1527/CHDB";
    private static final String dBusr = "rossoblu";
    private static final String dBpwd = "12345";

    public static void registerDB() throws SQLException {
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Apre la connessione al db
    private static void connect() throws SQLException {
        try {
            conn = DriverManager.getConnection(dBurl, dBusr, dBpwd);
            st = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Connesso a:" + dBurl);
    }

    //Chiude la connessione al db
    private static void disconnect() throws SQLException {
        st.close();
        conn.close();
        System.out.println("Disconnesso!");
    }

    //Crea le tabelle del database
    public static void createTables() throws SQLException {
        connect();
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
        try {
            
            //drop di tutte le tabelle esistenti
            st.execute("DROP TABLE SistemiCifratura");
            System.out.println("Tabella SistemiCifratura eliminata!");
            st.execute("DROP TABLE Messaggi");
            System.out.println("Tabella Messaggi eliminata!");
            st.execute("DROP TABLE Studenti");
            System.out.println("Tabella Studenti eliminata!");

            //creazione tabelle
            st.executeUpdate(queryStudenti);
            System.out.println("Tabella Studenti creata!");
            st.executeUpdate(queryMessaggi);
            System.out.println("Tabella Messaggi creata!");
            st.executeUpdate(querySistemiCifratura);
            System.out.println("Tabella SistemiCifratura creata!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }
    }

    //Esegue la query passata per parametro
    public static boolean execute(String query) throws SQLException {
        connect();
        try {
            st.executeUpdate(query);
            System.out.println("Query eseguita correttamente!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }
        return false;
    }

    //Preleva il messaggio indicato come parametro dal db
    public static Messaggio getMessaggio(int id) throws SQLException {
        connect();
         try{
            rs = st.executeQuery("SELECT * FROM Messaggi");
        } catch (SQLException e) { System.out.println(e.getMessage());
        }
        Messaggio result = null;
        while (rs.next()){
            if (Integer.parseInt(rs.getString("ID")) == id)
                result = new Messaggio(Integer.parseInt(rs.getString("ID")), rs.getString("Testo"),
                        rs.getString("TestoCifrato"), rs.getString("Lingua"), rs.getString("Titolo"),
                        Boolean.parseBoolean(rs.getString("Bozza")), Boolean.parseBoolean(rs.getString("Letto")));
        }
        disconnect();
        return result;
    }
    
    //Verifica le credenziali dell'utente per il login al sistema
    public static boolean verificaUtente(Studente studente) throws SQLException {
        connect();
        boolean result = false;
        String querry = "SELECT * FROM STUDENTI";
        try {
            rs = st.executeQuery(querry);
            while (rs.next()) {
                if (rs.getString("Nickname").equalsIgnoreCase(studente.getNickanme()) && rs.getString("Password").equals(studente.getPassword())) {
                    studente.setNome(rs.getString("Nome"));
                    studente.setCognome(rs.getString("Cognome"));
                    studente.setId(rs.getInt("ID"));
                    result = true;
                    System.out.println("verificaUtente"+studente.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }
        return result;
    }
}
