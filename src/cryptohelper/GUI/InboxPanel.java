package cryptohelper.GUI;

import cryptohelper.interfaces.MessaggioDestinatario;
import cryptohelper.interfaces.View;
import cryptohelper.com.GUIController;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import java.awt.*;

public class InboxPanel extends JPanel implements View {

    JPanel topPanel;         //pannello in alto
    JPanel leftPanel;        //pannello a sinistra
    JPanel rightPanel;       //pannello a destra
    JPanel bottomPanel;      //pannello in basso
    JLabel targetListLabel;
    JLabel messageTextLabel;
    JList elencoMessaggiRicevuti;
    JTextArea corpoMessaggio;
    JScrollPane scrollPane;
    ArrayList<MessaggioDestinatario> mittentiMessaggiArrLst; //elenco mittenti

    public InboxPanel(ArrayList<MessaggioDestinatario> mittentiMessaggiArrLst) {
        this.mittentiMessaggiArrLst = mittentiMessaggiArrLst;
        this.init();
    }

    //inizializza il pannello
    private void init() {
        System.out.println("Inizzializzazione Pannello Inbox...");   //comunicazione di controllo per i log

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
        targetListLabel = new JLabel("Messaggi ricevuti:");
        messageTextLabel = new JLabel("Testo del messaggio:");
        elencoMessaggiRicevuti = new JList(new Vector<MessaggioDestinatario>(mittentiMessaggiArrLst));
        elencoMessaggiRicevuti.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof MessaggioDestinatario) {
                    MessaggioDestinatario temp = (MessaggioDestinatario) value;
                    ((JLabel) renderer).setText(temp.getTitolo() + " " + temp.getId());
                }
                return renderer;
            }
        });
        elencoMessaggiRicevuti.setSelectedIndex(0);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(elencoMessaggiRicevuti);
        corpoMessaggio = new JTextArea();
        corpoMessaggio.setSize(new Dimension(540, 250));
        corpoMessaggio.setLineWrap(true);  //manda a capo il testo al bordo del controllo
        corpoMessaggio.setEditable(false); //rende in sola lettura il campo con il testo del messaggio
        corpoMessaggio.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea

        //AGGIUNTA DEI CONTROLLI AI PANNELLI        
        leftPanel.add(corpoMessaggio, BorderLayout.CENTER);
        rightPanel.add(targetListLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        //AGGIUNTA DEI PANNELLI
        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);

        //REGISTRAZIONE VISTA NEL COTROLLER
        registerController();
    }

    public void modificaCorpoMessaggio(String testo) {
        corpoMessaggio.setText(testo);
    }

    @Override
    public void registerController() {
        GUIController gc = GUIController.getInstance();
        gc.addView(this);
    }

    //METODI GETTER


    public JList getElencoMessaggiRicevuti() {
        return elencoMessaggiRicevuti;
    }

    public String getCorpoMessaggio() {
        return corpoMessaggio.getText();
    }


    //METODI SETTER
    public void setCorpoMessaggio(String msg) {
        this.corpoMessaggio.setText(msg);
    }

    public ArrayList<MessaggioDestinatario> getMittentiMessaggiArrLst() {
        return mittentiMessaggiArrLst;
    }

    public void setMittentiMessaggiArrLst(ArrayList<MessaggioDestinatario> bozzeArayLst) {
        this.mittentiMessaggiArrLst = bozzeArayLst;
    }
    
}
