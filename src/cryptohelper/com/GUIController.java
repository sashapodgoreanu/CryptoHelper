/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.com;

import cryptohelper.GUI.LoginForm;
import cryptohelper.bean.Studente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

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
public class GUIController implements ActionListener {

    private Studente st;
    private LoginForm loginForm;

    public GUIController(Studente st, LoginForm loginForm) {
        this.st = st;
        this.loginForm = loginForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton ev = (JButton) e.getSource();
        if (ev.getText().equals("Accedi")) {
            loginForm.fillStudent(st);
            if (st.authenticate()) {
                loginForm.dispose();
                JFrame jFrame = new JFrame();
                jFrame.setVisible(true);
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } else {
                loginForm.erorLogin.setText("Error");
            }

        }
    }
}
