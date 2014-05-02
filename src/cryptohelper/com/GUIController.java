/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.com;

import cryptohelper.GUI.LoginForm;
import cryptohelper.GUI.View;
import cryptohelper.data.Studente;
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
public class GUIController{

    private Studente st;
    private LoginForm loginForm;
    private static GUIController instance;

    private GUIController() {
    }

    public static GUIController getInstance() {
        if (instance == null) {
            instance = new GUIController();
        }
        return instance;
    }
    
    public void addModel(Model m){
        if(m instanceof Studente){
            
        }
    }

    public void addView(View v) {
        if (v instanceof LoginForm) {
            loginForm = (LoginForm) v;
            loginForm.submit.addActionListener(new ActionListener() {
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
            });
        }
    }

}
