/*
 * Copyright 2014 Sasha Alexandru Podgoreanu.
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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
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
