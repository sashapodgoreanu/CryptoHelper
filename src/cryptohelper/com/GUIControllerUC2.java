//Classe GUIControllerAL
//è un GUIController per l'area di lavoro della spia (parte UC2 del codice)
package cryptohelper.com;

import cryptohelper.GUI.UC1.LoginForm;
import cryptohelper.GUI.UC2.AreaLavoroPanel;
import cryptohelper.GUI.UC2.CaricaSessionePanel;
import cryptohelper.GUI.UC2.IntercettaMsgPanel;
import cryptohelper.GUI.UC2.NuovaSessionePanel;
import cryptohelper.data.HtmlVisitor;
import cryptohelper.data.Messaggio;
import cryptohelper.data.Mossa;
import cryptohelper.data.SessioneLavoro;
import cryptohelper.data.UserInfo;
import cryptohelper.interfaces.MessaggioIntercettato;
import cryptohelper.interfaces.VisitorGuiUC2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class GUIControllerUC2 implements VisitorGuiUC2 {

    private static GUIControllerUC2 instance;
    private final COMController comC;
    private IntercettaMsgPanel intercettaMessaggioPanel;
    private NuovaSessionePanel nuovaSessionePanel;
    private CaricaSessionePanel caricaSessionePanel;
    private AreaLavoroPanel areaLavoroPanel;
    private SessioneLavoro session;
    private final TableMappaturaListener tableListener = new TableMappaturaListener();

    private GUIControllerUC2() {
        comC = COMController.getInstance();
    }

    public static GUIControllerUC2 getInstance() {
        if (instance == null) {
            instance = new GUIControllerUC2();
        }
        return instance;
    }

    //METODI VISIT PER L'AGGIUNTA DEGLI ACTION LISTENER
    @Override
    public void visit(IntercettaMsgPanel imp) {
        intercettaMessaggioPanel = imp;
        intercettaMessaggioPanel.getNuovaSessioneBtn().addActionListener(new NewSessioneListener());
        intercettaMessaggioPanel.getCaricaSessioneBtn().addActionListener(new LoadSessionListener());
        intercettaMessaggioPanel.getSalvaSessioneBtn().addActionListener(new SaveSessionListener());
        intercettaMessaggioPanel.getLogoutBtn().addActionListener(new LogoutListener());
    }

    @Override
    public void visit(NuovaSessionePanel nsp) {
        nuovaSessionePanel = nsp;
        nuovaSessionePanel.getElencoMessaggi().addListSelectionListener(new ViewMsgChoiceListener());
        nuovaSessionePanel.getOkBtn().addActionListener(new NewWorkspaceListener());
    }

    @Override
    public void visit(CaricaSessionePanel csp) {
        caricaSessionePanel = csp;
        caricaSessionePanel.getElencoSessioni().addListSelectionListener(new ViewSessionChoiceListener());
        caricaSessionePanel.getEliminaSessioneBtn().addActionListener(new DeleteSessionListener());
        caricaSessionePanel.getOkBtn().addActionListener(new LoadWorkspaceListener());
    }

    @Override
    public void visit(AreaLavoroPanel alp) {
        areaLavoroPanel = alp;
        areaLavoroPanel.getMappatura().getModel().addTableModelListener(tableListener);
        areaLavoroPanel.getUndoBtn().addActionListener(new UndoListener());
        areaLavoroPanel.getBigrammiComboBox().addActionListener(new BigrammiComboListener());
        areaLavoroPanel.getBigrammiMsgComboBox().addActionListener(new BigrammiMsgComboListener());

    }

    private class TableMappaturaListener implements TableModelListener {

        @Override
        public void tableChanged(TableModelEvent e) {

            System.out.println("effettua una sostituzione");
            intercettaMessaggioPanel.setStatus(" ");
            int index = e.getColumn(); //index della colona editata
            String sost = (String) areaLavoroPanel.getMappatura().getValueAt(0, index); //valore della riga 0 e colona index
            char ch2 = sost.charAt(0); //trasforma la stringa sost in char
            boolean mossaEffettuatainPrecedenza = session.effetuaSostituzione((char) (index + 'A'), ch2);
            System.out.println(mossaEffettuatainPrecedenza);
            if (mossaEffettuatainPrecedenza) {
                intercettaMessaggioPanel.setStatus((char) (index + 'A') + "->" + ch2 + " - Mossa effetuata in precedenza!");
            }
            int actualPos = areaLavoroPanel.getCorpoTesto().getCaretPosition();
            areaLavoroPanel.getCorpoTesto().setText(session.getMessaggioIntercettato().getAreaLavoro());
            areaLavoroPanel.getCorpoTesto().setCaretPosition(actualPos);
        }
    }

    //classe listener per il button "logout" della finestra principale
    private class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            intercettaMessaggioPanel.dispose();
            LoginForm f = new LoginForm();
            System.out.println(this.getClass() + "Logout eseguito");
        }
    }

    //classe listener per il button Nuova Sessione 
    private class NewSessioneListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            intercettaMessaggioPanel.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            ArrayList<MessaggioIntercettato> temp = Messaggio.caricaMessaggi(comC.getStudente().getId());
            intercettaMessaggioPanel.getSalvaSessioneBtn().setEnabled(false);
            intercettaMessaggioPanel.initNuovaSessione(temp);
        }
    }

    //classe listener per il button "Carica Sessione" 
    private class LoadSessionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            ArrayList<SessioneLavoro> temp = SessioneLavoro.caricaSessioni(comC.getStudente().getId());
            intercettaMessaggioPanel.getSalvaSessioneBtn().setEnabled(false);
            intercettaMessaggioPanel.initCaricaSessione(temp);
        }
    }

    //classe listener per il button "Salva Sessione"
    private class SaveSessionListener implements ActionListener {

        @Override
        @SuppressWarnings("empty-statement")
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            if (session.salva()) {
                intercettaMessaggioPanel.setStatus("Sessione salvata correttamente!");
            } else {
                intercettaMessaggioPanel.setStatus("Si è verificato un errore nel salvataggio della sessione!");
            }
        }
    }

