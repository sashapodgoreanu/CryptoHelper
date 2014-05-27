//Pannello con gli strumenti per decifrare il messaggio per la spia
package cryptohelper.GUI.UC2;

import cryptohelper.com.GUIControllerUC2;
import cryptohelper.interfaces.MessaggioIntercettato;
import cryptohelper.interfaces.View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class AreaLavoroPanel extends JPanel implements View {

    private boolean DEBUG = true;

    JPanel topPanel;         //pannello in alto
    JPanel leftPanel;        //pannello a sinistra
    JPanel rightPanel;       //pannello a destra
    JPanel bottomPanel;      //pannello in basso
    JPanel leftPanelUp;      //divisore per pannello a sinistra   
    JPanel leftPanelDown;    //divisore per pannello a sinistra
    JLabel codedTextLabel;
    JLabel plainTextLabel;
    JLabel mappaturaLabel;
    JLabel languageLabel;
    JTextPane corpoTesto;
    JTextPane corpoTestoCifrato;
    JTable mappatura;
    JScrollPane scrollPane;
    ArrayList<JComboBox> jcomboBoxes;//dropdown per selezionare un carattere
    MessaggioIntercettato messaggioIntercettato; //sessione su cui si sta lavorando

    public AreaLavoroPanel(MessaggioIntercettato messaggioIntercettato) {
        topPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();
        leftPanelUp = new JPanel();
        leftPanelDown = new JPanel();
        this.messaggioIntercettato = messaggioIntercettato;
        this.init();
    }

    //inizializza il pannello
    private void init() {
        System.out.println("Inizzializzazione scleta messaggio...");   //comunicazione di controllo per i log

        //INIT DEI LAYOUT
        this.setLayout(new BorderLayout());
        topPanel.setLayout(new FlowLayout());
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());
        leftPanelUp.setLayout(new BorderLayout());
        leftPanelDown.setLayout(new BorderLayout());

        //INIT DEI CONTROLLI
        jcomboBoxes = new ArrayList<>();
        codedTextLabel = new JLabel("Testo cifrato:");
        plainTextLabel = new JLabel("Testo in chiaro:");
        mappaturaLabel = new JLabel("Mappatura corrente:");
        languageLabel = new JLabel("Lingua messaggio: " + messaggioIntercettato.getLingua());
        corpoTestoCifrato = new JTextPane();
        corpoTestoCifrato.setPreferredSize(new Dimension(500, 180));
        corpoTestoCifrato.setEditable(false); //rende in sola lettura il campo con il testo del messaggio
        //System.out.println(sessione.toString());

        corpoTestoCifrato.setText(messaggioIntercettato.getTestoCifrato());
        Border b = BorderFactory.createLineBorder(Color.GRAY);  //crea un bordo al controllo
        corpoTestoCifrato.setBorder(BorderFactory.createCompoundBorder(b, BorderFactory.createEmptyBorder(5, 5, 5, 5))); //assegna un margine al controllo
        corpoTesto = new JTextPane();
        corpoTesto.setText(messaggioIntercettato.getAreaLavoro());
        corpoTesto.setPreferredSize(new Dimension(500, 180));
        corpoTesto.setEditable(false); //rende in sola lettura il campo con il testo del messaggio
        b = BorderFactory.createLineBorder(Color.GRAY);  //crea un bordo al controllo
        corpoTesto.setBorder(BorderFactory.createCompoundBorder(b, BorderFactory.createEmptyBorder(5, 5, 5, 5))); //assegna un margine al controllo

        //String[] alfabeto = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        //Object[][] data = {{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}};
        mappatura = new JTable(new MappaturaModel());
        mappatura.setCellSelectionEnabled(true);
        for (int i = 0; i < 26; i++) {
            setUpCollona(mappatura.getColumnModel().getColumn(i));
        }

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(mappatura);
        scrollPane.setPreferredSize(new Dimension(800, 40));

        //AGGIUNTA DEI CONTROLLI AI PANNELLI             
        leftPanelUp.add(codedTextLabel, BorderLayout.NORTH);
        leftPanelUp.add(corpoTestoCifrato, BorderLayout.CENTER);
        leftPanelDown.add(plainTextLabel, BorderLayout.NORTH);
        leftPanelDown.add(corpoTesto, BorderLayout.CENTER);
        leftPanel.add(leftPanelUp, BorderLayout.NORTH);
        leftPanel.add(leftPanelDown, BorderLayout.SOUTH);
        topPanel.add(mappaturaLabel);
        topPanel.add(scrollPane);
        rightPanel.add(languageLabel, BorderLayout.NORTH);

        //AGGIUNTA DEI PANNELLI
        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
      //  this.add(bottomPanel, BorderLayout.SOUTH);

        //REGISTRAZIONE VISTA NEL COTROLLER
        registerController();
    }

    public void setUpCollona(TableColumn collona) {
        //Set up the editor for the sport cells.
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("-");
        for (int i = 'A'; i <= 'Z'; i++) {
            comboBox.addItem(String.valueOf((char) i));
        }
        jcomboBoxes.add(comboBox);
        collona.setCellEditor(new DefaultCellEditor(comboBox));
    }

    class MappaturaModel extends AbstractTableModel {

        private String[] alfabeto = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        private Object[][] data = {
            {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}
        };

        public int getColumnCount() {
            return alfabeto.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return alfabeto[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public boolean isCellEditable(int row, int col) {
            return col >= 0;
        }

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
    }

    @Override
    public void registerController() {
        GUIControllerUC2 gc = GUIControllerUC2.getInstance();
        gc.addView(this);
    }

    //METODI GETTER
    public JTextPane getCorpoTesto() {
        return corpoTesto;
    }

    public void setCorpoTesto(JTextPane corpoTesto) {
        this.corpoTesto = corpoTesto;
    }


    public JTextPane getCorpoTestoCifrato() {
        return corpoTestoCifrato;
    }

    public JTable getMappatura() {
        return mappatura;
    }

    public void setMappatura(JTable mappatura) {
        this.mappatura = mappatura;
    }

}
