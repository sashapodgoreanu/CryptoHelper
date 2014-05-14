package cryptohelper.com;

import cryptohelper.GUI.BozzePanel;
import cryptohelper.GUI.CreaSDCPanel;
import cryptohelper.GUI.InboxPanel;
import cryptohelper.GUI.LoginForm;
import cryptohelper.GUI.MessagePanel;
import cryptohelper.GUI.OutboxPanel;
import cryptohelper.GUI.PanelloPrincipale;
import cryptohelper.GUI.ProponiSDCPanel;
import cryptohelper.GUI.RegistrationForm;
import cryptohelper.GUI.SdcPanel;
import cryptohelper.GUI.View;
import cryptohelper.data.Mappatura;
import cryptohelper.data.Messaggio;
import cryptohelper.data.MessaggioMittente;
import cryptohelper.data.MessaggioDestinatario;
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
    private PanelloPrincipale pp;
    private SdcPanel sdcp;
    private MessagePanel msgp;
    private BozzePanel bp;
    private InboxPanel ip;
    private OutboxPanel op;
    private CreaSDCPanel csdcp;
    private static GUIController instance;
    private Messaggio msgMittente;
    private UserInfo utilizzatoreSistema;
    private SistemaCifratura sdc;
    private Mappatura mp;
    private ProponiSDCPanel psdc;

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
            ip = (InboxPanel) v;
            ip.getElencoMessaggiRicevuti().addListSelectionListener(new ViewReceivedMsgListener());
        } else if (v instanceof OutboxPanel) {
            op = (OutboxPanel) v;
            op.getElencoMessaggiInviati().addListSelectionListener(new ViewReceivedMsgListener());
        } else if (v instanceof PanelloPrincipale) {
            pp = (PanelloPrincipale) v;
            pp.getNuovoMessaggioBtn().addActionListener(new NuovoMessaggioListener());
            pp.getInboxBtn().addActionListener(new GestisciInbox());
            pp.getOutboxBtn().addActionListener(new GestisciOutbox());
            pp.getLogoutBtn().addActionListener(new LogoutListener());
            pp.getGestisciBozzeBtn().addActionListener(new GestisciBozzeListener());
            pp.getSDCBtn().addActionListener(new GestisciSDC());
        } else if (v instanceof BozzePanel) {
            bp = (BozzePanel) v;
            bp.getSaveBozzaBtn().addActionListener(new SalvaMessaggioListener());
            bp.getDeleteBozzaBtn().addActionListener(new ElimnaBozzaListener());
            bp.getElencoBozze().addListSelectionListener(new ViewBozzeMsgListener());
        } else if (v instanceof SdcPanel) {
            sdcp = (SdcPanel) v;
            sdcp.getCreaSDCBtn().addActionListener(new CreateSDCListener());
            sdcp.getProponiSDCBtn().addActionListener(new ProponiSDCListener());
        } else if (v instanceof CreaSDCPanel) {
            csdcp = (CreaSDCPanel) v;
            csdcp.getCesareRBtn().addActionListener(new MetodoDicifraturaListener());
            csdcp.getParolaChiaveRBtn().addActionListener(new MetodoDicifraturaListener());
            csdcp.getPseudocasualeRBtn().addActionListener(new MetodoDicifraturaListener());
            csdcp.getSalvaSdcBtn().addActionListener(new SalvaMetodoDicifraturaListener());
            csdcp.getProvasdcBtn().addActionListener(new ProvaMetodoDicifraturaListener());
        } else if (v instanceof MessagePanel) {
            msgp = (MessagePanel) v;
            msgp.getSalvaBozzaBtn().addActionListener(new SalvaMessaggioListener());
        } else if (v instanceof ProponiSDCPanel) {
            psdc = (ProponiSDCPanel) v;
            psdc.getProponiSDCBtn().addActionListener(new SendProponiSDCListener());
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
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " Clicked " + ev.getText());
            //TO-DO da modificare perche devono apparire solo destinatari con cui il studente ha concluso una proposta Scifratura
            pp.setDestinatariArrLst(comC.getDestinatari());
            System.out.println(comC.getDestinatari().toString());
            pp.initNuovoMessaggio();
            //predispone il nuovo messaggio
            msgMittente = new Messaggio();
        }
    }

