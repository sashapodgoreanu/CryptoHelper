/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.com;

import cryptohelper.GUI.LoginForm;
import cryptohelper.GUI.PanelloPrincipale;
import cryptohelper.GUI.View;
import cryptohelper.data.Messaggio;
import cryptohelper.data.UserInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
/**
 * Il controllore Deve conoscere: i modelli e le viste - JFrame 's
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
            //Si crea bottone nella interfaccia con get e set e qui si settano
            pp.getNuovoMessaggioBtn().addActionListener(new NuovoMessaggioListener());
            pp.getSalvaBozzaBtn().addActionListener(new SalvaMessaggioListener());
        }
    }

    //Classi che implementano ActionListener
    private class LoginFormListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean valid = comC.authenticate(loginForm.getUsername(), loginForm.getPassword());
            if (valid) {
                loginForm.dispose();
                PanelloPrincipale pp = new PanelloPrincipale();
                utilizzatoreSistema = new UserInfo(comC.getStudente().getId(), comC.getStudente().getNome(), comC.getStudente().getCognome());
            } else {
                loginForm.getErorLogin().setText("Error");
            }
        }
    }

    private class NuovoMessaggioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            //TO-DO da modificare perche devono apparire solo destinatari con cui il studente ha concluso una proposta Scifratura
            pp.setDestinatari(comC.getDestinatari());
            pp.initNuovoMessaggio();
            //predispone il nuovo messaggio
            msgMittente = new Messaggio();
        }
    }

    private class SalvaMessaggioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //un messaggio senza titolo non si puo salvare
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            System.out.println("Selected " + pp.getSelectedDestinatario().toString());
            System.out.println(pp.getTittoloMessaggioField()+" - Tittolo del messaggio");
            //se il tittolo del messaggio e vuouto mostra il messaggio
            String temp = pp.getTittoloMessaggioField().replaceAll("\\s+","");
            if (temp.equals("")) {
                pp.setErrorlabel("Il titolo del messaggio deve contenere almeno un carattere");
            } else {
                pp.setErrorlabel("");
                UserInfo destinatario = (UserInfo) pp.getSelectedDestinatario();
                msgMittente = new Messaggio(msgMittente.getId(), pp.getCorpoMessaggio(), pp.getTittoloMessaggioField(), true, utilizzatoreSistema, destinatario);
                msgMittente.salva();
                System.out.println("Messaggio: " + msgMittente.toString());
            }
        }
    }

}
