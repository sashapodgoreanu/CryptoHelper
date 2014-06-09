//Interfaccia del pattern Visitor per la creazione delle GUI di UC1  viene implementata da GuiController1

package cryptohelper.interfaces;

import cryptohelper.GUI.UC1.BozzePanel;
import cryptohelper.GUI.UC1.CreaSDCPanel;
import cryptohelper.GUI.UC1.GestisciPropostePanel;
import cryptohelper.GUI.UC1.GestisciSDCPanel;
import cryptohelper.GUI.UC1.InboxPanel;
import cryptohelper.GUI.UC1.InboxSDCPanel;
import cryptohelper.GUI.UC1.LoginForm;
import cryptohelper.GUI.UC1.MessagePanel;
import cryptohelper.GUI.UC1.OutboxPanel;
import cryptohelper.GUI.UC1.PannelloPrincipale;
import cryptohelper.GUI.UC1.ProponiSDCPanel;
import cryptohelper.GUI.UC1.RegistrationForm;
import cryptohelper.GUI.UC1.SdcPanel;

public interface VisitorGuiUC1 {

    public void visit(LoginForm lf);

    public void visit(RegistrationForm rf);

    public void visit(PannelloPrincipale pp);

    public void visit(MessagePanel mp);

    public void visit(BozzePanel bp);

    public void visit(InboxPanel ip);

    public void visit(OutboxPanel op);

    public void visit(SdcPanel sdcp);

    public void visit(CreaSDCPanel csdcp);

    public void visit(GestisciPropostePanel gsdcp);
    
    public void visit(GestisciSDCPanel lf);

    public void visit(ProponiSDCPanel psdcp);

    public void visit(InboxSDCPanel isdcp);

}
