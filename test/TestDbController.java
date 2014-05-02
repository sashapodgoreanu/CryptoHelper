
import cryptohelper.bean.Messaggio;
import cryptohelper.bean.Studente;
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
        Studente st = new Studente("Alexandru","Podgoreanu","sasha","1234");
        try {
            
            DBController db = DBController.getInstance();
            DBController.createTables();
            m.salva();
            m.elimina();
            st.salva();

        } catch (SQLException ex) {
            Logger.getLogger(TestDbController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
