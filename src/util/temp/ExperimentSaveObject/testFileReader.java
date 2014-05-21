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

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class testFileReader {

    public static void main(String args[]) throws IOException {

        //int i = 0;
        double d;
        boolean b;
        String str;

        //FileWriter fout = new FileWriter("test.txt");
        FileReader fin = new FileReader("bgItaliano.txt");
        Map<Character, ArrayList<Integer>> a = new HashMap<>();


        int[][] tabella = new int[26][26];
        Scanner src = new Scanner(fin);

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (src.hasNextInt()) {
                    tabella[i][j] = src.nextInt();
                } else {//se non è un intero consumalo
                    src.next();
                }
            }
        }

        fin.close();

        for (int i = 0; i < 26; i++) {
            System.out.println();
            for (int j = 0; j < 26; j++) {
                System.out.print(tabella[i][j]+" ");
            }

        }
    }
}
