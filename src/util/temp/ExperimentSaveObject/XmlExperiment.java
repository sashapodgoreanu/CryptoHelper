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

package util.temp.ExperimentSaveObject;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 *
 * @author SashaAlexandru
 */
public class XmlExperiment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        XStream xstream = new XStream(new StaxDriver());
        xstream.alias("persona", Persona.class);
        Persona joe1 = new Persona("Joe1", "Walnes",null);
        Persona joe2 = new Persona("Joe2", "Walnes",joe1);
        Persona joe3 = new Persona("Joe3", "Walnes",joe2);
        Persona joe4 = new Persona("Joe4", "Walnes",joe3);
        Persona joe5 = new Persona("Joe5", "Walnes",joe4);
        Persona[] a = new Persona[5];
        a[0] = joe1;
        a[1] = joe2;
        a[4] = joe3;
        a[2] = joe4;
        a[3] = joe5;
        System.out.println("1"+joe1.toString());
        
        String xml = xstream.toXML(a);
        System.out.println(xml);
        
        Persona[] newJoe = (Persona[])xstream.fromXML(xml);
        System.out.println("a"+newJoe[0].toString());
        
        
         

        
    }
    
}
