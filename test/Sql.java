/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.SqlUtils;

/**
 *
 * @author SashaAlexandru
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.OrderPizza;
import model.Pizza;
import model.User;
/**
 *
 * @author SashaAlexandru
 */
public class Sql {
    private static Connection conn;
    private static Statement st;
    private static ResultSet rs;
        
    
    public static void registerDB() throws SQLException{
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private static void conect(String url, String user, String pwd) throws SQLException{
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            st = conn.createStatement();
        } catch (SQLException e) { System.out.println(e.getMessage());
        } 
        
        System.out.println("Conected to:" + url);
    }
    private static void disconect() throws SQLException{
        st.close();
        conn.close();
        /*
         * A ResultSet rs is automaticaly closed when its statement is closed
         */
        //rs.close();
        System.out.println("Disconected");
    }
    
    public static void createPizzeria(String url, String user, String pwd) throws SQLException{
        //conecting to DB
        conect(url,user,pwd);
        try {
            st.execute("DROP TABLE ORDERS");
        } catch (SQLException e) { System.out.println(e.getMessage()+": no problem.");}
        try {
            st.execute("DROP TABLE USERS");
        } catch (SQLException e) { System.out.println(e.getMessage()+": no problem.");}
        
        try {
            st.execute("DROP TABLE PIZZA");
        } catch (SQLException e) { System.out.println(e.getMessage()+": no problem.");}
        
        st.executeUpdate("CREATE TABLE USERS"
                + "("
                + "UTENTE VARCHAR (20) PRIMARY KEY,"
                + "RUOLO VARCHAR (5)CHECK (RUOLO IN('ADMIN','USER','admin','user','Admin','User')) NOT NULL,"
                + "NOME VARCHAR (20) NOT NULL,"
                + "COGNOME VARCHAR (20) NOT NULL,"
                + "AGE VARCHAR(2),"
                + "ADRESSA VARCHAR (30),"
                + "ATTRIBUTO VARCHAR (10) CHECK (ATTRIBUTO IN('ATTIVO','NONATTIVO')) NOT NULL,"
                + "PASSWORD VARCHAR (30) NOT NULL"
                + ")"
                );
        System.out.println("Tabela USERS creata");
        st.executeUpdate("CREATE TABLE PIZZA"
                + "("
                + "NOME VARCHAR (65) PRIMARY KEY,"
                + "INGREDIENTI VARCHAR (256) NOT NULL,"
                + "PREZZO DOUBLE NOT NULL,"
                + "URL_IMG VARCHAR (256)"
                + ")"
                );
        System.out.println("Tabela Pizza creata");
        st.executeUpdate("CREATE TABLE ORDERS"
                + "("
                + "NUM_ORD INTEGER ,"
                + "UTENTE VARCHAR (20) ,"
                + "NOME VARCHAR (65) ,"
                + "PREZZO DOUBLE NOT NULL,"
                + "DATA_ORD DATE NOT NULL,"
                + "QTY INTEGER NOT NULL,"
                + "PRIMARY KEY(NUM_ORD, UTENTE, NOME),"
                + "FOREIGN KEY(UTENTE) REFERENCES USERS(UTENTE),"
                + "FOREIGN KEY(NOME) REFERENCES PIZZA(NOME)"
                + ")"
                );
        System.out.println("Tabela ORDERS creata");
        
        System.out.println("Tabele per Pizzeria create");
        disconect();
        
    }
    
