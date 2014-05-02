/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.com;

import cryptohelper.GUI.LoginForm;
import cryptohelper.GUI.PanelloPrincipale;
import cryptohelper.GUI.View;
import cryptohelper.data.Model;
import cryptohelper.data.Studente;
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
    private PanelloPrincipale panelloPrincipale;
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

    /*
    public void addModel(Model m) {
        if (m instanceof Studente) {
            studente = (Studente) m;
        }
    }
    */

    public void addView(View v) {
        if (v instanceof LoginForm) {
            loginForm = (LoginForm) v;
            loginForm.getSubmit().addActionListener(new LoginFormListener());
        } else if (v instanceof PanelloPrincipale) {
            panelloPrincipale = (PanelloPrincipale) v;
            panelloPrincipale.getNuovoMessaggioBtn().addActionListener(new PanelloPrincipaleListener());
        }
    }

    /*
    public Studente getStudente() {
        if (studente != null) {
            return studente;
        } else {
            return new Studente();
        }
    }*/

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
            //}
        }
    }

    private class PanelloPrincipaleListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked");
            //if (ev.getText().equals("Nuovo Messaggio")) {
            panelloPrincipale.initNuovoMessaggio();
            //}
        }
    }

}