//classe listener per il button Inbox della finestra principale
    private class GestisciInbox implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            ArrayList<MessaggioDestinatario> temp = Messaggio.caricaMessaggiDestinatario(utilizzatoreSistema.getId());
            pp.setMittentiMessaggiArrLst(temp);
            pp.initInbox();
        }
    }

    //classe listener per il button Inbox della finestra principale
    private class GestisciOutbox implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            ArrayList<MessaggioDestinatario> temp = Messaggio.caricaMessaggiDestinatario(utilizzatoreSistema.getId());
            pp.setMittentiMessaggiArrLst(temp);
            pp.initOutbox();
        }
    }

//classe listener per la Jlist "ElencoMessaggiRicevuti" 
    private class ViewReceivedMsgListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            System.out.println("Clicked LIST");
            MessaggioDestinatario mess = (MessaggioDestinatario) ip.getElencoMessaggiRicevuti().getSelectedValue();
            ip.modificaCorpoMessaggio("Mittente: " + mess.getMittente() + "\nTitolo messaggio: " + mess.getTitolo() + "\n\n" + mess.getTesto());
        }
    }

    //classe listener per la Jlist "ElencoBozze" 
    private class ViewBozzeMsgListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            System.out.println("Clicked LIST");
            MessaggioDestinatario mess = (MessaggioDestinatario) bp.getElencoBozze().getSelectedValue();
            bp.modificaCorpoMessaggio("Mittente: " + mess.getMittente() + "\n" + mess.getTesto());
            bp.setTitoloBozza(mess.getTitolo());
        }
    }

//classe listener per il button "bozze" della finestra principale 
    private class GestisciBozzeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            ArrayList<MessaggioMittente> temp = Messaggio.caricaBozze(utilizzatoreSistema.getId());
            pp.setBozzeArrayLst(temp);
            pp.initGestioneBozze();
        }
    }
//classe listener per il button "bozze" della finestra principale 

    private class ElimnaBozzaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MessaggioDestinatario mess = (MessaggioDestinatario) bp.getElencoBozze().getSelectedValue();
            mess.eliminaMessaggio();
            bp.getElencoBozze().repaint();
        }
    }

//classe listener per il button "elimina bozza" del pannello bozze
    private class GestisciSDC implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println("Clicked " + ev.getText());
            pp.initSDC();
        }
    }

    private class CreateSDCListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " Clicked " + ev.getText());
            sdcp.initCreateSDC();
        }
    }

//classe listener per JRadioButtons per selezionare il metodo di cifratura
    private class MetodoDicifraturaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton ev = (JRadioButton) e.getSource();
            System.out.println(this.getClass() + " selected " + ev.getText());
            if (ev.getText().equalsIgnoreCase("parola chiave")) {

                csdcp.initParolaChiave();
            } else if (ev.getText().equalsIgnoreCase("cesare")) {
                csdcp.initCesare();
            }
            //crea sistema di cifratura
            sdc = new SistemaCifratura(utilizzatoreSistema);
        }
    }

