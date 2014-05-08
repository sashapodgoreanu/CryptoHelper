package TestDB;

import cryptohelper.data.Messaggio;
import cryptohelper.data.MessaggioMittente;
import cryptohelper.data.QueryResult;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
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

        Studente st1 = new Studente("Alexandru", "Podgoreanu", "sasha", "1234");
        Studente st2 = new Studente("Giulio", "Pighini", "pgh", "1234");
        Studente st3 = new Studente("Luigi", "Solitro", "pipo", "1234");
        Studente st4 = new Studente("Pipo", "Pipooo", "pipa", "1234");
        Studente st5 = new Studente("Ciprian", "Melian", "cm", "1234");
        Studente st6 = new Studente("Vasco", "Rossi", "vR", "1234");
        Studente st7 = new Studente("Mario", "Rossi", "1", "1");

        try {
            DBController db = DBController.getInstance();
            db.createTables();
            st1.salva();
            System.out.println(st1.toString());
            st2.salva();
            System.out.println(st2.toString());
            st3.salva();
            st4.salva();
            st5.salva();
            st6.salva();
            for (int i = 0; i < 20; i++) {
                MessaggioMittente m = new Messaggio(0, "testo", "testoCifrato", "lingua", "titolo", true, true);
                st7.salva();
                st3.salva();
                st4.salva();
                st5.salva();
                st6.salva();
                UserInfo mittente = new UserInfo(st1.getId(), st1.getNome(), st1.getCognome());
                UserInfo destinatario = new UserInfo(st2.getId(), st2.getNome(), st2.getCognome());
                m.setMittente(mittente);
                m.setDestinatario(destinatario);
                System.out.println("messaggio" + m.toString());
                m.salva();
            }
            /*
             UserInfo mittente = new UserInfo(st1.getId(), st1.getNome(), st1.getCognome());
             UserInfo destinatario = new UserInfo(st2.getId(), st2.getNome(), st2.getCognome());
             m.setMittente(mittente);
             m.setDestinatario(destinatario);
             System.out.println("messaggio" + m.toString());
             m.salva();*/

            //db.getDestinatari();
            QueryResult lista = db.executeQuery("SELECT * FROM Studenti");

            try {
                System.out.println(lista.toString());
                while (lista.next()) {
                    System.out.println("idh=" + lista.getInt("id") + " " + lista.getString("nome"));
                }
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDbController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
