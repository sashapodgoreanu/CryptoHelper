/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.GUI;

import cryptohelper.com.GUIController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
public class SdcPanel extends JPanel implements View {

    private JButton proponiSDCBtn;
    private JButton inboxProposteSDCBtn;
    private JButton proposteAccetateBtn;
    private JButton creaSDCBtn;
    private JPanel topPanel;
    private JPanel centerSDCPanel;
    GUIController gc;

    public SdcPanel() {
        init();
        registerController();
    }

    private void init() {
        proponiSDCBtn = new JButton("Proponi Sistema Di Cifratura");
        inboxProposteSDCBtn = new JButton("Inbox");
        proposteAccetateBtn = new JButton("Proposte accettate");
        creaSDCBtn = new JButton("Crea Sisteme di Cifratura");
        topPanel = new JPanel();
        centerSDCPanel = new JPanel();
        this.setLayout(new BorderLayout());

        //aggiungi i bottoni
        topPanel.add(creaSDCBtn);
        topPanel.add(proponiSDCBtn);
        topPanel.add(inboxProposteSDCBtn);
        topPanel.add(proposteAccetateBtn);

        //aggiungi 2 panelli
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerSDCPanel, BorderLayout.SOUTH);
    }

    public void initCreateSDC() {
        System.out.println("inside of initCreateSDC");
        remake();
        JRadioButton parolaChiaveRBtn = new JRadioButton("Parola chiave");
        JRadioButton cesareRBtn = new JRadioButton("Cesare");
        JRadioButton pseudocasualeRBtn = new JRadioButton("Pseudocasuale");
        //ragrupa i bottoni   - when i select a radiobutton will deselect the precedent selected. 
        ButtonGroup group = new ButtonGroup();
        group.add(parolaChiaveRBtn);
        group.add(cesareRBtn);
        group.add(pseudocasualeRBtn);
        //meto i bottoni in un panelo
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(parolaChiaveRBtn);
        radioPanel.add(cesareRBtn);
        radioPanel.add(pseudocasualeRBtn);
        centerSDCPanel.add(radioPanel, BorderLayout.LINE_START);
        this.revalidate();
    }

    public void remake() {
        centerSDCPanel.removeAll();
        this.revalidate();
    }

    @Override
    public void registerController() {
        gc = GUIController.getInstance();
        gc.addView(this);
    }

    public JButton getProponiSDCBtn() {
        return proponiSDCBtn;
    }

    public void setProponiSDCBtn(JButton proponiSDCBtn) {
        this.proponiSDCBtn = proponiSDCBtn;
    }

    public JButton getInboxProposteSDCBtn() {
        return inboxProposteSDCBtn;
    }

    public void setInboxProposteSDCBtn(JButton inboxProposteSDCBtn) {
        this.inboxProposteSDCBtn = inboxProposteSDCBtn;
    }

    public JButton getProposteAccetateBtn() {
        return proposteAccetateBtn;
    }

    public void setProposteAccetateBtn(JButton proposteAccetateBtn) {
        this.proposteAccetateBtn = proposteAccetateBtn;
    }

    public JButton getCreaSDCBtn() {
        return creaSDCBtn;
    }

    public void setCreaSDCBtn(JButton creaSDCBtn) {
        this.creaSDCBtn = creaSDCBtn;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(JPanel topPanel) {
        this.topPanel = topPanel;
    }

    public JPanel getCenterSDCPanel() {
        return centerSDCPanel;
    }

    public void setCenterSDCPanel(JPanel centerSDCPanel) {
        this.centerSDCPanel = centerSDCPanel;
    }

}
