//Pannello che consente la gestione delle soluzioni salvate
package cryptohelper.GUI.UC2;

import cryptohelper.com.GUIControllerUC2;
import cryptohelper.interfaces.View;
import cryptohelper.data.HtmlVisitor;
import cryptohelper.data.Soluzione;
import cryptohelper.interfaces.VisitableGuiUC2;
import cryptohelper.interfaces.VisitorGuiUC2;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class GestisciSoluzioniPanel extends JPanel implements View, VisitableGuiUC2 {

    JPanel topPanel;         //pannello in alto
    JPanel leftPanel;        //pannello a sinistra
    JPanel rightPanel;       //pannello a destra
    JPanel bottomPanel;      //pannello in basso
    JLabel targetListLabel;
    JLabel messageTextLabel;
    JList elencoSoluzioni;
    JTextPane infoSoluzione;
    JScrollPane scrollPane;
    JButton eliminaSoluzioneBtn;
    JButton okBtn;
    ArrayList<Soluzione> elencoSoluzioniArrLst; //elenco soluzioni

    public GestisciSoluzioniPanel(ArrayList<Soluzione> elencoSoluzioni) {
        topPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();
        this.elencoSoluzioniArrLst = elencoSoluzioni;
        this.init();
    }

    //inizializza il pannello
    private void init() {
        System.out.println("Inizzializzazione gestisci soluzioni...");   //comunicazione di controllo per i log

        //INIT DEI LAYOUT
        this.setLayout(new BorderLayout());
        topPanel.setLayout(new FlowLayout());
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());
        topPanel.setBorder(new EmptyBorder(0, 0, 20, 0));       //padding per separare i controlli
        leftPanel.setBorder(new EmptyBorder(10, 150, 20, 0));   //padding per separare i controlli
        rightPanel.setBorder(new EmptyBorder(10, 0, 20, 150));  //padding per separare i controlli

        //INIT DEI CONTROLLI
        targetListLabel = new JLabel("Sessioni disponibili:");
        messageTextLabel = new JLabel("Informazioni soluzione:");
        eliminaSoluzioneBtn = new JButton("Elimina soluzione");
        okBtn = new JButton("Avanti");
        elencoSoluzioni = new JList(new Vector<Soluzione>(elencoSoluzioniArrLst));
        elencoSoluzioni.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof Soluzione) {
                    Soluzione temp = (Soluzione) value;
                    ((JLabel) renderer).setText(temp.getNome());
                }
                return renderer;
            }
        });
        elencoSoluzioni.setSelectedIndex(0);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(elencoSoluzioni);
        scrollPane.setPreferredSize(new Dimension(200, 250));
        infoSoluzione = new JTextPane();
        infoSoluzione.setPreferredSize(new Dimension(600, 250));
        infoSoluzione.setContentType("text/html"); //consente formattazione html
        infoSoluzione.setEditable(false); //rende in sola lettura il campo con il testo del messaggio
        Border b = BorderFactory.createLineBorder(Color.GRAY);  //crea un bordo al controllo
        infoSoluzione.setBorder(BorderFactory.createCompoundBorder(b, BorderFactory.createEmptyBorder(0, 10, 0, 10))); //assegna un margine al controllo
        Soluzione index0 = (Soluzione) elencoSoluzioni.getSelectedValue();
        if (index0 != null) {
            infoSoluzione.setText(new HtmlVisitor().visit(index0));
        }

        //AGGIUNTA DEI CONTROLLI AI PANNELLI        
        leftPanel.add(messageTextLabel, BorderLayout.NORTH);
        leftPanel.add(infoSoluzione, BorderLayout.CENTER);
        rightPanel.add(targetListLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(eliminaSoluzioneBtn);
        bottomPanel.add(okBtn);

        //AGGIUNTA DEI PANNELLI
        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);

        //REGISTRAZIONE VISTA NEL COTROLLER
        registerController();
    }

    //elimina l'elemento selezionato nella lista delle sessioni disponibili e aggiorna la vista
    public boolean deleteSelectedIndex() {
        int toDelete = elencoSoluzioni.getSelectedIndex();
        if (toDelete >= 0) {
            rightPanel.removeAll();
            topPanel.removeAll();
            leftPanel.removeAll();
            bottomPanel.removeAll();
            elencoSoluzioniArrLst.remove(toDelete);
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
        this.accept(GUIControllerUC2.getInstance());
    }

    @Override
    public void accept(VisitorGuiUC2 visitor) {
        visitor.visit(this);
    }

    //METODI GETTER
    public JList getElencoSoluzioni() {
        return elencoSoluzioni;
    }

    public JTextPane getInfoSoluzione() {
        return infoSoluzione;
    }

    public JButton getOkBtn() {
        return okBtn;
    }

    public JButton getEliminaSoluzioneBtn() {
        return eliminaSoluzioneBtn;
    }

    public Soluzione getSelectedSolution() {
        return elencoSoluzioniArrLst.get(elencoSoluzioni.getSelectedIndex());
    }
}
