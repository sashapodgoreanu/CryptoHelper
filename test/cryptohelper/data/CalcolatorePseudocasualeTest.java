package cryptohelper.data;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CalcolatorePseudocasualeTest {

    Mappatura result;

    public CalcolatorePseudocasualeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calcola method, of class CalcolatorePseudocasuale.
     */
    @Test
    public void testCalcola() {
        System.out.println("Test of calcola method, of class CalcolatorePseudocasuale.");
        String chiave = "1";
        CalcolatorePseudocasuale instance = new CalcolatorePseudocasuale();
        Mappatura result = instance.calcola(chiave);
        char[] a = result.getMappaInversa();
        boolean duplicati = false;
        for (int i = 0; i < 25; i++) {
            for (int j = i + 1; j < 26; j++) {
                if (a[i] == a[j]) {
                    duplicati = true;
                }
            }

        }
        boolean found = true;
        for (int i = 'a'; found && i <= 'z'; i++) {
            found = false;
            for (int j = 0; !found && j < 26; j++) {
                if (i == a[j]) {
                    found = true;
                } else {
                    found = false;
                }
            }
        }
        boolean existsAll = found;
        assertTrue(existsAll);
        assertFalse(duplicati);

    }

}
