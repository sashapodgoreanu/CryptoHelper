//Pannello che consente la scelta del messaggio da intercettare
package cryptohelper.GUI.UC2;

import cryptohelper.interfaces.View;
import cryptohelper.com.GUIControllerUC2;
import cryptohelper.data.HtmlVisitor;
import cryptohelper.data.Messaggio;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ScegliMsgPanel extends JPanel implements View {

    JPanel topPanel;         //pannello in alto
    JPanel leftPanel;        //pannello a sinistra
    JPanel rightPanel;       //pannello a destra
    JPanel bottomPanel;      //pannello in basso
    JLabel targetListLabel;
    JLabel messageTextLabel;
    JList elencoMessaggi;
    JTextPane corpoMessaggio;
    JScrollPane scrollPane;
    JButton okBtn;
    ArrayList<Messaggio> elencoMessaggiArrLst; //elenco destintari dei messaggi

    public ScegliMsgPanel(ArrayList<Messaggio> elencoMessaggiArrLst) {
        topPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();
        this.elencoMessaggiArrLst = elencoMessaggiArrLst;
        this.init();
    }

    //inizializza il pannello
    private void init() {
        System.out.println("Inizzializzazione scleta messaggio...");   //comunicazione di controllo per i log

        //INIT DEI LAYOUT
        this.setLayout(new BorderLayout());
        topPanel.setLayout(new FlowLayout());
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());
        topPanel.setBorder(new EmptyBorder(0, 0, 20, 0));   //padding per separare i controlli

        //INIT DEI CONTROLLI
        targetListLabel = new JLabel("Messaggi disponibili:");
        messageTextLabel = new JLabel("Contenuto del messaggio:");
        okBtn = new JButton("Avanti");
        elencoMessaggi = new JList(new Vector<Messaggio>(elencoMessaggiArrLst));
        elencoMessaggi.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof Messaggio) {
                    Messaggio temp = (Messaggio) value;
                    ((JLabel) renderer).setText(temp.getTitolo());
                }
                return renderer;
            }
        });
        elencoMessaggi.setSelectedIndex(0);
        elencoMessaggi.setPreferredSize(new Dimension(165, 250));
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(elencoMessaggi);
        corpoMessaggio = new JTextPane();
        corpoMessaggio.setPreferredSize(new Dimension(600, 250));
        corpoMessaggio.setContentType("text/html"); //consente formattazione html
        corpoMessaggio.setEditable(false); //rende in sola lettura il campo con il testo del messaggio
        Border b = BorderFactory.createLineBorder(Color.GRAY);  //crea un bordo al controllo
        corpoMessaggio.setBorder(BorderFactory.createCompoundBorder(b,BorderFactory.createEmptyBorder(0, 10, 0, 10))); //assegna un margine al controllo
        Messaggio index0 = (Messaggio) elencoMessaggi.getSelectedValue();
        if (index0 != null) {
            corpoMessaggio.setText(new HtmlVisitor().visit(index0));
        }

        //AGGIUNTA DEI CONTROLLI AI PANNELLI        
        leftPanel.add(messageTextLabel, BorderLayout.NORTH);
        leftPanel.add(corpoMessaggio, BorderLayout.CENTER);
        rightPanel.add(targetListLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(okBtn);

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
        GUIControllerUC2 gc = GUIControllerUC2.getInstance();
        gc.addView(this);
    }

    //METODI GETTER
    public JList getElencoMessaggi() {
        return elencoMessaggi;
    }

    public JTextPane getCorpoMessaggio() {
        return corpoMessaggio;
    }

    public JButton getokBtn() {
        return okBtn;
    }

}
