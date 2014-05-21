//Finestra principale della GUI
package cryptohelper.GUI.areaLavoro;

import cryptohelper.GUI.InboxPanel;
import cryptohelper.interfaces.View;
import cryptohelper.com.GUIControllerAL;
import cryptohelper.data.Messaggio;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class AreaLavoro extends JFrame implements View {

    JPanel toolbarPanel = new JPanel(); //pannello con pulsanti "nuovo messaggio", "inbox", "logout"...
    JPanel bodyPanel = new JPanel();    //pannello contenitore dell'area di lavoro
    JLabel statusLabel;                 //status bar per messaggi in basso
    JPanel sceglimsg;
    JButton nuovaSessioneBtn;
    JButton salvaSessioneBtn;
    JButton caricaSessioneBtn;
    JButton logoutBtn;
    ArrayList<Messaggio> msgArrLst;

    public AreaLavoro() {
        this.init();
    }

    private void init() {
        System.out.println("Inizzializzazione Area di Lvaoro...");   //comunicazione di controllo per i log

        //INIT DI TOOLBAR PANEL
        logoutBtn = new JButton("Logout");
        nuovaSessioneBtn = new JButton("Nuova sessione di lavoro");
        caricaSessioneBtn = new JButton("Carica  sessione di lavoro");
        salvaSessioneBtn = new JButton("Salva sessione di lavoro");
        salvaSessioneBtn.setEnabled(false);
        toolbarPanel.setBackground(Color.LIGHT_GRAY);
        toolbarPanel.add(nuovaSessioneBtn);
        toolbarPanel.add(caricaSessioneBtn);
        toolbarPanel.add(salvaSessioneBtn);
        toolbarPanel.add(logoutBtn);

        //INIT DI BODY PANEL
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.setBorder(new EmptyBorder(10, 50, 10, 50));   //padding per separare i controlli dal bordo della finestra

        //INIT DI STATUS LABEL
        statusLabel = new JLabel("Selezionare un'opzione per continuare");
        statusLabel.setOpaque(true);
        statusLabel.setBackground(Color.LIGHT_GRAY);
        statusLabel.setForeground(Color.RED);
        statusLabel.setBorder(new EmptyBorder(10, 10, 10, 10)); //padding per il tsto della status bar

        //INIT DEL FRAME
        this.setTitle("CryptoHelper - Intercetta un messaggio");
        this.setSize(900, 550);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.add(toolbarPanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);
        this.add(statusLabel, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);   //centra la finestra sullo schermo
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerController();
    }

    //pulisce i panelli dell'area principale dell'interfaccia
    private void resetPanels() {
        bodyPanel.removeAll();
        statusLabel.setText(" ");
    }

    //Inizializza l'interfaccia e i componenti quando viene premuto il button "nuovo messaggio"
    public void initNuovaSessione(ArrayList<Messaggio> msgArrLst) {
        this.resetPanels();
        this.setTitle("CryptoHelper - Nuova sessione di lavoro");    //cambia titolo al form
        this.setStatus("Selezionare il messaggio da intercettare e premere 'Avanti'"); //messaggio per la status label
        bodyPanel.add(new ScegliMsgPanel(msgArrLst));       //aggiunge il nuovo pannello
        bodyPanel.revalidate();                             //completa l'inizializzazione dell'interfaccia
    }

    @Override
    public void registerController() {
        GUIControllerAL gcAL = GUIControllerAL.getInstance();
        gcAL.addView(this);
    }

    //METODI GETTER
    public String getStatusLabelText() {
        return statusLabel.getText();
    }

    public JButton getLogoutBtn() {
        return logoutBtn;
    }

    public JButton getNuovaSessioneBtn() {
        return nuovaSessioneBtn;
    }

    //METODI SETTER
    public void setStatus(String statusLabel) {
        this.statusLabel.setText(statusLabel);
    }

}
