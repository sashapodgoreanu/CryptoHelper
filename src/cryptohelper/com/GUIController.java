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
import cryptohelper.abstractC.View;
import cryptohelper.data.Mappatura;
import cryptohelper.data.Messaggio;
import cryptohelper.abstractC.MessaggioMittente;
import cryptohelper.abstractC.MessaggioDestinatario;
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
            inboxPanel.getElencoMessaggiRicevuti().addListSelectionListener(new ViewReceivedMsgListener());
        } else if (v instanceof OutboxPanel) {
            outboxPanel = (OutboxPanel) v;
            outboxPanel.getElencoMessaggiInviati().addListSelectionListener(new ViewReceivedMsgListener());
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
            bozzePanel.getSaveBozzaBtn().addActionListener(new SalvaMessaggioListener());
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
            messagePanel.getSalvaBozzaBtn().addActionListener(new SalvaMessaggioListener());
        } else if (v instanceof ProponiSDCPanel) {
            proponiSDCPanel = (ProponiSDCPanel) v;
            proponiSDCPanel.getProponiSDCBtn().addActionListener(new SendProponiSDCListener());
        } else if (v instanceof InboxSDCPanel) {
            inboxSDCPanel = (InboxSDCPanel) v;
            inboxSDCPanel.getElencoProposteRicevute().addListSelectionListener(new ViewProponiSDCListener());
            
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
            panelloPrincipale.setStatusLabelText(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " Clicked " + ev.getText());
            //TO-DO da modificare perche devono apparire solo destinatari con cui il studente ha concluso una proposta Scifratura
            panelloPrincipale.setDestinatariArrLst(comC.getDestinatari());
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
            panelloPrincipale.setStatusLabelText(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            ArrayList<MessaggioDestinatario> temp = Messaggio.caricaMessaggiDestinatario(utilizzatoreSistema.getId());
            panelloPrincipale.initInbox(temp);
        }
    }

    //classe listener per il button Inbox della finestra principale
    private class GestisciOutbox implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatusLabelText(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            //ArrayList<MessaggioMittente> temp = Messaggio.caricaMessaggiDestinatario(utilizzatoreSistema.getId());
            //pp.initOutbox(temp);
        }
    }

//classe listener per la Jlist "ElencoMessaggiRicevuti" 
    private class ViewReceivedMsgListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            panelloPrincipale.setStatusLabelText(" ");
            System.out.println("Clicked LIST");
            MessaggioMittente mess = (MessaggioMittente) inboxPanel.getElencoMessaggiRicevuti().getSelectedValue();
            System.out.println(mess.toString());
            inboxPanel.modificaCorpoMessaggio("Mittente: " + mess.getMittente().getNome() + "\nTitolo messaggio: " + mess.getTitolo() + "\n\n" + mess.getTesto());
        }
    }

    //classe listener per la Jlist "ElencoBozze" 
    private class ViewBozzeMsgListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            panelloPrincipale.setStatusLabelText(" ");
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
            panelloPrincipale.setStatusLabelText(" ");
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
            panelloPrincipale.setStatusLabelText(" ");
            MessaggioMittente mess = (MessaggioMittente) bozzePanel.getElencoBozze().getSelectedValue();
            mess.elimina();
            bozzePanel.deleteSelectedIndex();
        }
    }

//classe listener per il button "elimina bozza" del pannello bozze
    private class GestisciSDC implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatusLabelText(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            panelloPrincipale.initSDC();
        }
    }

    private class CreateSDCListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatusLabelText(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " Clicked " + ev.getText());
            sdcPanel.initCreateSDC();
        }
    }

