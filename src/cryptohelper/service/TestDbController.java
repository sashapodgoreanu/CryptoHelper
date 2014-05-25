package cryptohelper.service;

import cryptohelper.data.AlberoIpotesi;
import cryptohelper.data.Messaggio;
import cryptohelper.data.QueryResult;
import cryptohelper.data.SessioneLavoro;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
import java.sql.SQLException;
import java.util.ArrayList;
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

            for (int i = 0; i < 3; i++) {
                Messaggio m = new Messaggio(0, "testo" + i, "testoCifrato", "italiano", "titolo", true, true);
                st7.salva();
                st3.salva();
                st4.salva();
                st5.salva();
                st6.salva();
                UserInfo destinatario = new UserInfo(st1.getId(), st1.getNome(), st1.getCognome());
                UserInfo mittente = new UserInfo(st2.getId(), st2.getNome(), st2.getCognome());
                m.setMittente(mittente);
                m.setDestinatario(destinatario);
                System.out.println("messaggio" + m.toString());
                m.salva();
            }

            
            /************************* TEST SAVE TO XML *****************************************/
            Messaggio m1 = new Messaggio(0, "testo" + 3, "testoCifrato", "lingua", "titolo", true, true);
            UserInfo destinatario = new UserInfo(st1.getId(), st1.getNome(), st1.getCognome());
            UserInfo mittente = new UserInfo(st2.getId(), st2.getNome(), st2.getCognome());
            m1.setMittente(mittente);
            m1.setDestinatario(destinatario);
            
            System.out.println(m1.toString());
            AlberoIpotesi alberoSessione = new AlberoIpotesi();
            
            SessioneLavoro s1 = new SessioneLavoro(0, "SessionePROVA", destinatario, m1,alberoSessione, null);
            System.out.println("S1");
            System.out.println(s1.toString());
            s1.salva();
            
            SessioneLavoro s2 = new SessioneLavoro(1, "Sessione2", destinatario,m1, alberoSessione, null);
            System.out.println("S2");
            System.out.println(s2.toString());
            s2.salva();
            
            SessioneLavoro s3 = new SessioneLavoro(1, "Sessione2", destinatario, m1, alberoSessione, null);
            System.out.println("S2");
            System.out.println(s3.toString());
            s3.salva();
            
            ArrayList<SessioneLavoro> sessioniUser1 = SessioneLavoro.caricaSessioni(destinatario.getId());
            System.out.println("Sessioni User1" + sessioniUser1.toString());
            
                        
            
   /*         SessioneLavoro s3 = new SessioneLavoro(2, "Sessione3", destinatario, alberoSessione, m1);
            s3.salva();
            
            SessioneLavoro s4 = new SessioneLavoro(3, "Sessione4", destinatario, alberoSessione, m1);
            s4.salva();
     */       
            /************************* TEST SAVE TO XML *****************************************/
            /*
             UserInfo mittente = new UserInfo(st1.getId(), st1.getNome(), st1.getCognome());
             UserInfo destinatario = new UserInfo(st2.getId(), st2.getNome(), st2.getCognome());
             m.setMittente(mittente);
             m.setDestinatario(destinatario);
             System.out.println("messaggio" + m.toString());
             m.salva();*/

            //db.getDestinatari();
            QueryResult lista = db.executeQuery("SELECT * FROM Studenti");
            //System.out.println(SistemaCifratura.getSistemaCifratura(1).toString());

        } catch (SQLException ex) {
            Logger.getLogger(TestDbController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
