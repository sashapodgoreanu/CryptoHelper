/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.GUI;

import cryptohelper.com.GUIController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
    // private JTable table;

    private JRadioButton parolaChiaveRBtn;
    private JRadioButton cesareRBtn;
    private JRadioButton pseudocasualeRBtn;
    
    private JTable table;
    



    public SdcPanel() {
        init();
        registerController();
    }

    public void init() {
        //creazione dei componenti
        proponiSDCBtn = new JButton("Proponi Sistema Di Cifratura");
        inboxProposteSDCBtn = new JButton("Inbox");
        proposteAccetateBtn = new JButton("Proposte accettate");
        creaSDCBtn = new JButton("Crea Sisteme di Cifratura");
        topPanel = new JPanel();
        centerSDCPanel = new JPanel(new BorderLayout());
        parolaChiaveRBtn = new JRadioButton("Parola chiave");
        cesareRBtn = new JRadioButton("Cesare");
        pseudocasualeRBtn = new JRadioButton("Pseudocasuale");
        /*String[] columnNames = {"First Name",
         "Last Name",
         "Sport",
         "# of Years",
         "Vegetarian"};

         Object[][] data = {
         {"Kathy", "Smith",
         "Snowboarding", new Integer(5), new Boolean(false)}
         };
        

         table = new JTable(data, columnNames);
         scrollPaneTable = new JScrollPane(table);

         */

        this.setLayout(new BorderLayout());

        //aggiungi i bottoni
        topPanel.add(creaSDCBtn);
        topPanel.add(proponiSDCBtn);
        topPanel.add(inboxProposteSDCBtn);
        topPanel.add(proposteAccetateBtn);

        //aggiungi 2 panelli
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerSDCPanel, BorderLayout.CENTER);
    }

    public void initCreateSDC() {
        System.out.println("inside of initCreateSDC");
        remake();
        
        //ragrupa i bottoni   - when i select a radiobutton will deselect the precedent selected. 
        ButtonGroup group = new ButtonGroup();
        group.add(parolaChiaveRBtn);
        group.add(cesareRBtn);
        group.add(pseudocasualeRBtn);
        //meto i bottoni in un panelo
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        JLabel selectSDClabel = new JLabel("Selezionare un metodo di cifratura");
        radioPanel.add(selectSDClabel);
        radioPanel.add(parolaChiaveRBtn);
        radioPanel.add(cesareRBtn);
        radioPanel.add(pseudocasualeRBtn);
        centerSDCPanel.add(radioPanel, BorderLayout.NORTH);

        this.revalidate();
    }

    public void initParolaChiave() {
        remake();
        JScrollPane scrollPane = new JScrollPane();
           String[] columnNames = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X"};
        Object[][] data = {
            new String[23]
        };
        table = new JTable(data, columnNames);
        scrollPane.setViewportView(table);
        scrollPane.setPreferredSize(new Dimension(500, 39));
        //Create the scroll pane and add the table to it.
        centerSDCPanel.add(scrollPane, BorderLayout.SOUTH);
        this.revalidate();
    }

    @Override
    public void removeAll() {
        super.removeAll();
        centerSDCPanel.removeAll();
        topPanel.removeAll();
    }

    public void remake() {
        centerSDCPanel.removeAll();
        this.revalidate();
    }

    @Override
    public void registerController() {
        GUIController gc = GUIController.getInstance();
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

    public JRadioButton getParolaChiaveRBtn() {
        return parolaChiaveRBtn;
    }

    public void setParolaChiaveRBtn(JRadioButton parolaChiaveRBtn) {
        this.parolaChiaveRBtn = parolaChiaveRBtn;
    }

    public JRadioButton getCesareRBtn() {
        return cesareRBtn;
    }

    public void setCesareRBtn(JRadioButton cesareRBtn) {
        this.cesareRBtn = cesareRBtn;
    }

    public JRadioButton getPseudocasualeRBtn() {
        return pseudocasualeRBtn;
    }

    public void setPseudocasualeRBtn(JRadioButton pseudocasualeRBtn) {
        this.pseudocasualeRBtn = pseudocasualeRBtn;
    }

}