    public static void addUser(String url, String user, String pwd, User utilizzatore) throws SQLException{
        //si crea la connessione
        conect(url,user,pwd);
        try {
            st.executeUpdate("INSERT INTO USERS(UTENTE,NOME,COGNOME,AGE,ADRESSA,RUOLO,PASSWORD,ATTRIBUTO) "
                    + "VALUES('"
                    + utilizzatore.getUserName()
                    + "','"
                    + utilizzatore.getNome()
                    + "','"
                    + utilizzatore.getCognome()
                    + "','"
                    + utilizzatore.getAge()
                    + "','"
                    + utilizzatore.getAdressa()
                    + "','"
                    + utilizzatore.getRuolo()
                    + "','"
                    + utilizzatore.getPassword()
                    + "','"
                    + utilizzatore.getAtributo()
                    + "')");
            System.out.println("Inserted with success: VALUES("
                    + utilizzatore.getUserName() 
                    + "," 
                    + utilizzatore.getNome() 
                    + "," 
                    + utilizzatore.getCognome() 
                    + "," 
                    + utilizzatore.getAge() 
                    + "," 
                    + utilizzatore.getAdressa() 
                    + "," 
                    + utilizzatore.getRuolo() 
                    + "," 
                    + utilizzatore.getPassword() 
                    + "," 
                    + utilizzatore.getAtributo()
                    + ")");
        } catch (SQLException e) {  System.out.println(e.getMessage());
        }
        
        disconect();
        
    }
    
    public static void deleteUser(String url, String user, String pwd, User utilizatore) throws SQLException{
        //si crea la connessione
        Connection conn = null;
        conect(url,user,pwd);
        Statement st = conn.createStatement();
        try {
            st.executeUpdate("DELETE FROM USERS "
                    + "WHERE UTENTE = '"
                    + utilizatore.getUserName()
                    + "'");
            System.out.println("Record removed with success:" + utilizatore.getUserName());
        } catch (SQLException e) {  System.out.println(e.getMessage());
        }
        disconect();
        
    }
    
    public static User getUserById(String url, String user, String pwd, String utente) throws SQLException{
        conect(url,user,pwd);
        User u = null;
        try {
            rs = st.executeQuery("SELECT *" + " FROM USERS");
        } catch (SQLException e) { System.out.println(e.getMessage());
        }
        
        while(rs.next()){
            if (rs.getString("UTENTE").equalsIgnoreCase(utente)){
                u = new User(rs.getString("UTENTE"),rs.getString("NOME") ,rs.getString("COGNOME"),
                    Integer.parseInt(rs.getString("AGE")), rs.getString("ADRESSA"), 
                    rs.getString("RUOLO"), rs.getString("PASSWORD"), rs.getString("ATTRIBUTO"));
            }
        }
        disconect();
        return u;
    }
    
    public static boolean verifyUser(String url, String user, String pwd, String utente, String pwdu) throws SQLException {
        User u1 = Sql.getUserById(url,user,pwd,utente);
        try {
            if (u1.getPassword().equals(pwdu))
            return true;
        } catch (Exception e) {
        }

        return false;
    }
    public static ArrayList<User> getUsers(String url, String user, String pwd) throws SQLException{
        conect(url,user,pwd);
        ArrayList<User> result = new ArrayList<>();
        try {
            rs = st.executeQuery("SELECT *" + "FROM USERS");
        } catch (SQLException e) { System.out.println(e.getMessage());
        }
        
        while(rs.next()){
            result.add(new User(rs.getString("UTENTE"),rs.getString("NOME") ,rs.getString("COGNOME"), Integer.parseInt(rs.getString("AGE")), rs.getString("ADRESSA"), rs.getString("RUOLO"), rs.getString("PASSWORD"), rs.getString("ATTRIBUTO") ));
        }
        disconect();
        return result;
    }
    
    public static int addPizza(String url, String user, String pwd, Pizza pizza) throws SQLException{
        //round to #.##
        pizza.setPrezzo(Math.round( pizza.getPrezzo() * 100.0 ) / 100.0);
        //si crea la connessione
        boolean exist = Sql.findPizza(url, user, pwd, pizza.getNome());
        if (!exist){
            conect(url,user,pwd);
            try {
                st.executeUpdate("INSERT INTO PIZZA(NOME,INGREDIENTI,PREZZO,URL_IMG) "
                        + "VALUES('"
                        + pizza.getNome()
                        + "','"
                        + pizza.getIngredienti()
                        + "',"
                        + pizza.getPrezzo()
                        + ",'"
                        + pizza.getUrl_img()
                        + "')");
                System.out.println("Inserted with success: " + pizza.getNome() +", "+pizza.getIngredienti()+", "
                        +pizza.getPrezzo()+", "+ pizza.getUrl_img());
            } catch (SQLException e) {  System.out.println(e.getMessage());
            }
            disconect();
            return 1;
        } else return -1;
          
    }
    public static Pizza getPizza(String url, String user, String pwd, String nomePizza) throws SQLException{
        Sql.conect(url, user, pwd);
        try{
            rs = st.executeQuery("SELECT * FROM PIZZA");
        } catch (SQLException e) { System.out.println(e.getMessage());
        }
        Pizza result = null;
        while (rs.next()){
            if (rs.getString("NOME").equalsIgnoreCase(nomePizza))
                result = new Pizza(rs.getString("NOME"), rs.getString("INGREDIENTI"),
                       Double.parseDouble(rs.getString("PREZZO")), rs.getString("URL_IMG") 
                );
        }
        Sql.disconect();
        return result;
    }
    
