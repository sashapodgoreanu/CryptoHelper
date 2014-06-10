package cryptohelper.data;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;

public class CalcolatoreCesareTest {

    Mappatura result;

    public CalcolatoreCesareTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        result = new Mappatura();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calcola method, of class CalcolatoreCesare.
     */
    @org.junit.Test
    public void testCalcola() {
        System.out.println("Test of calcola method, of class CalcolatoreCesare.");
        CalcolatoreCesare instance = new CalcolatoreCesare();
        result = instance.calcola("0");
        assertEquals('a' - result.mapOf('a'), 0);
        assertEquals('b' - result.mapOf('b'), 0);
        assertEquals('x' - result.mapOf('x'), 0);
        assertEquals('x' - result.mapOf('x'), 0);
        result = instance.calcola("1");
        assertEquals('b' - result.mapOf('a'), 0);
        assertEquals('c' - result.mapOf('b'), 0);
        assertEquals('a' - result.mapOf('z'), 0);
        result = instance.calcola("2");
        assertEquals('c' - result.mapOf('a'), 0);
        assertEquals('d' - result.mapOf('b'), 0);
        assertEquals('b' - result.mapOf('z'), 0);
    }

}
