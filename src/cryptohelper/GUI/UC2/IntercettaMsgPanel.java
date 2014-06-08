//Finestra principale della GUI
package cryptohelper.GUI.UC2;

import cryptohelper.com.GUIControllerUC2;
import cryptohelper.data.Messaggio;
import cryptohelper.data.SessioneLavoro;
import cryptohelper.interfaces.MessaggioIntercettato;
import cryptohelper.interfaces.View;
import cryptohelper.interfaces.VisitableGuiUC2;
import cryptohelper.interfaces.VisitorGuiUC2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class IntercettaMsgPanel extends JFrame implements View, VisitableGuiUC2 {

    JPanel toolbarPanel = new JPanel(); //pannello con pulsanti "nuovo messaggio", "inbox", "logout"...
    JPanel bodyPanel = new JPanel();    //pannello contenitore dell'area di lavoro
    JLabel statusLabel;                 //status bar per messaggi in basso
    JPanel sceglimsg;
    JButton nuovaSessioneBtn;
    JButton salvaSessioneBtn;
    JButton caricaSessioneBtn;
    JButton logoutBtn;
    ArrayList<Messaggio> msgArrLst;

    public IntercettaMsgPanel() {
        this.init();
    }

    private void init() {
        System.out.println("Inizzializzazione Area di Lvaoro...");   //comunicazione di controllo per i log

        //INIT DI TOOLBAR PANEL
        logoutBtn = new JButton("Logout");
        nuovaSessioneBtn = new JButton("Nuova sessione di lavoro");
        caricaSessioneBtn = new JButton("Carica sessione di lavoro");
        salvaSessioneBtn = new JButton("Salva sessione di lavoro");
        salvaSessioneBtn.setEnabled(false);
        toolbarPanel.setBackground(Color.LIGHT_GRAY);
        toolbarPanel.add(nuovaSessioneBtn);
        toolbarPanel.add(caricaSessioneBtn);
        toolbarPanel.add(salvaSessioneBtn);
        toolbarPanel.add(logoutBtn);

        //INIT DI BODY PANEL
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.setBorder(new EmptyBorder(10, 20, 10, 20));   //padding per separare i controlli dal bordo della finestra

        //INIT DI STATUS LABEL
        statusLabel = new JLabel("Selezionare un'opzione per continuare");
        statusLabel.setOpaque(true);
        statusLabel.setBackground(Color.LIGHT_GRAY);
        statusLabel.setForeground(Color.RED);
        statusLabel.setBorder(new EmptyBorder(10, 10, 10, 10)); //padding per il testo della status bar

        //INIT DEL FRAME
        this.setTitle("CryptoHelper - Intercetta un messaggio");
        this.setSize(1200, 700);
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

    //Inizializza l'interfaccia e i componenti quando viene premuto il button "nuova sessione"
    public void initNuovaSessione(ArrayList<MessaggioIntercettato> msgArrLst) {
        this.resetPanels();
        this.setTitle("CryptoHelper - Nuova sessione di lavoro");    //cambia titolo al form
        this.setStatus("Selezionare il messaggio da intercettare, immettere un nome per la sessione e premere 'Avanti'"); //messaggio per la status label
        bodyPanel.add(new NuovaSessionePanel(msgArrLst));           //aggiunge il nuovo pannello
        bodyPanel.revalidate();                                     //completa l'inizializzazione dell'interfaccia
    }

    //Inizializza l'interfaccia e i componenti quando viene premuto il button "carica sessione"
    public void initCaricaSessione(ArrayList<SessioneLavoro> sessioniArrLst) {
        this.resetPanels();
        this.setTitle("CryptoHelper - Carica sessione di lavoro");   //cambia titolo al form
        this.setStatus("Selezionare la sessione da aprire e premere 'Avanti'"); //messaggio per la status label
        System.out.println("initCaricaSessione" + sessioniArrLst.toString());
        bodyPanel.add(new CaricaSessionePanel(sessioniArrLst));      //aggiunge il nuovo pannello
        bodyPanel.revalidate();                                      //completa l'inizializzazione dell'interfaccia
    }

    //Inizializza l'interfaccia e i componenti quando viene premuto il button "carica sessione"
    public void initAreaLavoro(SessioneLavoro sessione) {
        this.resetPanels();
        this.setTitle("CryptoHelper - Analisi messaggio");   //cambia titolo al form
        if (sessione.getSoluzione().isValida()) {
            this.setStatus("HAI TROVATO LA SOLUZIONE IN QUESTA SESSIONE. NON PUOI APPORTARE MODIFICHE"); //messaggio per la status label
        } else {
            this.setStatus("Formulare una nuova ipotesi di sostituzione per iniziare a decifrare il messaggio"); //messaggio per la status label
        }
        bodyPanel.add(new AreaLavoroPanel(sessione));               //aggiunge il nuovo pannello
        bodyPanel.revalidate();                                     //completa l'inizializzazione dell'interfaccia
    }

    @Override
    public void registerController() {
        this.accept(GUIControllerUC2.getInstance());
    }

    @Override
    public void accept(VisitorGuiUC2 visitor) {
        visitor.visit(this);
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

    public JButton getCaricaSessioneBtn() {
        return caricaSessioneBtn;
    }

    public JButton getSalvaSessioneBtn() {
        return salvaSessioneBtn;
    }

    //METODI SETTER
    public void setStatus(String statusLabel) {
        this.statusLabel.setText(statusLabel);
    }

}
