package cryptohelper.com;

import cryptohelper.GUI.BozzePanel;
import cryptohelper.GUI.CreaSDCPanel;
import cryptohelper.GUI.InboxPanel;
import cryptohelper.GUI.InboxSDCPanel;
import cryptohelper.GUI.LoginForm;
import cryptohelper.GUI.MessagePanel;
import cryptohelper.GUI.OutboxPanel;
import cryptohelper.GUI.PanelloPrincipale;
import cryptohelper.GUI.ProponiSDCPanel;
import cryptohelper.GUI.RegistrationForm;
import cryptohelper.GUI.SdcPanel;
import cryptohelper.data.Cifratore;
import cryptohelper.interfaces.View;
import cryptohelper.data.Mappatura;
import cryptohelper.data.Messaggio;
import cryptohelper.interfaces.MessaggioMittente;
import cryptohelper.interfaces.MessaggioDestinatario;
import cryptohelper.data.HtmlVisitor;
import cryptohelper.data.Proposta;
import cryptohelper.data.SistemaCifratura;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Il controllore Deve conoscere: i modelli e le viste - JFrame's
 *
 * La vista Deve conoscere: i controllori e qualche modello
 *
 * Modello non deve conoscere nessuno dei due.
 *
 */
public class GUIController {

    private COMController comC;
    private LoginForm loginForm;
    private RegistrationForm regForm;
    private PanelloPrincipale panelloPrincipale;
    private SdcPanel sdcPanel;
    private MessagePanel messagePanel;
    private BozzePanel bozzePanel;
    private InboxPanel inboxPanel;
    private OutboxPanel outboxPanel;
    private CreaSDCPanel creaSDCPanel;
    private static GUIController instance;
    private Messaggio msgMittente;
    private UserInfo utilizzatoreSistema;
    private SistemaCifratura sistemaCifratura;
    private Mappatura mappatura;
    private ProponiSDCPanel proponiSDCPanel;
    private InboxSDCPanel inboxSDCPanel;

    private GUIController() {
        comC = new COMController();
    }

    public static GUIController getInstance() {
        if (instance == null) {
            instance = new GUIController();
        }
        return instance;
    }

