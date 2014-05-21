//Classe GUIControllerAL
//è un GUIController per l'area di lavoro della spia (parte UC2 del codice)
package cryptohelper.com;

import cryptohelper.GUI.areaLavoro.ScegliMsgPanel;
import cryptohelper.interfaces.View;

public class GUIControllerAL {

    private static GUIControllerAL instance;
    private COMController comC;

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
        if (v instanceof ScegliMsgPanel) {

        }
    }
}