//classe listener per la scelta dei messaggi nella nuova sessione 
    private class ViewMsgChoiceListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            System.out.println("Clicked LIST");
            MessaggioIntercettato chosenMsg = (MessaggioIntercettato) nuovaSessionePanel.getElencoMessaggi().getSelectedValue();
            nuovaSessionePanel.getCorpoMessaggio().setText((new HtmlVisitor().visit(chosenMsg)));
            nuovaSessionePanel.getOkBtn().setEnabled(true);
        }
    }

//classe listener per la scelta della sessione da caricare 
    private class ViewSessionChoiceListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            System.out.println("Clicked LIST");
            SessioneLavoro chosenSes = (SessioneLavoro) caricaSessionePanel.getElencoSessioni().getSelectedValue();
            caricaSessionePanel.getInfoSessione().setText((new HtmlVisitor().visit(chosenSes)));
        }
    }

//classe listener per il button "elimina sessione" del pannello "carica sessione" 
    private class DeleteSessionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SessioneLavoro sessionTemp = (SessioneLavoro) caricaSessionePanel.getElencoSessioni().getSelectedValue();
            sessionTemp.elimina();
            caricaSessionePanel.deleteSelectedIndex();
            intercettaMessaggioPanel.setStatus("Bozza eliminata correttamente!");
        }
    }

    //classe listener per il button "avanti" nel pannello carica sessione
    private class LoadWorkspaceListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            session = (SessioneLavoro) caricaSessionePanel.getElencoSessioni().getSelectedValue();
            System.out.println("LoadWorkspaceListener :" + session.toString());
            intercettaMessaggioPanel.getSalvaSessioneBtn().setEnabled(true);
            intercettaMessaggioPanel.initAreaLavoro(session);
        }
    }

    //classe listener per il button "avanti" nel pannello nuova sessione
    private class NewWorkspaceListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            MessaggioIntercettato msg = (MessaggioIntercettato) nuovaSessionePanel.getElencoMessaggi().getSelectedValue();
            msg.setAreaLavoro(msg.getTestoCifrato().toUpperCase());
            UserInfo user = new UserInfo(comC.getStudente().getId(), comC.getStudente().getNome(), comC.getStudente().getCognome());
            session = new SessioneLavoro(nuovaSessionePanel.getNomeSessione(), user, msg);
            if (nuovaSessionePanel.getNomeSessione().equals("")) {
                intercettaMessaggioPanel.setStatus("È necessario dare un nome alla nuova sessione per proseguire!");
            } else {
                System.out.println("LoadWorkspaceListener :" + session.toString());
                intercettaMessaggioPanel.getSalvaSessioneBtn().setEnabled(true);
                intercettaMessaggioPanel.initAreaLavoro(session);
            }
        }
    }

