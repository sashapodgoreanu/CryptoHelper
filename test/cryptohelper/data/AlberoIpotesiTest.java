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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
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
    AlberoIpotesi alberoIpotesi;

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
        alberoIpotesi = new AlberoIpotesi("AONJCO");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of cerca method, of class AlberoIpotesi.
     */
    @Test
    //@Ignore
    public void testCerca() {
        System.out.println("Test of cerca method, of class AlberoIpotesi.");
        String testoLavoro = "AONJCO";
        //System.out.println(alberoIpotesi.getMappaPosizioni().toString());
        testoLavoro = alberoIpotesi.effettuaSostituzione('A', 'c', testoLavoro);
        assertTrue(alberoIpotesi.cerca('A', 'c'));
        assertFalse(alberoIpotesi.cerca('a', 'c'));
        assertFalse(alberoIpotesi.cerca('c', 'a'));
        assertFalse(alberoIpotesi.cerca('C', 'A'));
        assertFalse(alberoIpotesi.cerca('A', 'C'));
        assertFalse(alberoIpotesi.cerca('a', 'C'));

        //System.out.println(alberoIpotesi.getMappaPosizioni().toString());
        testoLavoro = alberoIpotesi.effettuaSostituzione('O', 'a', testoLavoro);
        assertTrue(alberoIpotesi.cerca('O', 'a'));
        assertFalse(alberoIpotesi.cerca('o', 'a'));
        assertFalse(alberoIpotesi.cerca('a', 'o'));
        assertFalse(alberoIpotesi.cerca('O', 'A'));
        assertFalse(alberoIpotesi.cerca('A', 'o'));
        assertFalse(alberoIpotesi.cerca('a', 'O'));
        //System.out.println(testoLavoro);

        //System.out.println(alberoIpotesi.getMappaPosizioni().toString());
        testoLavoro = alberoIpotesi.effettuaSostituzione('N', 'p', testoLavoro);
        //System.out.println(testoLavoro);

        //System.out.println(alberoIpotesi.getMappaPosizioni().toString());
        testoLavoro = alberoIpotesi.effettuaSostituzione('J', 'i', testoLavoro);
        //System.out.println(testoLavoro);

        //System.out.println(alberoIpotesi.getMappaPosizioni().toString());
        testoLavoro = alberoIpotesi.effettuaSostituzione('C', 't', testoLavoro);

        //System.out.println(testoLavoro);
    }

    /**
     * Test of addIpotesi method, of class AlberoIpotesi.
     */
    @Test
    //@Ignore
    public void testAddIpotesi() {
        System.out.println("Test of addIpotesi method, of class AlberoIpotesi.");
        ip1 = new Ipotesi('H', 'w');
        boolean result = alberoIpotesi.addIpotesi(ip1);
        alberoIpotesi.display();
        assertEquals(true, result);
        assertNotNull(alberoIpotesi.getIpotesiCorrente());

        if (alberoIpotesi.addIpotesi(null)) {
            fail("Ha falito - non si puo aggiungere un'ipotesi nulla");
        }
    }

    /**
     * Test of effettuaSostituzione method, of class AlberoIpotesi.
     */
    @Test
    //@Ignore
    public void testEffettuaSostituzione() {
        String testoLavoro = "AONJCO";
        //System.out.println(alberoIpotesi.getMappaPosizioni().toString());
        testoLavoro = alberoIpotesi.effettuaSostituzione('A', 'c', testoLavoro);
        

        //System.out.println(alberoIpotesi.getMappaPosizioni().toString());
        testoLavoro = alberoIpotesi.effettuaSostituzione('O', 'a', testoLavoro);


        //System.out.println(alberoIpotesi.getMappaPosizioni().toString());
        testoLavoro = alberoIpotesi.effettuaSostituzione('N', 'p', testoLavoro);
        //System.out.println(testoLavoro);

        //System.out.println(alberoIpotesi.getMappaPosizioni().toString());
        testoLavoro = alberoIpotesi.effettuaSostituzione('J', 'i', testoLavoro);
        //System.out.println(testoLavoro);

        //System.out.println(alberoIpotesi.getMappaPosizioni().toString());
        testoLavoro = alberoIpotesi.effettuaSostituzione('C', 't', testoLavoro);
        //System.out.println(testoLavoro);
        assertEquals("capita", testoLavoro);

        alberoIpotesi = new AlberoIpotesi("A");
        testoLavoro = "A";
        testoLavoro = alberoIpotesi.effettuaSostituzione('A', 'A', testoLavoro);
        assertEquals("A", testoLavoro);

        alberoIpotesi = new AlberoIpotesi("A");
        testoLavoro = "A";
        testoLavoro = alberoIpotesi.effettuaSostituzione('A', 'A', testoLavoro);
        assertThat("a", is(not(testoLavoro)));
        assertThat("A", is(testoLavoro));

        alberoIpotesi = new AlberoIpotesi("AB");
        testoLavoro = "AB";
        System.out.println(alberoIpotesi.getMappaPosizioni().toString());
        testoLavoro = alberoIpotesi.effettuaSostituzione('B', 'A', testoLavoro);
        System.out.println(testoLavoro);
        System.out.println(alberoIpotesi.getMappaPosizioni().toString());
        assertThat("ba", is(not(testoLavoro)));
        assertThat("ab", is(not(testoLavoro)));
        assertThat("Ba", is(not(testoLavoro)));
        assertThat("bA", is(not(testoLavoro)));
        assertThat("aa", is(not(testoLavoro)));
        assertThat("aA", is(not(testoLavoro)));
        assertThat("AA", is(testoLavoro));
    }
    
    /**
     * Test of undo method, of class AlberoIpotesi.
     */
    @Test
    public void testUndo() {
        System.out.println("Test of undo method, of class AlberoIpotesi.");
        String testoLavoro = "AONJCO";
        //System.out.println(alberoIpotesi.getMappaPosizioni().toString());
        testoLavoro = alberoIpotesi.effettuaSostituzione('A', 'c', testoLavoro);
        System.out.println("testo lavoro "+testoLavoro);
        alberoIpotesi.display();
        testoLavoro = alberoIpotesi.effettuaSostituzione('C', 'd', testoLavoro);
        System.out.println("testo lavoro "+testoLavoro);
        alberoIpotesi.display();
        testoLavoro = alberoIpotesi.undo(testoLavoro);
        System.out.println("testo lavoro UNDO "+testoLavoro);
        alberoIpotesi.display();
        testoLavoro = alberoIpotesi.effettuaSostituzione('C', 'a', testoLavoro);
        System.out.println("testo lavoro "+testoLavoro);
        alberoIpotesi.display();
        //testoLavoro = alberoIpotesi.undo(testoLavoro);
        //alberoIpotesi.display();
        
    
    }
    
    
}
