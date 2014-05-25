/*
 * Copyright 2014 Luigi.
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Luigi
 */
public class testProxy1 {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<Integer>();
        File file = new File("bgItaliano.txt");
        BufferedReader reader = null;
        int i = 0;
        
        try {
            FileInputStream fis = new FileInputStream(file);
            char current;
            String numero = "";
            String numeroCompleto = "";
            
            
            while (fis.available() > 0) {
                current = (char) fis.read();
                
                if (current != '\t') {
//                    System.out.print(current);
                    numero = ""+current;
                    numeroCompleto = numeroCompleto + numero; 
                }
                else if (current == '\t') {
                    System.out.println(numeroCompleto);
                    numeroCompleto = "";
                    i++;
                    System.out.println("i = "+i);
                }
             
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("i = "+i);

    }

}
