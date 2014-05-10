//Pannello per le operazioni con il sistema di cifratura
package cryptohelper.GUI;

import cryptohelper.com.GUIController;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SdcPanel extends JPanel implements View {

    private JButton proponiSDCBtn;
    private JButton inboxProposteSDCBtn;
    private JButton proposteAccetateBtn;
    private JButton creaSDCBtn;
    private JPanel topPanel;
    private JPanel centerSDCPanel;
    private CreaSDCPanel creasdc;

    public SdcPanel() {
        init();
        registerController();
    }
    
    public void init() {
        //creazione dei componenti
        proponiSDCBtn = new JButton("Proponi Sistema Di Cifratura");
        inboxProposteSDCBtn = new JButton("Inbox");
        proposteAccetateBtn = new JButton("Proposte accettate");
        creaSDCBtn = new JButton("Crea Sistema di Cifratura");
        topPanel = new JPanel();
        centerSDCPanel = new JPanel(new BorderLayout());
        this.setLayout(new BorderLayout());
        
        //aggiungi i bottoni
        topPanel.add(creaSDCBtn);
        topPanel.add(proponiSDCBtn);
        topPanel.add(inboxProposteSDCBtn);
        topPanel.add(proposteAccetateBtn);

        //aggiungi 2 panelli
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerSDCPanel, BorderLayout.CENTER);
    }

    public void initCreateSDC(){
        System.out.println("initCreateSDC");
        //pulisci
        this.resetpanels();
        //crea nuovo panello 
        creasdc = new CreaSDCPanel();
        centerSDCPanel.add(creasdc);
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
        GUIController gc = GUIController.getInstance();
        gc.addView(this);
    }

    public JButton getProponiSDCBtn() {
        return proponiSDCBtn;
    }

    public void setProponiSDCBtn(JButton proponiSDCBtn) {
        this.proponiSDCBtn = proponiSDCBtn;
    }

    public JButton getInboxProposteSDCBtn() {
        return inboxProposteSDCBtn;
    }

    public void setInboxProposteSDCBtn(JButton inboxProposteSDCBtn) {
        this.inboxProposteSDCBtn = inboxProposteSDCBtn;
    }

    public JButton getProposteAccetateBtn() {
        return proposteAccetateBtn;
    }

    public void setProposteAccetateBtn(JButton proposteAccetateBtn) {
        this.proposteAccetateBtn = proposteAccetateBtn;
    }

    public JButton getCreaSDCBtn() {
        return creaSDCBtn;
    }

    public void setCreaSDCBtn(JButton creaSDCBtn) {
        this.creaSDCBtn = creaSDCBtn;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(JPanel topPanel) {
        this.topPanel = topPanel;
    }

    public JPanel getCenterSDCPanel() {
        return centerSDCPanel;
    }

    public void setCenterSDCPanel(JPanel centerSDCPanel) {
        this.centerSDCPanel = centerSDCPanel;
    }
}
