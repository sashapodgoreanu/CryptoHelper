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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
        mappapos = new MappaPosizioni();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of executeMossa method, of class MappaPosizioni.
     */
    @Test
    @Ignore
    public void testExecuteMossa() {
        System.out.println("executeMossa");
        Mossa mossa = null;
        String testoLavoro = "";
        MappaPosizioni instance = new MappaPosizioni();
        String expResult = "";
        String result = instance.executeMossa(mossa, testoLavoro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
            assertNull(mappapos.getMappa()[i]);
        }
        mappapos = new MappaPosizioni();
        mappapos.inizializza(testoCifrato);
        assertEquals(mappapos.getMappa()['A'].getCarattere(), 'A');
        assertEquals(Integer.valueOf(mappapos.getMappa()['A'].getListaPos().get(0)), Integer.valueOf(0));
        assertEquals(mappapos.getMappa()['Z'].getCarattere(), 'Z');
        assertEquals(Integer.valueOf(mappapos.getMappa()['Z'].getListaPos().get(0)), Integer.valueOf(1));
    }

    /**
     * Test of createMossaCorrente method, of class MappaPosizioni.
     */
    @Test
    @Ignore
    public void testCreateMossaCorrente() {
        System.out.println("createMossaCorrente");
        char ch = ' ';
        MappaPosizioni instance = new MappaPosizioni();
        Mossa expResult = null;
        Mossa result = instance.createMossaCorrente(ch);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