    public static boolean findPizza(String url, String user, String pwd, String nomePizza) throws SQLException{
        Sql.conect(url, user, pwd);
        try{
            rs = st.executeQuery("SELECT * FROM PIZZA");
        } catch (SQLException e) { System.out.println(e.getMessage());
        }
        boolean result = false;
        while (rs.next()){
            String a = "";
            try {
                a = rs.getString("NOME");
                if (a != null && a.equals(nomePizza))
                    result = true;
            } catch (Exception e) {
            }
            
        }
        Sql.disconect();
        return result;
    }
    
    public static boolean editPizza(String url, String user, String pwd, String pizzaOriginal, Pizza pizza) throws SQLException{
        conect(url,user,pwd);
        //round to #.##
        pizza.setPrezzo(Math.round( pizza.getPrezzo() * 100.0 ) / 100.0);
        int edit = st.executeUpdate("UPDATE PIZZA"
                + " SET NOME = '"+pizza.getNome()
                + "', INGREDIENTI = '"+pizza.getIngredienti()
                + "', PREZZO = "+pizza.getPrezzo()
                + " WHERE NOME = '"+pizzaOriginal
                + "'"
        );
        if (edit == 1) System.out.println("Record edited with success:" + pizzaOriginal);
        else System.out.println("Record NOT EDITED with success:" + pizzaOriginal);
        disconect();
        return (edit == 1 ? true : false);
    }
    
    
    public static boolean removePizza(String url, String user, String pwd, String nomePizza) throws SQLException{
        conect(url,user,pwd);
        //retrieve record
        int removed = st.executeUpdate("DELETE FROM PIZZA"
                + " WHERE NOME ='"
                + nomePizza
                + "'"
                );
        if (removed == 1) System.out.println("Record removed with success:" + nomePizza);
        else System.out.println("Record NOT REMOVED with success:" + nomePizza);
        disconect();
        return (removed == 1 ? true : false);
    }
    public static boolean removePizza(String url, String user, String pwd, Pizza aPizza) throws SQLException{
        conect(url,user,pwd);
        //retrieve record
        int removed = st.executeUpdate("DELETE FROM PIZZA"
                + " WHERE NOME ='"
                + aPizza.getNome()
                + "'"
                );
        if (removed == 1) System.out.println("Record removed with success:" + aPizza.getNome());
        else System.out.println("Record NOT REMOVED with success:" + aPizza.getNome());
        disconect();
        return (removed == 1 ? true : false);
    }
    
    public static ArrayList<Pizza> getPizza(String url, String user, String pwd) throws SQLException{
        Sql.conect(url, user, pwd);
        rs = st.executeQuery("SELECT * FROM PIZZA");
        ArrayList<Pizza> result = new ArrayList<>();
        while (rs.next()){

            result.add(new Pizza(rs.getString("NOME"),rs.getString("INGREDIENTI"),
                    Double.parseDouble(rs.getString("PREZZO")), rs.getString("URL_IMG") ));
        } 
        Sql.disconect();
        return result;
    }
    public static int getLastNumOrder(String url, String user, String pwd, String utente) throws SQLException{
        conect(url,user,pwd);
        int numOrd = 0;
        rs = st.executeQuery("SELECT *"
            + "FROM ORDERS ");
        while(rs.next()){
            String u = rs.getString("UTENTE");
            int num_ord = rs.getInt("NUM_ORD");
            if(u.equals(utente))
                numOrd = num_ord;
            System.out.println(u+ ", "+numOrd);
        }
        disconect();
        return numOrd;
    }
    
