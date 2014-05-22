//Pannello che consente il caricamento di una sessione salvata in precedenza
package cryptohelper.GUI.UC2;

import cryptohelper.interfaces.View;
import cryptohelper.com.GUIControllerUC2;
import cryptohelper.data.HtmlVisitor;
import cryptohelper.data.Messaggio;
import cryptohelper.data.SessioneLavoro;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class CaricaSessionePanel extends JPanel implements View {

    JPanel topPanel;         //pannello in alto
    JPanel leftPanel;        //pannello a sinistra
    JPanel rightPanel;       //pannello a destra
    JPanel bottomPanel;      //pannello in basso
    JLabel targetListLabel;
    JLabel messageTextLabel;
    JList elencoSessioni;
    JTextPane infoSessione;
    JScrollPane scrollPane;
    JButton eliminaSessioneBtn;
    JButton okBtn;
    ArrayList<SessioneLavoro> elencoSessioniArrLst; //elenco destintari dei messaggi

    //TO DO creare classe sessione e passare array come parametro qui
    public CaricaSessionePanel(/*ArrayList<Sessione> elencoSessioni*/) {
        topPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();
        // this.elencoSessioniArrLst = elencoSessioni;
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
        targetListLabel = new JLabel("Sessioni disponibili:");
        messageTextLabel = new JLabel("Informazioni sessione:");
        eliminaSessioneBtn = new JButton("Elimina sessione");
        okBtn = new JButton("Avanti");
        elencoSessioni = new JList(new Vector<SessioneLavoro>(elencoSessioniArrLst));
        elencoSessioni.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof SessioneLavoro) {
                    SessioneLavoro temp = (SessioneLavoro) value;
                    ((JLabel) renderer).setText(temp.getNomeSessione());
                }
                return renderer;
            }
        });
        elencoSessioni.setSelectedIndex(0);

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(elencoSessioni);
        scrollPane.setPreferredSize(new Dimension(165, 250));
        infoSessione = new JTextPane();
        infoSessione.setPreferredSize(new Dimension(600, 250));
        infoSessione.setContentType("text/html"); //consente formattazione html
        infoSessione.setEditable(false); //rende in sola lettura il campo con il testo del messaggio
        Border b = BorderFactory.createLineBorder(Color.GRAY);  //crea un bordo al controllo
        infoSessione.setBorder(BorderFactory.createCompoundBorder(b, BorderFactory.createEmptyBorder(0, 10, 0, 10))); //assegna un margine al controllo
        SessioneLavoro index0 = (SessioneLavoro) elencoSessioni.getSelectedValue();
        if (index0 != null) {
            infoSessione.setText(new HtmlVisitor().visit(index0));
        }

        //AGGIUNTA DEI CONTROLLI AI PANNELLI        
        leftPanel.add(messageTextLabel, BorderLayout.NORTH);
        leftPanel.add(infoSessione, BorderLayout.CENTER);
        rightPanel.add(targetListLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(eliminaSessioneBtn);
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
    public JList getElencoSessioni() {
        return elencoSessioni;
    }

    public JTextPane getInfoSessione() {
        return infoSessione;
    }

    public JButton getokBtn() {
        return okBtn;
    }

}
