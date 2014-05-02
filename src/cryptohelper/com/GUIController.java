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
import cryptohelper.data.MessaggioMittente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
/**
 * Il controllore Deve conoscere: i modelli - beans le viste - JFrame 's
 *
 * La vista Deve conoscere: i controllori i modelli
 *
 * Modello non deve conoscere nessuno dei due.
 *
 */
public class GUIController {

    private COMController comC;
    private LoginForm loginForm;
    private PanelloPrincipale pp;
    private static GUIController instance;

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
            pp.getNuovoMessaggioBtn().addActionListener(new NuovoMessaggioListener());
            pp.getSalvaBozzaBtn().addActionListener(new SalvaMessaggioListener());
        }
    }

    private class LoginFormListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean valid = comC.authenticate(loginForm.getUsername(), loginForm.getPassword());
            if (valid) {
                loginForm.dispose();
                PanelloPrincipale pp = new PanelloPrincipale();
            } else {
                loginForm.getErorLogin().setText("Error");
            }
        }
    }

    private class NuovoMessaggioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked "+ev.getText());
            pp.initNuovoMessaggio();
        }
    }
    private class SalvaMessaggioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked "+ev.getText());
            MessaggioMittente mm = new Messaggio();
            pp.getDestinatarioMessaggioField()
            comC.salvaMessaggioBozza(pp.getTittoloMessaggioField(), ,
            pp.getCorpoMessaggio());
        }
    }

}
