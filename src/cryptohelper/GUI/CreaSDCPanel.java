/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.GUI;

import cryptohelper.interfaces.View;
import cryptohelper.com.GUIControllerUC1;
import cryptohelper.interfaces.Cifrario;
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
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
public class CreaSDCPanel extends JPanel implements View {

    private JRadioButton monoalfabeticoBtn;
    private JRadioButton parolaChiaveRBtn;
    private JRadioButton cesareRBtn;
    private JRadioButton pseudocasualeRBtn;

    private JButton salvaSdcBtn;
    private JButton provasdcBtn;
    private JTextField nomeCifraturaField;
    private JTextField chiave;
    private JTextArea corpoMessaggioProva;
    private JTextArea corpoMessaggioResult;

    private JPanel topPanel;//contiene i JRadioButtons
    private JPanel leftPanel; // contiene la logica applicativa per creare un sistema di cifratura
    private JPanel rightPanel; // contiene la logica applicativa per creare un sistema di cifratura
    private JPanel bottomPanel; //scrollpane per table

    private JTable table; // tabela per mappatura per cifrario parolachiave
    private JScrollPane scrollPane; //scrollpane per table
    
    private  String metodo;//Metodo

    public CreaSDCPanel() {

        init();
        registerController();

    }

    public void init() {
        System.out.println("inside of init");
        topPanel = new JPanel(new GridLayout(1, 4));
        leftPanel = new JPanel(new BorderLayout());
        rightPanel = new JPanel(new BorderLayout());
        bottomPanel = new JPanel(new FlowLayout());
   
        monoalfabeticoBtn = new JRadioButton("Monoalfabetico");
        parolaChiaveRBtn = new JRadioButton("Parola chiave");
        cesareRBtn = new JRadioButton("Cesare");
        pseudocasualeRBtn = new JRadioButton("Pseudocasuale");
        scrollPane = new JScrollPane();
        salvaSdcBtn = new JButton("Salva cifrario");
        provasdcBtn = new JButton("Prova la cifratura");
        

        //ragrupa i bottoni   - when i select a radiobutton will deselect the precedent selected. 
        ButtonGroup group = new ButtonGroup();
        group.add(monoalfabeticoBtn);
        group.add(parolaChiaveRBtn);
        group.add(cesareRBtn);
        group.add(pseudocasualeRBtn);
        //meto i bottoni in un panelo
        //JPanel radioPanel = new JPanel(new GridLayout(1, 4));
        JLabel selectSDClabel = new JLabel("Metodo di cifratura:");
        topPanel.add(selectSDClabel);
        topPanel.add(monoalfabeticoBtn);
        topPanel.add(parolaChiaveRBtn);
        topPanel.add(cesareRBtn);
        topPanel.add(pseudocasualeRBtn);
        //config di thi panel
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));   //padding per separare i controlli dal bordo della finestra
        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.revalidate();
    }

    //inizializa il panello per creare cifrario parola chiave
    public void initMonoalfabetico() {
        remake();
        metodo = Cifrario.MONOALFABETICO;
       
        //mappatura
        String[] columnNames = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Object[][] data = {
            {"Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M"}
        };
        nomeCifraturaField = new JTextField(20);
        table = new JTable(data, columnNames);
        table.setCellSelectionEnabled(true);
         //Setup per table 
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        scrollPane.setPreferredSize(new Dimension(550, 39));
        
        //text area per provare sdc
        corpoMessaggioProva = new JTextArea();
        corpoMessaggioProva.setSize(new Dimension(60, 60));
        corpoMessaggioProva.setLineWrap(true);
        corpoMessaggioProva.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea
        //resultato di cifratura
        corpoMessaggioResult = new JTextArea();
        corpoMessaggioResult.setSize(new Dimension(60, 60));
        corpoMessaggioResult.setLineWrap(true);
        corpoMessaggioResult.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea

        
        leftPanel.add(new JLabel("Mapatura:"),BorderLayout.NORTH);
        rightPanel.add(scrollPane,BorderLayout.NORTH);
        leftPanel.add(new JLabel("Prova:"),BorderLayout.CENTER);
        rightPanel.add(corpoMessaggioProva,BorderLayout.CENTER);
        leftPanel.add(new JLabel("Resultato:"),BorderLayout.SOUTH);
        rightPanel.add(corpoMessaggioResult,BorderLayout.SOUTH);
        leftPanel.revalidate();
        rightPanel.revalidate();

        
        bottomPanel.add(provasdcBtn);
        bottomPanel.add(new JLabel("Nome cifratura"));
        bottomPanel.add(nomeCifraturaField);
        bottomPanel.add(salvaSdcBtn);
        bottomPanel.revalidate();

    }
    
    public void initCifrario(){
        remake();
        //50 - la dimensione del textfield - qui si inserisce la chiave
        chiave = new JTextField(50);
        //text area per provare sdc
        corpoMessaggioProva = new JTextArea();
        corpoMessaggioProva.setPreferredSize(new Dimension(60, 60));
        corpoMessaggioProva.setLineWrap(true);
        corpoMessaggioProva.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea
        
         //resultato di cifratura
        corpoMessaggioResult = new JTextArea();
        corpoMessaggioResult.setSize(new Dimension(60, 60));
        corpoMessaggioResult.setLineWrap(true);
        corpoMessaggioResult.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea

        //la parte della creatione della mapatura
        leftPanel.add(new JLabel("Chiave: "),BorderLayout.NORTH);
        rightPanel.add(chiave,BorderLayout.NORTH);
        leftPanel.add(new JLabel("Prova: "),BorderLayout.CENTER);
        rightPanel.add(corpoMessaggioProva,BorderLayout.CENTER);
        leftPanel.add(new JLabel("Resultato: "),BorderLayout.SOUTH);
        rightPanel.add(corpoMessaggioResult,BorderLayout.SOUTH);
        leftPanel.revalidate();
        rightPanel.revalidate();
        
        nomeCifraturaField = new JTextField(20);
        bottomPanel.add(provasdcBtn);
        bottomPanel.add(new JLabel("Nome cifratura"));
        bottomPanel.add(nomeCifraturaField);
        bottomPanel.add(salvaSdcBtn);
    }
    public void initCesare(){
        initCifrario();
        metodo = Cifrario.CESARE;
    }
    
    public void initParolaChiave() {
        initCesare();
        metodo = Cifrario.PAROLA_CHIAVE;
    }
    
    public void initPseudoCasuale() {
        initCesare();
        metodo = Cifrario.PSEUDOCASUALE;
    }

    private void remake() {
        leftPanel.removeAll();
        leftPanel.revalidate();
        rightPanel.removeAll();
        rightPanel.revalidate();
        bottomPanel.removeAll();
        bottomPanel.revalidate();

    }

    @Override
    public void registerController() {
        GUIControllerUC1 gc = GUIControllerUC1.getInstance();
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

    public JTextField getNomeCifraturaField() {
        return nomeCifraturaField;
    }

    public void setNomeCifraturaField(JTextField nomeCifraturaField) {
        this.nomeCifraturaField = nomeCifraturaField;
    }

    public String getMetodo() {
        return metodo;
    }

    public JTextField getChiave() {
        return chiave;
    }

    public JRadioButton getMappaturaBtn() {
        return monoalfabeticoBtn;
    }

    
    
    
    
    
    
      
    
    
    
    
    
    
    
    

}
