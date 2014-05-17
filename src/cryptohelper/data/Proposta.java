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
        boolean result = false;
        DBController dbc = DBController.getInstance();
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
                + " WHERE ID_CREATORE = " + this.getProponente().getId() + " AND ID_PARTNER = " + this.getPartner().getId() + " AND ID_SDC = " + this.getSdc().getId();
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
    public static ArrayList<Proposta> caricaProposteSistemiCifratura(UserInfo stud) {
        String query = "SELECT * FROM SDCPARTNERS WHERE ID_PARTNER =" + stud.getId();
        QueryResult qr = null;
        ArrayList<Proposta> sdcs = new ArrayList<>();
        try {
            qr = DBController.getInstance().executeQuery(query);
            while (qr.next()) {
                if (qr.getString("stato_proposta").equals("pending")) {
                    Proposta temp = new Proposta(SistemaCifratura.getSistemaCifratura(qr.getInt("ID_SDC")), UserInfo.getUserInfo(qr.getInt("ID_CREATORE")), UserInfo.getUserInfo(qr.getInt("ID_PARTNER")));
                    System.out.println("Proposta: " + temp.toString());
                    sdcs.add(temp);
                }

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
