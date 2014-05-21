//pannello messaggi in arrivo

package cryptohelper.GUI;

import cryptohelper.interfaces.MessaggioDestinatario;
import cryptohelper.interfaces.View;
import cryptohelper.com.GUIController;
import cryptohelper.data.HtmlVisitor;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import java.awt.*;
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
        messageTextLabel = new JLabel("Testo del messaggio:");
        decifraBtn = new JButton("Decifra messaggio");
        eliminaMessaggioBtn = new JButton("Elimina messaggio");
        corpoMessaggio = new JTextPane();
        corpoMessaggio.setPreferredSize(new Dimension(600, 250));
        corpoMessaggio.setContentType("text/html"); //consente formattazione html
        corpoMessaggio.setEditable(false); //rende in sola lettura il campo con il testo del messaggio
        corpoMessaggio.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea
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
        elencoMessaggiRicevuti.setPreferredSize(new Dimension(165, 250));
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(elencoMessaggiRicevuti);

        //AGGIUNTA DEI CONTROLLI AI PANNELLI        
        leftPanel.add(corpoMessaggio, BorderLayout.CENTER);
        rightPanel.add(targetListLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
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
        GUIController gc = GUIController.getInstance();
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

}