    public void addView(View v) {
        if (v instanceof LoginForm) {
            loginForm = (LoginForm) v;
            //solo per il test
            loginForm.setUsernameField("sasha");
            loginForm.setPasswordField("1234");
            loginForm.getSubmit().addActionListener(new LoginFormListener());
            loginForm.getRegistration().addActionListener(new RegistrationFormListener());
        } else if (v instanceof RegistrationForm) {
            regForm = (RegistrationForm) v;
            regForm.getCancelBtn().addActionListener(new CancelListener());
            regForm.getSubmitBtn().addActionListener(new RegisterListener());
        } else if (v instanceof InboxPanel) {
            inboxPanel = (InboxPanel) v;
            inboxPanel.getElencoMessaggiRicevuti().addListSelectionListener(new ViewInboxMsgListener());
        } else if (v instanceof OutboxPanel) {
            outboxPanel = (OutboxPanel) v;
            outboxPanel.getElencoMessaggiInviati().addListSelectionListener(new ViewInboxMsgListener());
        } else if (v instanceof PanelloPrincipale) {
            panelloPrincipale = (PanelloPrincipale) v;
            panelloPrincipale.getNuovoMessaggioBtn().addActionListener(new NuovoMessaggioListener());
            panelloPrincipale.getInboxBtn().addActionListener(new GestisciInbox());
            panelloPrincipale.getOutboxBtn().addActionListener(new GestisciOutbox());
            panelloPrincipale.getLogoutBtn().addActionListener(new LogoutListener());
            panelloPrincipale.getGestisciBozzeBtn().addActionListener(new GestisciBozzeListener());
            panelloPrincipale.getSDCBtn().addActionListener(new GestisciSDC());
        } else if (v instanceof BozzePanel) {
            bozzePanel = (BozzePanel) v;
            //TO-DO bozzePanel.getSaveBozzaBtn().addActionListener(new SalvaInviaMessaggioListener());
            bozzePanel.getDeleteBozzaBtn().addActionListener(new ElimnaBozzaListener());
            bozzePanel.getElencoBozze().addListSelectionListener(new ViewBozzeMsgListener());
        } else if (v instanceof SdcPanel) {
            sdcPanel = (SdcPanel) v;
            sdcPanel.getCreaSDCBtn().addActionListener(new CreateSDCListener());
            sdcPanel.getProponiSDCBtn().addActionListener(new ProponiSDCListener());
            sdcPanel.getInboxProposteSDCBtn().addActionListener(new InboxSDCListener());
        } else if (v instanceof CreaSDCPanel) {
            creaSDCPanel = (CreaSDCPanel) v;
            creaSDCPanel.getCesareRBtn().addActionListener(new MetodoDicifraturaListener());
            creaSDCPanel.getParolaChiaveRBtn().addActionListener(new MetodoDicifraturaListener());
            creaSDCPanel.getPseudocasualeRBtn().addActionListener(new MetodoDicifraturaListener());
            creaSDCPanel.getSalvaSdcBtn().addActionListener(new SalvaMetodoDicifraturaListener());
            creaSDCPanel.getProvasdcBtn().addActionListener(new ProvaMetodoDicifraturaListener());
        } else if (v instanceof MessagePanel) {
            messagePanel = (MessagePanel) v;
            messagePanel.getSalvaBozzaBtn().addActionListener(new SalvaInviaMessaggioListener());
            messagePanel.getInviaMessageBtn().addActionListener(new SalvaInviaMessaggioListener());
            messagePanel.getElencoDestinatari().addListSelectionListener(new SelectDestinatarioListener());

        } else if (v instanceof ProponiSDCPanel) {
            proponiSDCPanel = (ProponiSDCPanel) v;
            proponiSDCPanel.getProponiSDCBtn().addActionListener(new SendProponiSDCListener());
        } else if (v instanceof InboxSDCPanel) {
            inboxSDCPanel = (InboxSDCPanel) v;
            inboxSDCPanel.getElencoProposteRicevute().addListSelectionListener(new ViewProponiSDCListener());
            inboxSDCPanel.getAccettaBtn().addActionListener(new AccettaRifiutaSDCListener());
            inboxSDCPanel.getRifiutaBtn().addActionListener(new AccettaRifiutaSDCListener());

        }
    }

//classe listener per il login
    private class LoginFormListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean valid = comC.authenticate(loginForm.getUsername(), loginForm.getPassword());
            if (valid) {
                loginForm.dispose();
                PanelloPrincipale pp = new PanelloPrincipale();
                utilizzatoreSistema = new UserInfo(comC.getStudente().getId(), comC.getStudente().getNome(), comC.getStudente().getCognome());
            } else {
                loginForm.getErrorLoginLabel().setText("Errore di autenticazione");
            }
        }
    }

//classe listener per il button "nuovo messaggio" della finestra principale
    private class NuovoMessaggioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " Clicked " + ev.getText());
            //TO-DO da modificare perche devono apparire solo destinatari con cui il studente ha concluso una proposta Scifratura
            panelloPrincipale.setDestinatariArrLst(comC.getDestinatari(utilizzatoreSistema));
            System.out.println(comC.getDestinatari().toString());
            panelloPrincipale.initNuovoMessaggio();
            //predispone il nuovo messaggio
            msgMittente = new Messaggio();
        }
    }

