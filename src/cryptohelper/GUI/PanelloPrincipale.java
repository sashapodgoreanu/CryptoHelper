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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
public class PanelloPrincipale extends JFrame implements View {

    JPanel panel;
    JPanel nuovoMessaggioPnl;
    JPanel inboxPnl;
    JPanel apriBozzaPnl;

    JButton nuovoMessaggioBtn;
    JButton inboxBtn;
    JButton apriBozzaBtn;

    FlowLayout sl;
    JTextField tittoloMessaggioField;
    JTextField destinatarioMessaggioField;

    Studente studente;
    GUIController gc = GUIController.getInstance();

    public PanelloPrincipale() {
        panel = new JPanel();
        nuovoMessaggioPnl = new JPanel();
        inboxPnl = new JPanel();
        apriBozzaPnl = new JPanel();
        Init();
        gc.addView(this);
    }

    public void Init() {
        System.out.println("Init PanelloPrinciplae");
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
    }

    public void initNuovoMessaggio() {
        this.remove(inboxPnl);
        this.remove(apriBozzaPnl);
        this.setTitle(this.getTitle() + " - Nuovo Messaggio");
        tittoloMessaggioField = new JTextField("Titolo");
        destinatarioMessaggioField = new JTextField("Destinatario");
        nuovoMessaggioPnl.add(destinatarioMessaggioField);
        nuovoMessaggioPnl.add(tittoloMessaggioField);
        this.add(nuovoMessaggioPnl);
    }

    @Override
    public void registerController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
