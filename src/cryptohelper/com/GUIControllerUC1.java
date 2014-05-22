package cryptohelper.com;

import cryptohelper.GUI.BozzePanel;
import cryptohelper.GUI.CreaSDCPanel;
import cryptohelper.GUI.InboxPanel;
import cryptohelper.GUI.InboxSDCPanel;
import cryptohelper.GUI.GestisciSDCPanel;
import cryptohelper.GUI.LoginForm;
import cryptohelper.GUI.MessagePanel;
import cryptohelper.GUI.OutboxPanel;
import cryptohelper.GUI.PanelloPrincipale;
import cryptohelper.GUI.ProponiSDCPanel;
import cryptohelper.GUI.RegistrationForm;
import cryptohelper.GUI.SdcPanel;
import cryptohelper.GUI.UC2.IntercettaMessaggioPanel;
import cryptohelper.interfaces.View;
import cryptohelper.data.Mappatura;
import cryptohelper.data.Messaggio;
import cryptohelper.interfaces.MessaggioMittente;
import cryptohelper.data.HtmlVisitor;
import cryptohelper.data.Proposta;
import cryptohelper.data.SistemaCifratura;
import cryptohelper.data.Studente;
import cryptohelper.data.UserInfo;
import cryptohelper.interfaces.MessaggioDestinatario;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Il controllore Deve conoscere: i modelli e le viste - JFrame's La vista Deve
 * conoscere: i controllori e qualche modello Modello non deve conoscere nessuno
 * dei due.
 */
public class GUIControllerUC1 {

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
    private static GUIControllerUC1 instance;
    private Messaggio msgMittente;
    private UserInfo utilizzatoreSistema;
    private SistemaCifratura sistemaCifratura;
    private Mappatura mappatura;
    private ProponiSDCPanel proponiSDCPanel;
    private InboxSDCPanel inboxSDCPanel;
    private GestisciSDCPanel gestisciSDCPanel;

    private GUIControllerUC1() {
        comC = COMController.getInstance();
    }

    public static GUIControllerUC1 getInstance() {
        if (instance == null) {
            instance = new GUIControllerUC1();
        }
        return instance;
    }

