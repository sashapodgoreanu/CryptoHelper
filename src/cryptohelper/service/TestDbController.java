package cryptohelper.service;

import cryptohelper.data.AlberoIpotesi;
import cryptohelper.data.Lingua;
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

            for (int i = 0; i < 10; i++) {
                Messaggio m = new Messaggio(0, "Testo chiaro" + i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i, "testo cifrato" + i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i * i, "italiano", "titolo", false, false);
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


            /**
             * *********************** TEST SAVE TO XML ****************************************
             */
            UserInfo destinatario = new UserInfo(st1.getId(), st1.getNome(), st1.getCognome());
            UserInfo mittente = new UserInfo(st2.getId(), st2.getNome(), st2.getCognome());
            Messaggio m1 = new Messaggio(0, "English is a West Germanic language that "
                    + "was first spoken in early medieval England and is now a global lingua "
                    + "franca.It is spoken as a first language by the majority populations "
                    + "of several sovereign states, including the United Kingdom, the United States, "
                    + "Canada, Australia, Ireland, New Zealand and a number of Caribbean nations; and "
                    + "it is an official language of almost 60 sovereign states. It is the third-most-common "
                    + "native language in the world, after Mandarin Chinese and Spanish.[6] It is widely"
                    + " learned as a second language and is an official language of the European Union,"
                    + " many Commonwealth countries and the United Nations, as well as in many world organisations.",
                    "bkdifqe fq r wbqt dbpjrkfs irkdurdb tert wrq cfpqt qmlhbk fk brpiy jbafbvri bkdirka rka fq "
                    + "klw r dilori ifkdur cprksr.ft fq qmlhbk rq r cfpqt irkdurdb oy teb jrglpfty "
                    + "mlmuirtflkq lc qbvbpri qlvbpbfdk qtrtbq, fksiuafkd teb ukftba hfkdalj, teb ukftba qtrtbq,"
                    + " srkrar, ruqtprifr, fpbirka, kbw zbrirka rka r kujobp lc srpfoobrk krtflkq; rka ft fq rk"
                    + " lccfsfri irkdurdb lc rijlqt 60 qlvbpbfdk qtrtbq. ft fq teb tefpa-jlqt-sljjlk krtfvb irkdurdb"
                    + " fk teb wlpia, rctbp jrkarpfk sefkbqb rka qmrkfqe.ft fq wfabiy ibrpkba rq r qbslka"
                    + " irkdurdb rka fq rk lccfsfri irkdurdb lc"
                    + " teb buplmbrk ukflk, jrky sljjlkwbrite sluktpfbq rka teb ukftba krtflkq,"
                    + " rq wbii rq fk jrky wlpia lpdrkfqrtflkq.", Lingua.inglese, "titolo", true, true, mittente, destinatario
            );
            m1.setAreaLavoro(m1.getTestoCifrato().toUpperCase());

            System.out.println(m1.toString());
            AlberoIpotesi alberoSessione = new AlberoIpotesi(m1.getTestoCifrato());

            SessioneLavoro s1 = new SessioneLavoro(0, "SessionePROVA", destinatario, m1, alberoSessione, null);
            System.out.println("S1");
            System.out.println(s1.toString());
            s1.salva();

            SessioneLavoro s2 = new SessioneLavoro(1, "Sessione2", destinatario, m1, alberoSessione, null);
            System.out.println("S2");
            System.out.println(s2.toString());
            s2.salva();

            SessioneLavoro s3 = new SessioneLavoro(1, "Sessione2", destinatario, m1, alberoSessione, null);
            System.out.println("S2");
            System.out.println(s3.toString());
            s3.salva();

      //      ArrayList<SessioneLavoro> sessioniUser1 = SessioneLavoro.caricaSessioni(destinatario.getId());
           // System.out.println("Sessioni User1" + sessioniUser1.toString());

            /*         SessioneLavoro s3 = new SessioneLavoro(2, "Sessione3", destinatario, alberoSessione, m1);
             s3.salva();
            
             SessioneLavoro s4 = new SessioneLavoro(3, "Sessione4", destinatario, alberoSessione, m1);
             s4.salva();
             */
            /**
             * *********************** TEST SAVE TO XML ****************************************
             */
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
