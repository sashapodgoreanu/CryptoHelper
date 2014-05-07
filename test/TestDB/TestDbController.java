package TestDB;


import cryptohelper.data.Messaggio;
import cryptohelper.data.QueryResult;
import cryptohelper.data.Studente;
import cryptohelper.service.DBController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author st116629
 */
public class TestDbController {

    public static void main(String args[]) {

        Messaggio m = new Messaggio(21, "testo", "testoCifrato", "lingua", "titolo", true, true);
        Studente st1 = new Studente("Alexandru", "Podgoreanu", "sasha", "1234");
        Studente st2 = new Studente("Giulio", "Pighini", "pgh", "1234");
        Studente st3 = new Studente("Luigi", "Solitro", "pipo", "1234");
        Studente st4 = new Studente("Pipo", "Pipooo", "pipa", "1234");
        Studente st5 = new Studente("Ciprian", "Melian", "cm", "1234");
        Studente st6 = new Studente("Vasco", "Rossi", "vR", "1234");
        Studente st7 = new Studente("Mario", "Rossi", "1", "1");
        try {
            m.salva();
            DBController db = DBController.getInstance();
            db.createTables();
            //m.salva();
            //m.elimina();
            st1.salva();
            st2.salva();
            st3.salva();
            st4.salva();
            st5.salva();
            st6.salva();
            st7.salva();

            
            
            //db.getDestinatari();
           QueryResult lista = db.executeQuery("SELECT * FROM Studenti");

            try {
                System.out.println(lista.toString());
                while(lista.next()){
                    System.out.println("idh="+ lista.getInt("id")+" "+lista.getString("nome"));
                }
            } catch (Exception e) {
            }
     //       System.out.println(lista.toString());
     //       System.out.println(lista.get(0).get("ID"));
    //        System.out.println(lista.get(0).get("NOME"));
    //        System.out.println(lista.get(0).get("COGNOME"));

        } catch (SQLException ex) {
            Logger.getLogger(TestDbController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}