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
