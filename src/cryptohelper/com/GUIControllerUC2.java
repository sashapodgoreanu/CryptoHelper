//Classe GUIControllerAL
//è un GUIController per l'area di lavoro della spia (parte UC2 del codice)
package cryptohelper.com;

import cryptohelper.GUI.LoginForm;
import cryptohelper.GUI.UC2.IntercettaMessaggioPanel;
import cryptohelper.GUI.UC2.ScegliMsgPanel;
import cryptohelper.data.HtmlVisitor;
import cryptohelper.data.Messaggio;
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
    private IntercettaMessaggioPanel areaLavoro;
    private ScegliMsgPanel scegliMsgPanel;

    private GUIControllerUC2() {
        comC = new COMController();
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
            areaLavoro = (IntercettaMessaggioPanel) v;
            areaLavoro.getNuovaSessioneBtn().addActionListener(new NuovaSessioneListener());
            areaLavoro.getLogoutBtn().addActionListener(new LogoutListener());
        } else if (v instanceof ScegliMsgPanel) {
            scegliMsgPanel = (ScegliMsgPanel) v;
            scegliMsgPanel.getElencoMessaggi().addListSelectionListener(new ViewMsgChoiceListener());
        }
    }

    //classe listener per il button "logout" della finestra principale
    private class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            areaLavoro.dispose();
            LoginForm f = new LoginForm();
            System.out.println(this.getClass() + "Logout eseguito");
        }
    }

    //classe listener per la Jlist della outbox 
    private class NuovaSessioneListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            areaLavoro.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            ArrayList<Messaggio> temp = Messaggio.caricaMessaggi();
            areaLavoro.initNuovaSessione(temp);
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
}
