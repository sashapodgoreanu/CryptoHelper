/*
 * Copyright 2014 st106342.
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
package cryptohelper.proxy;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author st106342
 */
public class ProxyFrequenzaFillerTest {
    //ProxyFrequenzaFiller instance;

    public ProxyFrequenzaFillerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    //inizizlizare parametri locali
    @Before
    public void setUp() {
       //instance = new  ProxyFrequenzaFiller("frequenzeIta.txt","bgItaliano.txt");
        // System.out.println("inizializa instance");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getFreq method, of class ProxyFrequenzaFiller.
     */
    /* @Test
     public void testGetFreq() throws Exception {
        System.out.println("Test of getFreq method, of class ProxyFrequenzaFiller.");
        double[] result = instance.getFreq();
        for (int i = 0; i < result.length; i++) {
           System.out.println(result[i]); 
        }
        

    }
*/
    /**
     * Test of getBigrammi method, of class ProxyFrequenzaFiller.
     */
    /* @Test
     public void testGetBigrammi() throws Exception {
        System.out.println("getBigrammi");
        int[][] result = instance.getBigrammi();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                System.out.print(result[i][j]); 
            }
            System.out.println(); 
        }
        
        //assertArrayEquals(expResult, result);
    }*/

}
