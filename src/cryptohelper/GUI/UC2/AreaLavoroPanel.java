//Pannello con gli strumenti per decifrare il messaggio per la spia
package cryptohelper.GUI.UC2;

import cryptohelper.com.GUIControllerUC2;
import cryptohelper.data.AnalisiFrequenza;
import cryptohelper.data.SessioneLavoro;
import cryptohelper.interfaces.MessaggioIntercettato;
import cryptohelper.interfaces.View;
import cryptohelper.interfaces.VisitableGuiUC2;
import cryptohelper.interfaces.VisitorGuiUC2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class AreaLavoroPanel extends JPanel implements View, VisitableGuiUC2 {

    private boolean DEBUG = true; //attiva le stampe di debug
    private String[] alfabeto = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    JPanel topPanel;                //pannello in alto
    JPanel middlePanel;             //pannello al centro
    JPanel bottomPanel;             //pannello in basso
    JPanel bottomPanelCenter;       //pannello centrale del pannello in basso
    JPanel middlePanelLeft;         //pannello a sinistra nell'area centrale   
    JPanel middlePanelRight;        //pannello a destra nell'area centrale  
    JPanel infoMessaggio;           //pannello con informazioni sul messaggio intercettato
    JPanel infoMessaggioInner;      //pannello con informazioni sul messaggio intercettato
    JPanel bigrammiPanel;           //pannello per le frequenze dei bigrammi
    JPanel caratteriPanel;          //pannello per le frequenze dei caratteri
    JPanel caratteriMsgPanel;       //pannello per le frequenze dei caratteri nel messaggio   
    JPanel bigrammiMsgPanel;        //pannello per le frequenze dei bigrammi nel messaggio
    JLabel infoMessaggioLabel;
    JLabel codedTextLabel;
    JLabel plainTextLabel;
    JLabel mappaturaLabel;
    JLabel languageLabel;
    JLabel senderLabel;
    JLabel receiverLabel;
    JLabel bigrammiLabel;
    JLabel bigrammiMsgLabel;
    JLabel caratteriLabel;
    JLabel caratteriMsgLabel;
    JTextPane corpoTesto;
    JTextPane corpoTestoCifrato;
    JTable mappatura;
    JTable bigrammi;
    JTable caratteri;
    JTable bigrammiMsg;
    JTable caratteriMsg;
    JButton undoBtn;
    JScrollPane scrollPaneMappatura;
    JScrollPane scrollPaneBigrammi;
    JScrollPane scrollPaneCaratteri;
    JScrollPane scrollPaneBigrammiMsg;
    JScrollPane scrollPaneCaratteriMsg;
    JScrollPane scrollPaneTesto;
    JScrollPane scrollPaneTestoCifrato;
    JComboBox bigrammiComboBox;
    JComboBox bigrammiMsgComboBox;
    ArrayList<JComboBox> jcomboBoxes;//dropdown per selezionare un carattere
    SessioneLavoro sessioneCorrente;//sessione su cui si sta lavorando
    MessaggioIntercettato messaggioIntercettato; //messaggio della sessione
    AnalisiFrequenza analisiFrequenza; //dati sulle frequenze

    public AreaLavoroPanel(SessioneLavoro sessione) {
        topPanel = new JPanel();
        middlePanel = new JPanel();
        bottomPanel = new JPanel();
        bottomPanelCenter = new JPanel();
        middlePanelLeft = new JPanel();
        middlePanelRight = new JPanel();
        infoMessaggio = new JPanel();
        infoMessaggioInner = new JPanel();
        bigrammiPanel = new JPanel();
        caratteriPanel = new JPanel();
        bigrammiMsgPanel = new JPanel();
        caratteriMsgPanel = new JPanel();
        this.sessioneCorrente = sessione;
        this.messaggioIntercettato = sessione.getMessaggioIntercettato();
        this.analisiFrequenza = new AnalisiFrequenza(messaggioIntercettato.getLingua(), messaggioIntercettato.getTestoCifrato());
        System.out.println(messaggioIntercettato.getLingua() + "***************************************" + messaggioIntercettato.getTestoCifrato());
        this.init();
    }

    //inizializza il pannello
    private void init() {
        System.out.println("Inizzializzazione scelta messaggio...");   //comunicazione di controllo per i log

        //INIT DEI LAYOUT
        this.setLayout(new BorderLayout());
        topPanel.setLayout(new FlowLayout());
        middlePanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new BorderLayout());
        bottomPanelCenter.setLayout(new BorderLayout());
        middlePanelLeft.setLayout(new BorderLayout());
        middlePanelRight.setLayout(new BorderLayout());
        infoMessaggio.setLayout(new FlowLayout(FlowLayout.LEFT));
        infoMessaggioInner.setBorder(BorderFactory.createLineBorder(Color.GRAY)); //bordo per il panel
        infoMessaggioInner.setBackground(Color.WHITE);
        bigrammiPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        caratteriPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bigrammiPanel.setBorder(new EmptyBorder(5, 0, 0, 0)); //padding per il testo della status bar
        caratteriPanel.setBorder(new EmptyBorder(5, 0, 0, 0)); //padding per il testo della status bar
        bigrammiMsgPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        caratteriMsgPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bigrammiMsgPanel.setBorder(new EmptyBorder(5, 0, 0, 0)); //padding per il testo della status bar
        caratteriMsgPanel.setBorder(new EmptyBorder(5, 0, 0, 0)); //padding per il testo della status bar

        //INIT DEI CONTROLLI
        jcomboBoxes = new ArrayList<>();
        codedTextLabel = new JLabel("Testo cifrato:");
        plainTextLabel = new JLabel("Testo in chiaro:");
        mappaturaLabel = new JLabel("Mappatura corrente:");
        infoMessaggioLabel = new JLabel("Dettagli aggiuntivi sul messaggio:          ");
        undoBtn = new JButton("Annulla ipotesi");
        languageLabel = new JLabel("Lingua: " + messaggioIntercettato.getLingua() + "               ");
        senderLabel = new JLabel("Mittente: " + messaggioIntercettato.getMittente().getNome() + " " + messaggioIntercettato.getMittente().getCognome() + "               ");
        receiverLabel = new JLabel("Destinatario: " + messaggioIntercettato.getDestinatario().getNome() + " " + messaggioIntercettato.getDestinatario().getCognome());
        bigrammiLabel = new JLabel("Frequenze dei bigrammi in " + messaggioIntercettato.getLingua() + " per la lettera: ");
        bigrammiMsgLabel = new JLabel("Frequenze bigrammi nel messaggio per la lettera: ");
        caratteriLabel = new JLabel("Frequenze dei caratteri in " + messaggioIntercettato.getLingua() + ":         ");
        caratteriMsgLabel = new JLabel("Frequenze dei caratteri nel messaggio:");
        Font font = new Font("monospaced", Font.PLAIN, 16);
        corpoTestoCifrato = new JTextPane();
        corpoTestoCifrato.setEditable(true); //rende in sola lettura il campo con il testo del messaggio
        corpoTestoCifrato.setText(messaggioIntercettato.getTestoCifrato());
        corpoTestoCifrato.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); //assegna un margine al controllo
        corpoTestoCifrato.setFont(font);
        corpoTesto = new JTextPane();
        corpoTesto.setText(messaggioIntercettato.getAreaLavoro());
        corpoTesto.setEditable(false); //rende in sola lettura il campo con il testo del messaggio
        corpoTesto.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); //assegna un margine al controllo
        corpoTesto.setFont(font);
        mappatura = new JTable(new MappaturaModel());
        mappatura.getTableHeader().setReorderingAllowed(false);  //disabilita lo spostamento delle colonne della tabella
        mappatura.getTableHeader().setResizingAllowed(false);  //disabilita il ridimensionamento delle colonne della tabella
        mappatura.setCellSelectionEnabled(true);
        for (int i = 0; i < 26; i++) {
            setUpCollona(mappatura.getColumnModel().getColumn(i));
        }
        NumberFormat formatter = new DecimalFormat("#0.00");
        bigrammiComboBox = new JComboBox(alfabeto);
        bigrammi = new JTable(new BigrammiModel());
        bigrammi.getTableHeader().setReorderingAllowed(false);  //disabilita lo spostamento delle colonne della tabella
        bigrammi.getTableHeader().setResizingAllowed(false);  //disabilita il ridimensionamento delle colonne della tabella
        bigrammi.setCellSelectionEnabled(true);
        for (int i = 0; i < 26; i++) {
            //afafff
            //   Map<Character, ArrayList<Integer>> arr = analisiFrequenza.getBigrammiLingua('a');
            //     caratteriMsg.setValueAt(formatter.format(arr.), 0, i);
        }
        
        caratteri = new JTable(new CaratteriModel());
        caratteri.getTableHeader().setReorderingAllowed(false);  //disabilita lo spostamento delle colonne della tabella
        caratteri.getTableHeader().setResizingAllowed(false);  //disabilita il ridimensionamento delle colonne della tabella
        caratteri.setCellSelectionEnabled(true);
        for (int i = 0; i < 26; i++) {
            double[] arr = analisiFrequenza.getFrequenzaLingua();
            caratteri.setValueAt(arr[i], 0, i);
        }
        bigrammiMsgComboBox = new JComboBox(alfabeto);
        bigrammiMsg = new JTable(new BigrammiModel());
        bigrammiMsg.getTableHeader().setReorderingAllowed(false);  //disabilita lo spostamento delle colonne della tabella
        bigrammiMsg.getTableHeader().setResizingAllowed(false);  //disabilita il ridimensionamento delle colonne della tabella
        bigrammiMsg.setCellSelectionEnabled(true);

        for (int i = 0; i < 26; i++) {
            //   double[] arr = analisiFrequenza.getBigrammiMsg(ch);
            //  caratteriMsg.setValueAt(formatter.format(arr[i]), 0, i);
        }
        caratteriMsg = new JTable(new CaratteriModel());
        caratteriMsg.getTableHeader().setReorderingAllowed(false);  //disabilita lo spostamento delle colonne della tabella
        caratteriMsg.getTableHeader().setResizingAllowed(false);  //disabilita il ridimensionamento delle colonne della tabella
        caratteriMsg.setCellSelectionEnabled(true);
        //carica nella tabella le frequenze dei caratteri all'interno del messaggio
        for (int i = 0; i < 26; i++) {
            double[] arr = analisiFrequenza.getFrequenzaMsg();
            caratteriMsg.setValueAt(formatter.format(arr[i]), 0, i);
        }
        scrollPaneMappatura = new JScrollPane();
        scrollPaneBigrammi = new JScrollPane();
        scrollPaneCaratteri = new JScrollPane();
        scrollPaneBigrammiMsg = new JScrollPane();
        scrollPaneCaratteriMsg = new JScrollPane();
        scrollPaneTesto = new JScrollPane();
        scrollPaneTestoCifrato = new JScrollPane();
        scrollPaneMappatura.setViewportView(mappatura);
        scrollPaneMappatura.setPreferredSize(new Dimension(800, 39));
        scrollPaneBigrammi.setViewportView(bigrammi);
        scrollPaneBigrammi.setPreferredSize(new Dimension(800, 55));
        scrollPaneCaratteri.setViewportView(caratteri);
        scrollPaneCaratteri.setPreferredSize(new Dimension(800, 39));
        scrollPaneBigrammiMsg.setViewportView(bigrammiMsg);
        scrollPaneBigrammiMsg.setPreferredSize(new Dimension(800, 55));
        scrollPaneCaratteriMsg.setViewportView(caratteriMsg);
        scrollPaneCaratteriMsg.setPreferredSize(new Dimension(800, 39));
        scrollPaneTesto.setViewportView(corpoTesto);
        scrollPaneTesto.setPreferredSize(new Dimension(560, 150));
        scrollPaneTestoCifrato.setViewportView(corpoTestoCifrato);
        scrollPaneTestoCifrato.setPreferredSize(new Dimension(560, 150));
        corpoTesto.setCaretPosition(0); //porta in cima la scrollbar del pannello
        corpoTestoCifrato.setCaretPosition(0); //porta in cima la scrollbar del pannello

        //AGGIUNTA DEI CONTROLLI AI PANNELLI             
        middlePanelLeft.add(codedTextLabel, BorderLayout.NORTH);
        middlePanelLeft.add(scrollPaneTestoCifrato, BorderLayout.CENTER);
        middlePanelRight.add(plainTextLabel, BorderLayout.NORTH);
        middlePanelRight.add(scrollPaneTesto, BorderLayout.CENTER);
        middlePanel.add(middlePanelLeft, BorderLayout.WEST);
        middlePanel.add(middlePanelRight, BorderLayout.EAST);
        topPanel.add(mappaturaLabel);
        topPanel.add(scrollPaneMappatura);
        topPanel.add(undoBtn);
        infoMessaggio.add(infoMessaggioLabel);
        infoMessaggioInner.add(languageLabel);
        infoMessaggioInner.add(senderLabel);
        infoMessaggioInner.add(receiverLabel);
        infoMessaggio.add(infoMessaggioInner);
        bigrammiPanel.add(bigrammiLabel);
        bigrammiPanel.add(bigrammiComboBox);
        bigrammiPanel.add(scrollPaneBigrammi);
        caratteriPanel.add(caratteriLabel);
        caratteriPanel.add(scrollPaneCaratteri);
        bigrammiMsgPanel.add(bigrammiMsgLabel);
        bigrammiMsgPanel.add(bigrammiMsgComboBox);
        bigrammiMsgPanel.add(scrollPaneBigrammiMsg);
        caratteriMsgPanel.add(caratteriMsgLabel);
        caratteriMsgPanel.add(scrollPaneCaratteriMsg);
        bottomPanelCenter.add(caratteriMsgPanel, BorderLayout.NORTH);
        bottomPanelCenter.add(bigrammiPanel, BorderLayout.CENTER);
        bottomPanelCenter.add(bigrammiMsgPanel, BorderLayout.SOUTH);
        bottomPanel.add(caratteriPanel, BorderLayout.NORTH);
        bottomPanel.add(bottomPanelCenter, BorderLayout.CENTER);
        bottomPanel.add(infoMessaggio, BorderLayout.SOUTH);

        //AGGIUNTA DEI PANNELLI
        this.add(topPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        //REGISTRAZIONE VISTA NEL COTROLLER
        registerController();
    }

    //effettua il set-up dei combo-box delle colonne
    public void setUpCollona(TableColumn collonna) {
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("-");
        for (int i = 'A'; i <= 'Z'; i++) {
            comboBox.addItem(String.valueOf((char) i));
        }
        jcomboBoxes.add(comboBox);
        collonna.setCellEditor(new DefaultCellEditor(comboBox));
    }

    @Override
    public void registerController() {
        this.accept(GUIControllerUC2.getInstance());
    }

    @Override
    public void accept(VisitorGuiUC2 visitor) {
        visitor.visit(this);
    }

    //METODI GETTER
    public JTextPane getCorpoTesto() {
        return corpoTesto;
    }

    public JTextPane getCorpoTestoCifrato() {
        return corpoTestoCifrato;
    }

    public JTable getMappatura() {
        return mappatura;
    }

    public JButton getUndoBtn() {
        return undoBtn;
    }

    public SessioneLavoro getSessioneCorrente() {
        return sessioneCorrente;
    }

    public JScrollPane getScrollPaneTesto() {
        return scrollPaneTesto;
    }

    public JScrollPane getScrollPaneTestoCifrato() {
        return scrollPaneTestoCifrato;
    }

    //METODI SETTER
    public void setCorpoTesto(JTextPane corpoTesto) {
        this.corpoTesto = corpoTesto;
    }

    public void setMappatura(JTable mappatura) {
        this.mappatura = mappatura;
    }

    //**************************************************************************
    //
    //Classe interna per la tabella della mappatura
    class MappaturaModel extends AbstractTableModel {

        private Object[][] data = {
            {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},};

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i = 0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j = 0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            if (true) {
                System.out.println("Set valore nella " + row + "," + col
                        + " a " + value
                );
            }
            data[row][col] = value;
            fireTableCellUpdated(row, col);
            if (DEBUG) {
                System.out.println("Nuovo valore :");
                printDebugData();
            }
        }

        //METODI GETTER
        @Override
        public int getColumnCount() {
            return alfabeto.length;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public String getColumnName(int col) {
            return alfabeto[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return col >= 0;
        }

    } //end class MappaturaModel

    //**************************************************************************
    //
    //Classe interna per la tabella delle frequenze dei bigrammi
    class BigrammiModel extends AbstractTableModel {

        private String[] alfabetoBigrammi = {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        private Object[][] data = {
            {"A*", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"*A", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}
        };

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i = 0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j = 0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            if (true) {
                System.out.println("Set valore nella " + row + "," + col
                        + " a " + value
                );
            }
            data[row][col] = value;
            fireTableCellUpdated(row, col);
            if (DEBUG) {
                System.out.println("Nuovo valore :");
                printDebugData();
            }
        }

        //METODI GETTER
        @Override
        public int getColumnCount() {
            return alfabetoBigrammi.length;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public String getColumnName(int col) {
            return alfabetoBigrammi[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return col >= 0;
        }

    } //end class BigrammiModel

    //Classe interna per la tabella delle frequenze dei caratteri
    class CaratteriModel extends AbstractTableModel {

        private Object[][] data = {
            {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},};

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i = 0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j = 0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }

        //METODI SETTER
        @Override
        public int getColumnCount() {
            return alfabeto.length;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public String getColumnName(int col) {
            return alfabeto[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return col >= 0;
        }

        //METODI GETTER
        @Override
        public void setValueAt(Object value, int row, int col) {
            if (true) {
                System.out.println("Set valore nella " + row + "," + col + " a " + value);
            }
            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("Nuovo valore :");
                printDebugData();
            }
        }
    } //end class CaratteriModel

}//end class AreaLavoroPanel