    //registra i pannelli e i loro actionListener
    public void addView(View v) {
        if (v instanceof LoginForm) {
            loginForm = (LoginForm) v;
            //solo per il test
            loginForm.setUsernameField("sasha");
            loginForm.setPasswordField("1234");
            /**/
            loginForm.getSubmit().addActionListener(new LoginFormListener());
            loginForm.getRegistration().addActionListener(new RegistrationFormListener());
        } else if (v instanceof RegistrationForm) {
            regForm = (RegistrationForm) v;
            regForm.getCancelBtn().addActionListener(new CancelListener());
            regForm.getSubmitBtn().addActionListener(new RegisterListener());
        } else if (v instanceof InboxPanel) {
            inboxPanel = (InboxPanel) v;
            inboxPanel.getElencoMessaggiRicevuti().addListSelectionListener(new ViewInboxMsgListener());
            inboxPanel.getEliminaMessaggioBtn().addActionListener(new EliminaInboxMsgListener());
        } else if (v instanceof OutboxPanel) {
            outboxPanel = (OutboxPanel) v;
            outboxPanel.getElencoMessaggiInviati().addListSelectionListener(new ViewOutboxMsgListener());
            outboxPanel.getEliminaMessaggioBtn().addActionListener(new EliminaOutboxMsgListener());
        } else if (v instanceof PanelloPrincipale) {
            panelloPrincipale = (PanelloPrincipale) v;
            panelloPrincipale.getNuovoMessaggioBtn().addActionListener(new NuovoMessaggioListener());
            panelloPrincipale.getInboxBtn().addActionListener(new GestisciInbox());
            panelloPrincipale.getOutboxBtn().addActionListener(new GestisciOutbox());
            panelloPrincipale.getGestisciBozzeBtn().addActionListener(new GestisciBozzeListener());
            panelloPrincipale.getSDCBtn().addActionListener(new GestisciSDC());
            panelloPrincipale.getLogoutBtn().addActionListener(new LogoutListener());
            panelloPrincipale.getIntercettaBtn().addActionListener(new IntercettaBtnListener());
        } else if (v instanceof BozzePanel) {
            bozzePanel = (BozzePanel) v;
            bozzePanel.getSaveBozzaBtn().addActionListener(new SalvaInviaMessaggioListener());
            bozzePanel.getDeleteBozzaBtn().addActionListener(new ElimnaBozzaListener());
            bozzePanel.getSendBozzaBtn().addActionListener(new SalvaInviaMessaggioListener());
            bozzePanel.getElencoBozze().addListSelectionListener(new ViewBozzeMsgListener());
        } else if (v instanceof SdcPanel) {
            sdcPanel = (SdcPanel) v;
            sdcPanel.getCreaSDCBtn().addActionListener(new CreateSDCListener());
            sdcPanel.getGestisciSDCBtn().addActionListener(new GestisciSDCListener());
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
        } else if (v instanceof GestisciSDCPanel) {
            gestisciSDCPanel = (GestisciSDCPanel) v;
            gestisciSDCPanel.getEliminaBtn().addActionListener(new EliminaSDCListener());
        }
    }

//classe listener per il login
    private class LoginFormListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean valid = comC.authenticate(loginForm.getUsername(), loginForm.getPassword());
            if (valid) {
                loginForm.dispose();
                utilizzatoreSistema = new UserInfo(comC.getStudente().getId(), comC.getStudente().getNome(), comC.getStudente().getCognome());
                PanelloPrincipale pp = new PanelloPrincipale();
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
            panelloPrincipale.setDestinatariArrLst(comC.getDestinatari(utilizzatoreSistema));
            System.out.println("**************************" + comC.getDestinatari(utilizzatoreSistema).toString());
            panelloPrincipale.initNuovoMessaggio();
            msgMittente = new Messaggio();      //predispone il nuovo messaggio

        }
    }

