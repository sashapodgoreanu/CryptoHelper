//Classe Login Form - Gestisce il form per l'accesso al servizio da parte degli utenti

package cryptohelper.GUI;

import cryptohelper.data.Studente;
import cryptohelper.com.GUIController;
import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    Studente studente;
    GUIController gc;
    FlowLayout sl;
    JButton submit;
    JLabel username;
    JLabel password;
    public JLabel erorLogin;
    JTextField passwordField;
    JTextField usernameField;

    public LoginForm(Studente st) {
        studente = st;
        gc = new GUIController(studente, this);
        this.setPreferredSize(new Dimension(320, 200));
        this.setTitle("CryptoHelper - Login");
        sl = new FlowLayout();
        submit = new JButton("Accedi");
        submit.addActionListener(gc);
        passwordField = new JTextField();
        passwordField.setPreferredSize(new Dimension(200, 24));
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 24));
        username = new JLabel("Username");
        password = new JLabel("Password");
        erorLogin = new JLabel("");
        this.setLayout(sl);
        this.add(username);
        this.add(usernameField);
        this.add(password);
        this.add(passwordField);
        this.add(submit);
        this.add(erorLogin);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);   //centra la finestra sullo schermo
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void fillStudent(Studente st) {
        st.setNickanme(usernameField.getText());
        st.setPassword(passwordField.getText());
        System.out.println("fill " + st.toString());
    }
}
