package cryptohelper.com;

import cryptohelper.GUI.CreaSDCPanel;
import cryptohelper.GUI.LoginForm;
import cryptohelper.GUI.MessagePanel;
import cryptohelper.GUI.PanelloPrincipale;
import cryptohelper.GUI.RegistrationForm;
import cryptohelper.GUI.SdcPanel;
import cryptohelper.GUI.View;
import cryptohelper.data.Mappatura;
import cryptohelper.data.Messaggio;
import cryptohelper.data.MessaggioMittente;
import cryptohelper.data.MessaggioDestinatario;
import cryptohelper.data.SistemaCifratura;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JRadioButton;

/**
 * Il controllore Deve conoscere: i modelli e le viste - JFrame's
 *
 * La vista Deve conoscere: i controllori e qualche modello
 *
 * Modello non deve conoscere nessuno dei due.
 *
 */
public class GUIController {

    private COMController comC;
    private LoginForm loginForm;
    private RegistrationForm regForm;
    private PanelloPrincipale pp;
    private SdcPanel sdcp;
    private MessagePanel msgp;

    private CreaSDCPanel csdcp;
    private static GUIController instance;
    private Messaggio msgMittente;
    private UserInfo utilizzatoreSistema;
    private SistemaCifratura sdc;
    private Mappatura mp;

    private GUIController() {
        comC = new COMController();

    }

    public static GUIController getInstance() {
        if (instance == null) {
            instance = new GUIController();
        }
        return instance;
    }

    public void addView(View v) {
        if (v instanceof LoginForm) {
            loginForm = (LoginForm) v;
            //solo per il test
            loginForm.setUsernameField("sasha");
            loginForm.setPasswordField("1234");
            loginForm.getSubmit().addActionListener(new LoginFormListener());
            loginForm.getRegistration().addActionListener(new RegistrationFormListener());
        } else if (v instanceof PanelloPrincipale) {
            pp = (PanelloPrincipale) v;
            pp.getNuovoMessaggioBtn().addActionListener(new NuovoMessaggioListener());
            pp.getSalvaBozzaBtn().addActionListener(new SalvaMessaggioListener());
            pp.getInboxBtn().addActionListener(new GestisciInbox());
            pp.getVisualizzaMessaggioBtn().addActionListener(new visualizzaMessRicevListener());
            pp.getLogoutBtn().addActionListener(new LogoutListener());
            pp.getGestisciBozzeBtn().addActionListener(new GestisciBozzeListener());
            pp.getSDCBtn().addActionListener(new GestisciSDC());
        } else if (v instanceof SdcPanel) {
            sdcp = (SdcPanel) v;
            sdcp.getCreaSDCBtn().addActionListener(new CreateSDCListener());
        } else if (v instanceof CreaSDCPanel) {
            csdcp = (CreaSDCPanel) v;
            csdcp.getCesareRBtn().addActionListener(new MetodoDicifraturaListener());
            csdcp.getParolaChiaveRBtn().addActionListener(new MetodoDicifraturaListener());
            csdcp.getPseudocasualeRBtn().addActionListener(new MetodoDicifraturaListener());
            csdcp.getSalvaSdcBtn().addActionListener(new SalvaMetodoDicifraturaListener());
            csdcp.getProvasdcBtn().addActionListener(new ProvaMetodoDicifraturaListener());
        } else if (v instanceof RegistrationForm) {
            regForm = (RegistrationForm) v;
            regForm.getCancelBtn().addActionListener(new CancelListener());
            regForm.getSubmitBtn().addActionListener(new RegisterListener());
        }
    }

