// classe Proposta
package cryptohelper.data;

import static cryptohelper.data.SistemaCifratura.getSistemaCifratura;
import cryptohelper.service.DBController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SashaAlexandru
 */
public class Proposta {

    private SistemaCifratura sdc;
    private UserInfo proponente;
    private UserInfo partner;
    private String stato;

    public Proposta(SistemaCifratura sdc, UserInfo proponente, UserInfo partner) {
        this.sdc = sdc;
        this.proponente = proponente;
        this.partner = partner;
        this.stato = "pending";
    }

    public UserInfo getProponente() {
        return proponente;
    }

    public UserInfo getPartner() {
        return partner;
    }

    public void setProponente(UserInfo proponente) {
        this.proponente = proponente;
    }

    public void setPartner(UserInfo partner) {
        this.partner = partner;
    }

    public SistemaCifratura getSdc() {
        return sdc;
    }

    public void setSdc(SistemaCifratura sdc) {
        this.sdc = sdc;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public boolean salva() {
        //se proponente e il partner hanno gia concordato un sistema di cifratura in precedenza non potranno piu farlo. 
        //SistemaCifratura esisteSdc = SistemaCifratura.load(proponente.getId(), partner.getId());
        //System.out.println(esisteSdc.toString());
        // if(esisteSdc != null)
        // return false;
        System.out.println(this.toString());
        boolean result = false;
        DBController dbc = DBController.getInstance();
        String queryExists = "SELECT * "
                + " FROM SDCPARTNERS"
                + " WHERE ((ID_CREATORE = " + proponente.getId()
                + " AND ID_PARTNER = " + partner.getId()
                + ") OR (ID_CREATORE = " + partner.getId()
                + ")  AND ID_PARTNER = " + proponente.getId()
                + ") AND STATO_PROPOSTA = 'accettata'";
        QueryResult qr;
        try {
            qr = DBController.getInstance().executeQuery(queryExists);
            System.out.println("QR*************" + qr.toString());
            System.out.println("Size*************" + qr.getSize());
            if (qr.getSize() > 0) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Proposta.class.getName()).log(Level.SEVERE, null, ex);
        }

        String queryInsert = "INSERT INTO SDCPARTNERS(ID_CREATORE, ID_PARTNER,ID_SDC,STATO_PROPOSTA)"
                + "VALUES("
                + this.proponente.getId()
                + ","
                + this.partner.getId()
                + ","
                + this.sdc.getId()
                + ",'"
                + this.stato
                + "')";
        String querryUpdate = "UPDATE SDCPARTNERS"
                + " SET STATO_PROPOSTA = '" + this.getStato()
                + "'"
                + " WHERE ID_CREATORE = "
                + this.getProponente().getId()
                + " AND ID_PARTNER = " + this.getPartner().getId()
                + " AND ID_SDC = " + this.getSdc().getId();

        try {
            if (this.stato.equals("pending")) {

                result = dbc.executeUpdate(queryInsert);

                System.out.println("INFO DATA:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "Inserito: " + this.toString());
            } else {

                result = dbc.executeUpdate(querryUpdate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Proposta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     *
     * @param stud Studente logato al sistema
     * @return lista con proposte che hanno come stato pending
     */
    public static ArrayList<Proposta> caricaProposteSistemiCifraturaPedding(UserInfo stud) {
        String query = "SELECT * FROM SDCPARTNERS WHERE ID_PARTNER =" + stud.getId();
        QueryResult qr = null;
        ArrayList<Proposta> proposte = new ArrayList<>();
        try {
            qr = DBController.getInstance().executeQuery(query);
            while (qr.next()) {
                if (qr.getString("stato_proposta").equals("pending")) {
                    Proposta temp = new Proposta(SistemaCifratura.getSistemaCifratura(qr.getInt("ID_SDC")), UserInfo.getUserInfo(qr.getInt("ID_CREATORE")), UserInfo.getUserInfo(qr.getInt("ID_PARTNER")));
                    System.out.println("Proposta: " + temp.toString());
                    proposte.add(temp);

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(SistemaCifratura.class
                    .getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(SistemaCifratura.class
                    .getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return proposte;
    }

    /**
     *
     * @param stud Studente logato al sistema
     * @return lista con proposte accettate di cifratura accettate
     */
    public static ArrayList<Proposta> caricaProposteSistemiCifratura(UserInfo stud) {
        String query = "SELECT *"
                + "FROM SDCPARTNERS "
                + "WHERE ID_PARTNER = " + stud.getId() + " OR ID_CREATORE = " + stud.getId();
        QueryResult qr = null;
        ArrayList<Proposta> proposte = new ArrayList<>();
        try {
            qr = DBController.getInstance().executeQuery(query);
            System.out.println(qr.toString());
            while (qr.next()) {
                if (qr.getString("stato_proposta").equals("accettata")) {
                Proposta temp = new Proposta(SistemaCifratura.getSistemaCifratura(qr.getInt("ID_SDC")), UserInfo.getUserInfo(qr.getInt("ID_CREATORE")), UserInfo.getUserInfo(qr.getInt("ID_PARTNER")));
                System.out.println("Proposta: " + temp.toString());
                proposte.add(temp);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SistemaCifratura.class
                    .getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(SistemaCifratura.class
                    .getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return proposte;
    }
    
    //Elimina una proposta dalla tabella sdcpartners. Restituisce TRUE se l'oparazione va a buon fine
    public boolean elimina() {
        DBController dbc = DBController.getInstance();
        boolean result = false;
        String query = "DELETE FROM SDCPARTNERS WHERE ID_SDC =" + sdc.getId()+"AND ID_CREATORE = "+ proponente.getId()+"AND ID_PARTNER ="+ partner.getId()+"";
        try {

            result = dbc.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Proposta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }    
    

    /*
     public static Proposta getProposta(UserInfo u1, UserInfo u2) {
        
     }
     */
    @Override
    public String toString() {
        return "Proposta{" + "sdc=" + sdc.getId() + ", proponente=" + proponente.getId() + ", partner=" + partner.getId() + ", stato=" + stato + '}';
    }

}
