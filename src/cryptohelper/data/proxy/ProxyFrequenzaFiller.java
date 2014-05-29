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
package cryptohelper.data.proxy;

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
    private int[][] bigrammi;

    public ProxyFrequenzaFiller(String frequenzeFile, String bigrammiFile) {
        super(frequenzeFile, bigrammiFile);
        System.out.println("Creating a proxy cache");
    }

    @Override
    public double[] getFreq() throws IOException {

        if (realFiller == null) {
            realFiller = new RealFrequenzaFiller(super.getFileFreq(), super.getFileBigrammi());
            frequenza = realFiller.getFreq();
            return frequenza;

        } else {
            System.out.println("accessing from proxy cache");
             return frequenza;
        }

    }

    @Override
    public int[][] getBigrammi() throws IOException {

        if (realFiller == null) {
            realFiller = new RealFrequenzaFiller(super.getFileFreq(), super.getFileBigrammi());
            bigrammi = realFiller.getBigrammi();
            return bigrammi;
        } else {
            System.out.println("accessing from proxy cache");
            return bigrammi;
        } //chiudo else

    }

}
