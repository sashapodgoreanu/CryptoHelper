package cryptohelper.GUI.UC1;

import cryptohelper.interfaces.View;
import cryptohelper.com.GUIControllerUC1;
import cryptohelper.data.HtmlVisitor;
import cryptohelper.data.SistemaCifratura;
import cryptohelper.interfaces.VisitableGuiUC1;
import cryptohelper.interfaces.VisitorGuiUC1;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import static java.lang.System.gc;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Luigi
 */
public class GestisciSDCPanel extends JPanel implements View, VisitableGuiUC1 {

    JPanel topPanel;         //pannello in alto
    JPanel leftPanel;        //pannello a sinistra
    JPanel rightPanel;       //pannello a destra
    JPanel bottomPanel;      //pannello in basso
    JList elencoSistemiDiCifratura;
    JButton eliminaBtn;
    JLabel infoSdcLabel;
    JScrollPane scrollPane;
    ArrayList<SistemaCifratura> sistemiCifraturaArrLst; //elenco mittenti

    public GestisciSDCPanel(ArrayList<SistemaCifratura> SistemiCifraturaArrLst) {
        this.sistemiCifraturaArrLst = SistemiCifraturaArrLst;
        this.init();
    }

    //inizializza il pannello
    private void init() {
        System.out.println("Inizzializzazione Pannello Gestione SDC...");   //comunicazione di controllo per i log

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
        JLabel targetListLabel = new JLabel("Sistemi di cifratura creati:");
        eliminaBtn = new JButton("Elimina Sistema di cifratura");
        infoSdcLabel = new JLabel("");
        elencoSistemiDiCifratura = new JList(new Vector<SistemaCifratura>(sistemiCifraturaArrLst));
        elencoSistemiDiCifratura.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof SistemaCifratura) {
                    SistemaCifratura temp = (SistemaCifratura) value;
                    ((JLabel) renderer).setText("<html><font color=green>" + temp.getNome() + "</font></html>");
                }
                return renderer;
            }
        });
        elencoSistemiDiCifratura.setSelectedIndex(0);
        elencoSistemiDiCifratura.setPreferredSize(new Dimension(180, 250));
        SistemaCifratura index0 = (SistemaCifratura) elencoSistemiDiCifratura.getSelectedValue();
        if (index0 != null) {
            infoSdcLabel.setText(new HtmlVisitor().visit(index0));
        }
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(elencoSistemiDiCifratura);

        //AGGIUNTA DEI CONTROLLI AI PANNELLI        
        leftPanel.add(infoSdcLabel, BorderLayout.CENTER);
        rightPanel.add(targetListLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(eliminaBtn);

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
        this.accept(GUIControllerUC1.getInstance());
    }

    @Override
    public void accept(VisitorGuiUC1 visitor) {
        visitor.visit(this);
    }

    public JButton getEliminaBtn() {
        return eliminaBtn;
    }

    public void setEliminaBtn(JButton eliminaBtn) {
        this.eliminaBtn = eliminaBtn;
    }

    public JList getElencoSistemiDiCifratura() {
        return elencoSistemiDiCifratura;
    }

    public void setElencoSistemiDiCifratura(JList elencoSistemiDiCifratura) {
        this.elencoSistemiDiCifratura = elencoSistemiDiCifratura;
    }
}