//classe listener per JRadioButtons per selezionare il metodo di cifratura
    private class MetodoDicifraturaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatusLabelText(" ");
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
    private class SalvaMessaggioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatusLabelText(" ");
            //un messaggio senza titolo non si puo salvare
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " Clicked " + ev.getText());
            System.out.println(this.getClass() + " Selected " + messagePanel.getSelectedDestinatario().toString());
            System.out.println(messagePanel.getTitoloMessaggioField() + " - Tittolo del messaggio");
            //se il tittolo del messaggio e vuouto mostra il messaggio
            String temp = messagePanel.getTitoloMessaggioField().replaceAll("\\s+", "");
            if (temp.equals("")) {
                panelloPrincipale.setStatusLabelText("Il titolo del messaggio deve contenere almeno un carattere");
            } else { //altrimenti salva il messaggio
                panelloPrincipale.setStatusLabelText("");
                JList list = messagePanel.getElencoDestinatari();
                UserInfo destinatario = (UserInfo) list.getSelectedValue();
                System.out.println("Destinatario selected: " + destinatario.toString());
                msgMittente = new Messaggio(msgMittente.getId(), messagePanel.getCorpoMessaggio(), messagePanel.getTitoloMessaggioField(), true, utilizzatoreSistema, destinatario);
                //se msg.salva ritorna false allora errore
                if (msgMittente.salva()) {
                    panelloPrincipale.setStatusLabelText("Messaggio Salvato!");
                } else {
                    panelloPrincipale.setStatusLabelText("Si è verificato un durante il salvataggio del messaggio!");
                }
            }
        }
    }

//classe listener per salvare SDC - ascolta il bottone salva
    private class SalvaMetodoDicifraturaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatusLabelText("");
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
                        panelloPrincipale.setStatusLabelText("Salvato con sucesso");
                    } else {
                        panelloPrincipale.setStatusLabelText("E stato un errore! non salvato");
                    }
                } else {
                    panelloPrincipale.setStatusLabelText("La mappatura non è coretta o contiene caratteri illegali - sono accetate solo lettere");
                }
            }
        }
    }

//classe listener per provare SDC - ascolta il bottone prova sdc
    private class ProvaMetodoDicifraturaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatusLabelText("");
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
                panelloPrincipale.setStatusLabelText("La mappatura non è coretta o contiene caratteri illegali - sono accetate solo lettere");
            }

        }
    }

    //classe listener per inizializare il panello proponi sistema di cifratura
    private class ProponiSDCListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatusLabelText(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " selected " + ev.getText());
            sdcPanel.initProponiSDCPanel(comC.getDestinatari(), SistemaCifratura.caricaSistemiCifratura(utilizzatoreSistema));
        }
    }

    //classe listener per inviare una proposta di sistema di cifratura
    private class SendProponiSDCListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatusLabelText(" ");
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " selected " + ev.getText());
            SistemaCifratura sdc = (SistemaCifratura) proponiSDCPanel.getElencoSDC().getSelectedValue();
            UserInfo partner = (UserInfo) proponiSDCPanel.getElencoDestinatari().getSelectedValue();
            if(comC.proponiSistemaCifratura(utilizzatoreSistema, partner, sdc))
                panelloPrincipale.setStatusLabelText("Inviato con successo");
            else panelloPrincipale.setStatusLabelText("Proposta dublicata o errore di sistema");

        }
    }
    
    //classe listener per visualizare il panello con le proposte inbox sdc
    private class InboxSDCListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelloPrincipale.setStatusLabelText(" ");
            JButton ev = (JButton) e.getSource();
            sdcPanel.initInboxSDCPanel(Proposta.caricaProposteSistemiCifratura(utilizzatoreSistema));


        }
    }
    
    //classe listener per la Jlist "ElencoBozze" 
    private class ViewProponiSDCListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            panelloPrincipale.setStatusLabelText(" ");
            System.out.println("Clicked LIST");
            Proposta proposta = (Proposta) inboxSDCPanel.getElencoProposteRicevute().getSelectedValue();
            //bp.modificaCorpoMessaggio("Destinatario: " + mess.getDestinatario().getNome() + "\n" + mess.getTesto());
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
