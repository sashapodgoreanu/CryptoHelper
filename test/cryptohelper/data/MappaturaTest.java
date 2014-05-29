
package cryptohelper.data;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
public class MappaturaTest {

    Mappatura instance;

    public MappaturaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new Mappatura();
        char[] inversemap = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        instance.setMappaInversa(inversemap);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of map method, of class Mappatura.
     */
    @Test
    public void testMap() {
        System.out.println("Test of map method, of class Mappatura.");
        assertEquals('a', instance.mapOf('a'));
        assertEquals('b', instance.mapOf('b'));
        assertEquals('z', instance.mapOf('z'));
    }

    /**
     * Test of inverseMap method, of class Mappatura.
     */
    @Test
    public void testInverseMap() {
        System.out.println("Test of inverseMap method, of class Mappatura.");
        assertEquals('a', instance.inverseMapOf('a'));
        assertEquals('b', instance.inverseMapOf('b'));
        assertEquals('z', instance.inverseMapOf('z'));
    }

    /**
     * Test of getChar method, of class Mappatura.
     */
    @Test
    public void testGetChar() {
        System.out.println("Test of getChar method, of class Mappatura.");
        assertEquals('a', instance.getChar(0));
        assertEquals('z', instance.getChar(25));

    }

    /**
     * Test of getposizione method, of class Mappatura.
     */
    @Test
    public void testGetposizione() {
        System.out.println("Test of getposizione method, of class Mappatura.");
        assertEquals(0, instance.getposizione('a'));
        assertEquals(25, instance.getposizione('z'));
    }

}
