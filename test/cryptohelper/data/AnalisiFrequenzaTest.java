/*
 * Copyright 2014 SashaAlexandru.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cryptohelper.data;

import java.util.ArrayList;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
     * Test of getBigramiMsg method, of class AnalisiFrequenza.
     */
    @Test
    public void testGetBigramiMsg() {
        System.out.println("Test of Test of getBigramiMsg method, of class AnalisiFrequenza.");
        AnalisiFrequenza analisiFrequenza = new AnalisiFrequenza("italiano", "as as as as as as as as as as aa");
        analisiFrequenza.display();
        Map<Character, ArrayList<Integer>> a = analisiFrequenza.getBigramiMsg('A');
        System.out.println(a.toString());
        assertThat(a.get('A').get(0), is(1));
        assertThat(a.get('A').get(1), is(1));
        assertThat(a.get('S').get(0), is(10));
        assertThat(a.get('S').get(1), is(0));
    }

    /**
     * Test of getBigramiLingua method, of class AnalisiFrequenza.
     */
    @Test
    public void testGetBigramiLingua() {
        System.out.println("Test of Test of getBigramiLingua method, of class AnalisiFrequenza.");
        AnalisiFrequenza analisiFrequenza = new AnalisiFrequenza("italiano", "as as as as as as as as as as aa");
        Map<Character, ArrayList<Integer>> a = analisiFrequenza.getBigramiLingua('A');
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

    /**
     * Test of getFrequenzaLingua method, of class AnalisiFrequenza.
     */
    @Test
    @Ignore
    public void testGetFrequenzaLingua() {
        System.out.println("getFrequenzaLingua");
        AnalisiFrequenza instance = null;
        double[] expResult = null;
        double[] result = instance.getFrequenzaLingua();

    }

    /**
     * Test of getCaratteriSingoli method, of class AnalisiFrequenza.
     */
    @Test
    @Ignore
    public void testGetCaratteriSingoli() {
        System.out.println("getCaratteriSingoli");
        Object aLang = null;
        AnalisiFrequenza instance = null;
        ArrayList<Character> expResult = null;
        ArrayList<Character> result = instance.getCaratteriSingoli(aLang);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCaratteriSingoliTestoCifrato method, of class
     * AnalisiFrequenza.
     */
    @Test
    @Ignore
    public void testGetCaratteriSingoliTestoCifrato() {
        System.out.println("getCaratteriSingoliTestoCifrato");
        Object aTestoCifrato = null;
        AnalisiFrequenza instance = null;
        ArrayList<Character> expResult = null;
        ArrayList<Character> result = instance.getCaratteriSingoliTestoCifrato(aTestoCifrato);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
