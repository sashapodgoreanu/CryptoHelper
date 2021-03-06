//Pannello per la proporre un sistema di cifratura d altri utenti
package cryptohelper.GUI.UC1;

import cryptohelper.interfaces.View;
import cryptohelper.com.GUIControllerUC1;
import cryptohelper.data.SistemaCifratura;
import cryptohelper.data.UserInfo;
import cryptohelper.interfaces.VisitableGuiUC1;
import cryptohelper.interfaces.VisitorGuiUC1;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import static java.lang.System.gc;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class ProponiSDCPanel extends JPanel implements View, VisitableGuiUC1 {

    JPanel topPanel;         //pannello in alto
    JPanel leftPanel;        //pannello a sinistra
    JPanel rightPanel;       //pannello a destra
    JPanel bottomPanel;      //pannello in basso
    JButton proponiSDCBtn;
    JScrollPane scrollPaneDest;
    JScrollPane scrollPaneSdc;
    JList elencoDestinatari;    //visualizza la lista dei destinatari (destinatariArrLst)
    JList elencoSDC;            //visualizza la lista dei sistemi di cifraura (sdcArrLst)
    ArrayList<UserInfo> destinatariArrLst;
    ArrayList<SistemaCifratura> sdcArrLst;

    public ProponiSDCPanel(ArrayList<UserInfo> destinatariArrLst, ArrayList<SistemaCifratura> sdc) {
        this.destinatariArrLst = destinatariArrLst;
        sdcArrLst = sdc;
        this.init();
    }

    //inizializza il pannello
    private void init() {
        System.out.println("Inizializzazione Pannello Nuovo Messaggio...");   //comunicazione di controllo per i log

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
        leftPanel.setBorder(new EmptyBorder(0, 200, 0, 0));   //padding per separare i controlli
        rightPanel.setBorder(new EmptyBorder(0, 0, 0, 200));   //padding per separare i controlli

        //INIT DEI CONTROLLI   
        proponiSDCBtn = new JButton("Invia Proposta");
        elencoDestinatari = new JList(new Vector<UserInfo>(destinatariArrLst));
        elencoDestinatari.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof UserInfo) {
                    UserInfo temp = (UserInfo) value;
                    //System.out.println("renderer " + temp.toString());
                    ((JLabel) renderer).setText(temp.getId() + " " + temp.getNome() + " " + temp.getCognome());
                }
                return renderer;
            }
        });
        elencoDestinatari.setSelectedIndex(0);
        scrollPaneDest = new JScrollPane();
        scrollPaneDest.setViewportView(elencoDestinatari);
        scrollPaneDest.setPreferredSize(new Dimension(165, 250));
        elencoSDC = new JList(new Vector<SistemaCifratura>(sdcArrLst));
        elencoSDC.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof SistemaCifratura) {
                    SistemaCifratura temp = (SistemaCifratura) value;
                    //System.out.println("renderer " + temp.toString());
                    ((JLabel) renderer).setText(temp.getId() + " " + temp.getNome() + " - " + temp.getMetodo());
                }
                return renderer;
            }
        });
        elencoSDC.setSelectedIndex(0);
        scrollPaneSdc = new JScrollPane();
        scrollPaneSdc.setViewportView(elencoSDC);
        scrollPaneSdc.setPreferredSize(new Dimension(120, 250));

        //AGGIUNTA DEI CONTROLLI AI PANNELLI
        leftPanel.add(new JLabel("Sistemi di cifratura disponibili:"), BorderLayout.NORTH);
        leftPanel.add(scrollPaneSdc, BorderLayout.CENTER);
        rightPanel.add(new JLabel("Destinatari:"), BorderLayout.NORTH);
        rightPanel.add(scrollPaneDest, BorderLayout.CENTER);
        bottomPanel.add(proponiSDCBtn);

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

    //METODI GETTER
    public JList getElencoSDC() {
        return elencoSDC;
    }

    public JList getElencoDestinatari() {
        return elencoDestinatari;
    }

    public JButton getProponiSDCBtn() {
        return proponiSDCBtn;
    }

    //METODI SETTER
    public void setElencoSDC(JList elencoSDC) {
        this.elencoSDC = elencoSDC;
    }

    public void setElencoDestinatari(JList elencoDestinatari) {
        this.elencoDestinatari = elencoDestinatari;
    }

    public void setProponiSDCBtn(JButton proponiSDCBtn) {
        this.proponiSDCBtn = proponiSDCBtn;
    }
}
