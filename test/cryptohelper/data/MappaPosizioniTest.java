
package cryptohelper.data;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author SashaAlexandru
 */
public class MappaPosizioniTest {

    MappaPosizioni mappapos;

    public MappaPosizioniTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mappapos = new MappaPosizioni("555");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of executeMossa method, of class MappaPosizioni.
     */
    @Test
    public void testExecuteMossa() {
        System.out.println("Test of executeMossa method, of class MappaPosizioni.");
        Mossa mossa = new Mossa('A', 'd');
        String testoLavoro = "ABCD";
        MappaPosizioni instance = new MappaPosizioni(testoLavoro);
        String expResult = "dBCD";
        String result = instance.executeMossa(mossa, testoLavoro);
        assertEquals(expResult, result);
        /**
         * ******************************************************************
         */
        mossa = new Mossa('x', 'y');
        testoLavoro = "ABCD";
        instance = new MappaPosizioni(testoLavoro);
        expResult = "ABCD";
        result = instance.executeMossa(mossa, testoLavoro);
        assertEquals(expResult, result);
    }

    /**
     * Test of inizializza method, of class MappaPosizioni.
     */
    @Test
    public void testInizializza() {
        System.out.println("Test of inizializza method, of class MappaPosizioni.");
        String testoCifrato = "5555";
        mappapos.inizializza(testoCifrato);
        for (int i = 'A'; i <= 'Z'; i++) {
            assertNotNull(mappapos.getMappa()[i]);
        }
        //System.out.println(mappapos.toString());
        mappapos = new MappaPosizioni("az");
        testoCifrato = "az";
        mappapos.inizializza(testoCifrato);
        assertEquals(mappapos.getMappa()['A'].getCarattere(), 'A');
        assertEquals(Integer.valueOf(mappapos.getMappa()['A'].getListaPos().get(0)), Integer.valueOf(0));
        assertEquals(mappapos.getMappa()['Z'].getCarattere(), 'Z');
        assertEquals(Integer.valueOf(mappapos.getMappa()['Z'].getListaPos().get(0)), Integer.valueOf(1));
    }
}
