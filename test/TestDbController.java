
import cryptohelper.bean.Messaggio;
import cryptohelper.service.DBController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author st116629
 */
public class TestDbController {

    public static void main(String args[]) {

        Messaggio m = new Messaggio(21, "testo", "testoCifrato", "lingua", "titolo", true, true);
        try {
            DBController.registerDB();
            DBController.createTables();
            m.salva();
            m.elimina();

        } catch (SQLException ex) {
            Logger.getLogger(TestDbController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
