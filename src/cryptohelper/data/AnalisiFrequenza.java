/*
 * Copyright 2014 st106342.
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

import cryptohelper.data.proxy.ProxyFrequenzaFiller;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author st106342
 */
public class AnalisiFrequenza {

    SessioneLavoro sessione;
    double[] frequenzaTestoCifrato;
    int[][] bigrammiTesto;
    int numCaratteri;
    int numBigrammi;
    String lingua;
    String testoCifrato;
    ProxyFrequenzaFiller proxyFile;

    public AnalisiFrequenza(String lingua, String testoCifrato) {
        this.lingua = lingua;
        this.testoCifrato = testoCifrato;
    }

    public void calcolaAnalisiBigrami(String testoCifrato) {
        throw new UnsupportedOperationException();
    }

    public void calcolaAnalisiFrequenza(String testoCifrato) {
        throw new UnsupportedOperationException();
    }

    public Map<Character, ArrayList<Integer>> getBigramiTestoCifrato(char ch) {
        throw new UnsupportedOperationException();
    }

    public Map<Character, ArrayList<Integer>> getBigramiMsg(char ch) {
        throw new UnsupportedOperationException();
    }

    public double[] getFrequenzaMsg() {
        throw new UnsupportedOperationException();
    }

    public double[] getFrequenzaLingua() {
        throw new UnsupportedOperationException();
    }

    public ArrayList<Character> getCaratteriSingoli(Object aLang) {
        throw new UnsupportedOperationException();
    }

    public ArrayList<Character> getCaratteriSingoliTestoCifrato(Object aTestoCifrato) {
        throw new UnsupportedOperationException();
    }

}
