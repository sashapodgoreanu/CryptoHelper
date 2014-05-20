//Finestra principale della GUI
package cryptohelper.GUI;

import cryptohelper.interfaces.View;
import cryptohelper.com.GUIController;
import cryptohelper.data.HtmlVisitor;
import cryptohelper.data.Messaggio;
import cryptohelper.interfaces.MessaggioMittente;
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
    JLabel languageLabel;
    JLabel destinatarioPromptLabel;  
    JTextField destinatarioField;
    JTextField titoloBozzaField;    //inputBox per il messaggio
    JList elencoBozze;                  //visualizza lalista delle bozze
    JScrollPane scrollPane;
    JTextPane corpoBozza;
    JComboBox linguaDropdown;
    ArrayList<MessaggioMittente> bozzeArrLst;   //array list con elenco delle bozze disponibili (messaggi in cui l'utente loggato è il mittente)

    public BozzePanel(ArrayList<MessaggioMittente> bozzeArrLst) {
        topPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();
        this.bozzeArrLst = bozzeArrLst;
        this.init();
    }

    //inizializza il pannello
    private void init() {
        System.out.println("Inizzializzazione Bozze...");   //comunicazione di controllo per i log

        //INIT DEI PANNELLI E DEI LAYOUT
        this.setLayout(new BorderLayout());
        topPanel.setLayout(new FlowLayout());
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());

        //INIT DEI CONTROLLI
        String[] lingua = {"inglese", "italiano"};
        linguaDropdown = new JComboBox(lingua);
        linguaDropdown.setBackground(Color.WHITE);
        saveBozzaBtn = new JButton("Salva"); //non modificare 
        deleteBozzaBtn = new JButton("Elimina");//non modificare 
        sendBozzaBtn = new JButton("Invia bozza");
        msgTitleLabel = new JLabel("Titolo della bozza:");
        bozzeListLabel = new JLabel("Bozze disponibili:");
        languageLabel = new JLabel("Lingua: ");
        destinatarioPromptLabel = new JLabel("Destinatario: ");
        destinatarioField = new JTextField("");
        destinatarioField.setEditable(false);
        destinatarioField.setBackground(Color.WHITE);
        destinatarioField.setOpaque(true);
        titoloBozzaField = new JTextField(21);
        corpoBozza = new JTextPane();
        corpoBozza.setPreferredSize(new Dimension(540, 250));
        corpoBozza.setContentType("text/html"); //consente formattazione html
        corpoBozza.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // aggiunge un bordo alla textArea
        elencoBozze = new JList(new Vector<MessaggioMittente>(bozzeArrLst));
        elencoBozze.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof MessaggioMittente) {
                    MessaggioMittente temp = (Messaggio) value;
                    //System.out.println("renderer " + temp.toString());
                    ((JLabel) renderer).setText(temp.getTitolo());
                }
                return renderer;
            }
        });
        elencoBozze.setSelectedIndex(0);
        MessaggioMittente index0 = (MessaggioMittente) elencoBozze.getSelectedValue();
        if (index0 != null) {
            setCorpoBozza(index0.getTitolo());
            setTitoloBozza(index0.getTitolo());
            setDestinatario(index0.getDestinatario().getNome() + " " + index0.getDestinatario().getCognome());
            linguaDropdown.setSelectedItem(index0.getLingua());
        }
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(180, 250));
        scrollPane.setViewportView(elencoBozze);

        //AGGIUNTA DEI CONTROLLI AI PANNELLI
        topPanel.add(msgTitleLabel);
        topPanel.add(titoloBozzaField);
        topPanel.add(destinatarioPromptLabel);
        topPanel.add(destinatarioField);
        topPanel.add(languageLabel);
        topPanel.add(linguaDropdown);
        bottomPanel.add(saveBozzaBtn);
        bottomPanel.add(deleteBozzaBtn);
        bottomPanel.add(sendBozzaBtn);
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

    public void setSelectedIndex() {

    }

    //TO-DO
    public boolean deleteSelectedIndex() {
        int toDelete = elencoBozze.getSelectedIndex();
        if (toDelete >= 0) {
            rightPanel.removeAll();
            topPanel.removeAll();
            leftPanel.removeAll();
            bottomPanel.removeAll();
            bozzeArrLst.remove(toDelete);
            init();
            rightPanel.revalidate();
            topPanel.revalidate();
            leftPanel.revalidate();
            bottomPanel.revalidate();
            return true;
        }
        return false;
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
        return titoloBozzaField.getText();
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

    public JTextPane getCorpoBozza() {
        return corpoBozza;
    }

    public String getDestinatario() {
        return destinatarioField.getText();
    }

    public String getLingua() {
        return linguaDropdown.getSelectedItem().toString();
    }

    public JComboBox getLinguaDropdown() {
        return linguaDropdown;
    }
    
    public ArrayList<MessaggioMittente> getBozzeArrLst() {
        return bozzeArrLst;
    }

    //METODI SETTER
    public void setTitoloBozza(String titolo) {
        titoloBozzaField.setText(titolo);
    }

    public void setDestinatario(String dest) {
        destinatarioField.setText(dest);
    }
    
    public void setCorpoBozza(String testo) {
        corpoBozza.setText(testo);
    }
}
