//classe DbSampleSetup: inizializza le tabelle del database e registra alcuni utenti, messaggi e sessioni di lavoro di esempio
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

public class DbSampleSetup {

    public static void main(String args[]) {

        Studente st1 = new Studente("Alexandru", "Podgoreanu", "sasha", "1234");
        Studente st2 = new Studente("Giulio", "Pighini", "giulio", "1234");
        Studente st3 = new Studente("Luigi", "Solitro", "luigi", "1234");
        UserInfo destinatario;
        UserInfo mittente;

        try {
            DBController db = DBController.getInstance();
            db.createTables();
            System.out.println("DbSetup: tabelle create");
            st1.salva();
            st2.salva();
            st3.salva();
            System.out.println("DbSetup: utenti registrati");

            /**
             * *** REGISTRAZIONE MESSAGGI DI PROVA ****
             */
            //MESSAGGIO 1 (testo sull'informatica): CIFRATO CON CIFRARIO DI CESARE
            //CHIAVE = "4"
            Messaggio m1 = new Messaggio(0, "L''informatica (termine che deriva dalla lingua francese informatique, "
                    + "contrazione di informazione automatica) è la scienza che ha per oggetto lo studio dei fondamenti "
                    + "teorici dell''informazione, della sua computazione a livello logico e delle tecniche pratiche per "
                    + "la sua implementazione e applicazione in sistemi elettronici automatizzati detti quindi sistemi informatici.",
                    "q''nsktwrfynhf (yjwrnsj hmj ijwnaf ifqqf qnslzf kwfshjxj nsktwrfynvzj, htsywfentsj in nsktwrfentsj fzytrfynhf) è"
                    + " qf xhnjsef hmj mf ujw tlljyyt qt xyzint ijn ktsifrjsyn yjtwnhn ijqq''nsktwrfentsj, ijqqf xzf htruzyfentsj"
                    + " f qnajqqt qtlnht j ijqqj yjhsnhmj uwfynhmj ujw qf xzf nruqjrjsyfentsj j fuuqnhfentsj ns xnxyjrn jqjyywtsnhn"
                    + " fzytrfyneefyn ijyyn vznsin xnxyjrn nsktwrfynhn.", "Italiano", "Informatica", false, false);
            mittente = new UserInfo(st1.getId(), st1.getNome(), st1.getCognome());
            destinatario = new UserInfo(st2.getId(), st2.getNome(), st2.getCognome());

            m1.setMittente(mittente);
            m1.setDestinatario(destinatario);
            m1.salva();
            System.out.println("DbSetup: messaggio 1 salvato");

            //MESSAGGIO 2 (testo sulla crittografia): CIFRATO CON CIFRARIO PSEUDOCASUALE
            //CHIAVE = "8"
            Messaggio m2 = new Messaggio(0, "La crittografia è la branca della crittologia che tratta delle \"scritture nascoste\","
                    + " ovvero dei metodi per rendere un messaggio \"offuscato\" in modo da non essere comprensibile/intelligibile "
                    + "a persone non autorizzate a leggerlo.\n Un tale messaggio si chiama comunemente crittogramma e le tecniche usate tecniche di cifratura.",
                    "sl rkjqqbdklajl è sl oklyrl uxssl rkjqqbsbdjl rgx qklqql uxssx \"nrkjqqtkx ylnrbnqx\", bwwxkb uxj vxqbuj exk kxyuxkx ty vxnnlddjb"
                    + "\"baatnrlqb\" jy vbub ul yby xnnxkx rbvekxynjojsx/jyqxssjdjojsx l exknbyx yby ltqbkjiilqx l sxddxksb.\n"
                    + "ty qlsx vxnnlddjb nj rgjlvl rbvtyxvxyqx rkjqqbdklvvl x sx qxryjrgx tnlqx qxryjrgx uj rjaklqtkl.", "Italiano", "Crittografia", false, false);
            destinatario = new UserInfo(st2.getId(), st2.getNome(), st2.getCognome());
            mittente = new UserInfo(st3.getId(), st3.getNome(), st3.getCognome());
            m2.setMittente(mittente);
            m2.setDestinatario(destinatario);
            m2.salva();
            System.out.println("DbSetup: messaggio 2 salvato");

            //MESSAGGIO 3 (testo sulla lingua inglese): CIFRATO CON PAROLA CHIAVE
            //CHIAVE = "english"
            Messaggio m3 = new Messaggio(0, "English is a West Germanic language that was first spoken in early medieval England and is now"
                    + " a global lingua franca.It is spoken as a first language by the majority populations of several sovereign states, "
                    + "including the United Kingdom, the United States, Canada, Australia, Ireland, New Zealand and a number of Caribbean "
                    + "nations; and it is an official language of almost 60 sovereign states. It is the third-most-common native language "
                    + "in the world, after Mandarin Chinese and Spanish. It is widely learned as a second language and is an official language of "
                    + "the European Union, many Commonwealth countries and the United Nations, as well as in many world organisations.",
                    "ikhfbra br e wirt hiqjekbg fekhuehi taet wer sbqrt romdik bk ieqfy jilbivef ikhfekl ekl br kmw e hfmnef fbkhue sqekge.bt br "
                    + "romdik er e sbqrt fekhuehi ny tai jecmqbty omoufetbmkr ms riviqef rmviqibhk rtetir, bkgfulbkh tai ukbtil dbkhlmj, "
                    + "tai ukbtil rtetir, gekele, eurtqefbe, bqifekl, kiw ziefekl ekl e kujniq ms geqbnniek ketbmkr; ekl bt br ek mssbgbef "
                    + "fekhuehi ms efjmrt 60 rmviqibhk rtetir. bt br tai tabql-jmrt-gmjjmk ketbvi fekhuehi bk tai wmqfl, estiq jekleqbk gabkiri "
                    + "ekl roekbra. bt br wblify fieqkil er e rigmkl fekhuehi ekl br ek mssbgbef fekhuehi ms tai iuqmoiek ukbmk, jeky "
                    + "gmjjmkwiefta gmuktqbir ekl tai ukbtil ketbmkr, er wiff er bk jeky wmqfl mqhekbretbmkr.", "Inglese", "Lignua inglese", false, false);
            destinatario = new UserInfo(st3.getId(), st3.getNome(), st3.getCognome());
            mittente = new UserInfo(st1.getId(), st1.getNome(), st1.getCognome());
            m3.setMittente(mittente);
            m3.setDestinatario(destinatario);
            m3.salva();
            System.out.println("DbSetup: messaggio 3 salvato");

            /**
             * *********************** TEST SAVE TO XML
             * ****************************************
             *//*
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
             );*/

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

            ArrayList<SessioneLavoro> sessioniUser1 = SessioneLavoro.caricaSessioni(destinatario.getId());
            System.out.println("Sessioni User1" + sessioniUser1.toString());

            /*         SessioneLavoro s3 = new SessioneLavoro(2, "Sessione3", destinatario, alberoSessione, m1);
             s3.salva();
            
             SessioneLavoro s4 = new SessioneLavoro(3, "Sessione4", destinatario, alberoSessione, m1);
             s4.salva();
             */
            /**
             * *********************** TEST SAVE TO XML
             * ****************************************
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
            Logger.getLogger(DbSampleSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
