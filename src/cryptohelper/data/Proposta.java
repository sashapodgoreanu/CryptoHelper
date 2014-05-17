// classe Proposta
package cryptohelper.data;

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

    
    
    //METODI GETTER
    public UserInfo getProponente() {
        return proponente;
    }

    public UserInfo getPartner() {
        return partner;
    }

    //METODI SETTER
    public void setProponente(UserInfo proponente) {
        this.proponente = proponente;
    }

    public void setPartner(UserInfo partner) {
        this.partner = partner;
    }

    public boolean salva() {
        boolean result = false;
        DBController dbc = DBController.getInstance();
        String queryInsert = "INSERT INTO SDCPARTNERS(ID_CREATORE, ID_PARTNER,ID_SDC,STATO_PROPOSTA)"
                + "VALUES("
                + this.proponente.getId()
                + ","
                +this.partner.getId()
                + ","
                + this.sdc.getId()
                + ",'"
                +this.stato
                + "')";
        try {
            result = dbc.executeUpdate(queryInsert);
            System.out.println("INFO DATA:" + this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "Inserito: " + this.toString());
        } catch (SQLException ex) {
            Logger.getLogger(Proposta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static ArrayList<Proposta> caricaProposteSistemiCifratura(UserInfo stud) {
        String query = "SELECT * FROM SDCPARTNERS WHERE ID_PARTNER ="+stud.getId();
        QueryResult qr = null;
        ArrayList<Proposta> sdcs = new ArrayList<>();
        try {
            qr = DBController.getInstance().executeQuery(query);
            while (qr.next()) {
                    Proposta temp = new Proposta(SistemaCifratura.getSistemaCifratura(qr.getInt("ID_SDC")), UserInfo.getUserInfo(qr.getInt("ID_CREATORE")), UserInfo.getUserInfo(qr.getInt("ID_PARTNER")));
                    System.out.println("Proposta: "+temp.toString());
                    sdcs.add(temp);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SistemaCifratura.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(SistemaCifratura.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return sdcs;
    }

    @Override
    public String toString() {
        return "Proposta{" + "sdc=" + sdc.getId() + ", proponente=" + proponente.getId() + ", partner=" + partner.getId() + ", stato=" + stato + '}';
    }
    
    
    
    
}
