/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.GUI.UC1;

import cryptohelper.interfaces.View;
import cryptohelper.com.GUIControllerUC1;
import cryptohelper.interfaces.Cifrario;
import cryptohelper.interfaces.VisitableGUI;
import cryptohelper.interfaces.VisitorGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import static java.lang.System.gc;
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

public class CreaSDCPanel extends JPanel implements View, VisitableGUI {

    JRadioButton parolaChiaveRBtn;
    JRadioButton cesareRBtn;
    JRadioButton pseudocasualeRBtn;
    JButton salvaSdcBtn;
    JButton provasdcBtn;
    JTextField nomeCifraturaField;
    JTextField chiave;
    JTextArea corpoMessaggioProva;
    JTextArea corpoMessaggioResult;
    JLabel selectSDClabel;
    JPanel topPanel;            //contiene i JRadioButtons
    JPanel centerPanelA;
    JPanel centerPanelB;
    JPanel centerPanelC;
    JPanel centerPanel;
    JPanel bottomPanel;         //scrollpane per table
    JTable table;               // tabella per mappatura per cifrario parolachiave
    JScrollPane scrollPane;     //scrollpane per la tabella
    String metodo;//Metodo

    public CreaSDCPanel() {
        topPanel = new JPanel();
        centerPanel = new JPanel();
        centerPanelA = new JPanel();
        centerPanelB = new JPanel();
        centerPanelC = new JPanel();
        bottomPanel = new JPanel();
        init();
    }

    public void init() {

        //INIT DEI PANNELLI E DEI LAYOUT
        this.setLayout(new BorderLayout());
        topPanel.setLayout(new FlowLayout());
        centerPanel.setLayout(new BorderLayout());
        centerPanelA.setLayout(new BorderLayout());
        centerPanelB.setLayout(new BorderLayout());
        centerPanelC.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());
        centerPanel.setBorder(new EmptyBorder(0, 100, 0, 100));   //padding per separare i controlli dal bordo della finestra
        centerPanelA.setBorder(new EmptyBorder(10, 0, 10, 0));   //padding per separare i controlli dal bordo della finestra
        centerPanelB.setBorder(new EmptyBorder(10, 0, 10, 0));   //padding per separare i controlli dal bordo della finestra
        centerPanelC.setBorder(new EmptyBorder(10, 0, 10, 0));   //padding per separare i controlli dal bordo della finestra

        //INIT DEI CONTROLLI
        parolaChiaveRBtn = new JRadioButton("Parola chiave");
        cesareRBtn = new JRadioButton("Cesare");
        pseudocasualeRBtn = new JRadioButton("Pseudocasuale");
        scrollPane = new JScrollPane();
        salvaSdcBtn = new JButton("Salva cifrario");
        provasdcBtn = new JButton("Prova la cifratura");

        //AGGIUNTA DEI CONTROLLI AI PANNELLI
        ButtonGroup group = new ButtonGroup();  //ragruppa i radio buttons 
        group.add(parolaChiaveRBtn);
        group.add(cesareRBtn);
        group.add(pseudocasualeRBtn);
        selectSDClabel = new JLabel("Metodo di cifratura:");
        topPanel.add(selectSDClabel);
        topPanel.add(parolaChiaveRBtn);
        topPanel.add(cesareRBtn);
        topPanel.add(pseudocasualeRBtn);

        //AGGIUNTA DEI PANNELLI
        this.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(centerPanelA, BorderLayout.NORTH);
        centerPanel.add(centerPanelB, BorderLayout.CENTER);
        centerPanel.add(centerPanelC, BorderLayout.SOUTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.revalidate();

        //REGISTRAZIONE VISTA NEL COTROLLER
        registerController();
    }

    public void initCifrario() {
        centerPanelA.removeAll();
        centerPanelB.removeAll();
        centerPanelC.removeAll();
        bottomPanel.removeAll();
        chiave = new JTextField(50);
        //text area per provare sdc
        corpoMessaggioProva = new JTextArea();
        corpoMessaggioProva.setBorder(new EmptyBorder(30, 0, 20, 0));   //padding per separare i controlli dal bordo della finestra
        corpoMessaggioProva.setPreferredSize(new Dimension(100, 50));
        corpoMessaggioProva.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea
        corpoMessaggioProva.setText("Inserire un testo di prova per vedere il risultato della cifratura");

        //risultato di cifratura
        corpoMessaggioResult = new JTextArea();
        corpoMessaggioResult.setBorder(new EmptyBorder(30, 0, 20, 0));   //padding per separare i controlli dal bordo della finestra
        corpoMessaggioProva.setPreferredSize(new Dimension(100, 50));
        corpoMessaggioResult.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea
        corpoMessaggioResult.setEditable(false);

        //la parte della creatione della mapatura
        centerPanelA.add(new JLabel("Chiave: "), BorderLayout.WEST);
        centerPanelA.add(chiave, BorderLayout.CENTER);
        centerPanelB.add(new JLabel("Prova: "), BorderLayout.WEST);
        centerPanelB.add(corpoMessaggioProva, BorderLayout.CENTER);
        centerPanelC.add(new JLabel("Risultato: "), BorderLayout.WEST);
        centerPanelC.add(corpoMessaggioResult, BorderLayout.CENTER);
        centerPanel.revalidate();

        nomeCifraturaField = new JTextField(20);
        bottomPanel.add(provasdcBtn);
        bottomPanel.add(new JLabel("Nome cifratura"));
        bottomPanel.add(nomeCifraturaField);
        bottomPanel.add(salvaSdcBtn);

        this.revalidate();
        centerPanelA.revalidate();
        centerPanelB.revalidate();
        centerPanelC.revalidate();
        bottomPanel.revalidate();
    }

    public void initCesare() {
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

    @Override
    public void registerController() {
        gc = GUIControllerUC1.getInstance();
        this.accept(gc);
    }

    @Override
    public void accept(VisitorGUI visitor) {
        visitor.visit(this);
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

}
