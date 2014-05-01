/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.Dimension;
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

    private JPanel panel;
    private JButton submit;
    private JLabel username;
    private JLabel password;
    private JTextField passwordField;
    private JTextField usernameField;

    public LoginForm() {
        this.setTitle("CryptoHelper - Login");
        panel = new JPanel();
        submit = new JButton("Accedi");
        passwordField = new JTextField();
        passwordField.setPreferredSize( new Dimension( 200, 24 ) );
        usernameField = new JTextField();
        usernameField.setPreferredSize( new Dimension( 200, 24 ) );
        username = new JLabel("Username");
        password = new JLabel("Password");
        panel.add(username);
        panel.add(usernameField);
        panel.add(password);
        panel.add(passwordField);
        panel.add(submit);
        this.add(panel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
