package cryptohelper.data;

import cryptohelper.data.proxy.ProxyFrequenzaFiller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AnalisiFrequenza {

    private static final Log log = LogFactory.getLog(Messaggio.class);

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
        calcolaAnalisiBigrami();
        if (lingua.endsWith(Lingua.italiano)) {
            proxyFile = new ProxyFrequenzaFiller("frequenzeIta.txt", "bgItaliano.txt");
        } else if (lingua.endsWith(Lingua.inglese)) {
            proxyFile = new ProxyFrequenzaFiller("frequenzeIng.txt", "bgInglese.txt");
        }

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
            log.fatal("Null");
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

    /**
     * Metodo ausiliario che calcola i bigrammi nel testoCifrato e riempe
     * l'array bigrammiTesto con i respetivi numeri di bigrammi
     */
    private void calcolaAnalisiBigrami() {
        if (testoCifrato == null) {
            log.fatal("Null");
            throw new NullPointerException("testoCifrato is null!");
        }
        testoCifrato = testoCifrato.toUpperCase(); //trasforma caratteri in maiuscolo - facilita il conteggio
        bigrammiTesto = new int[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                bigrammiTesto[i][j] = 0;
            }
        }
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                String bigrammo = (char) (i + 65) + "" + (char) (j + 65);
                //System.out.print(bigrammo + " ");
                int numBigramm = StringUtils.countMatches(testoCifrato, bigrammo);
                bigrammiTesto[i][j] = numBigramm;
                numBigrammi = numBigrammi + numBigramm;
            }
            //System.out.println();
        }
    }

    /**
     * Restituisce una Map con bigrammi del carattere ch. Puo essere utilizato
     * per visualizare i bigrammi del testoCifrato che viene passato al
     * costrutore
     *
     * @param ch carattere
     * @return una Map di tutte le occorenze dei caraterri prima e dopo
     * carattere ch
     */
    public Map<Character, ArrayList<Integer>> getBigramiMsg(char ch) {
        Map<Character, ArrayList<Integer>> hMap = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            ArrayList<Integer> arrL = new ArrayList<>();
            arrL.add(bigrammiTesto[ch - 65][i]);
            hMap.put((char) (i + 65), arrL);
        }
        for (int i = 0; i < 26; i++) {
            ArrayList<Integer> arrL = hMap.get((char) (i + 65));
            arrL.add(bigrammiTesto[i][ch - 65]);
            hMap.put((char) (i + 65), arrL);
        }
        return hMap;
    }

    /**
     * Restituisce una Map con bigrammi del carattere ch nella lingua che è
     * stato passato come parametro al costruttore. Puo essere utilizato per
     * visualizare i bigrammi di una lingua che viene passato al costrutore.
     *
     * @param ch carattere
     * @return una Map di tutte le occorenze dei caraterri prima e dopo
     * carattere ch
     */
    public Map<Character, ArrayList<Integer>> getBigramiLingua(char ch) {
        Map<Character, ArrayList<Integer>> hMap = new HashMap<>();
        int[][] bigrammiLingua = null;
        try {
            bigrammiLingua = proxyFile.getBigrammi();
        } catch (IOException ex) {
            log.fatal(ex.getMessage());
        }
        for (int i = 0; i < 26; i++) {
            ArrayList<Integer> arrL = new ArrayList<>();
            arrL.add(bigrammiLingua[ch - 65][i]);
            hMap.put((char) (i + 65), arrL);
        }
        for (int i = 0; i < 26; i++) {
            ArrayList<Integer> arrL = hMap.get((char) (i + 65));
            arrL.add(bigrammiLingua[i][ch - 65]);
            hMap.put((char) (i + 65), arrL);
        }
        return hMap;
    }

    public double[] getFrequenzaLingua() {
        try {
            return proxyFile.getFreq();
        } catch (IOException ex) {
            log.fatal(ex.getMessage());
        }
        return null;
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
