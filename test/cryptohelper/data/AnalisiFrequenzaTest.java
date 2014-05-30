
package cryptohelper.data;

import java.util.ArrayList;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author SashaAlexandru
 */
public class AnalisiFrequenzaTest {

    public AnalisiFrequenzaTest() {
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
     * Test of getBigrammiMsg method, of class AnalisiFrequenza.
     */
    @Test
    public void testGetBigramiMsg() {
        System.out.println("Test of Test of getBigramiMsg method, of class AnalisiFrequenza.");
        AnalisiFrequenza analisiFrequenza = new AnalisiFrequenza("italiano", "as as as as as as as as as as aa");
        analisiFrequenza.display();
        Map<Character, ArrayList<Integer>> a = analisiFrequenza.getBigrammiMsg('A');
        System.out.println(a.toString());
        assertThat(a.get('A').get(0), is(1));
        assertThat(a.get('A').get(1), is(1));
        assertThat(a.get('S').get(0), is(10));
        assertThat(a.get('S').get(1), is(0));
    }

    /**
     * Test of getBigrammiLingua method, of class AnalisiFrequenza.
     */
    @Test
    public void testGetBigramiLingua() {
        System.out.println("Test of Test of getBigramiLingua method, of class AnalisiFrequenza.");
        AnalisiFrequenza analisiFrequenza = new AnalisiFrequenza("italiano", "as as as as as as as as as as aa");
        Map<Character, ArrayList<Integer>> a = analisiFrequenza.getBigrammiLingua('A');
        System.out.println(a.toString());
        assertThat(a.get('A').get(0), is(21));
        assertThat(a.get('A').get(1), is(21));
        assertThat(a.get('B').get(0), is(4));
        assertThat(a.get('B').get(1), is(13));
        assertThat(a.get('C').get(0), is(41));
        assertThat(a.get('C').get(1), is(60));
        assertThat(a.get('Z').get(0), is(18));
        assertThat(a.get('Z').get(1), is(22));
    }

    /**
     * Test of getFrequenzaMsg method, of class AnalisiFrequenza.
     */
    @Test
    public void testGetFrequenzaMsg() {
        System.out.println("Test of getFrequenzaMsg method, of class AnalisiFrequenza.");
        AnalisiFrequenza analisiFrequenza = new AnalisiFrequenza("italiano", "aaaaaaaaaa");
        assertThat(100.00, is(analisiFrequenza.getFrequenzaMsg()[0]));
        analisiFrequenza = new AnalisiFrequenza("italiano", "aaabbb");
        assertThat(50.00, is(analisiFrequenza.getFrequenzaMsg()[0]));
        assertThat(50.00, is(analisiFrequenza.getFrequenzaMsg()[1]));
        for (int i = 2; i < 26; i++) {
            assertThat(0.00, is(analisiFrequenza.getFrequenzaMsg()[i]));
        }

    }
}
