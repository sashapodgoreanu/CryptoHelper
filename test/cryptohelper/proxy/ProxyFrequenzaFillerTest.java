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
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
    @Test
    public void testGetFreq() throws Exception {
        ProxyFrequenzaFiller instance = new ProxyFrequenzaFiller("frequenzeIta.txt", "bgItaliano.txt");
        String expextedString = "11.5 1.0 4.7 3.7 12.1 1.1 1.7 1.4 9.5 0.0 0.0 5.6 2.4 7.3 9.6 3.0 0.8 6.6 5.5 6.1 3.6 2.3 0.0 0.0 0.0 0.8 ";

        double[] b = instance.getFreq();
        String resultString = "";
        for (int i = 0; i < b.length; i++) {
            resultString = resultString + b[i] + " ";
        }
        assertEquals(expextedString, resultString); 
        
        resultString = "";

        b = instance.getFreq(); // test cache
        for (int i = 0; i < b.length; i++) {
            resultString = resultString + b[i] + " ";
        }        
        assertEquals(expextedString, resultString); // test cache        
    }

    /**
     * Test of getBigrammi method, of class ProxyFrequenzaFiller.
     */
    @Test
    public void testGetBigrammi() throws Exception {
        System.out.println("getBigrammi");
        ProxyFrequenzaFiller instance = new ProxyFrequenzaFiller("frequenzeIta.txt", "bgItaliano.txt");

        String expextedString = "21 4 41 49 47 15 7 11 109 0 0 118 78 50 40 37 0 130 33 116 41 32 0 0 0 18 \n"
                + "13 17 0 0 16 0 0 0 10 0 0 0 5 0 10 0 0 4 0 0 4 0 0 0 0 0 \n"
                + "60 0 28 0 67 0 0 0 91 0 0 15 0 38 42 0 0 14 35 0 9 0 0 0 0 0 \n"
                + "65 0 0 1 113 0 0 0 69 0 0 4 0 55 71 0 0 9 0 0 11 0 0 0 0 0 \n"
                + "29 15 35 120 37 16 15 64 64 0 0 124 85 114 27 62 0 146 77 154 50 39 0 0 0 4 \n"
                + "13 0 0 0 13 7 0 0 21 0 0 3 0 11 12 0 0 4 6 0 1 0 0 0 0 0 \n"
                + "29 0 0 0 34 0 15 0 24 0 0 4 0 15 31 0 0 7 0 0 4 0 0 0 0 0 \n"
                + "1 0 83 0 4 0 7 0 2 0 0 0 0 1 1 0 0 0 0 0 0 0 0 0 0 0 \n"
                + "25 27 51 166 57 26 44 25 19 0 0 78 32 62 35 57 0 109 104 83 20 28 0 0 0 40 \n"
                + "2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
                + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
                + "138 2 6 0 158 1 18 0 77 0 0 111 0 1 91 9 0 9 0 1 16 0 0 0 0 0 \n"
                + "48 0 0 0 54 0 0 0 72 0 0 14 7 9 45 0 0 16 3 4 8 0 0 0 0 0 \n"
                + "145 0 0 0 154 0 10 0 125 0 0 4 0 15 172 0 0 15 0 0 59 0 0 0 0 0 \n"
                + "6 5 121 41 23 14 14 1 113 1 0 59 43 125 6 62 0 69 59 106 20 15 0 0 0 5 \n"
                + "46 0 0 0 44 0 0 0 46 0 0 17 29 8 52 19 0 4 24 0 12 0 0 0 0 0 \n"
                + "11 0 2 0 18 0 0 0 17 0 0 3 0 9 8 0 0 2 1 0 2 0 0 0 0 0 \n"
                + "125 8 9 2 181 9 21 0 29 0 0 1 0 1 99 41 0 23 0 73 28 2 0 0 0 0 \n"
                + "69 0 0 0 154 0 0 0 78 0 0 15 0 25 108 0 0 22 67 0 14 0 0 0 0 0 \n"
                + "93 0 0 0 62 0 0 0 50 0 0 37 0 117 34 0 0 46 102 58 26 0 0 0 0 0 \n"
                + "12 2 22 19 10 7 9 0 34 0 0 24 3 13 7 14 76 8 40 29 0 0 0 0 0 0 \n"
                + "24 0 0 0 24 0 0 0 30 0 0 2 0 5 17 0 0 11 0 0 3 3 0 0 0 0 \n"
                + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
                + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
                + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
                + "22 0 0 0 6 0 0 0 11 0 0 0 0 21 1 0 0 3 0 0 3 0 0 0 0 5 \n";
        int[][] result = instance.getBigrammi();
        String resultString = "";

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                resultString = resultString + result[i][j] + " ";
            }
            resultString = resultString + "\n";
        }
        assertEquals(expextedString, resultString);
        resultString = "";

        result = instance.getBigrammi(); // test cache

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                resultString = resultString + result[i][j] + " ";
            }
            resultString = resultString + "\n";
        }
        
       assertEquals(expextedString, resultString); // test cache
        
    }
}
