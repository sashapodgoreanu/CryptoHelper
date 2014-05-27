//Classe GUIControllerAL
//è un GUIController per l'area di lavoro della spia (parte UC2 del codice)
package cryptohelper.com;

import cryptohelper.GUI.LoginForm;
import cryptohelper.GUI.UC2.CaricaSessionePanel;
import cryptohelper.GUI.UC2.IntercettaMsgPanel;
import cryptohelper.GUI.UC2.NuovaSessionePanel;
import cryptohelper.data.HtmlVisitor;
import cryptohelper.data.Messaggio;
import cryptohelper.data.SessioneLavoro;
import cryptohelper.interfaces.MessaggioIntercettato;
import cryptohelper.interfaces.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUIControllerUC2 {

    private static GUIControllerUC2 instance;
    private COMController comC;
    private IntercettaMsgPanel intercettaMessaggioPanel;
    private NuovaSessionePanel nuovaSessionePanel;
    private CaricaSessionePanel caricaSessionePanel;

    private GUIControllerUC2() {
        comC = COMController.getInstance();
    }

    public static GUIControllerUC2 getInstance() {
        if (instance == null) {
            instance = new GUIControllerUC2();
        }
        return instance;
    }

    //registra i pannelli e i loro actionListener
    public void addView(View v) {
        if (v instanceof IntercettaMsgPanel) {
            intercettaMessaggioPanel = (IntercettaMsgPanel) v;
            intercettaMessaggioPanel.getNuovaSessioneBtn().addActionListener(new NewSessioneListener());
            intercettaMessaggioPanel.getCaricaSessioneBtn().addActionListener(new LoadSessionListener());
            intercettaMessaggioPanel.getLogoutBtn().addActionListener(new LogoutListener());
        } else if (v instanceof NuovaSessionePanel) {
            nuovaSessionePanel = (NuovaSessionePanel) v;
            nuovaSessionePanel.getElencoMessaggi().addListSelectionListener(new ViewMsgChoiceListener());
            // nuovaSessionePanel.getOkBtn().addActionListener(new LoadWorkspaceListener());
        } else if (v instanceof CaricaSessionePanel) {
            caricaSessionePanel = (CaricaSessionePanel) v;
            caricaSessionePanel.getElencoSessioni().addListSelectionListener(new ViewSessionChoiceListener());
            caricaSessionePanel.getEliminaSessioneBtn().addActionListener(new DeleteSessionListener());
            caricaSessionePanel.getOkBtn().addActionListener(new LoadWorkspaceListener());
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
            ArrayList<MessaggioIntercettato> temp = Messaggio.caricaMessaggi();
            intercettaMessaggioPanel.getSalvaSessioneBtn().setEnabled(false);
            intercettaMessaggioPanel.initNuovaSessione(temp);
        }
    }

    //classe listener per il button Carica Sessione 
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
            SessioneLavoro session = (SessioneLavoro) caricaSessionePanel.getElencoSessioni().getSelectedValue();
            session.elimina();
            caricaSessionePanel.deleteSelectedIndex();
            intercettaMessaggioPanel.setStatus("Bozza eliminata correttamente!");
        }
    }

    //classe listener per il button "avanti" in nuova sessione e carica sessione
    private class LoadWorkspaceListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            SessioneLavoro session = (SessioneLavoro) caricaSessionePanel.getElencoSessioni().getSelectedValue();
            System.out.println(session.toString());
            intercettaMessaggioPanel.getSalvaSessioneBtn().setEnabled(true);
            intercettaMessaggioPanel.initAreaLavoro(session);
        }
    }

}
