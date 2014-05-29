//Classe Login Form - Gestisce il form per l'accesso al servizio da parte degli utenti
package cryptohelper.GUI.UC1;

import cryptohelper.interfaces.*;
import cryptohelper.com.GUIControllerUC1;
import cryptohelper.interfaces.VisitableGUI;
import cryptohelper.interfaces.VisitorGUI;
import java.awt.*;
import javax.swing.*;

public class LoginForm extends JFrame implements View, VisitableGUI {

    GUIControllerUC1 gc;
    JButton submitBtn;
    JButton registrationBtn;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JLabel errorLoginLabel;
    JTextField passwordField;
    JTextField usernameField;

    public LoginForm() {
        submitBtn = new JButton("Accedi");
        registrationBtn = new JButton("Nuovo utente");
        passwordField = new JTextField();
        passwordField.setPreferredSize(new Dimension(200, 24));
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 24));
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        errorLoginLabel = new JLabel("");
        errorLoginLabel.setForeground(Color.RED);
        this.setTitle("CryptoHelper - Login");
        this.setSize(new Dimension(320, 200));
        this.setLayout(new FlowLayout());
        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(registrationBtn);
        this.add(submitBtn);
        this.add(errorLoginLabel);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);   //centra la finestra sullo schermo
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.registerController();
    }

    @Override
    public void registerController() {
        gc = GUIControllerUC1.getInstance();
        this.accept(gc);
    }

    @Override
    public void accept(VisitorGUI visitor) {
        visitor.visit(this);
    }

    //METODI GETTER
    public JLabel getErrorLoginLabel() {
        return errorLoginLabel;
    }

    public JButton getSubmit() {
        return submitBtn;
    }

    public JButton getRegistration() {
        return registrationBtn;
    }

    public String getUsername() {
        System.out.println(usernameField.getText());
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    //METODI SETTER
    public void setSubmit(JButton submit) {
        this.submitBtn = submit;
    }

    public void setPasswordField(String pwd) {
        this.passwordField.setText(pwd);
    }

    public void setUsernameField(String usr) {
        this.usernameField.setText(usr);
    }

    public void setErrorLogin(JLabel erorLogin) {
        this.errorLoginLabel = erorLogin;
    }

}
