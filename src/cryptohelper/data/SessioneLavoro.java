/*
 * Copyright 2014 st106342.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cryptohelper.data;

import cryptohelper.com.COMController;
import cryptohelper.interfaces.MessaggioMittente;
import cryptohelper.service.DBController;
import static java.lang.StrictMath.log;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author st106342
 */
public class SessioneLavoro {
    
    int idSessione;
    UserInfo utente;
    Date ultimaModifica;
     // alberoipotesi
    Messaggio messaggioIntercettato;
    // soluzione
        
     //COSTRUTTORE I
    public SessioneLavoro (int id, UserInfo studente, AlberoIpotesi albero, Messaggio messaggio /* soluzione*/) {
    
        idSessione = id;
        utente=studente;
        messaggioIntercettato = messaggio;
        
        ultimaModifica = new Date(); //inizializza ultimaModifica con la data attuale.       
    }    
    
    public int getIdSessione() {
        return idSessione;
    }

    public void setIdSessione(int idSessione) {
        this.idSessione = idSessione;
    }

    public UserInfo getUtente() {
        return utente;
    }

    public void setUtente(UserInfo utente) {
        this.utente = utente;
    }

    public Date getUltimaModifica() {
        return ultimaModifica;
    }

    public void setUltimaModifica(Date ultimaModifica) {
        this.ultimaModifica = ultimaModifica;
    }

    public Messaggio getMessaggioIntercettato() {
        return messaggioIntercettato;
    }

    public void setMessaggioIntercettato(Messaggio messaggioIntercettato) {
        this.messaggioIntercettato = messaggioIntercettato;
    }  

    //Salva una sessione nella tabella SESSIONELAVORO del db. Restituisce TRUE se l'oparazione va a buon fine
    public boolean salva() {
        boolean result = false;
        DBController dbc = DBController.getInstance();
        String queryInsert = "INSERT INTO Sessionelavoro(Id_Utente, Id_Messaggio_Intercettato, Ultima_Modifica)"
                + "VALUES("
                + this.getUtente().getId()
                + ","
                + this.getMessaggioIntercettato().getId()
                + ",'"
                + this.getUltimaModifica()
                + "')";
        String querryUpdate = "UPDATE MESSAGGI"
                + " Id_Utente = '" + this.getUtente().getId()
                + "',"
                + " Id_Messaggio_Intercettato = '"+ this.getMessaggioIntercettato().getId()
                + "',"
                + " Ultima_Modifica = "+ this.getUltimaModifica()
                + "',"
                + " WHERE ID = " + this.getIdSessione();
        try {
            //un nuovo messaggo
            if (this.getIdSessione() == 0) {
                int newID = dbc.executeUpdateAndReturnKey(queryInsert);
                //se newID = -1 allora è stato un errore nel inserimento nel db;
                if (newID != -1) {
                    this.idSessione = newID;
                    System.out.println("INFO DATA:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ": Aggiunto con successo " + this.toString());
                    System.out.println(true);
                    return true;

                }
                if (newID == -1 && this.idSessione != 0) {
                    System.out.println(false);
                    //errore nel inserimento
                    return false;
                }
                //aggiornamento di un messaggio
            } else {
                result = dbc.executeUpdate(querryUpdate);
                System.out.println("INFO DATA:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "Aggiornato: " + this.toString());
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
public SessioneLavoro (int id, int studente, int albero, int messaggio, int soluzione) {
    
        idSessione = id;
        utente.setId(studente);
        ultimaModifica = new Date(); //inizializza ultimaModifica con la data attuale.       
    }    


    //Preleva l'elenco dei messaggi inviati dallo studente indicato
    public static ArrayList<SessioneLavoro> caricaSessioni(int idStudente) {
        String query = "SELECT * FROM SessioneLavoro WHERE ID_Utente = " + idStudente;
        QueryResult qr = null;
        ArrayList<SessioneLavoro> sessioni = new ArrayList<>();
        try {
            qr = DBController.getInstance().executeQuery(query);
            while (qr.next()) {
                UserInfo user = UserInfo.getUserInfo(idStudente);
                SessioneLavoro temp = new SessioneLavoro(qr.getInt("ID"), qr.getInt("id_utente"), qr.getInt("id_albero"),
                        qr.getInt("id_messaggio_intercettato"), qr.getInt("ultima_modifica"));
                sessioni.add(temp);
            }
        } catch (Exception ex) {
            Logger.getLogger(COMController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return sessioni;
    }
    
    
    
}
