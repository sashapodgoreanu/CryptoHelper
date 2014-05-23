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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SashaAlexandru
 */
public class CalcolatorePseudocasualeTest {

    Mappatura result;

    public CalcolatorePseudocasualeTest() {
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
     * Test of calcola method, of class CalcolatorePseudocasuale.
     */
    @Test
    public void testCalcola() {
        System.out.println("Test of calcola method, of class CalcolatorePseudocasuale.");
        String chiave = "1";
        CalcolatorePseudocasuale instance = new CalcolatorePseudocasuale();
        Mappatura result = instance.calcola(chiave);
        char[] a = result.getMappaInversa();
        boolean duplicati = false;
        for (int i = 0; i < 25; i++) {
            for (int j = i + 1; j < 26; j++) {
                if (a[i] == a[j]) {
                    duplicati = true;
                }
            }

        }    
        boolean found = true;
        for (int i = 'a'; found && i <= 'z'; i++) {
            found = false;
            for (int j = 0; !found && j < 26; j++) {
                if (i == a[j]) {
                    found = true;
                } else found = false;
            }
        }
        boolean existsAll = found;
        assertTrue(existsAll);
        assertFalse(duplicati);

    }

}
