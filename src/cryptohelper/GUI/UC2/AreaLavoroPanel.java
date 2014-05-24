//Pannello con gli strumenti per decifrare il messaggio per la spia
package cryptohelper.GUI.UC2;

import cryptohelper.interfaces.View;
import cryptohelper.com.GUIControllerUC2;
import cryptohelper.interfaces.MessaggioIntercettato;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

public class AreaLavoroPanel extends JPanel implements View {

    JPanel topPanel;         //pannello in alto
    JPanel leftPanel;        //pannello a sinistra
    JPanel rightPanel;       //pannello a destra
    JPanel bottomPanel;      //pannello in basso
    JPanel leftPanelUp;      //divisore per pannello a sinistra   
    JPanel leftPanelDown;    //divisore per pannello a sinistra
    JLabel codedTextLabel;
    JLabel plainTextLabel;
    JLabel mappaturaLabel;
    JTextPane corpoTesto;
    JTextPane corpoTestoCifrato;
    JTable mappatura;
    JScrollPane scrollPane;
    MessaggioIntercettato messaggioIntercettato; //messaggio intercettato su cui lavorare

    public AreaLavoroPanel(/*MessaggioIntercettato messaggio*/) {
        topPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();
        leftPanelUp = new JPanel();
        leftPanelDown = new JPanel();
        //    this.messaggioIntercettato = messaggio;
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
        codedTextLabel = new JLabel("Testo in chiaro:");
        plainTextLabel = new JLabel("Testo cifrato:");
        mappaturaLabel = new JLabel("Mappatura corrente:");
        corpoTestoCifrato = new JTextPane();
        corpoTestoCifrato.setPreferredSize(new Dimension(500, 180));
        corpoTestoCifrato.setEditable(false); //rende in sola lettura il campo con il testo del messaggio
        Border b = BorderFactory.createLineBorder(Color.GRAY);  //crea un bordo al controllo
        corpoTestoCifrato.setBorder(BorderFactory.createCompoundBorder(b, BorderFactory.createEmptyBorder(0, 10, 10, 10))); //assegna un margine al controllo
        corpoTesto = new JTextPane();
        corpoTesto.setPreferredSize(new Dimension(500, 180));
        corpoTesto.setEditable(false); //rende in sola lettura il campo con il testo del messaggio
        b = BorderFactory.createLineBorder(Color.GRAY);  //crea un bordo al controllo
        corpoTesto.setBorder(BorderFactory.createCompoundBorder(b, BorderFactory.createEmptyBorder(10, 10, 10, 10))); //assegna un margine al controllo

        String[] alfabeto = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Object[][] data = {{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}};
        mappatura = new JTable(data, alfabeto);
        mappatura.setCellSelectionEnabled(true);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(mappatura);
        scrollPane.setPreferredSize(new Dimension(600, 40));

        //AGGIUNTA DEI CONTROLLI AI PANNELLI             
        leftPanelUp.add(codedTextLabel, BorderLayout.NORTH);
        leftPanelUp.add(corpoTestoCifrato, BorderLayout.CENTER);
        leftPanelDown.add(plainTextLabel, BorderLayout.NORTH);
        leftPanelDown.add(corpoTesto, BorderLayout.CENTER);
        leftPanel.add(leftPanelUp, BorderLayout.NORTH);
        leftPanel.add(leftPanelDown, BorderLayout.SOUTH);
        topPanel.add(mappaturaLabel);
        topPanel.add(scrollPane);

        //AGGIUNTA DEI PANNELLI
        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
      //  this.add(bottomPanel, BorderLayout.SOUTH);

        //REGISTRAZIONE VISTA NEL COTROLLER
        registerController();
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

    public JTextPane getCorpoTestoCifrato() {
        return corpoTestoCifrato;
    }

}
