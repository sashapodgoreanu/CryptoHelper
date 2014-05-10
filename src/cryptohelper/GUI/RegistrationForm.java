//Classe RegistrationForm - Gestisce l'interfaccia per la registrazione di nuovi utenti
package cryptohelper.GUI;

import cryptohelper.com.GUIController;
import java.awt.*;
import javax.swing.*;

public class RegistrationForm extends JFrame implements View {

    GUIController gc;
    JButton submitBtn;
    JButton cancelBtn;
    JLabel nameLabel;
    JLabel surnameLabel;
    JLabel nicknameLabel;
    JLabel passwordLabel;
    JLabel errorLoginLabel;
    JTextField nameField;
    JTextField surnameField;
    JTextField nicknameField;
    JTextField passwordField;

    public RegistrationForm() {
        submitBtn = new JButton("OK");
        cancelBtn = new JButton("Annulla");
        passwordField = new JTextField();
        passwordField.setPreferredSize(new Dimension(200, 24));
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 24));
        surnameField = new JTextField();
        surnameField.setPreferredSize(new Dimension(200, 24));
        nicknameField = new JTextField();
        nicknameField.setPreferredSize(new Dimension(200, 24));
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 24));
        nameLabel = new JLabel("Nome");
        surnameLabel = new JLabel("Cognome");
        nicknameLabel = new JLabel("Nickname");
        passwordLabel = new JLabel("Password");
        errorLoginLabel = new JLabel("");
        errorLoginLabel.setForeground(Color.RED);
        this.setTitle("CryptoHelper - Registrazione di un nuovo utente");
        this.setSize(new Dimension(300, 200));
        this.setLayout(new FlowLayout());
        this.add(nameLabel);
        this.add(nameField);
        this.add(surnameLabel);
        this.add(surnameField);
        this.add(nicknameLabel);
        this.add(nicknameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(cancelBtn);
        this.add(submitBtn);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);   //centra la finestra sullo schermo
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.registerController();
    }

    @Override
    public void registerController() {
        gc = GUIController.getInstance();
        gc.addView(this);
    }

    //METODI GETTER
    public JLabel getErrorLoginLabel() {
        return errorLoginLabel;
    }

    public JButton getSubmitBtn() {
        return submitBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }
  
    
    //METODI SETTER
    public void setErorLogin(JLabel erorLogin) {
        this.errorLoginLabel = erorLogin;
    }
}
