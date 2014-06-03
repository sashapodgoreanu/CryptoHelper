//classe DbSampleSetup: inizializza le tabelle del database e
//registra tre utenti di prova, ciascuno con un messaggio inviato, un messaggio ricevuto e una sessione di lavoro salvata
package cryptohelper.service;

import cryptohelper.data.AlberoIpotesi;
import cryptohelper.data.Lingua;
import cryptohelper.data.Messaggio;
import cryptohelper.data.Proposta;
import cryptohelper.data.SessioneLavoro;
import cryptohelper.data.SistemaCifratura;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
import cryptohelper.interfaces.Cifrario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbSampleSetup {

    public static void main(String args[]) {

        Studente st1 = new Studente("Alexandru", "Podgoreanu", "sasha", "1234");
        Studente st2 = new Studente("Giulio", "Pighini", "giulio", "1234");
        Studente st3 = new Studente("Luigi", "Solitro", "luigi", "1234");
        Proposta proposta;
        SistemaCifratura sdc;
        UserInfo destinatario;
        UserInfo mittente;
        UserInfo spia;
        AlberoIpotesi alberoSessione;

        try {
            DBController db = DBController.getInstance();
            db.createTables();
            System.out.println("DbSetup: tabelle create");
            st1.salva();
            st2.salva();
            st3.salva();
            System.out.println("DbSetup: utenti registrati");

            /**
             * *** REGISTRAZIONE MESSAGGI ****
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
                    + " fzytrfyneefyn ijyyn vznsin xnxyjrn nsktwrfynhn.", Lingua.italiano, "Informatica", false, false);
            mittente = new UserInfo(st1.getId(), st1.getNome(), st1.getCognome());
            destinatario = new UserInfo(st2.getId(), st2.getNome(), st2.getCognome());
            sdc = new SistemaCifratura(0, "SDCm1", "4", Cifrario.CESARE, mittente);
            sdc.salva();
            m1.setMittente(mittente);
            m1.setDestinatario(destinatario);
            m1.setSistemaCifratura(sdc);
            m1.salva();
            proposta = new Proposta(sdc, mittente, destinatario);
            proposta.salva();
            System.out.println("DbSetup: messaggio 1 salvato");

            //MESSAGGIO 2 (testo sulla crittografia): CIFRATO CON CIFRARIO PSEUDOCASUALE
            //CHIAVE = "8"
            Messaggio m2 = new Messaggio(0, "La crittografia è la branca della crittologia che tratta delle \"scritture nascoste\","
                    + " ovvero dei metodi per rendere un messaggio \"offuscato\" in modo da non essere comprensibile/intelligibile "
                    + "a persone non autorizzate a leggerlo.\n Un tale messaggio si chiama comunemente crittogramma e le tecniche usate tecniche di cifratura.",
                    "sl rkjqqbdklajl è sl oklyrl uxssl rkjqqbsbdjl rgx qklqql uxssx \"nrkjqqtkx ylnrbnqx\", bwwxkb uxj vxqbuj exk kxyuxkx ty vxnnlddjb"
                    + "\"baatnrlqb\" jy vbub ul yby xnnxkx rbvekxynjojsx/jyqxssjdjojsx l exknbyx yby ltqbkjiilqx l sxddxksb.\n"
                    + "ty qlsx vxnnlddjb nj rgjlvl rbvtyxvxyqx rkjqqbdklvvl x sx qxryjrgx tnlqx qxryjrgx uj rjaklqtkl.", Lingua.italiano, "Crittografia", false, false);
            mittente = new UserInfo(st2.getId(), st2.getNome(), st2.getCognome());
            destinatario = new UserInfo(st3.getId(), st3.getNome(), st3.getCognome());
            sdc = new SistemaCifratura(0, "SDCm2", "8", Cifrario.PSEUDOCASUALE, mittente);
            sdc.salva();
            m2.setMittente(mittente);
            m2.setDestinatario(destinatario);
            m2.setSistemaCifratura(sdc);
            m2.salva();
            proposta = new Proposta(sdc, mittente, destinatario);
            proposta.salva();
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
                    + "gmjjmkwiefta gmuktqbir ekl tai ukbtil ketbmkr, er wiff er bk jeky wmqfl mqhekbretbmkr.", Lingua.inglese, "English Language", false, false);
            mittente = new UserInfo(st3.getId(), st3.getNome(), st2.getCognome());
            destinatario = new UserInfo(st1.getId(), st1.getNome(), st1.getCognome());
            sdc = new SistemaCifratura(0, "SDCm3", "english", Cifrario.PAROLA_CHIAVE, mittente);
            sdc.salva();
            m3.setMittente(mittente);
            m3.setDestinatario(destinatario);
            m3.setSistemaCifratura(sdc);
            m3.salva();
            proposta = new Proposta(sdc, mittente, destinatario);
            proposta.salva();
            System.out.println("DbSetup: messaggio 3 salvato");

            /**
             * *** REGISTRAZIONE SESSIONI LAVORO ****
             */
            //MESSAGGIO 4 (testo su wikipedia): CIFRATO CON PAROLA CHIAVE
            //CHIAVE = "wikipedia"
            Messaggio m4 = new Messaggio(0, "Wikipedia è un''enciclopedia online, collaborativa e gratuita. Disponibile in "
                    + "oltre 280 lingue, affronta sia gli argomenti tipici delle enciclopedie tradizionali sia quelli "
                    + "presenti in almanacchi, dizionari geografici e pubblicazioni specialistiche.\n"
                    + "Wikipedia è liberamente modificabile: chiunque può contribuire alle voci esistenti o crearne di nuove. Ogni contenuto "
                    + "è pubblicato sotto licenza Creative Commons e può pertanto essere copiato e riutilizzato adottando la medesima licenza.",
                    "vcgcnepcw è tl''elkckhmnepcw mlhcle, kmhhwimqwscuw e aqwstcsw. pcrnmlciche cl mhsqe 280 hclate, wddqmlsw rcw ahc wqamjelsc "
                    + "scnckc pehhe elkckhmnepce sqwpczcmlwhc rcw otehhc nqerelsc cl whjwlwkkbc, pczcmlwqc aemaqwdckc e ntiihckwzcmlc "
                    + "rnekcwhcrsckbe.\n vcgcnepcw è hcieqwjelse jmpcdckwiche: kbctlote ntò kmlsqcitcqe whhe umkc ercrselsc m kqewqle "
                    + "pc ltmue. malc kmlseltsm è ntiihckwsm rmssm hckelzw kqewscue kmjjmlr e ntò neqswlsm erreqe kmncwsm e qctschczzwsm "
                    + "wpmsswlpm hw jepercjw hckelzw.", Lingua.italiano, "Wikipedia", true, true);
            mittente = new UserInfo(st3.getId(), st3.getNome(), st3.getCognome());
            destinatario = new UserInfo(st2.getId(), st2.getNome(), st2.getCognome());
            spia = new UserInfo(st1.getId(), st1.getNome(), st1.getCognome());
            m4.setMittente(mittente);
            m4.setDestinatario(destinatario);
            m4.setAreaLavoro(m4.getTestoCifrato().toUpperCase());
            alberoSessione = new AlberoIpotesi(m4.getTestoCifrato());
            SessioneLavoro s1 = new SessioneLavoro(0, "Sessione salvata", spia, m4, alberoSessione, null);
            s1.salva();
            System.out.println("DbSetup: sessione di lavoro s1 salvata");

            //MESSAGGIO 5 (testo su Alan Turing): CIFRATO CON CIFRARIO DI CESARE
            //CHIAVE = "9"
            Messaggio m5 = new Messaggio(0, "Alan Mathison Turing, (23 June 1912 – 7 June 1954) was a British mathematician, "
                    + "logician, cryptanalyst, computer scientist and philosopher. He was highly influential in the development of computer science, "
                    + "providing a formalisation of the concepts of \"algorithm\" and \"computation\" with the Turing machine, which can be considered a model "
                    + "of a general purpose computer. Turing is widely considered as the \"Father of Theoretical Computer Science and Artificial Intelligence.\n"
                    + "During World War II, Turing worked for the Government Code and Cypher School at Bletchley Park, Britain''s codebreaking centre. For a time he led Hut 8, the section "
                    + "responsible for German naval cryptanalysis. He devised a number of techniques for breaking German ciphers, including improvements to the pre-war "
                    + "Polish bombe method, an electromechanical machine that could find settings for the Enigma machine.",
                    "jujw vjcqrbxw cdarwp, (23 sdwn 1912 – 7 sdwn 1954) fjb j karcrbq vjcqnvjcrlrjw, uxprlrjw, lahycjwjuhbc, lxvydcna blrnwcrbc jwm "
                    + "yqruxbxyqna. qn fjb qrpquh rwoudnwcrju rw cqn mnenuxyvnwc xo lxvydcna blrnwln, yaxermrwp j oxavjurbjcrxw xo cqn lxwlnycb "
                    + "xo \"jupxarcqv\" jwm \"lxvydcjcrxw\" frcq cqn cdarwp vjlqrwn, fqrlq ljw kn lxwbrmnanm j vxmnu xo j pnwnaju ydayxbn "
                    + "lxvydcna. cdarwp rb frmnuh lxwbrmnanm jb cqn \"ojcqna xo cqnxancrlju lxvydcna blrnwln jwm jacrorlrju rwcnuurpnwln.\n"
                    + "mdarwp fxaum fja rr, cdarwp fxatnm oxa cqn pxenawvnwc lxmn jwm lhyqna blqxxu jc kunclqunh yjat, karcjrw''b lxmnkanjtrwp lnwcan. oxa j crvn qn unm "
                    + "qdc 8, cqn bnlcrxw anbyxwbrkun oxa pnavjw wjeju lahycjwjuhbrb. qn mnerbnm j wdvkna xo cnlqwrzdnb oxa kanjtrwp pnavjw "
                    + "lryqnab, rwludmrwp rvyaxenvnwcb cx cqn yan-fja yxurbq kxvkn vncqxm, jw nunlcaxvnlqjwrlju vjlqrwn cqjc lxdum orwm bnccrwpb "
                    + "oxa cqn nwrpvj vjlqrwn.", Lingua.inglese, "Alan Turing", true, true);
            mittente = new UserInfo(st2.getId(), st2.getNome(), st2.getCognome());
            destinatario = new UserInfo(st1.getId(), st1.getNome(), st1.getCognome());
            spia = new UserInfo(st3.getId(), st3.getNome(), st3.getCognome());
            m5.setMittente(mittente);
            m5.setDestinatario(destinatario);
            m5.setAreaLavoro(m5.getTestoCifrato().toUpperCase());
            alberoSessione = new AlberoIpotesi(m5.getTestoCifrato());
            SessioneLavoro s2 = new SessioneLavoro(0, "Sessione salvata", spia, m5, alberoSessione, null);
            s2.salva();
            System.out.println("DbSetup: sessione di lavoro s2 salvata");

            //MESSAGGIO 6 (testo su Software Engenering): CIFRATO CON CIFRARIO PSEUDOCASUALE
            //CHIAVE = "1"
            Messaggio m6 = new Messaggio(0, "Software Engineering is the study and application of engineering to the design, development, "
                    + "and maintenance of software.\n The field is one of the youngest of engineering, having been started in the early "
                    + "1940s and the term itself not used until 1968. As such, there is still much controversy within the field itself "
                    + "as to what the term means and if it is even the best term to describe the field. Other terms such as software development "
                    + "and information technology are widely used instead.",
                    "gutjsedq qrwcrqqdcrw cg jzq gjmny ern exxlckejcur ut qrwcrqqdcrw ju jzq nqgcwr, nqpqluxoqrj, ern oecrjqrerkq ut gutjsedq.\n"
                    + "jzq tcqln cg urq ut jzq yumrwqgj ut qrwcrqqdcrw, zepcrw hqqr gjedjqn cr jzq qedly 1940g ern jzq jqdo cjgqlt ruj mgqn mrjcl 1968. eg gmkz, jzqdq cg "
                    + "gjcll omkz kurjdupqdgy scjzcr jzq tcqln cjgqlt eg ju szej jzq jqdo oqerg ern ct cj cg qpqr jzq hqgj jqdo ju nqgkdchq "
                    + "jzq tcqln. ujzqd jqdog gmkz eg gutjsedq nqpqluxoqrj ern crtudoejcur jqkzruluwy edq scnqly mgqn crgjqen.",
                    Lingua.inglese, "Software Engeneering", true, true);
            mittente = new UserInfo(st1.getId(), st1.getNome(), st1.getCognome());
            destinatario = new UserInfo(st3.getId(), st3.getNome(), st3.getCognome());
            spia = new UserInfo(st2.getId(), st2.getNome(), st2.getCognome());
            m6.setMittente(mittente);
            m6.setDestinatario(destinatario);
            m6.setAreaLavoro(m6.getTestoCifrato().toUpperCase());
            alberoSessione = new AlberoIpotesi(m6.getTestoCifrato());
            SessioneLavoro s3 = new SessioneLavoro(0, "Sessione salvata", spia, m6, alberoSessione, null);
            s3.salva();
            System.out.println("DbSetup: sessione di lavoro s3 salvata");

        } catch (SQLException ex) {
            Logger.getLogger(DbSampleSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
