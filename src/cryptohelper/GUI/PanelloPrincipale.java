//Finestra principale della GUI
package cryptohelper.GUI;

import cryptohelper.com.GUIController;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class PanelloPrincipale extends JFrame implements View {

    //TO-DO 
    //aggiungere una JList con lingue
    //aggiungere una lista con destinatari (Vedi state machine diagram file UML.)
    JMenuBar menuBar;
    JMenu fileMenu;
    JPanel toolbarPanel;
    JPanel nuovoMessaggioPnl;
    JPanel inboxPnl;
    JPanel apriBozzaPnl;
    JPanel panelcorpoMessaggio;
    JPanel panelTitolo;
    JPanel bodyPanel;
    JLabel statusLabel;
    JButton nuovoMessaggioBtn;
    JButton inboxBtn;
    JButton apriBozzaBtn;
    JButton logoutBtn;
    JButton salvaBozzaBtn;

    //Input per Messaggio
    JTextField titoloMessaggioField;
    //forse è una lista di destinatari con cui o concordato un SCifratura
    JList destinatariCC;
    JTextArea corpoMessaggio;
    ArrayList<UserInfo> destinatari;

    Studente studente;
    GUIController gc = GUIController.getInstance();

    public PanelloPrincipale() {
        this.init();
    }

    private void init() {
        System.out.println("Inizzializzazione PanelloPrincipale...");   //comunicazione di controllo per i log

        //CREAZIONE PANNELLI
        toolbarPanel = new JPanel();        //pannello in alto con i button "nuovo messaggio", "inbox", ecc...
        bodyPanel = new JPanel();           //pannello dei contenuti posto sotto al toolbarPanel

        //PROPRIETA' TOOLBAR PANEL
        nuovoMessaggioBtn = new JButton("Nuovo Messaggio");
        inboxBtn = new JButton("Inbox");
        apriBozzaBtn = new JButton("Bozze");
        logoutBtn = new JButton("Logout");
        toolbarPanel.setBackground(Color.BLUE);
        toolbarPanel.add(nuovoMessaggioBtn);
        toolbarPanel.add(inboxBtn);
        toolbarPanel.add(apriBozzaBtn);
        toolbarPanel.add(logoutBtn);

        //PROPRIETA' BODY PANEL
        bodyPanel.setBackground(Color.GREEN);
        bodyPanel.setLayout(new BorderLayout());

        
       // nuovoMessaggioPnl = new JPanel();
       // inboxPnl = new JPanel();
       // apriBozzaPnl = new JPanel();
       // panelcorpoMessaggio = new JPanel();
      //  panelTitolo = new JPanel();
       // panelTitolo.setBackground(Color.RED);

        statusLabel = new JLabel("ERRORE - da eliminare la scrita al inizio- prova test");
        statusLabel.setForeground(Color.red);

        salvaBozzaBtn = new JButton("Salva Messaggio");

        this.setTitle("CryptoHelper - Menu");
        this.setSize(600, 500);
        this.setLayout(new BorderLayout());
        this.add(toolbarPanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);
        this.add(statusLabel, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerController();
    }
    
    //Riorganizza línterfaccia quando viene premuto il button "nuovo messaggio"
     public void initNuovoMessaggio(){
         
        cleanBodyPanel();   //pulisce tutti i panel
        this.setTitle("CryptoHelper - Menu - Nuovo Messaggio"); //cambia titolo al form      
        JLabel msgTitlelLabel = new JLabel("Titolo: ");
        titoloMessaggioField = new JTextField(10);
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(msgTitlelLabel);
        titlePanel.add(titoloMessaggioField);
        bodyPanel.add(titlePanel,BorderLayout.NORTH);
        JLabel targetMessageLabel = new JLabel("Destinatario: ");
        destinatariCC = new JList(new Vector<UserInfo>(destinatari));
        destinatariCC.setVisibleRowCount(30);
        destinatariCC.setCellRenderer(new DefaultListCellRenderer() {
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
        destinatariCC.setSelectedIndex(0);
        JPanel rightPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(destinatariCC);
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(targetMessageLabel,BorderLayout.NORTH);
        rightPanel.add(scrollPane,BorderLayout.CENTER);
        bodyPanel.add(rightPanel,BorderLayout.EAST);


        corpoMessaggio = new JTextArea();
        corpoMessaggio.setPreferredSize(new Dimension(300, 500));
        JLabel messaggioLabel = new JLabel("Messaggio");
        bodyPanel.add(messaggioLabel);
        bodyPanel.add(corpoMessaggio);
        bodyPanel.add(salvaBozzaBtn);
        SwingUtilities.updateComponentTreeUI(this);
        System.out.println("initNuovoMessaggio");
    }
     
    private void cleanBodyPanel() {
        bodyPanel.removeAll();
        SwingUtilities.updateComponentTreeUI(this);
    }

   

    public void eliminaListaDestinatariESetDestinatario() {

    }

    public JButton getSalvaBozzaBtn() {
        return salvaBozzaBtn;
    }

    @Override
    public void registerController() {
        gc = GUIController.getInstance();
        gc.addView(this);
    }

    public String getErrorlabel() {
        return statusLabel.getText();
    }

    public void setErrorlabel(String errorlabel) {
        this.statusLabel.setText(errorlabel);
    }

    public JList getDestinatariCC() {
        return destinatariCC;
    }

    public void setDestinatariCC(JList destinatariCC) {
        this.destinatariCC = destinatariCC;
    }

    public Object getSelectedDestinatario() {
        return destinatariCC.getSelectedValue();
    }

    public JButton getNuovoMessaggioBtn() {
        return nuovoMessaggioBtn;
    }

    public void setNuovoMessaggioBtn(JButton nuovoMessaggioBtn) {
        this.nuovoMessaggioBtn = nuovoMessaggioBtn;
    }

    public JButton getInboxBtn() {
        return inboxBtn;
    }

    public void setInboxBtn(JButton inboxBtn) {
        this.inboxBtn = inboxBtn;
    }

    public JButton getApriBozzaBtn() {
        return apriBozzaBtn;
    }

    public void setApriBozzaBtn(JButton apriBozzaBtn) {
        this.apriBozzaBtn = apriBozzaBtn;
    }

    public String getTittoloMessaggioField() {
        return titoloMessaggioField.getText();
    }

    public void setTittoloMessaggioField(String titolo) {
        this.titoloMessaggioField.setText(titolo);
    }

    /*
     public String getDestinatarioMessaggioField() {
     return destinatariCC.getText();
     }

     public void setDestinatarioMessaggioField(String dest) {
     this.destinatariCC.setText(dest);
     }*/
    public String getCorpoMessaggio() {
        return corpoMessaggio.getText();
    }

    public void setCorpoMessaggio(String msg) {
        this.corpoMessaggio.setText(msg);
    }

    public ArrayList<UserInfo> getDestinatari() {
        return destinatari;
    }

    public void setDestinatari(ArrayList<UserInfo> destinatari) {
        this.destinatari = destinatari;
    }

    public JButton getLogoutBtn() {
        return logoutBtn;
    }

    public void setLogoutBtn(JButton logoutBtn) {
        this.logoutBtn = logoutBtn;
    }

}
