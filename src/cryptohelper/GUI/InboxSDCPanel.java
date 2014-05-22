//Finestra principale della GUI
package cryptohelper.GUI;

import cryptohelper.interfaces.View;
import cryptohelper.com.GUIControllerUC1;
import cryptohelper.data.HtmlVisitor;
import cryptohelper.data.Proposta;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

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
        bottomPanel.setBorder(new EmptyBorder(20, 0, 0, 0));   //padding per separare i controlli
        leftPanel.setBorder(new EmptyBorder(0, 100, 0, 0));   //padding per separare i controlli
        rightPanel.setBorder(new EmptyBorder(0, 0, 0, 100));   //padding per separare i controlli

        //INIT DEI CONTROLLI
        JLabel targetListLabel = new JLabel("Tutte le proposte:");
        accettaBtn = new JButton("Accetta");
        rifiutaBtn = new JButton("Rifiuta");
        infoSdcLabel = new JLabel("");

        elencoProposteRicevute = new JList(new Vector<Proposta>(proposteArrLst));
        elencoProposteRicevute.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof Proposta) {
                    Proposta temp = (Proposta) value;
                    ((JLabel) renderer).setText("<html><font color=green>" + temp.getSdc().getNome() + "</font></html>");
                }
                return renderer;
            }
        });
        elencoProposteRicevute.setSelectedIndex(0);
        elencoProposteRicevute.setPreferredSize(new Dimension(180, 250));
        Proposta index0 = (Proposta) elencoProposteRicevute.getSelectedValue();
        if (index0 != null) {
            infoSdcLabel.setText(new HtmlVisitor().visit(index0));
        }
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
        GUIControllerUC1 gc = GUIControllerUC1.getInstance();
        gc.addView(this);
    }

    public boolean deleteSelectedIndex() {
        int toDelete = this.elencoProposteRicevute.getSelectedIndex();
        if (toDelete >= 0) {
            rightPanel.removeAll();
            topPanel.removeAll();
            leftPanel.removeAll();
            leftPanel.repaint();//clear all garbadge
            bottomPanel.removeAll();
            proposteArrLst.remove(toDelete);
            init();
            rightPanel.revalidate();
            topPanel.revalidate();
            leftPanel.revalidate();
            bottomPanel.revalidate();
            return true;
        } else {
            return false;
        }
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

    public JButton getAccettaBtn() {
        return accettaBtn;
    }

    public JButton getRifiutaBtn() {
        return rifiutaBtn;
    }

}
