/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.com;

import cryptohelper.GUI.LoginForm;
import cryptohelper.GUI.PanelloPrincipale;
import cryptohelper.GUI.SdcPanel;
import cryptohelper.GUI.View;
import cryptohelper.data.Messaggio;
import cryptohelper.data.MessaggioMittente;
import cryptohelper.data.UserInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

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
    private PanelloPrincipale pp;
    private SdcPanel sdcp;
    private static GUIController instance;
    private Messaggio msgMittente;
    private UserInfo utilizzatoreSistema;

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
        } else if (v instanceof PanelloPrincipale) {
            pp = (PanelloPrincipale) v;
            //listenere dei bottoni delle interface
            //Si crea il button nell'interfaccia. Con get e set qui si settano
            pp.getNuovoMessaggioBtn().addActionListener(new NuovoMessaggioListener());
            pp.getSalvaBozzaBtn().addActionListener(new SalvaMessaggioListener());
            pp.getLogoutBtn().addActionListener(new LogoutListener());
            pp.getGestisciBozzeBtn().addActionListener(new GestisciBozzeListener());
            pp.getSDCBtn().addActionListener(new GestisciSDC());
        } else if (v instanceof SdcPanel) {
            System.out.println(" instanceof SdcPanel");
            sdcp = (SdcPanel) v;
            sdcp.getCreaSDCBtn().addActionListener(new createSDC());
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
                loginForm.getErrorLoginLabel().setText("Error");
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

            pp.setDestinatari(comC.getDestinatari());
            System.out.println(comC.getDestinatari().toString());
            pp.initNuovoMessaggio();
            //predispone il nuovo messaggio
            msgMittente = new Messaggio();
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
                pp.setStatus("Il titolo del messaggio deve contenere almeno un carattere");
            } else { //altrimenti salva il messaggio
                pp.setStatus("");
                UserInfo destinatario = (UserInfo) pp.getSelectedDestinatario();
                msgMittente = new Messaggio(msgMittente.getId(), pp.getCorpoMessaggio(), pp.getTittoloMessaggioField(), true, utilizzatoreSistema, destinatario);
                //se msg.salva ritorna false allora errore
                if (msgMittente.salva()) {
                    pp.setStatus("Messaggio Salvato!");
                } else {
                    pp.setStatus("Si è verificato un durante il salvataggio del messaggio!");
                }
            }
        }
    }

    //classe listener per il button "bozze" della finestra principale 
    private class GestisciBozzeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            ArrayList<MessaggioMittente> temp = Messaggio.caricaBozze(utilizzatoreSistema.getId());
            pp.setBozzeArayLst(temp);
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

    private class createSDC implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " Clicked " + ev.getText());
            sdcp.initCreateSDC();
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

}
