//Finestra principale della GUI
package cryptohelper.GUI;

import cryptohelper.GUI.areaLavoro.AreaLavoro;
import cryptohelper.interfaces.View;
import cryptohelper.com.GUIController;
import cryptohelper.data.Messaggio;
import cryptohelper.interfaces.MessaggioDestinatario;
import cryptohelper.interfaces.MessaggioMittente;
import cryptohelper.data.UserInfo;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class PanelloPrincipale extends JFrame implements View {

    JPanel toolbarPanel = new JPanel(); //pannello con pulsanti "nuovo messaggio", "inbox", "logout"...
    JPanel bodyPanel = new JPanel();    //pannello contenitore dell'area di lavoro
    JLabel statusLabel;                 //status bar per messaggi in basso
    JButton nuovoMessaggioBtn;
    JButton inboxBtn;
    JButton outboxBtn;
    JButton gestisciBozzeBtn;
    JButton SDCBtn;
    JButton logoutBtn;
    JButton intercettaBtn;
    ArrayList<UserInfo> destinatariArrLst;    //elenco dei destinatari
    JPanel sdcPanel = new SdcPanel();
    JButton proponiSDCBtn;
    JButton creaSDCBtn;
    JButton inboxProposteSDCBtn;
    JButton proposteAccetateBtn;

    public PanelloPrincipale() {
        this.init();
    }

    private void init() {
        System.out.println("Inizzializzazione Panello Principale...");   //comunicazione di controllo per i log

        //INIT DI TOOLBAR PANEL
        nuovoMessaggioBtn = new JButton("Nuovo Messaggio");
        inboxBtn = new JButton("Inbox");
        gestisciBozzeBtn = new JButton("Gestisci bozze");
        outboxBtn = new JButton("Messaggi inviati");
        SDCBtn = new JButton("Sistemi di Cifratura");
        intercettaBtn = new JButton("Intercetta un messaggio");
        logoutBtn = new JButton("Logout");
        toolbarPanel.setBackground(Color.LIGHT_GRAY);
        toolbarPanel.add(nuovoMessaggioBtn);
        toolbarPanel.add(inboxBtn);
        toolbarPanel.add(gestisciBozzeBtn);
        toolbarPanel.add(outboxBtn);
        toolbarPanel.add(SDCBtn);
        toolbarPanel.add(intercettaBtn);
        toolbarPanel.add(logoutBtn);

        //INIT DI BODY PANEL
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.setBorder(new EmptyBorder(10, 50, 10, 50));   //padding per separare i controlli dal bordo della finestra

        //INIT DI STATUS LABEL
        statusLabel = new JLabel("Selezionare un'opzione per iniziare");
        statusLabel.setOpaque(true);
        statusLabel.setBackground(Color.LIGHT_GRAY);
        statusLabel.setForeground(Color.RED);
        statusLabel.setBorder(new EmptyBorder(10, 10, 10, 10)); //padding per il tsto della status bar

        //INIT DEL FRAME
        this.setTitle("CryptoHelper - Menu Principale");
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

    //Inizializza l'interfaccia e i componenti quando viene premuto il button "nuovo messaggio"
    public void initNuovoMessaggio() {
        this.resetPanels();
        this.setTitle("CryptoHelper - Nuovo Messaggio");    //cambia titolo al form
        this.setStatus("Compilare i campi richiesti per creare un nuovo messaggio"); //messaggio per la status label
        bodyPanel.add(new MessagePanel(destinatariArrLst)); //aggiunge il nuovo pannello
        bodyPanel.revalidate();                             //completa l'inizializzazione dell'interfaccia
    }

    //Iniziallizza l'interfaccia e i componenti quando viene premuto il button "Inbox"
    public void initInbox(ArrayList<MessaggioDestinatario> mittentiArrLst) {
        this.resetPanels();
        this.setTitle("CryptoHelper - Inbox");    //cambia titolo al form
        this.setStatus("Selezionare un messaggio per aprirlo"); //messaggio per la status label
        bodyPanel.add(new InboxPanel(mittentiArrLst));      //aggiunge il nuovo pannello
        bodyPanel.revalidate();                             //completa l'inizializzazione dell'interfaccia
    }

    //Iniziallizza l'interfaccia e i componenti quando viene premuto il button "Outbox"
    public void initOutbox(ArrayList<MessaggioMittente> destArrLst) {
        this.resetPanels();
        this.setTitle("CryptoHelper - Messaggi inviati");  //cambia titolo al form
        this.setStatus("Selezionare un messaggio per visualizzarlo");           //messaggio per la status label
        bodyPanel.add(new OutboxPanel(destArrLst));                             //aggiunge il nuovo pannello
        bodyPanel.revalidate();                                                 //completa l'inizializzazione dell'interfaccia
    }

    //Inizializza l'interfaccia e i componenti quando viene premuto il button "gestisci bozze"  
    public void initGestioneBozze(ArrayList<MessaggioMittente> bozzeArrLst) {
        this.resetPanels();
        this.setTitle("CryptoHelper - Gestisci Bozze");   //cambia titolo al form
        this.setStatus("Selezionare una bozza per visualizzarla"); //messaggio per la status label
        bodyPanel.add(new BozzePanel(bozzeArrLst));       //aggiunge il nuovo pannello
        bodyPanel.revalidate();                           //completa l'inizializzazione dell'interfaccia
    }

    //Inizializza l'interfaccia e i componenti quando viene premuto il button "gestisci sistemi di cifratura"  
    public void initSDC() {
        System.out.println("inside initSDC");
        this.resetPanels();
        this.setTitle("CryptoHelper - Sistema di Cifratura");   //cambia titolo al form
        sdcPanel = new SdcPanel();
        bodyPanel.add(sdcPanel);
        bodyPanel.revalidate();
    }
    
    //Inizializza l'area di lavoro per la spia e chiude la finestra principale
       public void initAreaLavoro(ArrayList<Messaggio> msgArrLst) {
        AreaLavoro al = new AreaLavoro(msgArrLst);
        this.dispose();
    }
       
    //pulisce i panelli dell'area principale dell'interfaccia
    private void resetPanels() {
        bodyPanel.removeAll();
        sdcPanel.removeAll();
        statusLabel.setText(" ");
    }

    @Override
    public void registerController() {
        GUIController gc = GUIController.getInstance();
        gc.addView(this);
    }

    //METODI GETTER
    public JButton getNuovoMessaggioBtn() {
        return nuovoMessaggioBtn;
    }

    public JButton getInboxBtn() {
        return inboxBtn;
    }

    public JButton getOutboxBtn() {
        return outboxBtn;
    }

    public JButton getSDCBtn() {
        return SDCBtn;
    }

    public JButton getGestisciBozzeBtn() {
        return gestisciBozzeBtn;
    }

    public String getStatusLabelText() {
        return statusLabel.getText();
    }

    public JButton getIntercettaBtn() {
        return intercettaBtn;
    }

    public JButton getLogoutBtn() {
        return logoutBtn;
    }

    public ArrayList<UserInfo> getDestinatari() {
        return destinatariArrLst;
    }

    //METODI SETTER
    public void setDestinatariArrLst(ArrayList<UserInfo> destinatari) {
        this.destinatariArrLst = destinatari;
    }

    public void setLogoutBtn(JButton logoutBtn) {
        this.logoutBtn = logoutBtn;
    }

    public void setStatus(String statusLabel) {
        this.statusLabel.setText(statusLabel);
    }

    public void setInboxBtn(JButton inboxBtn) {
        this.inboxBtn = inboxBtn;
    }

    public void setNuovoMessaggioBtn(JButton nuovoMessaggioBtn) {
        this.nuovoMessaggioBtn = nuovoMessaggioBtn;
    }
}
