//pannello per la gestione dei messaggi in arrivo
package cryptohelper.GUI.UC1;

import cryptohelper.com.GUIControllerUC1;
import cryptohelper.data.HtmlVisitor;
import cryptohelper.interfaces.MessaggioDestinatario;
import cryptohelper.interfaces.View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class InboxPanel extends JPanel implements View {

    JPanel topPanel;         //pannello in alto
    JPanel leftPanel;        //pannello a sinistra
    JPanel rightPanel;       //pannello a destra
    JPanel bottomPanel;      //pannello in basso
    JLabel targetListLabel;
    JLabel messageTextLabel;
    JButton decifraBtn;
    JButton eliminaMessaggioBtn;
    JTextField chiaveField;
    JLabel chiaveLabel;
    JList elencoMessaggiRicevuti;
    JTextPane corpoMessaggio;
    JScrollPane scrollPane;
    ArrayList<MessaggioDestinatario> mittentiMessaggiArrLst; //elenco mittenti dei messaggi di cui l'utente loggato è destinatario

    public InboxPanel(ArrayList<MessaggioDestinatario> mittentiMessaggiArrLst) {
        topPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();
        this.mittentiMessaggiArrLst = mittentiMessaggiArrLst;
        this.init();
    }

    //inizializza il pannello
    private void init() {
        System.out.println("Inizzializzazione Pannello Inbox...");   //comunicazione di controllo per i log

        //INIT DEI LAYOUT
        this.setLayout(new BorderLayout());
        topPanel.setLayout(new FlowLayout());
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());
        topPanel.setBorder(new EmptyBorder(0, 0, 20, 0));   //padding per separare i controlli

        //INIT DEI CONTROLLI
        targetListLabel = new JLabel("Messaggi ricevuti:");
        messageTextLabel = new JLabel("Contenuto del messaggio:");
        decifraBtn = new JButton("Decifra messaggio");
        eliminaMessaggioBtn = new JButton("Elimina messaggio");

        chiaveField = new JTextField(10);
        chiaveLabel = new JLabel("Chiave:");
        corpoMessaggio = new JTextPane();
        corpoMessaggio.setPreferredSize(new Dimension(600, 250));
        corpoMessaggio.setContentType("text/html"); //consente formattazione html
        corpoMessaggio.setEditable(false); //rende in sola lettura il campo con il testo del messaggio
        Border b = BorderFactory.createLineBorder(Color.GRAY);  //crea un bordo al controllo
        corpoMessaggio.setBorder(BorderFactory.createCompoundBorder(b, BorderFactory.createEmptyBorder(0, 10, 0, 10))); //assegna un margine al controllo
        elencoMessaggiRicevuti = new JList(new Vector<MessaggioDestinatario>(mittentiMessaggiArrLst));
        elencoMessaggiRicevuti.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof MessaggioDestinatario) {
                    MessaggioDestinatario temp = (MessaggioDestinatario) value;
                    ((JLabel) renderer).setText(temp.getTitolo() + " ");
                }
                return renderer;
            }
        });
        elencoMessaggiRicevuti.setSelectedIndex(0);
        MessaggioDestinatario index0 = (MessaggioDestinatario) elencoMessaggiRicevuti.getSelectedValue();
        if (index0 != null) {
            corpoMessaggio.setText(new HtmlVisitor().visit(index0));
        }
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(165, 250));
        scrollPane.setViewportView(elencoMessaggiRicevuti);

        //AGGIUNTA DEI CONTROLLI AI PANNELLI      
        leftPanel.add(messageTextLabel, BorderLayout.NORTH);
        leftPanel.add(corpoMessaggio, BorderLayout.CENTER);
        rightPanel.add(targetListLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(chiaveLabel);
        bottomPanel.add(chiaveField);
        bottomPanel.add(decifraBtn);
        bottomPanel.add(eliminaMessaggioBtn);

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
        GUIControllerUC1 gc = GUIControllerUC1.getInstance();
        gc.addView(this);
    }

    //elimina l'elemento selezionato nella lista delle bozze e aggiorna la vista
    public boolean deleteSelectedIndex() {
        int toDelete = elencoMessaggiRicevuti.getSelectedIndex();
        if (toDelete >= 0) {
            rightPanel.removeAll();
            topPanel.removeAll();
            leftPanel.removeAll();
            bottomPanel.removeAll();
            mittentiMessaggiArrLst.remove(toDelete);
            init();
            rightPanel.revalidate();
            topPanel.revalidate();
            leftPanel.revalidate();
            bottomPanel.revalidate();
            return true;
        }
        return false;
    }

    //METODI GETTER
    public JList getElencoMessaggiRicevuti() {
        return elencoMessaggiRicevuti;
    }

    public JTextPane getCorpoMessaggio() {
        return corpoMessaggio;
    }

    public JButton getEliminaMessaggioBtn() {
        return eliminaMessaggioBtn;
    }

    //METODI SETTER 
    public void setCorpoMessaggio(String testo) {
        corpoMessaggio.setText(testo);
    }

    public ArrayList<MessaggioDestinatario> getMittentiMessaggiArrLst() {
        return mittentiMessaggiArrLst;
    }

    public void setMittentiMessaggiArrLst(ArrayList<MessaggioDestinatario> bozzeArayLst) {
        this.mittentiMessaggiArrLst = bozzeArayLst;
    }

    public JButton getDecifraBtn() {
        return decifraBtn;
    }

    public JTextField getChiaveField() {
        return chiaveField;
    }



}
