/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.GUI;

import cryptohelper.com.GUIController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
public class CreaSDCPanel extends JPanel implements View {

    private JRadioButton parolaChiaveRBtn;
    private JRadioButton cesareRBtn;
    private JRadioButton pseudocasualeRBtn;
    
    private JButton salvaSdcBtn;
    private JButton provasdcBtn;
    private JTextField nomeCifraturaField;

    private JPanel topPanel;//contiene i JRadioButtons
    private JPanel centerPanel; // contiene la logica applicativa per creare un sistema di cifratura
    private JPanel bottomPanel; //scrollpane per table
    
    private JTable table; // tabela per mappatura per cifrario parolachiave
    private JScrollPane scrollPane; //scrollpane per table


    public CreaSDCPanel() {

        initCreateSDC();
        registerController();

    }

    public void initCreateSDC() {
        System.out.println("inside of initCreateSDC");
        topPanel = new JPanel(new FlowLayout());
        bottomPanel = new JPanel(new FlowLayout());
        centerPanel = new JPanel(new BorderLayout());
        parolaChiaveRBtn = new JRadioButton("Parola chiave");
        cesareRBtn = new JRadioButton("Cesare");
        pseudocasualeRBtn = new JRadioButton("Pseudocasuale");
        scrollPane = new JScrollPane();

        
        
        
        
        this.setLayout(new BorderLayout());

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
        topPanel.add(radioPanel, BorderLayout.NORTH);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.revalidate();
    }

    public void initParolaChiave() {
        
        
        remake();
        scrollPane = new JScrollPane();
        String[] columnNames = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X"};
        Object[][] data = {
            new String[23]
        };
        salvaSdcBtn = new JButton("Salva");
        provasdcBtn = new JButton("Prova la cifratura");
        nomeCifraturaField = new JTextField(20);
        table = new JTable(data, columnNames);
        scrollPane.setViewportView(table);
        scrollPane.setPreferredSize(new Dimension(500, 39));
        JTextArea corpoMessaggio = new JTextArea();
        corpoMessaggio.setSize(new Dimension(100, 150));
        corpoMessaggio.setLineWrap(true);
        corpoMessaggio.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea
        
        
        centerPanel.add(scrollPane, BorderLayout.NORTH);
        centerPanel.add(corpoMessaggio, BorderLayout.CENTER);
        centerPanel.revalidate();
        
        bottomPanel.add(provasdcBtn);
        bottomPanel.add(new JLabel("Nome cifratura"));
        bottomPanel.add(nomeCifraturaField);
        bottomPanel.add(salvaSdcBtn);
        bottomPanel.revalidate();
        
        
    }
    
    private void remake(){
        centerPanel.removeAll();
        centerPanel.revalidate();
        bottomPanel.removeAll();
        bottomPanel.revalidate();

        
    }

    @Override
    public void registerController() {
        GUIController gc = GUIController.getInstance();
        gc.addView(this);
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