//classe listener per il button "salva messaggio" della finestra principale
    private class SalvaMessaggioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //un messaggio senza titolo non si puo salvare
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " Clicked " + ev.getText());
            System.out.println(this.getClass() + " Selected " + msgp.getSelectedDestinatario().toString());
            System.out.println(msgp.getTitoloMessaggioField() + " - Tittolo del messaggio");
            //se il tittolo del messaggio e vuouto mostra il messaggio
            String temp = msgp.getTitoloMessaggioField().replaceAll("\\s+", "");
            if (temp.equals("")) {
                pp.setStatusLabelText("Il titolo del messaggio deve contenere almeno un carattere");
            } else { //altrimenti salva il messaggio
                pp.setStatusLabelText("");
                JList list = msgp.getElencoDestinatari();
                UserInfo destinatario = (UserInfo) list.getSelectedValue();
                System.out.println("Destinatario selected: " + destinatario.toString());
                msgMittente = new Messaggio(msgMittente.getId(), msgp.getCorpoMessaggio(), msgp.getTitoloMessaggioField(), true, utilizzatoreSistema, destinatario);
                //se msg.salva ritorna false allora errore
                if (msgMittente.salva()) {
                    pp.setStatusLabelText("Messaggio Salvato!");
                } else {
                    pp.setStatusLabelText("Si è verificato un durante il salvataggio del messaggio!");
                }
            }
        }
    }

//classe listener per salvare SDC - ascolta il bottone salva
    private class SalvaMetodoDicifraturaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            pp.setStatusLabelText("");
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " selected " + ev.getText());
            if (ev.getText().equalsIgnoreCase("Salva cifrario parola chiave")) {
                String metodo = "parola chiave";
                if (csdcp.getTable().isEditing()) {
                    csdcp.getTable().getCellEditor().stopCellEditing();
                }
                String chiave = "";
                for (int i = 0; i < 26; i++) {
                    chiave = chiave + "" + csdcp.getData(0, i);
                }
                if (sdc.valid(metodo, chiave)) {
                    mp = sdc.create(metodo, chiave);
                    sdc.setNome(csdcp.getNomeCifraturaField().getText());
                    if (sdc.salva()) {
                        pp.setStatusLabelText("Salvato con sucesso");
                    } else {
                        pp.setStatusLabelText("E stato un errore! non salvato");
                    }
                } else {
                    pp.setStatusLabelText("La mappatura non è coretta o contiene caratteri illegali - sono accetate solo lettere");
                }
            }
        }
    }

//classe listener per provare SDC - ascolta il bottone prova sdc
    private class ProvaMetodoDicifraturaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            pp.setStatusLabelText("");
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " selected " + ev.getText());
            sdc = new SistemaCifratura(utilizzatoreSistema);
            String metodo = "parola chiave";
            if (csdcp.getTable().isEditing()) {
                csdcp.getTable().getCellEditor().stopCellEditing();
            }
            String chiave = "";
            for (int i = 0; i < 26; i++) {
                chiave = chiave + "" + csdcp.getData(0, i);
            }
            if (sdc.valid(metodo, chiave)) {
                mp = sdc.create(metodo, chiave);
                String a = csdcp.getCorpoMessaggioProva().getText();
                csdcp.getCorpoMessaggioResult().setText(sdc.prova(a));
            } else {
                pp.setStatusLabelText("La mappatura non è coretta o contiene caratteri illegali - sono accetate solo lettere");
            }

        }
    }

    //classe listener per inizializare il panello proponi sistema di cifratura
    private class ProponiSDCListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " selected " + ev.getText());
            sdcp.initProponiSDCPanel(comC.getDestinatari(), SistemaCifratura.caricaSistemiCifratura(utilizzatoreSistema));
        }
    }

    //classe listener per inviare una proposta di sistema di cifratura
    private class SendProponiSDCListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton ev = (JButton) e.getSource();
            System.out.println(this.getClass() + " selected " + ev.getText());
            SistemaCifratura sdc = (SistemaCifratura) psdc.getElencoSDC().getSelectedValue();
            UserInfo partner = (UserInfo) psdc.getElencoDestinatari().getSelectedValue();
            comC.inviaProposta(utilizzatoreSistema, partner, sdc);

        }
    }

//classe listener per il button "salva messaggio" della finestra principale
    private class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            pp.dispose();
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
