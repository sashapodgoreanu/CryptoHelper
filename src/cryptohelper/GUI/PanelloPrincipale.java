//Finestra principale della GUI
package cryptohelper.GUI;

import cryptohelper.com.GUIController;
import cryptohelper.data.MessaggioDestinatario;
import cryptohelper.data.MessaggioMittente;
import cryptohelper.data.Studente;
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
    ArrayList<UserInfo> destinatariArrLst;                //elenco destinatari
    ArrayList<MessaggioDestinatario> mittentiArrLst;      //elenco messaggi inbox
    ArrayList<MessaggioMittente> destArrLst;              //elenco messaggi outobx
    ArrayList<MessaggioMittente> bozzeArrLst;             //elenco delle bozze

    JPanel sdcPanel = new SdcPanel();
    JButton proponiSDCBtn;
    JButton creaSDCBtn;
    JButton inboxProposteSDCBtn;
    JButton proposteAccetateBtn;

    Studente studente;

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
        logoutBtn = new JButton("Logout");
        toolbarPanel.setBackground(Color.LIGHT_GRAY);
        toolbarPanel.add(nuovoMessaggioBtn);
        toolbarPanel.add(inboxBtn);
        toolbarPanel.add(gestisciBozzeBtn);
        toolbarPanel.add(outboxBtn);
        toolbarPanel.add(SDCBtn);
        toolbarPanel.add(logoutBtn);

        //INIT DI BODY PANEL
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.setBorder(new EmptyBorder(10, 10, 10, 10));   //padding per separare i controlli dal bordo della finestra

        //INIT DI STATUS LABEL
        statusLabel = new JLabel("Selezionare un'opzione per iniziare");
        statusLabel.setOpaque(true);
        statusLabel.setBackground(Color.LIGHT_GRAY);
        statusLabel.setForeground(Color.RED);
        statusLabel.setBorder(new EmptyBorder(10, 10, 10, 10)); //padding per il tsto della status bar

        //INIT DEL FRAME
        this.setTitle("CryptoHelper - Menu Principale");
        this.setSize(750, 500);
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
        this.setTitle("CryptoHelper - Nuovo Messggio");     //cambia titolo al form
        bodyPanel.add(new MessagePanel(destinatariArrLst)); //aggiunge il nuovo pannello
        bodyPanel.revalidate();                             //completa l'inizializzazione dell'interfaccia
    }

    //Iniziallizza l'interfaccia e i componenti quando viene premuto il button "Inbox"
    public void initInbox() {
        this.resetPanels();
        this.setTitle("CryptoHelper - Inbox");    //cambia titolo al form
        this.setStatusLabelText("Selezionare un messaggio per aprirlo"); //messaggio per la status label
        bodyPanel.add(new InboxPanel(mittentiArrLst));      //aggiunge il nuovo pannello
        bodyPanel.revalidate();                             //completa l'inizializzazione dell'interfaccia
    }

    //Iniziallizza l'interfaccia e i componenti quando viene premuto il button "Outbox"
    public void initOutbox() {
        this.resetPanels();
        this.setTitle("CryptoHelper - Messaggi inviati");    //cambia titolo al form
        this.setStatusLabelText("Selezionare un messaggio per visualizzarlo"); //messaggio per la status label
        bodyPanel.add(new OutboxPanel(destArrLst));          //aggiunge il nuovo pannello
        bodyPanel.revalidate();                              //completa l'inizializzazione dell'interfaccia
    }

    //Inizializza l'interfaccia e i componenti quando viene premuto il button "gestisci bozze"  
    public void initGestioneBozze() {
        this.resetPanels();
        this.setTitle("CryptoHelper - Gestisci Bozze");   //cambia titolo al form
        this.setStatusLabelText("Selezionare una bozza per visualizzarla"); //messaggio per la status label
        bodyPanel.add(new BozzePanel(bozzeArrLst));       //aggiunge il nuovo pannello
        bodyPanel.revalidate();                           //completa l'inizializzazione dell'interfaccia
    }

    public void initSDC() {
        System.out.println("inside initSDC");
        this.resetPanels();
        this.setTitle("CryptoHelper - Sistema di Cifratura");   //cambia titolo al form
        sdcPanel = new SdcPanel();
        bodyPanel.add(sdcPanel);
        bodyPanel.revalidate();
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

    public JButton getLogoutBtn() {
        return logoutBtn;
    }

    public ArrayList<UserInfo> getDestinatari() {
        return destinatariArrLst;
    }

    public ArrayList<MessaggioDestinatario> getMittentiMessaggiArrLst() {
        return mittentiArrLst;
    }

    public ArrayList<MessaggioMittente> getBozzeArayLst() {
        return bozzeArrLst;
    }

    //METODI SETTER
    public void setDestinatariArrLst(ArrayList<UserInfo> destinatari) {
        this.destinatariArrLst = destinatari;
    }

    public void setLogoutBtn(JButton logoutBtn) {
        this.logoutBtn = logoutBtn;
    }

    public void setStatusLabelText(String statusLabel) {
        this.statusLabel.setText(statusLabel);
    }

    public void setInboxBtn(JButton inboxBtn) {
        this.inboxBtn = inboxBtn;
    }

    public void setNuovoMessaggioBtn(JButton nuovoMessaggioBtn) {
        this.nuovoMessaggioBtn = nuovoMessaggioBtn;
    }

    public void setMittentiMessaggiArrLst(ArrayList<MessaggioDestinatario> bozzeArayLst) {
        this.mittentiArrLst = bozzeArayLst;
    }

    public void setBozzeArrayLst(ArrayList<MessaggioMittente> bozzeArayLst) {
        this.bozzeArrLst = bozzeArayLst;
    }

}
