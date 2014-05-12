//Finestra principale della GUI
package cryptohelper.GUI;

import cryptohelper.com.GUIController;
import cryptohelper.data.Messaggio;
import cryptohelper.data.MessaggioDestinatario;
import cryptohelper.data.MessaggioMittente;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class BozzePanel extends JPanel implements View {

    JPanel topPanel;         //pannello in alto
    JPanel leftPanel;        //pannello a sinistra
    JPanel rightPanel;       //pannello a destra
    JPanel bottomPanel;      //pannello in basso
    JButton saveBozzaBtn;
    JButton deleteBozzaBtn;
    JButton sendMessageBtn;
    JLabel msgTitleLabel;
    JLabel bozzeListLabel;
    JTextField titoloMessaggioField;    //inputBox per il messaggio
    JList elencoDestinatari;            //visualizza la lista dei destinatariArrLst
    JList elencoMessaggiRicevuti;       //elenca i mittenti di tutti i messaggi ricevuti  
    JList elencoBozze;                  //visualizza lalista delle bozze
    JScrollPane scrollPane;
    JTextArea corpoMessaggio;
    ArrayList<UserInfo> destinatariArrLst;
    ArrayList<MessaggioDestinatario> mittentiMessaggiArrLst;
    ArrayList<MessaggioMittente> bozzeArrLst;

    Studente studente;

    public BozzePanel() {
        this.init();
    }

    //inizializza il pannello
    private void init() {
        System.out.println("Inizzializzazione Bozze...");   //comunicazione di controllo per i log

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
        saveBozzaBtn = new JButton("Salva bozza");
        deleteBozzaBtn = new JButton("Elimina bozza");
        sendMessageBtn = new JButton("Invia messaggio");
        msgTitleLabel = new JLabel("Titolo della bozza:");
        bozzeListLabel = new JLabel("Bozze disponibili:");
        titoloMessaggioField = new JTextField(21);
        corpoMessaggio = new JTextArea();
        corpoMessaggio.setSize(new Dimension(540, 250));
        corpoMessaggio.setLineWrap(true);
        corpoMessaggio.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea
        elencoBozze = new JList(new Vector<MessaggioMittente>(bozzeArrLst));
        elencoBozze.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof MessaggioMittente) {
                    MessaggioMittente temp = (Messaggio) value;
                    //System.out.println("renderer " + temp.toString());
                    ((JLabel) renderer).setText(temp.getTitolo() + " " + temp.getTesto());
                }
                return renderer;
            }
        });
        elencoBozze.setSelectedIndex(0);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(elencoBozze);

        //AGGIUNTA DEI CONTROLLI AI PANNELLI
        topPanel.add(msgTitleLabel);
        topPanel.add(titoloMessaggioField);
        bottomPanel.add(saveBozzaBtn);
        bottomPanel.add(deleteBozzaBtn);
        leftPanel.add(corpoMessaggio, BorderLayout.CENTER);
        rightPanel.add(bozzeListLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

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

    public JButton getSalvaBozzaBtn() {
        return saveBozzaBtn;
    }

    public String getCorpoMessaggio() {
        return corpoMessaggio.getText();
    }

    public ArrayList<UserInfo> getDestinatari() {
        return destinatariArrLst;
    }

    public ArrayList<MessaggioDestinatario> getMittentiMessaggiArrLst() {
        return mittentiMessaggiArrLst;
    }

    public ArrayList<MessaggioMittente> getBozzeArayLst() {
        return bozzeArrLst;
    }

    //METODI SETTER
    public void setCorpoMessaggio(String msg) {
        this.corpoMessaggio.setText(msg);
    }

    public void setDestinatari(ArrayList<UserInfo> destinatari) {
        this.destinatariArrLst = destinatari;
    }

    public void setElencoDestinatari(JList elencoDestinatari) {
        this.elencoDestinatari = elencoDestinatari;
    }

    public void setTittoloMessaggioField(String titolo) {
        this.titoloMessaggioField.setText(titolo);
    }

    public void setDestinatariCC(JList destinatariCC) {
        this.elencoDestinatari = destinatariCC;
    }

    public void setMittentiMessaggiArrLst(ArrayList<MessaggioDestinatario> bozzeArayLst) {
        this.mittentiMessaggiArrLst = bozzeArayLst;
    }

    public void setBozzeArayLst(ArrayList<MessaggioMittente> bozzeArayLst) {
        this.bozzeArrLst = bozzeArayLst;
    }

}
