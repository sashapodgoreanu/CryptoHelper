//Finestra principale della GUI
package cryptohelper.GUI;

import cryptohelper.abstractC.View;
import cryptohelper.com.GUIController;
import cryptohelper.abstractC.MessaggioDestinatario;
import cryptohelper.abstractC.MessaggioMittente;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class OutboxPanel extends JPanel implements View {

    JPanel topPanel;         //pannello in alto
    JPanel leftPanel;        //pannello a sinistra
    JPanel rightPanel;       //pannello a destra
    JPanel bottomPanel;      //pannello in basso
    JLabel targetListLabel;
    JLabel messageTextLabel;
    JList elencoMessaggiInviati;
    JTextArea corpoMessaggio;
    JScrollPane scrollPane;
    ArrayList<MessaggioMittente> destinatariMessaggiArrLst; //elenco mittenti

    public OutboxPanel(ArrayList<MessaggioMittente> destinatariMessaggiArrLst) {
        this.destinatariMessaggiArrLst = destinatariMessaggiArrLst;
        this.init();
    }

    //inizializza il pannello
    private void init() {
        System.out.println("Inizzializzazione Pannello Outbox...");   //comunicazione di controllo per i log

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
        targetListLabel = new JLabel("Messaggi inviati:");
        messageTextLabel = new JLabel("Testo del messaggio:");
        elencoMessaggiInviati = new JList(new Vector<MessaggioMittente>(destinatariMessaggiArrLst));
        elencoMessaggiInviati.setCellRenderer(new DefaultListCellRenderer() {
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
        elencoMessaggiInviati.setSelectedIndex(0);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(elencoMessaggiInviati);
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


    public JList getElencoMessaggiInviati() {
        return elencoMessaggiInviati;
    }

    public String getCorpoMessaggio() {
        return corpoMessaggio.getText();
    }


    //METODI SETTER
    public void setCorpoMessaggio(String msg) {
        this.corpoMessaggio.setText(msg);
    }

    public ArrayList<MessaggioMittente> getMittentiMessaggiArrLst() {
        return destinatariMessaggiArrLst;
    }

    public void setMittentiMessaggiArrLst(ArrayList<MessaggioMittente> bozzeArayLst) {
        this.destinatariMessaggiArrLst = bozzeArayLst;
    }
    
    

}
