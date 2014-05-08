//Classe Login Form - Gestisce il form per l'accesso al servizio da parte degli utenti

package cryptohelper.GUI;

import cryptohelper.com.GUIController;
import java.awt.*;
import javax.swing.*;

public class LoginForm extends JFrame implements View {
    
    JPanel panel;
    JButton submit;
    GUIController gc;
    JLabel username;
    JLabel password;
    JLabel errorLoginLabel;
    JTextField passwordField;
    JTextField usernameField;
    
    public LoginForm() {
        this.setPreferredSize(new Dimension(320, 200));
        this.setTitle("CryptoHelper - Login");
        panel = new JPanel();
        submit = new JButton("Accedi");
        passwordField = new JTextField();
        passwordField.setPreferredSize(new Dimension(200, 24));
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 24));
        username = new JLabel("Username");
        password = new JLabel("Password");
        errorLoginLabel = new JLabel("");
        errorLoginLabel.setForeground(Color.RED);
        panel.setLayout(new FlowLayout());
        panel.add(username);
        panel.add(usernameField);
        panel.add(password);
        panel.add(passwordField);
        panel.add(submit);
        panel.add(errorLoginLabel);
        this.add(panel);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);   //centra la finestra sullo schermo
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.registerController();
    }
    
    @Override
    public void registerController(){
        gc = GUIController.getInstance();
        gc.addView(this);
    }

    public JLabel getErrorLoginLabel() {
        return errorLoginLabel;
    }

    public void setErorLogin(JLabel erorLogin) {
        this.errorLoginLabel = erorLogin;
    }
    
    public JButton getSubmit() {
        return submit;
    }

    public void setSubmit(JButton submit) {
        this.submit = submit;
    }
    
     public String getPassword() {
        return passwordField.getText();
    }

    public void setPasswordField(String pwd) {
        this.passwordField.setText(pwd);
    }

    public String getUsername() {
        System.out.println(usernameField.getText());
        return usernameField.getText();
        
    }

    public void setUsernameField(String usr) {
        this.usernameField.setText(usr);
    }
}
