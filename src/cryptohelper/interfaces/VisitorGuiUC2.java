//Interfaccia del pattern Visitor per la creazione delle GUI di UC2 viene implementata da GuiController2

package cryptohelper.interfaces;

import cryptohelper.GUI.UC2.AreaLavoroPanel;
import cryptohelper.GUI.UC2.CaricaSessionePanel;
import cryptohelper.GUI.UC2.GestisciSoluzioniPanel;
import cryptohelper.GUI.UC2.IntercettaMsgPanel;
import cryptohelper.GUI.UC2.NuovaSessionePanel;

public interface VisitorGuiUC2 {

    public void visit(IntercettaMsgPanel imp);

    public void visit(NuovaSessionePanel nsp);

    public void visit(AreaLavoroPanel ap);

    public void visit(CaricaSessionePanel csp);

    public void visit(GestisciSoluzioniPanel gsp);
}
