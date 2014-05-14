//Finestra principale della GUI
package cryptohelper.GUI;

import cryptohelper.com.GUIController;
import cryptohelper.data.Messaggio;
import cryptohelper.data.MessaggioDestinatario;
import cryptohelper.data.MessaggioMittente;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class BozzePanel extends JPanel implements View {

    JPanel topPanel;         //pannello in alto
    JPanel leftPanel;        //pannello a sinistra
    JPanel rightPanel;       //pannello a destra
    JPanel bottomPanel;      //pannello in basso
    JButton saveBozzaBtn;
    JButton deleteBozzaBtn;
    JButton sendBozzaBtn;
    JLabel msgTitleLabel;
    JLabel bozzeListLabel;
    JTextField titoloMessaggioField;    //inputBox per il messaggio
    JList elencoDestinatari;            //visualizza la lista dei destinatariArrLst
    JList elencoMessaggiRicevuti;       //elenca i mittenti di tutti i messaggi ricevuti  
    JList elencoBozze;                  //visualizza lalista delle bozze
    JScrollPane scrollPane;
    JTextArea corpoBozza;
    ArrayList<MessaggioMittente> bozzeArrLst;   //array list con elenco delle bozze disponibili

    Studente studente;

    public BozzePanel(ArrayList<MessaggioMittente> bozzeArrLst) {
        this.bozzeArrLst = bozzeArrLst;
        this.init();
    }

    //inizializza il pannello
    private void init() {
        System.out.println("Inizzializzazione Bozze...");   //comunicazione di controllo per i log

        //INIT DEI PANNELLI E DEI LAYOUT
        this.setLayout(new BorderLayout());
        topPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());

        //INIT DEI CONTROLLI
        saveBozzaBtn = new JButton("Salva bozza");
        deleteBozzaBtn = new JButton("Elimina bozza");
        sendBozzaBtn = new JButton("Invia messaggio");
        msgTitleLabel = new JLabel("Titolo della bozza:");
        bozzeListLabel = new JLabel("Bozze disponibili:");
        titoloMessaggioField = new JTextField(21);
        corpoBozza = new JTextArea();
        corpoBozza.setSize(new Dimension(540, 250));
        corpoBozza.setLineWrap(true);
        corpoBozza.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea
        elencoBozze = new JList(new Vector<MessaggioMittente>(bozzeArrLst));
        elencoBozze.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof MessaggioMittente) {
                    MessaggioMittente temp = (Messaggio) value;
                    //System.out.println("renderer " + temp.toString());
                    ((JLabel) renderer).setText(temp.getTitolo() + " " + temp.getTesto());
                }
                return renderer;
            }
        });
        elencoBozze.setSelectedIndex(0);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(elencoBozze);

        //AGGIUNTA DEI CONTROLLI AI PANNELLI
        topPanel.add(msgTitleLabel);
        topPanel.add(titoloMessaggioField);
        bottomPanel.add(saveBozzaBtn);
        bottomPanel.add(deleteBozzaBtn);
        leftPanel.add(corpoBozza, BorderLayout.CENTER);
        rightPanel.add(bozzeListLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        //AGGIUNTA DEI PANNELLI
        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);
        //REGISTRAZIONE VISTA NEL COTROLLER
        registerController();
    }
    
    public void setSelectedIndex(){
        
    }
    
    //TO-DO
    public boolean deleteSelectedIndex(){
        this.removeAll();
        int toDelete = elencoBozze.getSelectedIndex();
        if(toDelete > 0){
            bozzeArrLst.remove(toDelete);
            init();
            revalidate();
        } //else pp.setStatus  - devi selezionare almeno un lemento per fare delete. 
        //TO-DO - da aggiornare pp.setStatus se qualcosa è andato storto.    bozzeArrLst.remove(toDelete); restituisce true o false.... 
        return true;
    }

    @Override
    public void registerController() {
        GUIController gc = GUIController.getInstance();
        gc.addView(this);
    }
    
    public void modificaCorpoMessaggio(String testo) {
        corpoBozza.setText(testo);
    }

    //METODI GETTER
    public JList getElencoBozze() {
        return elencoBozze;
    }

    public String getTitoloBozza() {
        return titoloMessaggioField.getText();
    }

    public JButton getDeleteBozzaBtn() {
        return deleteBozzaBtn;
    }

    public JButton getSendBozzaBtn() {
        return sendBozzaBtn;
    }

     public JButton getSaveBozzaBtn() {
        return saveBozzaBtn;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public ArrayList<MessaggioMittente> getBozzeArrLst() {
        return bozzeArrLst;
    }

 
    //METODI SETTER
    public void setTitoloBozza(String titolo) {
        titoloMessaggioField.setText(titolo);
    }
    
     public void setCorpoBozza(String testo) {
        corpoBozza.setText(testo);
    }
}
