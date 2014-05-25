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
import java.io.FileNotFoundException;
import java.io.FileReader;
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

        try {
            FileInputStream fis = new FileInputStream(file);
            char current;
            while (fis.available() > 0) {
                current = (char) fis.read();
                if(current != '\t')
                    System.out.print(current);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            int i;
            while ((text = reader.readLine()) != null) {
                System.out.println(text);
                i = ((int) Integer.parseInt(text));
                System.out.println(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
*/
//print out the list
        System.out.println(list);

    }

}
