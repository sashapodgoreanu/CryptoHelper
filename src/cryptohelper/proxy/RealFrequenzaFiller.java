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

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Luigi
 */
public class RealFrequenzaFiller extends FrequenzaFiller {

    private double[] frequenza;
    private int[][] bigrammi;

    public RealFrequenzaFiller(String frequenzeFile, String bigrammiFile) {
        super(frequenzeFile, bigrammiFile);
        System.out.println("Creating a real handler ");
    }

    @Override
    public double[] getFreq() throws IOException {

        System.out.println("accessing the file");
        
        FileReader fin = new FileReader(super.getFileFreq());

        frequenza = new double[26];
        Scanner src = new Scanner(fin);

        for (int i = 0; i < 26; i++) {
            if (src.hasNextDouble()) {
                frequenza[i] = src.nextDouble();
   //             System.out.println(frequenza[i]);
            } else {//se non è un intero consumalo
                src.next();
            }
        }
        fin.close();
        return frequenza;
    }

    @Override
    public int[][] getBigrammi() throws IOException {

        System.out.println("accessing the file");
        FileReader fin = new FileReader(super.getFileBigrammi());

        bigrammi = new int[26][26];
        Scanner src = new Scanner(fin);

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (src.hasNextInt()) {
                    bigrammi[i][j] = src.nextInt();
        //            System.out.println(bigrammi[i][j]);
                } else {//se non è un intero consumalo
                    src.next();
                }
            }

        }
        fin.close();
        return bigrammi;

    }

}
