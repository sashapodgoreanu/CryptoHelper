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
    JPanel toolbarPanel;
    JPanel nuovoMessaggioPnl;
    JPanel inboxPnl;
    JPanel apriBozzaPnl;
    JPanel panelcorpoMessaggio;
    JPanel panelTitolo;
    JPanel bodyPanel; 
    JLabel errorlabel;
    JButton nuovoMessaggioBtn;
    JButton inboxBtn;
    JButton apriBozzaBtn;
    JButton logoutBtn;
    JButton salvaBozzaBtn;

    //Input per Messaggio
    JTextField tittoloMessaggioField;
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
        System.out.println("Init PanelloPrincipale");
        this.setTitle("CryptoHelper - Menu");    
        this.setPreferredSize(new Dimension(600, 500));
        
        //CREAZIONE PANNELLI
        toolbarPanel = new JPanel();        //pannello in alto con i button "nuovo messaggio", "inbox", ecc...
        bodyPanel = new JPanel();           //pannello dei contenuti posto sotto al toolbarPanel
        
        //PRIOPRIETA' TOOLBAR PANEL
        nuovoMessaggioBtn = new JButton("Nuovo Messaggio");
        inboxBtn = new JButton("Inbox");
        apriBozzaBtn = new JButton("Bozze");
        logoutBtn = new JButton("Logout"); 
        toolbarPanel.setBackground(Color.blue);
        toolbarPanel.add(nuovoMessaggioBtn);
        toolbarPanel.add(inboxBtn);
        toolbarPanel.add(apriBozzaBtn);
        toolbarPanel.add(logoutBtn);
        
        nuovoMessaggioPnl = new JPanel();
        inboxPnl = new JPanel();
        apriBozzaPnl = new JPanel();
        panelcorpoMessaggio = new JPanel();
        panelTitolo = new JPanel();

        errorlabel = new JLabel("ERRORE - da eliminare la scrita al inizio- prova test");
        errorlabel.setForeground(Color.red);

 
        salvaBozzaBtn = new JButton("Salva Messaggio");


        




        this.add(toolbarPanel, BorderLayout.NORTH);
        this.add(errorlabel, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerController();
    }

    public JButton getSalvaBozzaBtn() {
        return salvaBozzaBtn;
    }

    public void initNuovoMessaggio() {
        remakeCENTERPanels();
        this.setTitle("CryptoHelper - Menu - Nuovo Messaggio");

        JLabel tittoloMessaggioLabel = new JLabel("Titolo: ");
        tittoloMessaggioField = new JTextField(20);
        panelTitolo.setLayout(new BorderLayout());
        panelTitolo.add(tittoloMessaggioLabel, BorderLayout.WEST);
        panelTitolo.add(tittoloMessaggioField);
        JLabel destinatarioMessaggioLabel = new JLabel("Destinatario: ");
        destinatariCC = new JList(new Vector<UserInfo>(destinatari));
        destinatariCC.setVisibleRowCount(6);
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
        bodyPanel.add(destinatarioMessaggioLabel);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(destinatariCC);
        bodyPanel.add(scrollPane);
        corpoMessaggio = new JTextArea();
        corpoMessaggio.setPreferredSize(new Dimension(300, 500));
        JLabel messaggioLabel = new JLabel("Messaggio");
        panelcorpoMessaggio.add(messaggioLabel);
        panelcorpoMessaggio.add(corpoMessaggio);
        nuovoMessaggioPnl.setLayout(new FlowLayout());
        nuovoMessaggioPnl.add(bodyPanel);
        nuovoMessaggioPnl.add(panelTitolo);
        nuovoMessaggioPnl.add(panelcorpoMessaggio);
        nuovoMessaggioPnl.add(salvaBozzaBtn);

        this.add(nuovoMessaggioPnl, BorderLayout.CENTER);
        this.pack();
        SwingUtilities.updateComponentTreeUI(this);
        System.out.println("initNuovoMessaggio");
    }

    public void eliminaListaDestinatariESetDestinatario() {

    }

    private void remakeCENTERPanels() {

        System.out.println("remake: Nuovo Messaggio");
        //remake nuovo messaggio
        bodyPanel.removeAll();
        panelTitolo.removeAll();
        panelcorpoMessaggio.removeAll();
        nuovoMessaggioPnl.removeAll();

        this.remove(nuovoMessaggioPnl);

        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void registerController() {
        gc = GUIController.getInstance();
        gc.addView(this);
    }

    public String getErrorlabel() {
        return errorlabel.getText();
    }
    

    public void setErrorlabel(String errorlabel) {
        this.errorlabel.setText(errorlabel);
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
        return tittoloMessaggioField.getText();
    }

    public void setTittoloMessaggioField(String titolo) {
        this.tittoloMessaggioField.setText(titolo);
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

}
