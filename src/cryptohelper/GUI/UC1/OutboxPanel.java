//Pannello per la gestione dei messaggi inviati
package cryptohelper.GUI.UC1;

import cryptohelper.interfaces.View;
import cryptohelper.com.GUIControllerUC1;
import cryptohelper.data.HtmlVisitor;
import cryptohelper.interfaces.MessaggioDestinatario;
import cryptohelper.interfaces.MessaggioMittente;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class OutboxPanel extends JPanel implements View {

    JPanel topPanel;         //pannello in alto
    JPanel leftPanel;        //pannello a sinistra
    JPanel rightPanel;       //pannello a destra
    JPanel bottomPanel;      //pannello in basso
    JLabel targetListLabel;
    JLabel messageTextLabel;
    JList elencoMessaggiInviati;
    JTextPane corpoMessaggio;
    JScrollPane scrollPane;
    JButton eliminaMessaggioBtn;
    ArrayList<MessaggioMittente> messaggiMittenteArrLst; //elenco destintari dei messaggi

    public OutboxPanel(ArrayList<MessaggioMittente> messaggiMittenteArrLst) {
        topPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();
        this.messaggiMittenteArrLst = messaggiMittenteArrLst;
        this.init();
    }

    //inizializza il pannello
    private void init() {
        System.out.println("Inizzializzazione Pannello Outbox...");   //comunicazione di controllo per i log

        //INIT DEI LAYOUT
        this.setLayout(new BorderLayout());
        topPanel.setLayout(new FlowLayout());
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());
        topPanel.setBorder(new EmptyBorder(0, 0, 20, 0));   //padding per separare i controlli

        //INIT DEI CONTROLLI
        targetListLabel = new JLabel("Messaggi inviati:");
        messageTextLabel = new JLabel("Contenuto del messaggio:");
        eliminaMessaggioBtn = new JButton("Elimina messaggio");
        elencoMessaggiInviati = new JList(new Vector<MessaggioMittente>(messaggiMittenteArrLst));
        elencoMessaggiInviati.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof MessaggioDestinatario) {
                    MessaggioDestinatario temp = (MessaggioDestinatario) value;
                    ((JLabel) renderer).setText(temp.getTitolo());
                }
                return renderer;
            }
        });
        elencoMessaggiInviati.setSelectedIndex(0);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(elencoMessaggiInviati);
        scrollPane.setPreferredSize(new Dimension(165, 250));
        corpoMessaggio = new JTextPane();
        corpoMessaggio.setPreferredSize(new Dimension(600, 250));
        corpoMessaggio.setContentType("text/html"); //consente formattazione html
        corpoMessaggio.setEditable(false); //rende in sola lettura il campo con il testo del messaggio
 Border b = BorderFactory.createLineBorder(Color.GRAY);  //crea un bordo al controllo
        corpoMessaggio.setBorder(BorderFactory.createCompoundBorder(b,BorderFactory.createEmptyBorder(0, 10, 0, 10))); //assegna un margine al controllo
        MessaggioMittente index0 = (MessaggioMittente) elencoMessaggiInviati.getSelectedValue();
        if (index0 != null) {
            corpoMessaggio.setText(new HtmlVisitor().visit(index0));
        }

        //AGGIUNTA DEI CONTROLLI AI PANNELLI    
        leftPanel.add(messageTextLabel, BorderLayout.NORTH);
        leftPanel.add(corpoMessaggio, BorderLayout.CENTER);
        rightPanel.add(targetListLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(eliminaMessaggioBtn);

        //AGGIUNTA DEI PANNELLI
        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);

        //REGISTRAZIONE VISTA NEL COTROLLER
        registerController();
    }

    //elimina l'elemento selezionato nella lista dei messaggi inviati e aggiorna la vista
    public boolean deleteSelectedIndex() {
        int toDelete = elencoMessaggiInviati.getSelectedIndex();
        if (toDelete >= 0) {
            rightPanel.removeAll();
            topPanel.removeAll();
            leftPanel.removeAll();
            bottomPanel.removeAll();
            messaggiMittenteArrLst.remove(toDelete);
            init();
            rightPanel.revalidate();
            topPanel.revalidate();
            leftPanel.revalidate();
            bottomPanel.revalidate();
            return true;
        }
        return false;
    }

    @Override
    public void registerController() {
        GUIControllerUC1 gc = GUIControllerUC1.getInstance();
        gc.addView(this);
    }

    //METODI GETTER
    public JList getElencoMessaggiInviati() {
        return elencoMessaggiInviati;
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

    public ArrayList<MessaggioMittente> getMessaggiMittenteArrLst() {
        return messaggiMittenteArrLst;
    }

    public void setMessaggiMittenteArrLst(ArrayList<MessaggioMittente> bozzeArayLst) {
        messaggiMittenteArrLst = bozzeArayLst;
    }

}
