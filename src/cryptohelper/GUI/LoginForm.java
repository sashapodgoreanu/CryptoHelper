//Classe Login Form - Gestisce il form per l'accesso al servizio da parte degli utenti

package cryptohelper.GUI;

import cryptohelper.data.Studente;
import cryptohelper.com.GUIController;
import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
public class LoginForm extends JFrame implements View {
    
    JPanel panel;
    JButton submit;
    Studente studente;
    GUIController gc;
    FlowLayout sl;
    JLabel username;
    JLabel password;
    JLabel erorLogin;
    JTextField passwordField;
    JTextField usernameField;
    
    public LoginForm() {
        //studente = new Studente();
        this.setPreferredSize(new Dimension(320, 200));
        this.setTitle("CryptoHelper - Login");
        sl = new FlowLayout();
        panel = new JPanel();
        submit = new JButton("Accedi");
        //submit.addActionListener(gc);
        passwordField = new JTextField();
        passwordField.setPreferredSize(new Dimension(200, 24));
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 24));
        username = new JLabel("Username");
        password = new JLabel("Password");
        erorLogin = new JLabel("");
        panel.setLayout(sl);
        panel.add(username);
        panel.add(usernameField);
        panel.add(password);
        panel.add(passwordField);
        panel.add(submit);
        panel.add(erorLogin);
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
        studente = gc.getSt();
    }

    public void fillStudent(Studente st) {
        st.setNickanme(usernameField.getText());
        st.setPassword(passwordField.getText());
        System.out.println("fill " + st.toString());
    }
    
    
    public JLabel getErorLogin() {
        return erorLogin;
    }

    public void setErorLogin(JLabel erorLogin) {
        this.erorLogin = erorLogin;
    }
    
    public JButton getSubmit() {
        return submit;
    }

    public void setSubmit(JButton submit) {
        this.submit = submit;
    }
}
