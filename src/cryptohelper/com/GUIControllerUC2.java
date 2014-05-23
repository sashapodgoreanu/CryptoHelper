//Classe GUIControllerAL
//è un GUIController per l'area di lavoro della spia (parte UC2 del codice)
package cryptohelper.com;

import cryptohelper.GUI.LoginForm;
import cryptohelper.GUI.UC2.CaricaSessionePanel;
import cryptohelper.GUI.UC2.IntercettaMessaggioPanel;
import cryptohelper.GUI.UC2.ScegliMsgPanel;
import cryptohelper.data.HtmlVisitor;
import cryptohelper.data.Messaggio;
import cryptohelper.data.SessioneLavoro;
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
    private IntercettaMessaggioPanel intercettaMessaggioPanel;
    private ScegliMsgPanel scegliMsgPanel;
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
        if (v instanceof IntercettaMessaggioPanel) {
            intercettaMessaggioPanel = (IntercettaMessaggioPanel) v;
            intercettaMessaggioPanel.getNuovaSessioneBtn().addActionListener(new NuovaSessioneListener());
            intercettaMessaggioPanel.getCaricaSessioneBtn().addActionListener(new CaricaSessioneListener());
            intercettaMessaggioPanel.getLogoutBtn().addActionListener(new LogoutListener());
        } else if (v instanceof ScegliMsgPanel) {
            scegliMsgPanel = (ScegliMsgPanel) v;
            scegliMsgPanel.getElencoMessaggi().addListSelectionListener(new ViewMsgChoiceListener());
        }
        else if (v instanceof CaricaSessionePanel) {
            caricaSessionePanel = (CaricaSessionePanel) v;
            caricaSessionePanel.getElencoSessioni().addListSelectionListener(new ViewSessionChoiceListener());
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
    private class NuovaSessioneListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            intercettaMessaggioPanel.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            ArrayList<Messaggio> temp = Messaggio.caricaMessaggi();
            intercettaMessaggioPanel.initNuovaSessione(temp);
        }
    }

    //classe listener per il button Carica Sessione 
    private class CaricaSessioneListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            intercettaMessaggioPanel.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            ArrayList<SessioneLavoro> temp = SessioneLavoro.caricaSessioni(comC.getStudente().getId());
            System.out.println(comC.getStudente().getId() + "");
            intercettaMessaggioPanel.initCaricaSessione(temp);
        }
    }

    //classe listener per la scelta dei messaggi nella nuova sessione 
    private class ViewMsgChoiceListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            System.out.println("Clicked LIST");
            Messaggio chosenMsg = (Messaggio) scegliMsgPanel.getElencoMessaggi().getSelectedValue();
            scegliMsgPanel.getCorpoMessaggio().setText((new HtmlVisitor().visit(chosenMsg)));
        }
    }
    
    //classe listener per la scelta della sessione da caricare 
    private class ViewSessionChoiceListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            System.out.println("Clicked LIST");
            SessioneLavoro chosenMsg = (SessioneLavoro) caricaSessionePanel.getElencoSessioni().getSelectedValue();
            caricaSessionePanel.getInfoSessione().setText((new HtmlVisitor().visit(chosenMsg)));
        }
    }
    
}