//classe listener per il button Inbox della finestra principale
    private class GestisciInbox implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            System.out.println("IL MIO ID" +utilizzatoreSistema.getId() );
            ArrayList<MessaggioDestinatario> temp = Messaggio.caricaMessaggiDestinatario(utilizzatoreSistema.getId());
            System.out.println("MessaggioDestinatario"+temp.toString());
            panelloPrincipale.initInbox(temp);
        }
    }

    //classe listener per il button Inbox della finestra principale
    private class GestisciOutbox implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            //ArrayList<MessaggioMittente> temp = Messaggio.caricaMessaggiDestinatario(utilizzatoreSistema.getId());
            //pp.initOutbox(temp);
        }
    }

    //classe listener per la Jlist "ElencoMessaggiRicevuti" 
    private class ViewInboxMsgListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            panelloPrincipale.setStatus(" ");
            System.out.println("Clicked LIST");
            MessaggioDestinatario mess = (MessaggioDestinatario) inboxPanel.getElencoMessaggiRicevuti().getSelectedValue();
            System.out.println(mess.toString());
            inboxPanel.getCorpoMessaggio().setText((new HtmlVisitor().visit(mess)));
        }
    }

    //classe listener per la Jlist "ElencoBozze" 
    private class ViewBozzeMsgListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            panelloPrincipale.setStatus(" ");
            System.out.println("Clicked LIST");
            MessaggioDestinatario mess = (MessaggioDestinatario) bozzePanel.getElencoBozze().getSelectedValue();
            //bp.modificaCorpoMessaggio("Destinatario: " + mess.getDestinatario().getNome() + "\n" + mess.getTesto());
            bozzePanel.setTitoloBozza(mess.getTitolo());
        }
    }

//classe listener per il button "bozze" della finestra principale 
    private class GestisciBozzeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            ArrayList<MessaggioMittente> temp = Messaggio.caricaBozze(utilizzatoreSistema.getId());
            panelloPrincipale.initGestioneBozze(temp);
        }
    }
//classe listener per il button "bozze" della finestra principale 

    private class ElimnaBozzaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatus(" ");
            MessaggioMittente mess = (MessaggioMittente) bozzePanel.getElencoBozze().getSelectedValue();
            mess.elimina();
            bozzePanel.deleteSelectedIndex();
        }
    }

//classe listener per il button "elimina bozza" del pannello bozze
    private class GestisciSDC implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            panelloPrincipale.initSDC();
        }
    }

    private class CreateSDCListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " Clicked " + ev.getText());
            sdcPanel.initCreateSDC();
        }
    }

//classe listener per JRadioButtons per selezionare il metodo di cifratura
    private class MetodoDicifraturaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatus(" ");
            JRadioButton ev = (JRadioButton) e.getSource();
            System.out.println(this.getClass() + " selected " + ev.getText());
            if (ev.getText().equalsIgnoreCase("parola chiave")) {
                creaSDCPanel.initParolaChiave();
            } else if (ev.getText().equalsIgnoreCase("cesare")) {
                creaSDCPanel.initCesare();
            }
            //crea sistema di cifratura
            sistemaCifratura = new SistemaCifratura(utilizzatoreSistema);
        }
    }

