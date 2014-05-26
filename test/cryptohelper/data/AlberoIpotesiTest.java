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
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author SashaAlexandru
 */
public class AlberoIpotesiTest {

    Ipotesi ip1;
    Ipotesi ip2;
    Ipotesi ip3;
    Ipotesi ip4;
    Ipotesi ip5;

    public AlberoIpotesiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ip1 = new Ipotesi('a', 'b');
        ip2 = new Ipotesi('c', 'd');
        ip3 = new Ipotesi('f', 'g');
        ip4 = new Ipotesi('s', 'g');
        ip5 = new Ipotesi('h', 'y');
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of cerca method, of class AlberoIpotesi.
     */
    @SuppressWarnings("unused")
    @Test
    @Ignore
    public void testCerca() {
        System.out.println("cerca");
        Mossa mossa = null;
        AlberoIpotesi instance = new AlberoIpotesi();
        boolean expResult = false;
        boolean result = instance.cerca(mossa);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addIpotesi method, of class AlberoIpotesi.
     */
    @Test
    public void testAddIpotesi() {
        System.out.println("Test of addIpotesi method, of class AlberoIpotesi.");

        AlberoIpotesi alberoIpotesi = new AlberoIpotesi();
        //boolean expResult = false;
        boolean result = alberoIpotesi.addIpotesi(ip1);
        result = alberoIpotesi.addIpotesi(ip2);
        result = alberoIpotesi.addIpotesi(ip3);
        result = alberoIpotesi.addIpotesi(ip4);
        result = alberoIpotesi.addIpotesi(ip5);
        alberoIpotesi.display();
        //to do
        //assertEquals(expResult, result);
    }

    /**
     * Test of getIpotesiCorrente method, of class AlberoIpotesi.
     */
    @Test
    @Ignore
    public void testGetIpotesiCorrente() {
        System.out.println("getIpotesiCorrente");
        AlberoIpotesi instance = new AlberoIpotesi();
        Ipotesi expResult = null;
        Ipotesi result = instance.getIpotesiCorrente();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of effettuaSostituzione method, of class AlberoIpotesi.
     */
    @Test
    @Ignore
    public void testEffettuaSostituzione() {
        System.out.println("effettuaSostituzione");
        char ch1 = ' ';
        char ch2 = ' ';
        AlberoIpotesi instance = new AlberoIpotesi();
        String expResult = "";
        String result = instance.effettuaSostituzione(ch1, ch2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class AlberoIpotesi.
     */
    @SuppressWarnings("unused")
    @Test
    @Ignore
    public void testIsEmpty() {
        System.out.println("isEmpty");
        AlberoIpotesi instance = new AlberoIpotesi();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createIpotesiCorrente method, of class AlberoIpotesi.
     */
    @SuppressWarnings("unused")
    @Test
    @Ignore
    public void testCreateIpotesiCorrente() {
        System.out.println("createIpotesiCorrente");
        char ch1 = ' ';
        char ch2 = ' ';
        AlberoIpotesi instance = new AlberoIpotesi();
        Ipotesi expResult = null;
        Ipotesi result = instance.createIpotesiCorrente(ch1, ch2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoot method, of class AlberoIpotesi.
     */
    @SuppressWarnings("unused")
    @Test
    @Ignore
    public void testGetRoot() {
        System.out.println("getRoot");
        AlberoIpotesi instance = new AlberoIpotesi();
        Ipotesi expResult = null;
        Ipotesi result = instance.getRoot();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class AlberoIpotesi.
     */
    @SuppressWarnings("unused")
    @Test
    @Ignore
    public void testToString() {
        System.out.println("toString");
        AlberoIpotesi instance = new AlberoIpotesi();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
