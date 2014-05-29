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
        numCaratteri = testoCifrato.length();
    }

    /**
     * Utiliza questo metodo con NumberFormat formatter = new
     * DecimalFormat("#0.00"); formatter.format(doubleNumber);
     *
     * @return array con le frequenze del testoCifrato
     */
    public double[] getFrequenzaMsg() {
        calcolaAnalisiFrequenza();
        return frequenzaTestoCifrato;
    }

    /**
     * Metodo ausiliario che calcola la frequenza dei caratteri nel testoCifrato
     * e riempe il array frequenzaTestoCifrato con le frequenza
     */
    private void calcolaAnalisiFrequenza() {
        if (testoCifrato == null) {
            throw new NullPointerException("testoCifrato is null!");
        }
        frequenzaTestoCifrato = new double[26];
        for (int i = 0; i < frequenzaTestoCifrato.length; i++) {
            frequenzaTestoCifrato[i] = 0;
        }
        testoCifrato = testoCifrato.toUpperCase(); //trasforma caratteri in maiuscolo - facilita il conteggio
        //conteggio dei ogni singoli caratteri
        for (int i = 0; i < testoCifrato.length(); i++) {
            for (int j = 'A'; j <= 'Z'; j++) {
                if (testoCifrato.charAt(i) == j) {
                    double temp = frequenzaTestoCifrato[(int) j - 65];
                    temp++;
                    frequenzaTestoCifrato[(int) j - 65] = temp;
                }
            }
        }
        //il array viene modificato per contenere frequenze
        for (int i = 0; i < 26; i++) {
            double temp = frequenzaTestoCifrato[i];
            temp = (temp / numCaratteri) * 100;
            frequenzaTestoCifrato[i] = temp;
        }
    }

    public void calcolaAnalisiBigrami() {
        if (testoCifrato == null) {
            throw new NullPointerException("testoCifrato is null!");
        }
        testoCifrato = testoCifrato.toUpperCase(); //trasforma caratteri in maiuscolo - facilita il conteggio
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                bigrammiTesto[i][j] = 0;
            }
        }

        for (int i = 0; i < testoCifrato.length(); i++) {
            char chPrima;
            char chEsaminato = testoCifrato.charAt(i);
            char chDopo;
            if (chEsaminato >= 'A' && chEsaminato <= 'Z') {
                //chEsaminato ha i ambi caratteri vecini che sono lettere
                if (i != 0 || i != testoCifrato.length() - 1) {
                    chPrima = testoCifrato.charAt(i - 1);
                    chDopo = testoCifrato.charAt(i + 1);
                    if (chPrima >= 'A' && chPrima <= 'Z'){
                        int num = bigrammiTesto[(int) (chPrima) - 65][(int) (chEsaminato) - 65];
                        bigrammiTesto[(int) (chPrima) - 65][(int) (chEsaminato) - 65] = num++;
                    }
                    if (chDopo >= 'A' && chDopo <= 'Z') {
                        int num = bigrammiTesto[(int) (chEsaminato) - 65][(int) (chDopo) - 65];
                        bigrammiTesto[(int) (chEsaminato) - 65][(int) (chDopo) - 65] = num++;
                    }
                }
            }

        }

    }

    public Map<Character, ArrayList<Integer>> getBigramiMsg(char ch) {
        throw new UnsupportedOperationException();
    }

    public Map<Character, ArrayList<Integer>> getBigramiTestoCifrato(char ch) {
        throw new UnsupportedOperationException();
    }

    public double[] getFrequenzaLingua() {
        throw new UnsupportedOperationException();
    }

    public void display() {
        for (int i = 'A'; i <= 'Z'; i++) {
            System.out.print((char) (i) + "=" + frequenzaTestoCifrato[i - 65] + " ");
        }
        System.out.print("/n");
    }

    /**
     * **** Next Patch *************************
     */
    public ArrayList<Character> getCaratteriSingoli(Object aLang) {
        throw new UnsupportedOperationException();
    }

    public ArrayList<Character> getCaratteriSingoliTestoCifrato(Object aTestoCifrato) {
        throw new UnsupportedOperationException();
    }

    /**
     * **** Next Patch *************************
     */
    public double[] getFrequenzaTestoCifrato() {
        return frequenzaTestoCifrato;
    }

    public void setFrequenzaTestoCifrato(double[] frequenzaTestoCifrato) {
        this.frequenzaTestoCifrato = frequenzaTestoCifrato;
    }

}
