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

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author SashaAlexandru
 */
public class CalcolatoreParolaChiaveTest {
    Mappatura result;
    
    public CalcolatoreParolaChiaveTest() {
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
     * Test of calcola method, of class CalcolatoreParolaChiave.
     */
    @Test
    public void testCalcola() {
        System.out.println("Test of calcola method, of class CalcolatoreParolaChiave.");
        String chiave = "comestai";
        CalcolatoreParolaChiave instance = new CalcolatoreParolaChiave();
        result = instance.calcola(chiave);
        char[] expResult = {'c', 'o', 'm', 'e', 's', 't', 'a', 'i', 'b', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'n', 'p', 'q', 'r', 'u', 'v', 'w', 'x', 'y', 'z'};
        assertTrue(Arrays.equals(expResult, result.getMappaInversa()));

        result = instance.calcola("");
        assertTrue(Arrays.equals(result.getMappa(), result.getMappaInversa()));
        result = instance.calcola(" ");
        assertTrue(!Arrays.equals(result.getMappa(), result.getMappaInversa()));
        result = instance.calcola("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertTrue(Arrays.equals(result.getMappa(), result.getMappaInversa()));
    }
    
}
