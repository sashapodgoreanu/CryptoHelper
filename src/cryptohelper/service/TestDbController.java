package cryptohelper.service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import cryptohelper.data.AlberoIpotesi;
import cryptohelper.data.Messaggio;
import cryptohelper.data.QueryResult;
import cryptohelper.data.SessioneLavoro;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
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

            for (int i = 0; i < 3; i++) {
                Messaggio m = new Messaggio(0, "testo" + i, "testoCifrato", "lingua", "titolo", true, true);
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
            XStream xstream = new XStream(new StaxDriver());
            System.out.println(m1.toString());
            String xml = xstream.toXML(m1);
            System.out.println(xml);
            Messaggio newJoe = (Messaggio) xstream.fromXML(xml);
            System.out.println(newJoe.toString());
            AlberoIpotesi alberoSessione = new AlberoIpotesi();
            SessioneLavoro s1 = new SessioneLavoro (0, destinatario, alberoSessione, newJoe);
            s1.salva();
            
            
            
            
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