//classe listener per il button "undo" dell'area di lavoro
    private class UndoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //retrieve the actual scrollbar position
            int actualPos = areaLavoroPanel.getCorpoTesto().getCaretPosition();
            Mossa u = session.undo(session.getMessaggioIntercettato().getAreaLavoro());
            //modifica testo con la mossa undo
            areaLavoroPanel.getCorpoTesto().setText(session.getMessaggioIntercettato().getAreaLavoro());
            //update mappatura con la mossa undo
            areaLavoroPanel.getMappatura().getModel().removeTableModelListener(tableListener);
            areaLavoroPanel.getMappatura().setValueAt(String.valueOf(u.getInverseChar()), 0, u.getCharacter() - 65);
            areaLavoroPanel.getMappatura().getModel().addTableModelListener(tableListener);
            //set the actual scroll position. - update jtextarea makes text scroll down
            areaLavoroPanel.getCorpoTesto().setCaretPosition(actualPos);
            intercettaMessaggioPanel.setStatus("Ipotesi anulata per: " + u.getCharacter() + " -> " + u.getInverseChar());
        }
    }

    //classe listener per il combBox deli bigrammi della lingua nel pannello area di lavoro
    private class BigrammiComboListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String tmp = (String) areaLavoroPanel.getBigrammiComboBox().getSelectedItem();
            char letter = tmp.charAt(0);
            Map<Character, ArrayList<Integer>> map = areaLavoroPanel.getAnalisiFrequenzaSessione().getBigrammiLingua(letter);
            areaLavoroPanel.getBigrammi().setValueAt(String.valueOf(letter) + " *", 0, 0);
            areaLavoroPanel.getBigrammi().setValueAt("* " + String.valueOf(letter), 1, 0);
            for (int i = 1; i < 27; i++) {
                int j = i - 1;
                areaLavoroPanel.getBigrammi().setValueAt(map.get((char) (j + 65)).get(0), 0, i);
                areaLavoroPanel.getBigrammi().setValueAt(map.get((char) (j + 65)).get(1), 1, i);
            }
        }
    }

    //classe listener per il combBox deli bigrammi del messaggio nel pannello area di lavoro
    private class BigrammiMsgComboListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String tmp = (String) areaLavoroPanel.getBigrammiMsgComboBox().getSelectedItem();
            char letter = tmp.charAt(0);

            Map<Character, ArrayList<Integer>> mapMsg = areaLavoroPanel.getAnalisiFrequenzaSessione().getBigrammiMsg(letter);
            areaLavoroPanel.getBigrammiMsg().setValueAt(String.valueOf(letter) + " *", 0, 0);
            areaLavoroPanel.getBigrammiMsg().setValueAt("* " + String.valueOf(letter), 1, 0);
            for (int i = 1; i < 27; i++) {
                int j = i - 1;
                areaLavoroPanel.getBigrammiMsg().setValueAt(mapMsg.get((char) (j + 65)).get(0), 0, i);
                areaLavoroPanel.getBigrammiMsg().setValueAt(mapMsg.get((char) (j + 65)).get(1), 1, i);
            }
        }
    }
}
