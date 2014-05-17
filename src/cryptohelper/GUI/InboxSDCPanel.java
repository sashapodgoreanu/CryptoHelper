//Finestra principale della GUI
package cryptohelper.GUI;

import cryptohelper.abstractC.View;
import cryptohelper.com.GUIController;
import cryptohelper.abstractC.MessaggioDestinatario;
import cryptohelper.abstractC.Visitor;
import cryptohelper.data.HtmlVisitor;
import cryptohelper.data.Proposta;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import java.awt.*;

public class InboxSDCPanel extends JPanel implements View {

    JPanel topPanel;         //pannello in alto
    JPanel leftPanel;        //pannello a sinistra
    JPanel rightPanel;       //pannello a destra
    JPanel bottomPanel;      //pannello in basso
    JList elencoProposteRicevute;
    JButton accettaBtn;
    JButton rifiutaBtn;
    JLabel infoSdcLabel;
    JScrollPane scrollPane;
    ArrayList<Proposta> proposteArrLst; //elenco mittenti

    public InboxSDCPanel(ArrayList<Proposta> proposteArrLst) {
        this.proposteArrLst = proposteArrLst;
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
        JLabel targetListLabel = new JLabel("Proposte ricevuti:");
        accettaBtn = new JButton("Accetta");
        rifiutaBtn= new JButton("Rifiuta");
        
        elencoProposteRicevute = new JList(new Vector<Proposta>(proposteArrLst));
        elencoProposteRicevute.setCellRenderer(new DefaultListCellRenderer() {
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
        elencoProposteRicevute.setSelectedIndex(0);
        Proposta index0 = (Proposta) elencoProposteRicevute.getSelectedValue();
        Visitor visitor= new HtmlVisitor();
        infoSdcLabel = new JLabel(visitor.visit(index0));
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(elencoProposteRicevute);
        
        //AGGIUNTA DEI CONTROLLI AI PANNELLI        
        leftPanel.add(infoSdcLabel, BorderLayout.CENTER);
        rightPanel.add(targetListLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(accettaBtn);
        bottomPanel.add(rifiutaBtn);

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

    public JList getElencoProposteRicevute() {
        return elencoProposteRicevute;
    }

    public void setElencoProposteRicevute(JList elencoProposteRicevute) {
        this.elencoProposteRicevute = elencoProposteRicevute;
    }

    public JLabel getInfoSdcLabel() {
        return infoSdcLabel;
    }

    public void setInfoSdcLabel(JLabel infoSdcLabel) {
        this.infoSdcLabel = infoSdcLabel;
    }
    
    
}