    //classe listener per il login
    private class LoginFormListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean valid = comC.authenticate(loginForm.getUsername(), loginForm.getPassword());
            if (valid) {
                loginForm.dispose();
                PanelloPrincipale pp = new PanelloPrincipale();
                utilizzatoreSistema = new UserInfo(comC.getStudente().getId(), comC.getStudente().getNome(), comC.getStudente().getCognome());
            } else {
                loginForm.getErrorLoginLabel().setText("Errore di autenticazione");
            }
        }
    }

    //classe listener per il button "nuovo messaggio" della finestra principale
    private class NuovoMessaggioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " Clicked " + ev.getText());
            //TO-DO da modificare perche devono apparire solo destinatari con cui il studente ha concluso una proposta Scifratura
            pp.setDestinatariArrLst(comC.getDestinatari());
            System.out.println(comC.getDestinatari().toString());
            pp.initNuovoMessaggio();
            //predispone il nuovo messaggio
            msgMittente = new Messaggio();
        }
    }

    //classe listener per il button Inbox della finestra principale
    private class GestisciInbox implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            ArrayList<MessaggioDestinatario> temp = Messaggio.caricaMessaggiDestinatario(utilizzatoreSistema.getId());
            pp.setMittentiMessaggiArrLst(temp);
            pp.initInBox();
        }
    }
    
    //classe listener per la Jlist "ElencoMessaggiRicevuti" 
    private class visualizzaMessRicevListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
  //          System.out.println("QUIIIIIIIIIIIIIIIIIIIIIIII");
            MessaggioDestinatario mess = (MessaggioDestinatario) pp.getElencoMessaggiRicevuti().getSelectedValue();
  //          System.out.println(mess.getTesto());
             pp.modificaCorpoMessaggio(mess.getTesto());
        }
    } 
    
    
    

    //classe listener per il button "bozze" della finestra principale 
    private class GestisciBozzeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            ArrayList<MessaggioMittente> temp = Messaggio.caricaBozze(utilizzatoreSistema.getId());
            pp.setBozzeArrayLst(temp);
            pp.initGestioneBozze();
        }
    }

    //classe listener per il button "Sistema di cifratura" della finestra principale
    private class GestisciSDC implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            pp.initSDC();
        }
    }

    private class CreateSDCListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " Clicked " + ev.getText());
            sdcp.initCreateSDC();
        }
    }

    //classe listener per JRadioButtons per selezionare il metodo di cifratura
    private class MetodoDicifraturaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton ev = (JRadioButton) e.getSource();
            System.out.println(this.getClass() + " selected " + ev.getText());
            if (ev.getText().equalsIgnoreCase("parola chiave")) {
                csdcp.initParolaChiave();
            } else if (ev.getText().equalsIgnoreCase("cesare")) {
                csdcp.initCesare();
            }
        }
    }

    //classe listener per il button "salva messaggio" della finestra principale
    private class SalvaMessaggioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //un messaggio senza titolo non si puo salvare
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " Clicked " + ev.getText());
            System.out.println(this.getClass() + " Selected " + pp.getSelectedDestinatario().toString());
            System.out.println(pp.getTittoloMessaggioField() + " - Tittolo del messaggio");
            //se il tittolo del messaggio e vuouto mostra il messaggio
            String temp = pp.getTittoloMessaggioField().replaceAll("\\s+", "");
            if (temp.equals("")) {
                pp.setStatusLabelText("Il titolo del messaggio deve contenere almeno un carattere");
            } else { //altrimenti salva il messaggio
                pp.setStatusLabelText("");
                JList list = pp.getElencoDestinatari();
                UserInfo destinatario = (UserInfo) list.getSelectedValue();
                System.out.println("Destinatario selected: " + destinatario.toString());
                msgMittente = new Messaggio(msgMittente.getId(), pp.getCorpoMessaggio(), pp.getTittoloMessaggioField(), true, utilizzatoreSistema, destinatario);
                //se msg.salva ritorna false allora errore
                if (msgMittente.salva()) {
                    pp.setStatusLabelText("Messaggio Salvato!");
                } else {
                    pp.setStatusLabelText("Si è verificato un durante il salvataggio del messaggio!");
                }
            }
        }
    }

    //classe listener per salvare SDC - ascolta il bottone salva
    private class SalvaMetodoDicifraturaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            pp.setStatusLabelText("");
            sdc = new SistemaCifratura(utilizzatoreSistema);
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " selected " + ev.getText());
            if (ev.getText().equalsIgnoreCase("Salva cifrario parola chiave")) {
                String metodo = "parola chiave";
                if (csdcp.getTable().isEditing()) {
                    csdcp.getTable().getCellEditor().stopCellEditing();
                }
                String chiave = "";
                for (int i = 0; i < 26; i++) {
                    chiave = chiave + "" + csdcp.getData(0, i);
                }
                if (sdc.valid(metodo, chiave)) {
                    mp = sdc.create(metodo, chiave);
                    System.out.print(mp.inverseMap('a'));
                    System.out.print(mp.inverseMap('b'));
                    System.out.print(mp.inverseMap('c'));
                } else {
                    pp.setStatusLabelText("La mappatura non è coretta o contiene caratteri illegali - sono accetate solo lettere");
                }
            }
        }
    }

    //classe listener per provare SDC - ascolta il bottone prova sdc
    private class ProvaMetodoDicifraturaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            pp.setStatusLabelText("");
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " selected " + ev.getText());
            sdc = new SistemaCifratura(utilizzatoreSistema);
            String metodo = "parola chiave";
            if (csdcp.getTable().isEditing()) {
                csdcp.getTable().getCellEditor().stopCellEditing();
            }
            String chiave = "";
            for (int i = 0; i < 26; i++) {
                chiave = chiave + "" + csdcp.getData(0, i);
            }
            if (sdc.valid(metodo, chiave)) {
                mp = sdc.create(metodo, chiave);
                String a = csdcp.getCorpoMessaggioProva().getText();
                csdcp.getCorpoMessaggioResult().setText(sdc.prova(a));
            } else {
                pp.setStatusLabelText("La mappatura non è coretta o contiene caratteri illegali - sono accetate solo lettere");
            }

        }
    }

    //classe listener per il button "salva messaggio" della finestra principale
    private class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            pp.dispose();
            LoginForm f = new LoginForm();
            System.out.println(this.getClass() + " Messaggio: Logout");
        }
    }

    //classe listener per la reistrazione dell'utente
    private class RegistrationFormListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loginForm.dispose();
            RegistrationForm rf = new RegistrationForm();
        }
    }

    //classe listener per il button "annulla" della finestra registrazione utente
    private class CancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            regForm.dispose();
            LoginForm f = new LoginForm();
            System.out.println(this.getClass() + "Registration: cancel");
        }
    }

    //classe listener per il button "OK" della finestra registrazione utente
    private class RegisterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Studente s = new Studente(regForm.getNameField(), regForm.getSurnameField(),
                    regForm.getNicknameField(), regForm.getPasswordField());
            s.salva();
            regForm.dispose();
            LoginForm f = new LoginForm();
            System.out.println(this.getClass() + "Registration: OK");
        }
    }

}
