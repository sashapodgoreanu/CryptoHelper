//Pannello per le operazioni con il sistema di cifratura
package cryptohelper.GUI.UC1;

import cryptohelper.interfaces.View;
import cryptohelper.com.GUIControllerUC1;
import cryptohelper.data.Proposta;
import cryptohelper.data.SistemaCifratura;
import cryptohelper.data.UserInfo;
import cryptohelper.interfaces.VisitableGuiUC1;
import cryptohelper.interfaces.VisitorGuiUC1;
import java.awt.BorderLayout;
import static java.lang.System.gc;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SdcPanel extends JPanel implements View, VisitableGuiUC1 {

    private JButton proponiSDCBtn;
    private JButton gestisciSDCBtn;
    private JButton inboxProposteSDCBtn;
    private JButton creaSDCBtn;
    private JPanel topPanel;
    private JPanel centerSDCPanel;
    private CreaSDCPanel creasdc;
    private ProponiSDCPanel psdcp;
    private InboxSDCPanel isdcp;
    private GestisciSDCPanel gsdcp;

    public SdcPanel() {
        init();
        registerController();
    }

    public void init() {
        System.out.println("initCreateSDC");
        //creazione dei componenti
        proponiSDCBtn = new JButton("Proponi sistema di cifratura");
        inboxProposteSDCBtn = new JButton("Inbox");
        creaSDCBtn = new JButton("Crea Sistema di Cifratura");
        gestisciSDCBtn = new JButton("Sistemi di Cifratura accettati");
        topPanel = new JPanel();
        centerSDCPanel = new JPanel(new BorderLayout());
        this.setLayout(new BorderLayout());

        //aggiungi i bottoni
        topPanel.add(creaSDCBtn);
        topPanel.add(proponiSDCBtn);
        topPanel.add(inboxProposteSDCBtn);
        topPanel.add(gestisciSDCBtn);

        //aggiungi 2 panelli
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerSDCPanel, BorderLayout.CENTER);
        topPanel.setBorder(new EmptyBorder(0, 0, 20, 0));   //padding per separare i controlli
    }

    public void initCreateSDC() {
        System.out.println("initCreateSDC");
        //pulisci
        this.resetpanels();
        //crea nuovo panello 
        creasdc = new CreaSDCPanel();
        centerSDCPanel.add(creasdc);
        this.revalidate();
    }

    public void initProponiSDCPanel(ArrayList<UserInfo> destinatariArrLst, ArrayList<SistemaCifratura> sdc) {
        System.out.println("proponi sistema di cifratura");
        //pulisci
        this.resetpanels();
        //crea nuovo panello 
        psdcp = new ProponiSDCPanel(destinatariArrLst, sdc);
        centerSDCPanel.add(psdcp);
        this.revalidate();
    }

    public void initInboxSDCPanel(ArrayList<Proposta> proposteArrLst) {
        //   System.out.println("proponi sistema di cifratura");
        //pulisci
        this.resetpanels();
        //crea nuovo panello 
        isdcp = new InboxSDCPanel(proposteArrLst);
        centerSDCPanel.add(isdcp);
        this.revalidate();
    }

    public void initGestisciSDCPanel(ArrayList<Proposta> proposteArrLst) {
        //     System.out.println("proponi sistema di cifratura");
        //pulisci
        System.out.println("Init GestisciSDCPanel");
        this.resetpanels();
        //crea nuovo panello 
        gsdcp = new GestisciSDCPanel(proposteArrLst);
        centerSDCPanel.add(gsdcp);
        this.revalidate();
    }

    @Override
    public void removeAll() {
        super.removeAll();
        centerSDCPanel.removeAll();
        topPanel.removeAll();
    }

    public void resetpanels() {
        centerSDCPanel.removeAll();
        this.revalidate();
    }

    @Override
    public void registerController() {
        this.accept(GUIControllerUC1.getInstance());
    }

    @Override
    public void accept(VisitorGuiUC1 visitor) {
        visitor.visit(this);
    }

    //METODI GETTER
    public JButton getProponiSDCBtn() {
        return proponiSDCBtn;
    }

    public JButton getCreaSDCBtn() {
        return creaSDCBtn;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public JPanel getCenterSDCPanel() {
        return centerSDCPanel;
    }

    public JButton getGestisciSDCBtn() {
        return gestisciSDCBtn;
    }

    //METODI SETTER
    public void setProponiSDCBtn(JButton proponiSDCBtn) {
        this.proponiSDCBtn = proponiSDCBtn;
    }

    public JButton getInboxProposteSDCBtn() {
        return inboxProposteSDCBtn;
    }

    public void setInboxProposteSDCBtn(JButton inboxProposteSDCBtn) {
        this.inboxProposteSDCBtn = inboxProposteSDCBtn;
    }

    public void setCenterSDCPanel(JPanel centerSDCPanel) {
        this.centerSDCPanel = centerSDCPanel;
    }

    public void setCreaSDCBtn(JButton creaSDCBtn) {
        this.creaSDCBtn = creaSDCBtn;
    }

    public void setTopPanel(JPanel topPanel) {
        this.topPanel = topPanel;
    }

    public void setGestisciSDCBtn(JButton gestisciSDCBtn) {
        this.gestisciSDCBtn = gestisciSDCBtn;
    }

}
