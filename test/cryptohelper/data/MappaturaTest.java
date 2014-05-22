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
public class MappaturaTest {
    Mappatura instance;
    
    public MappaturaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       instance = new Mappatura();
       char[] inversemap = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
       instance.setMappaInversa(inversemap);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of map method, of class Mappatura.
     */
    @Test
    public void testMap() {
        System.out.println("map");
        assertEquals('a', instance.mapOf('a'));
        assertEquals('b', instance.mapOf('b'));
        assertEquals('z', instance.mapOf('z'));
    }

    /**
     * Test of inverseMap method, of class Mappatura.
     */
    @Test
    public void testInverseMap() {
        System.out.println("inverseMap");
        assertEquals('a', instance.inverseMapOf('a'));
        assertEquals('b', instance.inverseMapOf('b'));
        assertEquals('z', instance.inverseMapOf('z'));
    }

    /**
     * Test of getChar method, of class Mappatura.
     */
    @Test
    public void testGetChar() {
        System.out.println("getChar");
        assertEquals('a', instance.getChar(0));
        assertEquals('z', instance.getChar(25));

        
    }

    /**
     * Test of getposizione method, of class Mappatura.
     */
    @Test
    public void testGetposizione() {
        System.out.println("getposizione");
        assertEquals(0, instance.getposizione('a'));
        assertEquals(25, instance.getposizione('z'));
    }
    
}
