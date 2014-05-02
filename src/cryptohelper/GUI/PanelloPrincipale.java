/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.GUI;

import cryptohelper.com.GUIController;
import cryptohelper.data.Studente;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
public class PanelloPrincipale extends JFrame implements View {

    JPanel panel;
    JPanel nuovoMessaggioPnl;
    JPanel inboxPnl;
    JPanel apriBozzaPnl;
    JPanel panelcorpoMessaggio;
    JPanel panelTitolo;
    JPanel panelDest;

    JButton nuovoMessaggioBtn;
    JButton inboxBtn;
    JButton apriBozzaBtn;

    FlowLayout sl;
    JTextField tittoloMessaggioField;
    JTextField destinatarioMessaggioField;
    JTextArea corpoMessaggio;

    Studente studente;
    GUIController gc = GUIController.getInstance();

    public PanelloPrincipale() {
        panel = new JPanel();
        nuovoMessaggioPnl = new JPanel();
        inboxPnl = new JPanel();
        apriBozzaPnl = new JPanel();
        panelcorpoMessaggio = new JPanel();
        panelTitolo = new JPanel();
        panelDest = new JPanel();
        Init();
    }

    public void Init() {
        System.out.println("Init PanelloPrincipale");
        this.setPreferredSize(new Dimension(800, 600));
        this.setTitle("CryptoHelper - Menu");
        nuovoMessaggioBtn = new JButton("Nuovo Messaggio");
        inboxBtn = new JButton("Inbox");
        apriBozzaBtn = new JButton("Bozze");

        panel.add(nuovoMessaggioBtn);
        panel.add(inboxBtn);
        panel.add(apriBozzaBtn);

        this.add(panel, BorderLayout.WEST);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerController();
    }

    public void initNuovoMessaggio() {
        remakeCENTERPanels();
        this.setTitle("CryptoHelper - Menu - Nuovo Messaggio");
        tittoloMessaggioField = new JTextField(20);
        JLabel DestinatarioMessaggioLabel = new JLabel("Destinatario: ");
        panelDest.add(DestinatarioMessaggioLabel);
        panelDest.add(tittoloMessaggioField);
        destinatarioMessaggioField = new JTextField(20);
        JLabel tittoloMessaggioLabel = new JLabel("Titolo: ");
        panelTitolo.add(tittoloMessaggioLabel);
        panelTitolo.add(destinatarioMessaggioField);
        corpoMessaggio = new JTextArea();
        corpoMessaggio.setPreferredSize(new Dimension(300, 500));
        panelcorpoMessaggio.add(new JLabel("Messaggio"));
        panelcorpoMessaggio.add(corpoMessaggio);
        nuovoMessaggioPnl.setLayout(new FlowLayout());
        nuovoMessaggioPnl.add(panelDest);
        nuovoMessaggioPnl.add(panelTitolo);
        nuovoMessaggioPnl.add(panelcorpoMessaggio);

        this.add(nuovoMessaggioPnl, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
        System.out.println("initNuovoMessaggio");
    }

    private void remakeCENTERPanels() {

        System.out.println("remake: Nuovo Messaggio");
        //remake nuovo messaggio
        panelDest.removeAll();
        panelTitolo.removeAll();
        panelcorpoMessaggio.removeAll();
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void registerController() {
        gc = GUIController.getInstance();
        gc.addView(this);
    }

    public JButton getNuovoMessaggioBtn() {
        return nuovoMessaggioBtn;
    }

    public void setNuovoMessaggioBtn(JButton nuovoMessaggioBtn) {
        this.nuovoMessaggioBtn = nuovoMessaggioBtn;
    }

    public JButton getInboxBtn() {
        return inboxBtn;
    }

    public void setInboxBtn(JButton inboxBtn) {
        this.inboxBtn = inboxBtn;
    }

    public JButton getApriBozzaBtn() {
        return apriBozzaBtn;
    }

    public void setApriBozzaBtn(JButton apriBozzaBtn) {
        this.apriBozzaBtn = apriBozzaBtn;
    }

}