//classe listener per il button "salva messaggio" della finestra principale
    private class SalvaInviaMessaggioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatus(" ");
            //un messaggio senza titolo non si puo salvare
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " Clicked " + ev.getText());
            if (ev.getText().equalsIgnoreCase("Salva")) {
                if (messagePanel.getSelectedDestinatario() != null) {
                    System.out.println(messagePanel.getTitoloMessaggioField() + " - Tittolo del messaggio");
                    //se il tittolo del messaggio e vuouto mostra un  messaggio di errore
                    String temp = messagePanel.getTitoloMessaggioField().replaceAll("\\s+", "");
                    if (temp.equals("")) {
                        panelloPrincipale.setStatus("Il titolo del messaggio deve contenere almeno un carattere");
                    } else { //altrimenti salva il messaggio
                        panelloPrincipale.setStatus("");
                        JList list = messagePanel.getElencoDestinatari();
                        UserInfo destinatario = (UserInfo) list.getSelectedValue();
                        System.out.println("Destinatario selected: " + destinatario.toString());
                        msgMittente = new Messaggio(msgMittente.getId(), messagePanel.getCorpoMessaggio(), messagePanel.getTitoloMessaggioField(), true, utilizzatoreSistema, destinatario);
                        //se msg.salva ritorna false allora errore
                        if (msgMittente.salva()) {
                            panelloPrincipale.setStatus("Messaggio Salvato!");
                        } else {
                            panelloPrincipale.setStatus("Si è verificato un errore durante il salvataggio del messaggio!");
                        }
                    }
                } else {
                    panelloPrincipale.setStatus("Devi selezionare un destinatario");
                }
            } else if (ev.getText().equalsIgnoreCase("invia")) {
                String temp = messagePanel.getTitoloMessaggioField().replaceAll("\\s+", "");
                if (temp.equals("")) {
                    panelloPrincipale.setStatus("Il titolo del messaggio deve contenere almeno un carattere");
                } else { //altrimenti invia il messaggio
                    System.out.println("INVIA MESSAGGIO");
                    panelloPrincipale.setStatus("");
                    UserInfo destinatario = (UserInfo) messagePanel.getElencoDestinatari().getSelectedValue();
                    SistemaCifratura sdc = SistemaCifratura.load(utilizzatoreSistema, destinatario);

                    String testoCifrato = Cifratore.cifraMonoalfabetica(sdc.getMp(), messagePanel.getCorpoMessaggio());
                    //Messaggio( String titolo, boolean bozza, boolean letto)
                    msgMittente = new Messaggio(msgMittente.getId(),//id
                            messagePanel.getCorpoMessaggio(),//testo in chiaro
                            testoCifrato,//testo cifrato
                            (String) messagePanel.getLinguaDropdown().getSelectedItem(), //lingua
                            messagePanel.getTitoloMessaggioField(),// titolo messaggio
                            false,// isBooza
                            false,//isLeto
                            utilizzatoreSistema,//mittente
                            destinatario);//destinatario
                    //se msg.salva ritorna false allora errore
                    if (msgMittente.salva()) {
                        panelloPrincipale.setStatus("Messaggio Inviato!");
                    } else {
                        panelloPrincipale.setStatus("Si è verificato un errore durante il invio del messaggio!");
                    }
                }

            }
        }
    }

    //classe listener per la Jlist "elencoProposte" 
    private class SelectDestinatarioListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            panelloPrincipale.setStatus(" ");
            System.out.println("Clicked LIST");
            UserInfo userInfo = (UserInfo) messagePanel.getElencoDestinatari().getSelectedValue();
            SistemaCifratura sdc = SistemaCifratura.load(utilizzatoreSistema, userInfo);
            System.out.println(sdc.toString());
            //messagePanel.initChiave(sdc.getMetodo());
        }
    }

//classe listener per salvare SDC - ascolta il bottone salva
    private class SalvaMetodoDicifraturaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatus("");
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " selected " + ev.getText());
            if (ev.getText().equalsIgnoreCase("Salva cifrario parola chiave")) {
                String metodo = "parola chiave";
                if (creaSDCPanel.getTable().isEditing()) {
                    creaSDCPanel.getTable().getCellEditor().stopCellEditing();
                }
                String chiave = "";
                for (int i = 0; i < 26; i++) {
                    chiave = chiave + "" + creaSDCPanel.getData(0, i);
                }
                if (sistemaCifratura.valid(metodo, chiave)) {
                    mappatura = sistemaCifratura.create(metodo, chiave);
                    sistemaCifratura.setNome(creaSDCPanel.getNomeCifraturaField().getText());
                    if (sistemaCifratura.salva()) {
                        panelloPrincipale.setStatus("Salvato con sucesso");
                    } else {
                        panelloPrincipale.setStatus("E stato un errore! non salvato");
                    }
                } else {
                    panelloPrincipale.setStatus("La mappatura non è coretta o contiene caratteri illegali - sono accetate solo lettere");
                }
            }
        }
    }

