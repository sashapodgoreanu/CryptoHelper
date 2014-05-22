/*
 * Copyright 2014 st116629.
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
 * @author st116629
 */
public class CalcolatoreCesareTest {
    Mappatura result;
    
    public CalcolatoreCesareTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        result = new Mappatura();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calcola method, of class CalcolatoreCesare.
     */
    @org.junit.Test
    public void testCalcola() {
        System.out.println("calcola");
        CalcolatoreCesare instance = new CalcolatoreCesare();
        //'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        //'z','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y'};
        //'y', 'z','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x'};
        
        result = instance.calcola("0");
        assertEquals('a' - result.mapOf('a'),0);
        assertEquals('b'-  result.mapOf('b'),0);
        assertEquals('x'-  result.mapOf('x'),0);  
        assertEquals('x' - result.mapOf('x'),0);  
        result = instance.calcola("1");
        assertEquals('z' - result.mapOf('a'),0);
        assertEquals('a'- result.mapOf('b'), 0);
        assertEquals('y'- result.mapOf('z'),0);
        result = instance.calcola("2");
        assertEquals('y'- result.mapOf('a'),0);
        assertEquals('z'-result.mapOf('b'),0);
        assertEquals('x' - result.mapOf('z'),0);
        
       
    }
    
}
