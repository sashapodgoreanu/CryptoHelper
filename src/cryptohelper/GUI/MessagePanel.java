//Finestra principale della GUI
package cryptohelper.GUI;

import cryptohelper.com.GUIController;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MessagePanel extends JPanel implements View {

    JPanel topPanel;         //pannello in alto
    JPanel leftPanel;        //pannello a sinistra
    JPanel rightPanel;       //pannello a destra
    JPanel bottomPanel;      //pannello in basso
    JButton newMessageBtn;
    JButton saveBozzaBtn;
    JButton sendMessageBtn;
    JLabel msgTitlelLabel;
    JLabel targetListLabel;
    JLabel messageTextLabel;
    JTextArea corpoMessaggio;
    JTextField titoloMessaggioField;
    JScrollPane scrollPane;
    JList elencoDestinatari;            //visualizza la lista dei destinatariArrLst
    ArrayList<UserInfo> destinatariArrLst;
    JComboBox linguaDropdown;

    
    public MessagePanel(ArrayList<UserInfo> destinatariArrLst) {
        this.destinatariArrLst = destinatariArrLst;
        this.init();

    }

    //inizializza il pannello
    private void init() {
        System.out.println("Inizializzazione Pannello Nuovo Messaggio...");   //comunicazione di controllo per i log

        //INIT DEI PANNELLI E DEI LAYOUT
        this.setLayout(new BorderLayout());
        topPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());

        //INIT DEI CONTROLLI
        //drop box
        String[] lingua = {"inglese", "italiano"};
        linguaDropdown = new JComboBox(lingua);
        
        newMessageBtn = new JButton("Invia Messaggio");
        saveBozzaBtn = new JButton("Salva messaggio come bozza");
        sendMessageBtn = new JButton("nuovo Messaggio");
        msgTitlelLabel = new JLabel("Titolo del messaggio:");
        targetListLabel = new JLabel("Destinatari disponibili:");
        messageTextLabel = new JLabel("Testo del messaggio:");
        titoloMessaggioField = new JTextField(21);
        elencoDestinatari = new JList(new Vector<UserInfo>(destinatariArrLst));
        elencoDestinatari.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof UserInfo) {
                    UserInfo temp = (UserInfo) value;
                    //System.out.println("renderer " + temp.toString());
                    ((JLabel) renderer).setText(temp.getId() + " " + temp.getNome() + " " + temp.getCognome());
                }
                return renderer;
            }
        });
        elencoDestinatari.setSelectedIndex(0);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(elencoDestinatari);
        corpoMessaggio = new JTextArea();
        corpoMessaggio.setSize(new Dimension(540, 250));
        corpoMessaggio.setLineWrap(true);
        corpoMessaggio.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea

        //AGGIUNTA DEI CONTROLLI AI PANNELLI
        topPanel.add(msgTitlelLabel);
        topPanel.add(titoloMessaggioField);
        topPanel.add(linguaDropdown);
        leftPanel.add(messageTextLabel, BorderLayout.NORTH);
        leftPanel.add(corpoMessaggio, BorderLayout.CENTER);
        rightPanel.add(targetListLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(saveBozzaBtn);
        bottomPanel.add(sendMessageBtn);

        //AGGIUNTA DEI PANNELLI
        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);

        //REGISTRAZIONE VISTA NEL COTROLLER
        registerController();
    }

    @Override
    public void registerController() {
        GUIController gc = GUIController.getInstance();
        gc.addView(this);
    }

    //METODI GETTER
    public JList getDestinatariCC() {
        return elencoDestinatari;
    }

    public Object getSelectedDestinatario() {
        return elencoDestinatari.getSelectedValue();
    }

    public JList getElencoDestinatari() {
        return elencoDestinatari;
    }

    public JButton getNuovoMessaggioBtn() {
        return newMessageBtn;
    }

    public JButton getSalvaBozzaBtn() {
        return saveBozzaBtn;
    }

    public String getTitoloMessaggioField() {
        return titoloMessaggioField.getText();
    }

    public String getCorpoMessaggio() {
        return corpoMessaggio.getText();
    }

    public ArrayList<UserInfo> getDestinatari() {
        return destinatariArrLst;
    }

    //METODI SETTER
    public void setCorpoMessaggio(String msg) {
        this.corpoMessaggio.setText(msg);
    }

    public void setDestinatari(ArrayList<UserInfo> destinatari) {
        this.destinatariArrLst = destinatari;
    }

    public void setTittoloMessaggioField(String titolo) {
        this.titoloMessaggioField.setText(titolo);
    }

    public void setNuovoMessaggioBtn(JButton nuovoMessaggioBtn) {
        this.newMessageBtn = nuovoMessaggioBtn;
    }

    public void setDestinatariCC(JList destinatariCC) {
        this.elencoDestinatari = destinatariCC;
    }

    public void setElencoDestinatari(JList elencoDestinatari) {
        this.elencoDestinatari = elencoDestinatari;
    }

}
