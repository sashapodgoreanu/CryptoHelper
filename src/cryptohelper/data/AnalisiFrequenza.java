package cryptohelper.data;

import cryptohelper.data.proxy.ProxyFrequenzaFiller;
import java.util.ArrayList;
import java.util.Map;

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
        numBigrammi = 0;
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
        bigrammiTesto = new int[26][26];
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
                //se chEsaminato ha i ambi caratteri vecini che sono lettere.
                if (i > 0 && i < testoCifrato.length() - 1) {
                    chPrima = testoCifrato.charAt(i - 1);
                    chDopo = testoCifrato.charAt(i + 1);
                    if (chPrima >= 'A' && chPrima <= 'Z') {
                        int num = bigrammiTesto[(int) (chPrima) - 65][(int) (chEsaminato) - 65];
                        num++;
                        bigrammiTesto[(int) (chPrima) - 65][(int) (chEsaminato) - 65] = num;
                        numBigrammi++;
                    }
                    if (chDopo >= 'A' && chDopo <= 'Z') {
                        int num = bigrammiTesto[(int) (chEsaminato) - 65][(int) (chDopo) - 65];
                        num++;
                        bigrammiTesto[(int) (chEsaminato) - 65][(int) (chDopo) - 65] = num;
                        numBigrammi++;
                    }
                }//se chEsaminato non ha chPrima null
                else if (i != 0) {
                    chPrima = testoCifrato.charAt(i - 1);
                    if (chPrima >= 'A' && chPrima <= 'Z') {
                        int num = bigrammiTesto[(int) (chPrima) - 65][(int) (chEsaminato) - 65];
                        num++;
                        bigrammiTesto[(int) (chPrima) - 65][(int) (chEsaminato) - 65] = num;
                        numBigrammi++;
                    }

                }//se chEsaminato non ha chDopo null
                else if (i != 0) {
                    chDopo = testoCifrato.charAt(i + 1);
                    if (chDopo >= 'A' && chDopo <= 'Z') {
                        int num = bigrammiTesto[(int) (chEsaminato) - 65][(int) (chDopo) - 65];
                        num++;
                        bigrammiTesto[(int) (chEsaminato) - 65][(int) (chDopo) - 65] = num;
                        numBigrammi++;
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

        if (frequenzaTestoCifrato != null) {
            System.out.println("FREQUENZA:");
            for (int i = 'A'; i <= 'Z'; i++) {
                System.out.print((char) (i) + "=" + frequenzaTestoCifrato[i - 65] + " ");
            }
            System.out.println();
        }
        if (bigrammiTesto != null) {

            System.out.println("BIGRAMMI: " + numBigrammi);
            System.out.print("  ");
            for (int i = 'A'; i <= 'Z'; i++) {
                System.out.print((char) i + " ");
            }
            System.out.println();
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (j == 0) {
                        System.out.print((char) (i + 65) + " ");
                    }
                    System.out.print(bigrammiTesto[i][j] + " ");
                }
                System.out.println();
            }
        }

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

    public double[] getFrequenzaTestoCifrato() {
        return frequenzaTestoCifrato;
    }

    public void setFrequenzaTestoCifrato(double[] frequenzaTestoCifrato) {
        this.frequenzaTestoCifrato = frequenzaTestoCifrato;
    }

}