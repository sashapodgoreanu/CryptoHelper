/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.GUI;

import cryptohelper.bean.Studente;
import cryptohelper.com.GUIController;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
public class LoginForm extends JFrame {

    JPanel panel;
    JButton submit;
    JLabel username;
    JLabel password;
    public JLabel erorLogin;
    JTextField passwordField;
    JTextField usernameField;
    Studente studente;
    GUIController gc;

    public LoginForm(Studente st) {
        studente = st;
        gc = new GUIController(studente, this);
        this.setTitle("CryptoHelper - Login");
        panel = new JPanel();
        submit = new JButton("Accedi");
        submit.addActionListener(gc);
        passwordField = new JTextField();
        passwordField.setPreferredSize(new Dimension(200, 24));
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 24));
        username = new JLabel("Username");
        password = new JLabel("Password");
        erorLogin = new JLabel("");
        panel.add(username);
        panel.add(usernameField);
        panel.add(password);
        panel.add(passwordField);
        panel.add(submit);
        panel.add(erorLogin);
        this.add(panel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void fillStudent(Studente st) {
        st.setNickanme(usernameField.getText());
        st.setPassword(passwordField.getText());
        System.out.println("fill" + st.toString());
    }

    public JLabel getErorLogin() {
        return erorLogin;
    }

    public void setErorLogin(JLabel erorLogin) {
        this.erorLogin = erorLogin;
    }
}