//classe listener per il button Inbox della finestra principale
    private class GestisciInbox implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            ArrayList<MessaggioDestinatario> temp = Messaggio.caricaMessaggiDestinatario(utilizzatoreSistema.getId());
            System.out.println("MessaggioDestinatario" + temp.toString());
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
            ArrayList<MessaggioMittente> temp = Messaggio.caricaMessaggiInviati(utilizzatoreSistema.getId());
            panelloPrincipale.initOutbox(temp);
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

    private class GestisciSDCListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            sdcPanel.initGestisciSDCPanel(Proposta.caricaProposteSistemiCifratura(utilizzatoreSistema));
        }
    }

    //classe listener per il button "logout" della finestra principale
    private class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.dispose();
            LoginForm f = new LoginForm();
            System.out.println(this.getClass() + "Logout eseguito");
        }
    }

    //classe listener per il button "intercetta un messaggio" della finestra principale
    private class IntercettaBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            ArrayList<Messaggio> msgArrLst = Messaggio.caricaMessaggi();
            IntercettaMessaggioPanel al = new IntercettaMessaggioPanel();
            panelloPrincipale.dispose();
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

    //classe listener per il button "elimina" del pannello outbox 
    private class EliminaInboxMsgListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MessaggioMittente mess = (MessaggioMittente) inboxPanel.getElencoMessaggiRicevuti().getSelectedValue();
            mess.elimina();
            inboxPanel.deleteSelectedIndex();
            panelloPrincipale.setStatus("Messaggio eliminato correttamente!");
        }
    }

    //classe listener per la Jlist della outbox 
    private class ViewOutboxMsgListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            panelloPrincipale.setStatus(" ");
            System.out.println("Clicked LIST");
            MessaggioMittente outboxMsg = (MessaggioMittente) outboxPanel.getElencoMessaggiInviati().getSelectedValue();
            outboxPanel.getCorpoMessaggio().setText((new HtmlVisitor().visit(outboxMsg)));
        }
    }

    //classe listener per il button "elimina" del pannello outbox 
    private class EliminaOutboxMsgListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MessaggioMittente mess = (MessaggioMittente) outboxPanel.getElencoMessaggiInviati().getSelectedValue();
            mess.elimina();
            outboxPanel.deleteSelectedIndex();
            panelloPrincipale.setStatus("Messaggio eliminato correttamente!");
        }
    }

    //classe listener per la Jlist "ElencoBozze" 
    private class ViewBozzeMsgListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            panelloPrincipale.setStatus(" ");
            System.out.println("Clicked LIST");
            MessaggioMittente mess = (MessaggioMittente) bozzePanel.getElencoBozze().getSelectedValue();
            bozzePanel.getCorpoBozza().setText(mess.getTesto());
            bozzePanel.setTitoloBozza(mess.getTitolo());
            bozzePanel.setDestinatario(mess.getDestinatario().getNome() + " " + mess.getDestinatario().getCognome());
            bozzePanel.getLinguaDropdown().setSelectedItem(mess.getLingua());
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
            panelloPrincipale.setStatus("Bozza eliminata correttamente!");
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
            if (ev.getText().equalsIgnoreCase("Salva come bozza")) {
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
                        System.out.println(this.getClass() + "SalvaInviaMessaggioListener:  Destinatario selected: " + destinatario.toString());
                        SistemaCifratura sdc = SistemaCifratura.load(utilizzatoreSistema.getId(), destinatario.getId());
                        //Messaggio( String titolo, boolean bozza, boolean letto)
                        msgMittente = new Messaggio(msgMittente.getId(),//id
                                messagePanel.getCorpoMessaggio(),//testo in chiaro
                                (String) messagePanel.getLinguaDropdown().getSelectedItem(), //lingua
                                messagePanel.getTitoloMessaggioField(),// titolo messaggio
                                true,// isBozza
                                false,//isLetto
                                utilizzatoreSistema,//mittente
                                destinatario,//destinatario
                                sdc//Sistema cifratura
                        );
                        msgMittente.cifra();
                        //se msg.salva ritorna false allora c'è un errore
                        if (msgMittente.salva()) {
                            panelloPrincipale.setStatus("Messaggio Salvato!");
                        } else {
                            panelloPrincipale.setStatus("Si è verificato un errore durante il salvataggio del messaggio!");
                        }
                    }
                } else {
                    panelloPrincipale.setStatus("Devi selezionare un destinatario");
                }
            } else if (ev.getText().equalsIgnoreCase("Invia")) {
                String temp = messagePanel.getTitoloMessaggioField().replaceAll("\\s+", "");
                if (temp.equals("")) {
                    panelloPrincipale.setStatus("Il titolo del messaggio deve contenere almeno un carattere");
                } else { //altrimenti invia il messaggio
                    System.out.println("INVIA MESSAGGIO");
                    panelloPrincipale.setStatus("");
                    UserInfo destinatario = (UserInfo) messagePanel.getElencoDestinatari().getSelectedValue();
                    if (destinatario == null) {
                        panelloPrincipale.setStatus("Devi selezionare un destinatario");
                        return;
                    }
                    SistemaCifratura sdc = SistemaCifratura.load(utilizzatoreSistema.getId(), destinatario.getId());
                    System.out.println(this.getClass() + " SalvaInviaMessaggioListener:  SistemaCifratura: " + sdc);
                    //Messaggio( String titolo, boolean bozza, boolean letto)
                    msgMittente = new Messaggio(msgMittente.getId(),//id
                            messagePanel.getCorpoMessaggio(),//testo in chiaro
                            (String) messagePanel.getLinguaDropdown().getSelectedItem(), //lingua
                            messagePanel.getTitoloMessaggioField(),// titolo messaggio
                            false,// isBozza
                            false,//isLetto
                            utilizzatoreSistema,//mittente
                            destinatario,//destinatario
                            sdc//Sistema cifratura
                    );
                    msgMittente.cifra();
                    //se msg.salva ritorna false allora errore
                    if (msgMittente.salva()) {
                        panelloPrincipale.setStatus("Messaggio Inviato!");
                        messagePanel.getSalvaBozzaBtn().setEnabled(false);
                    } else {
                        panelloPrincipale.setStatus("Si è verificato un errore durante il invio del messaggio!");
                    }
                }
            }
            if (ev.getText().equalsIgnoreCase("Salva")) {
                //se il tittolo del messaggio e vuouto mostra un  messaggio di errore
                if (bozzePanel.getTitoloBozza().equals("")) {
                    panelloPrincipale.setStatus("Il titolo del messaggio deve contenere almeno un carattere");
                } else { //altrimenti salva il messaggio
                    panelloPrincipale.setStatus("");
                    Messaggio m = ((Messaggio) bozzePanel.getElencoBozze().getSelectedValue());

                    SistemaCifratura sdc = SistemaCifratura.load(utilizzatoreSistema.getId(), m.getDestinatario().getId());
                    //Messaggio( String titolo, boolean bozza, boolean letto)
                    msgMittente = new Messaggio(msgMittente.getId(),//id
                            messagePanel.getCorpoMessaggio(),//testo in chiaro
                            (String) messagePanel.getLinguaDropdown().getSelectedItem(), //lingua
                            messagePanel.getTitoloMessaggioField(),// titolo messaggio
                            false,// isBozza
                            false,//isLetto
                            utilizzatoreSistema,//mittente
                            m.getDestinatario(),//destinatario
                            sdc//Sistema cifratura
                    );
                    msgMittente.cifra();
                    //se msg.salva ritorna false allora c'è un errore
                    if (msgMittente.salva()) {
                        panelloPrincipale.setStatus("Messaggio Salvato!");
                    } else {
                        panelloPrincipale.setStatus("Si è verificato un errore durante il salvataggio del messaggio!");
                    }
                }
            } else if (ev.getText().equalsIgnoreCase("Invia bozza")) {
                String temp = bozzePanel.getTitoloBozza().replaceAll("\\s+", "");
                if (temp.equals("")) {
                    panelloPrincipale.setStatus("Il titolo del messaggio deve contenere almeno un carattere");
                } else { //altrimenti invia il messaggio
                    System.out.println("INVIA MESSAGGIO");
                    panelloPrincipale.setStatus("");
                    Messaggio m = ((Messaggio) bozzePanel.getElencoBozze().getSelectedValue());
                    SistemaCifratura sdc = SistemaCifratura.load(utilizzatoreSistema.getId(), m.getDestinatario().getId());
                    //Messaggio( String titolo, boolean bozza, boolean letto)
                    msgMittente = new Messaggio(msgMittente.getId(),//id
                            messagePanel.getCorpoMessaggio(),//testo in chiaro
                            (String) messagePanel.getLinguaDropdown().getSelectedItem(), //lingua
                            messagePanel.getTitoloMessaggioField(),// titolo messaggio
                            false,// isBozza
                            false,//isLetto
                            utilizzatoreSistema,//mittente
                            m.getDestinatario(),//destinatario
                            sdc//Sistema cifratura
                    );
                    msgMittente.cifra();
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
            SistemaCifratura sdc = SistemaCifratura.load(utilizzatoreSistema.getId(), userInfo.getId());
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
            String metodo = creaSDCPanel.getMetodo();
            if (metodo.equals("parola chiave")) {
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
            } else if (metodo.equals("cifrario cesare")) {
                String chiave = creaSDCPanel.getChiave().getText();
                if (sistemaCifratura.valid(metodo, chiave)) {
                    mappatura = sistemaCifratura.create(metodo, chiave);//crea la mappatura data una chiave e un metodo
                    sistemaCifratura.setNome(creaSDCPanel.getNomeCifraturaField().getText());//nome della cifratura per salvare in db
                    if (sistemaCifratura.salva()) {
                        panelloPrincipale.setStatus("Salvato con sucesso");
                    } else {
                        panelloPrincipale.setStatus("E stato un errore! non salvato");
                    }
                } else {
                    panelloPrincipale.setStatus("La chiave non è coretta o contiene caratteri illegali - deve essere un numero");
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
            String metodo = creaSDCPanel.getMetodo();
            if (metodo.equals("parola chiave")) {
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
            } else if (metodo.equals("cifrario cesare")) {
                String chiave = creaSDCPanel.getChiave().getText();
                if (sistemaCifratura.valid(metodo, chiave)) {
                    mappatura = sistemaCifratura.create(metodo, chiave);
                    String a = creaSDCPanel.getCorpoMessaggioProva().getText();
                    creaSDCPanel.getCorpoMessaggioResult().setText(sistemaCifratura.prova(a));
                } else {
                    panelloPrincipale.setStatus("La chiave non è coretta o contiene caratteri illegali - deve essere un numero");
                }
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
                panelloPrincipale.setStatus("Proposa inviata con successo!");
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
            sdcPanel.initInboxSDCPanel(Proposta.caricaProposteSistemiCifraturaPedding(utilizzatoreSistema));

        }
    }

    //classe listener per visualizare il panello con le proposte inbox sdc
    private class AccettaRifiutaSDCListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(" AccettaRifiutaSDCListener ");
            panelloPrincipale.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            //proposta selezionata dalla jlist
            Proposta proposta = (Proposta) inboxSDCPanel.getElencoProposteRicevute().getSelectedValue();
            if (ev.getText().equalsIgnoreCase("accetta") && proposta != null) {
                proposta.setStato("accettata");
                proposta.salva();
                panelloPrincipale.setStatus("La proposta è stata accettata correttamente!");
            } else if (ev.getText().equalsIgnoreCase("rifiuta") && proposta != null) {
                proposta.setStato("rifiutata");
                proposta.salva();
                inboxSDCPanel.getInfoSdcLabel().setText("");
                inboxSDCPanel.deleteSelectedIndex();
                //panelloPrincipale.setStatus("Non si puo' cancellare");
            } else {
                panelloPrincipale.setStatus("Seleziona una proposta!");
            }
        }
    }

    //classe listener per eliminare un sistema di cifratura concordato in precedenza
    private class EliminaSDCListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(" eliminaSDCListener ");
            panelloPrincipale.setStatus(" ");
            JButton ev = (JButton) e.getSource();
            //proposta selezionata dalla jlist
            Proposta proposta = (Proposta) gestisciSDCPanel.getElencoProposteAccettate().getSelectedValue();
            if (proposta != null) {
                System.out.println(proposta.getSdc().getId());
                proposta.elimina();
                panelloPrincipale.setStatus("La proposta è stata eliminata correttamente!");
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
            if (regForm.getNameField().equals("") || regForm.getSurnameField().equals("")
                    || regForm.getNicknameField().equals("") || regForm.getPasswordField().equals("")) {
                System.out.println(this.getClass() + "Registration: error");
                regForm.setErrorLabel("Errore: tutti i campi devono essere compilati!");
            } else {
                Studente s = new Studente(regForm.getNameField(), regForm.getSurnameField(),
                        regForm.getNicknameField(), regForm.getPasswordField());
                s.salva();
                regForm.dispose();
                LoginForm f = new LoginForm();
                System.out.println(this.getClass() + "Registration: OK");
            }
        }
    }

    public COMController getComC() {
        return comC;
    }

}
