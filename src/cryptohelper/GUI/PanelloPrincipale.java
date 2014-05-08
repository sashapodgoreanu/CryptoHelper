//Finestra principale della GUI
package cryptohelper.GUI;

import cryptohelper.com.GUIController;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class PanelloPrincipale extends JFrame implements View {

    JPanel toolbarPanel = new JPanel(); //pannello con pulsanti "nuovo messaggio", "inbox", "logout"...
    JPanel bodyPanel = new JPanel();    //pannello contenitore dell'area di lavoro
    JPanel topPanel = new JPanel();     //pannello in alto all'interno del bodyPanel;
    JPanel leftPanel = new JPanel();    //pannello a sinistra all'interno del bodyPanel;
    JPanel rightPanel = new JPanel();   //pannello a destra con elenco destinatari
    JPanel bottomPanel = new JPanel();  //pannello in basso con i pulsanti all'interno del bosy panel
    JLabel statusLabel;
    JButton nuovoMessaggioBtn;
    JButton gestisciBozzeBtn;
    JButton inboxBtn;
    JButton logoutBtn;
    JButton saveBozzaBtn = new JButton("Salva bozza");
    JButton deleteBozzaBtn = new JButton("Elimina bozza");
    JButton sendMessageBtn = new JButton("Invia messaggio");
    JTextField titoloMessaggioField;    //Input per Messaggio
    JList elencoDestinatari;            //visualizza la lista dei destinatari
    JList elencoBozze;                  //visualizza lalista delle bozze
    JTextArea corpoMessaggio;
    ArrayList<UserInfo> destinatari;

    Studente studente;
    GUIController gc = GUIController.getInstance();

    public PanelloPrincipale() {
        this.init();
    }

    private void init() {
        System.out.println("Inizzializzazione PanelloPrincipale...");   //comunicazione di controllo per i log

        //CONFIG DI TOOLBAR PANEL
        nuovoMessaggioBtn = new JButton("Nuovo Messaggio");
        inboxBtn = new JButton("Inbox");
        gestisciBozzeBtn = new JButton("Gestisci bozze");
        logoutBtn = new JButton("Logout");
        toolbarPanel.setBackground(Color.LIGHT_GRAY);
        toolbarPanel.add(nuovoMessaggioBtn);
        toolbarPanel.add(inboxBtn);
        toolbarPanel.add(gestisciBozzeBtn);
        toolbarPanel.add(logoutBtn);

        //CONFIG DI BODY PANEL
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.setBorder(new EmptyBorder(10, 10, 10, 10));   //padding per separare i controlli dal bordo della finestra
        bodyPanel.add(topPanel, BorderLayout.NORTH);
        bodyPanel.add(rightPanel, BorderLayout.EAST);
        bodyPanel.add(leftPanel, BorderLayout.WEST);
        bodyPanel.add(bottomPanel, BorderLayout.SOUTH);

        //CONFIG DEI PANNELLI INTERNI AL BODY PANEL
        topPanel.setLayout(new FlowLayout());
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());

        //CONFIG DI STATUS LABEL
        statusLabel = new JLabel("Selezionare un'opzione per iniziare");
        statusLabel.setOpaque(true);
        statusLabel.setBackground(Color.LIGHT_GRAY);
        statusLabel.setForeground(Color.RED);
        statusLabel.setBorder(new EmptyBorder(10, 10, 10, 10)); //padding per il tsto della status bar

        //CONFIG DI MAIN FORM
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

    //pulisce i panelli dell'area di lavoro
    private void resetPanels() {
        topPanel.removeAll();
        leftPanel.removeAll();
        rightPanel.removeAll();
        bottomPanel.removeAll();
        statusLabel.setText(" ");
    }

    //Inizializza l'interfaccia e i componenti quando viene premuto il button "nuovo messaggio"
    public void initNuovoMessaggio() {
        resetPanels();
        this.setTitle("CryptoHelper - Nuovo Messaggio");    //cambia titolo al form      
        JLabel msgTitlelLabel = new JLabel("Titolo del messaggio:");
        titoloMessaggioField = new JTextField(21);
        topPanel.add(msgTitlelLabel);
        topPanel.add(titoloMessaggioField);
        JLabel targetListLabel = new JLabel("Destinatari disponibili:");
        elencoDestinatari = new JList(new Vector<UserInfo>(destinatari));
        elencoDestinatari.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof UserInfo) {
                    UserInfo temp = (UserInfo) value;
                    ((JLabel) renderer).setText(temp.getNome() + " " + temp.getCognome());
                }
                return renderer;
            }
        });
        elencoDestinatari.setSelectedIndex(0);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(elencoDestinatari);
        rightPanel.add(targetListLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        corpoMessaggio = new JTextArea();
        corpoMessaggio.setSize(new Dimension(540, 250));
        corpoMessaggio.setLineWrap(true);
        corpoMessaggio.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea
        JLabel messaggioLabel = new JLabel("Testo del messaggio:");
        bottomPanel.add(saveBozzaBtn);
        bottomPanel.add(sendMessageBtn);
        leftPanel.add(messaggioLabel, BorderLayout.NORTH);
        leftPanel.add(corpoMessaggio, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
        System.out.println("initNuovoMessaggio");
        bodyPanel.revalidate();  //completa l'inizializzazione dell'interfaccia
    }

    //Inizializza l'interfaccia e i componenti quando viene premuto il button "nuovo messaggio"  
    public void initGestioneBozze() {
        resetPanels();
        this.setTitle("CryptoHelper - Gestisci Bozze");   //cambia titolo al form
        JLabel msgTitlelLabel = new JLabel("Titolo della bozza:");
        titoloMessaggioField = new JTextField(21);
        topPanel.add(msgTitlelLabel);
        topPanel.add(titoloMessaggioField);
        bottomPanel.add(saveBozzaBtn);
        bottomPanel.add(deleteBozzaBtn);
        corpoMessaggio = new JTextArea();
        corpoMessaggio.setSize(new Dimension(540, 250));
        corpoMessaggio.setLineWrap(true);
        corpoMessaggio.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea
        leftPanel.add(corpoMessaggio);

        JLabel bozzeListLabel = new JLabel("Bozze disponibili:");
        JScrollPane scrollPane = new JScrollPane();
        //   scrollPane.setViewportView(elencoBozze);
        elencoBozze = new JList(new Vector<UserInfo>(destinatari));
        rightPanel.add(bozzeListLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        bodyPanel.revalidate();     //completa l'inizializzazione dell'interfaccia

    }

    public void eliminaListaDestinatariESetDestinatario() {

    }

    @Override
    public void registerController() {
        gc = GUIController.getInstance();
        gc.addView(this);
    }

    //METODI GETTER
    public JList getDestinatariCC() {
        return elencoDestinatari;
    }

    public Object getSelectedDestinatario() {
        return elencoDestinatari.getSelectedValue();
    }

    public JButton getNuovoMessaggioBtn() {
        return nuovoMessaggioBtn;
    }

    public JButton getSalvaBozzaBtn() {
        return saveBozzaBtn;
    }

    public JButton getInboxBtn() {
        return inboxBtn;
    }

    public String getTittoloMessaggioField() {
        return titoloMessaggioField.getText();
    }

    public JButton getGestisciBozzeBtn() {
        return gestisciBozzeBtn;
    }

    public String getStatus() {
        return statusLabel.getText();
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public JButton getLogoutBtn() {
        return logoutBtn;
    }

    public String getCorpoMessaggio() {
        return corpoMessaggio.getText();
    }

    public ArrayList<UserInfo> getDestinatari() {
        return destinatari;
    }

    //METODI SETTER
    public void setCorpoMessaggio(String msg) {
        this.corpoMessaggio.setText(msg);
    }

    public void setDestinatari(ArrayList<UserInfo> destinatari) {
        this.destinatari = destinatari;
    }

    public void setLogoutBtn(JButton logoutBtn) {
        this.logoutBtn = logoutBtn;
    }

    public void setStatus(String statusLabel) {
        this.statusLabel.setText(statusLabel);
    }

    public void setStatusLabel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
    }

    public void setTittoloMessaggioField(String titolo) {
        this.titoloMessaggioField.setText(titolo);
    }

    public void setInboxBtn(JButton inboxBtn) {
        this.inboxBtn = inboxBtn;
    }

    public void setNuovoMessaggioBtn(JButton nuovoMessaggioBtn) {
        this.nuovoMessaggioBtn = nuovoMessaggioBtn;
    }

    public void setDestinatariCC(JList destinatariCC) {
        this.elencoDestinatari = destinatariCC;
    }

}
