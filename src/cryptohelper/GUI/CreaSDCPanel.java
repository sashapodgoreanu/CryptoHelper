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
    private JTextArea corpoMessaggioProva;
    private JTextArea corpoMessaggioResult;

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
        salvaSdcBtn = new JButton("Salva cifrario parola chiave");
        provasdcBtn = new JButton("Prova la cifratura");

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
        String[] columnNames = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Object[][] data = {
            {"Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M"}
        };
        //salvaSdcBtn = new JButton("Salva cifrario parola chiave");
        
        nomeCifraturaField = new JTextField(20);
        table = new JTable(data, columnNames);
        table.setCellSelectionEnabled(true);
        
        scrollPane.setViewportView(table);
        scrollPane.setPreferredSize(new Dimension(500, 39));
        corpoMessaggioProva = new JTextArea();
        corpoMessaggioProva.setSize(new Dimension(60, 60));
        corpoMessaggioProva.setLineWrap(true);
        corpoMessaggioProva.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea
        corpoMessaggioResult = new JTextArea();
        corpoMessaggioResult.setSize(new Dimension(60, 60));
        corpoMessaggioResult.setLineWrap(true);
        corpoMessaggioResult.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea

        centerPanel.add(scrollPane, BorderLayout.NORTH);
        centerPanel.add(corpoMessaggioProva, BorderLayout.CENTER);
        centerPanel.add(corpoMessaggioResult, BorderLayout.SOUTH);
        centerPanel.revalidate();

        bottomPanel.add(provasdcBtn);
        bottomPanel.add(new JLabel("Nome cifratura"));
        bottomPanel.add(nomeCifraturaField);
        bottomPanel.add(salvaSdcBtn);
        bottomPanel.revalidate();

    }

    private void remake() {
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

    public JButton getSalvaSdcBtn() {
        return salvaSdcBtn;
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

    public JTable getTable() {
        return table;
    }

    public String getData(int row_index, int col_index) {
        return (String) table.getModel().getValueAt(row_index, col_index);
    }

    public JButton getProvasdcBtn() {
        return provasdcBtn;
    }

    public void setProvasdcBtn(JButton provasdcBtn) {
        this.provasdcBtn = provasdcBtn;
    }

    public JTextArea getCorpoMessaggioResult() {
        return corpoMessaggioResult;
    }

    public void setCorpoMessaggioResult(JTextArea corpoMessaggioResult) {
        this.corpoMessaggioResult = corpoMessaggioResult;
    }

    public JTextArea getCorpoMessaggioProva() {
        return corpoMessaggioProva;
    }

    public void setCorpoMessaggioProva(JTextArea corpoMessaggioProva) {
        this.corpoMessaggioProva = corpoMessaggioProva;
    }
    
    
    
    
    
    
    

}
