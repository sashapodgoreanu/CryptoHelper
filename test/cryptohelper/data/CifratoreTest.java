package cryptohelper.data;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CifratoreTest {

    Mappatura mappa;

    public CifratoreTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mappa = new Mappatura();
        char[] inversemap = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        mappa.setMappaInversa(inversemap);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of cifraMonoalfabetica method, of class Cifratore.
     */
    @Test
    public void testCifraMonoalfabetica() {
        System.out.println("Test of cifraMonoalfabetica method, of class Cifratore.");
        assertTrue("true".equals(Cifratore.cifraMonoalfabetica(mappa, "true")));
        assertFalse("aa".equals(Cifratore.cifraMonoalfabetica(mappa, "aaa")));
        assertFalse("zzz".equals(Cifratore.cifraMonoalfabetica(mappa, "aaa")));
        assertFalse("false".equals(Cifratore.cifraMonoalfabetica(mappa, "true")));
    }

    /**
     * Test of decifraMonoalfabetica method, of class Cifratore.
     */
    @Test
    public void testDecifraMonoalfabetica() {
        System.out.println("Test of decifraMonoalfabetica method, of class Cifratore.");
        assertEquals("aaa", Cifratore.decifraMonoalfabetica(mappa, "aaa"));
        assertFalse("aa".equals(Cifratore.decifraMonoalfabetica(mappa, "aaa")));
        assertFalse("zzz".equals(Cifratore.decifraMonoalfabetica(mappa, "aaa")));
    }

}