//classe listener per provare SDC - ascolta il bottone prova sdc
    private class ProvaMetodoDicifraturaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatus("");
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " selected " + ev.getText());
            sistemaCifratura = new SistemaCifratura(utilizzatoreSistema);
            String metodo = "parola chiave";
            if (creaSDCPanel.getTable().isEditing()) {
                creaSDCPanel.getTable().getCellEditor().stopCellEditing();
            }
            String chiave = "";
            for (int i = 0; i < 26; i++) {
                chiave = chiave + "" + creaSDCPanel.getData(0, i);
            }
            if (sistemaCifratura.valid(metodo, chiave)) {
                mappatura = sistemaCifratura.create(metodo, chiave);
                String a = creaSDCPanel.getCorpoMessaggioProva().getText();
                creaSDCPanel.getCorpoMessaggioResult().setText(sistemaCifratura.prova(a));
            } else {
                panelloPrincipale.setStatus("La mappatura non è coretta o contiene caratteri illegali - sono accetate solo lettere");
            }

        }
    }

    //classe listener per inizializare il panello proponi sistema di cifratura
    private class ProponiSDCListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " selected " + ev.getText());
            sdcPanel.initProponiSDCPanel(comC.getDestinatari(), SistemaCifratura.caricaSistemiCifratura(utilizzatoreSistema));
        }
    }

    //classe listener per inviare una proposta di sistema di cifratura
    private class SendProponiSDCListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " selected " + ev.getText());
            SistemaCifratura sdc = (SistemaCifratura) proponiSDCPanel.getElencoSDC().getSelectedValue();
            UserInfo partner = (UserInfo) proponiSDCPanel.getElencoDestinatari().getSelectedValue();
            if (comC.proponiSistemaCifratura(utilizzatoreSistema, partner, sdc)) {
                panelloPrincipale.setStatus("Inviato con successo");
            } else {
                panelloPrincipale.setStatus("Proposta duplicata o errore di sistema!");
            }
        }
    }

    //classe listener per visualizare il panello con le proposte inbox sdc
    private class InboxSDCListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            sdcPanel.initInboxSDCPanel(Proposta.caricaProposteSistemiCifratura(utilizzatoreSistema));

        }
    }

    //classe listener per visualizare il panello con le proposte inbox sdc
    private class AccettaRifiutaSDCListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(" AccettaRifiutaSDCListener ");
            panelloPrincipale.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            Proposta proposta = (Proposta) inboxSDCPanel.getElencoProposteRicevute().getSelectedValue();
            if (ev.getText().equalsIgnoreCase("accetta") && proposta != null) {
                proposta.setStato("accettata");
                proposta.salva();
            } else if (ev.getText().equalsIgnoreCase("rifiuta") && proposta != null) {
                proposta.setStato("rifiutata");
                proposta.salva();
                inboxSDCPanel.getInfoSdcLabel().setText("");
                inboxSDCPanel.deleteSelectedIndex();
                //panelloPrincipale.setStatus("Non si puo cancelare");

            } else {
                panelloPrincipale.setStatus("Seleziona una proposta!");
            }
        }
    }

    //classe listener per la Jlist "elencoProposte" 
    private class ViewProponiSDCListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            panelloPrincipale.setStatus(" ");
            System.out.println("Clicked LIST");
            Proposta proposta = (Proposta) inboxSDCPanel.getElencoProposteRicevute().getSelectedValue();
            inboxSDCPanel.getInfoSdcLabel().setText((new HtmlVisitor().visit(proposta)));
        }
    }

//classe listener per il button "salva messaggio" della finestra principale
    private class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.dispose();
            LoginForm f = new LoginForm();
            System.out.println(this.getClass() + " Messaggio: Logout");
        }
    }

//classe listener per la reistrazione dell'utente
    private class RegistrationFormListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loginForm.dispose();
            RegistrationForm rf = new RegistrationForm();
        }
    }

//classe listener per il button "annulla" della finestra registrazione utente
    private class CancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            regForm.dispose();
            LoginForm f = new LoginForm();
            System.out.println(this.getClass() + "Registration: cancel");
        }
    }

//classe listener per il button "OK" della finestra registrazione utente
    private class RegisterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Studente s = new Studente(regForm.getNameField(), regForm.getSurnameField(),
                    regForm.getNicknameField(), regForm.getPasswordField());
            s.salva();
            regForm.dispose();
            LoginForm f = new LoginForm();
            System.out.println(this.getClass() + "Registration: OK");
        }
    }

}
