//Classe GUIControllerAL
//è un GUIController per l'area di lavoro della spia (parte UC2 del codice)
package cryptohelper.com;

import cryptohelper.GUI.LoginForm;
import cryptohelper.GUI.areaLavoro.AreaLavoro;
import cryptohelper.GUI.areaLavoro.ScegliMsgPanel;
import cryptohelper.interfaces.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIControllerAL {

    private static GUIControllerAL instance;
    private COMController comC;
    private AreaLavoro areaLavoro;
    private ScegliMsgPanel scegliMsgPanel;

    private GUIControllerAL() {
        comC = new COMController();
    }

    public static GUIControllerAL getInstance() {
        if (instance == null) {
            instance = new GUIControllerAL();
        }
        return instance;
    }

    public void addView(View v) {
        if (v instanceof AreaLavoro) {
            areaLavoro = (AreaLavoro) v;
            areaLavoro.getLogoutBtn().addActionListener(new LogoutListener());
        } else if (v instanceof ScegliMsgPanel) {
            scegliMsgPanel = (ScegliMsgPanel) v;
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
}