    public static ArrayList<OrderPizza> getOrders(String url, String user, String pwd) throws SQLException{
        ArrayList<OrderPizza> result = new ArrayList<>();
        ArrayList<OrderBox> box = new ArrayList<>();
        conect(url,user,pwd);
        rs = st.executeQuery("SELECT * FROM ORDERS");
        while(rs.next()){
            String pizza = rs.getString("NOME");
            String u = rs.getString("UTENTE");
            int qty = rs.getInt("QTY");
            String date = rs.getString("DATA_ORD");
            box.add(new OrderBox(u,pizza,date,qty));
        }
        for (int i = box.size()-1; i > -1; i--)
            result.add(box.get(i).getOrderPizza(url, user, pwd));
        disconect();
        return result;
    }
    
    public static boolean createOrder(String url, String user, String pwd,int numOrd, String utente, String nome_pizza, String dataOrd, int quantita) throws SQLException{
        //Calendar data_ord = Calendar.getInstance();
        //SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        conect(url,user,pwd);
        rs = st.executeQuery("SELECT *"
            + "FROM PIZZA "
            /*+ "WHERE NOME = '"+nome_pizza+"'"*/);
        int prezzo = 0;
        while(rs.next()){
            String pizza_name = rs.getString("NOME");
            int prezzo_pizza = rs.getInt("PREZZO");
            if(pizza_name.equals(nome_pizza))
                prezzo = prezzo_pizza;
            System.out.println(pizza_name+ ", "+prezzo_pizza);
        }
        
        
        int createOrder = st.executeUpdate("INSERT INTO ORDERS(NUM_ORD,UTENTE,NOME,PREZZO,DATA_ORD,QTY) "
                + "VALUES("
                + numOrd
                + ",'"
                + utente
                + "','"
                +nome_pizza
                + "',"
                + prezzo*quantita
                + ",'"
                + dataOrd
                + "',"
                + quantita
                + ")");
        
        if (createOrder == 1) System.out.println("Record inserted with success:" + utente
                + "','"
                +nome_pizza
                + "',"
                + prezzo*quantita
                + ",'"
                + dataOrd
                + "',"
                + quantita);
        else System.out.println("Record NOT REMOVED with success:" + utente
                + "','"
                +nome_pizza
                + "',"
                + prezzo*quantita
                + ",'"
                + dataOrd
                + "',"
                + quantita);
        disconect();
        return (createOrder == 1 ? true : false);
    }
    
    public static void deleteOrder(String url, String user, String pwd,
            String utente, String nome_pizza) throws SQLException{
        conect(url,user,pwd);
        //retrieve record
        st.executeUpdate("DELETE FROM ORDERS"
                + " WHERE NOME ='"
                + nome_pizza
                + "'"
                + " AND "
                + " UTENTE ='"
                + utente
                + "'"
                );
        System.out.println("Record removed with success:" + utente +", "+nome_pizza);
        disconect();
    }
    
    public static String viewTable(String url, String user, String pwd, String table) throws SQLException{
        conect(url,user,pwd);
        String rs_result = "Data:\n";
        if (table.equals("USERS")) {
            try {
                rs = st.executeQuery("SELECT * FROM USERS");
                while(rs.next()){
                    rs_result = rs_result + rs.getString("utente") +" "+ rs.getString("ruolo") +"\n";
                    
            }
            } catch (SQLException sQLException) {System.out.println(sQLException.getMessage());
            }
        } else if (table.equals("ORDERS")) {
            try {
                rs = st.executeQuery("SELECT * FROM ORDERS");
            } catch (SQLException sQLException) {System.out.println(sQLException.getMessage());
            }
        } else if (table.equals("PIZZA")){
            try {
                rs = st.executeQuery("SELECT * FROM PIZZA");
            } catch (SQLException sQLException) {System.out.println(sQLException.getMessage());
            }
        }
        disconect();
        return rs_result;
    } 
}   

