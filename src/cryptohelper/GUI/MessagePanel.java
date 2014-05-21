//Finestra principale della GUI
package cryptohelper.GUI;

import cryptohelper.interfaces.View;
import cryptohelper.com.GUIController;
import cryptohelper.data.UserInfo;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import javax.swing.border.EmptyBorder;

public class MessagePanel extends JPanel implements View {

    JPanel topPanel;         //pannello in alto
    JPanel leftPanel;        //pannello a sinistra
    JPanel rightPanel;       //pannello a destra
    JPanel bottomPanel;      //pannello in basso
    JButton newMessageBtn;
    JButton saveBozzaBtn;
    JButton inviaMessageBtn;
    JLabel msgTitlelLabel;
    JLabel targetListLabel;
    JLabel messageTextLabel;
    JLabel languageLabel;
    JLabel chiaveMsg;
    JTextArea corpoMessaggio;
    JTextField titoloMessaggioField;
    JTextField chiaveField;
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
        topPanel.setBorder(new EmptyBorder(0, 0, 20, 0));   //padding per separare i controlli

        //INIT DEI CONTROLLI
        String[] lingua = {"inglese", "italiano"};
        linguaDropdown = new JComboBox(lingua);
        linguaDropdown.setBackground(Color.WHITE);
        newMessageBtn = new JButton("");
        saveBozzaBtn = new JButton("Salva come bozza");
        inviaMessageBtn = new JButton("Invia");
        msgTitlelLabel = new JLabel("Titolo del messaggio:");
        targetListLabel = new JLabel("Destinatari disponibili:");
        messageTextLabel = new JLabel("Testo del messaggio:");
        languageLabel = new JLabel("Lingua del messaggio: ");
        titoloMessaggioField = new JTextField(21);
        chiaveField = new JTextField(21);
        chiaveMsg = new JLabel();
        elencoDestinatari = new JList(new Vector<UserInfo>(destinatariArrLst));
        elencoDestinatari.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof UserInfo) {
                    UserInfo temp = (UserInfo) value;
                    //System.out.println("renderer " + temp.toString());
                    ((JLabel) renderer).setText(temp.getNome() + " " + temp.getCognome());
                }
                return renderer;
            }
        });  
        inviaMessageBtn.setEnabled(true);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(elencoDestinatari);
        scrollPane.setPreferredSize(new Dimension(165, 250));
        corpoMessaggio = new JTextArea();
        corpoMessaggio.setSize(new Dimension(600, 250));
        corpoMessaggio.setLineWrap(true);
        corpoMessaggio.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea

        //AGGIUNTA DEI CONTROLLI AI PANNELLI
        topPanel.add(msgTitlelLabel);
        topPanel.add(titoloMessaggioField);
        topPanel.add(languageLabel);
        topPanel.add(linguaDropdown);
        leftPanel.add(messageTextLabel, BorderLayout.NORTH);
        leftPanel.add(corpoMessaggio, BorderLayout.CENTER);
        rightPanel.add(targetListLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(saveBozzaBtn);
        bottomPanel.add(inviaMessageBtn);

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

    /*
     public void initChiave(String metodo){
     bottomPanel.remove(chiaveField);
     bottomPanel.remove(chiaveMsg);
     if(metodo.equals("parola chiave")){
     chiaveField = new JTextField(26);
     chiaveMsg.setText("Parola-chiave password");
     bottomPanel.add(chiaveMsg, 0);
     bottomPanel.add(chiaveField, 1);
     bottomPanel.revalidate();
     }
     }*/
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

    public String getLingua() {
        return linguaDropdown.getSelectedItem().toString();
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

    public JComboBox getLinguaDropdown() {
        return linguaDropdown;
    }

    public JButton getInviaMessageBtn() {
        return inviaMessageBtn;
    }

}
