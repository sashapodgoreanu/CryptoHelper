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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Luigi
 */
public class ProxyFrequenzaFiller extends FrequenzaFiller {

    private RealFrequenzaFiller realFiller;
    private double[] frequenza;
    private double[][] bigrammi;

    public ProxyFrequenzaFiller(String fName) {
        super(fName);
        System.out.println("Creating a proxy cache");
    }

    @Override
    public double[] getFreqFromFile(File file) {

        return frequenza;
    }

    public double[][] getBigrammiFromFile(File file) throws IOException {

        if (realFiller == null) {
            realFiller = new RealFrequenzaFiller(filename);

            return realFiller.getBigrammiFromFile(file);
        } else {

            System.out.println("accessing from proxy cache");
            FileReader fin = new FileReader("bgItaliano.txt");

            bigrammi = new double[26][26];
            Scanner src = new Scanner(fin);

            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (src.hasNextDouble()) {
                        bigrammi[i][j] = src.nextDouble();
                    } else {//se non è un intero consumalo
                        src.next();
                    }
                }
                fin.close();
            } 

            return bigrammi;
        } //chiudo else

    }

}
