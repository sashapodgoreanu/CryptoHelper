package cryptohelper.data.proxy;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RealFrequenzaFiller extends FrequenzaFiller {

    private double[] frequenza;
    private int[][] bigrammi;

    public RealFrequenzaFiller(String frequenzeFile, String bigrammiFile) {
        super(frequenzeFile, bigrammiFile);
        System.out.println("Creating a real handler");
    }

    @Override
    public double[] getFreq() throws IOException {

        System.out.println("accessing the file - heavy operation");
        try (FileReader fin = new FileReader(super.getFileFreq())) {
            frequenza = new double[26];
            Scanner src = new Scanner(fin);
            for (int i = 0; i < 26; i++) {
                if (src.hasNextDouble()) {
                    frequenza[i] = src.nextDouble();
                    //System.out.println(frequenza[i]);
                } else {//se non è un intero consumalo
                    src.next();
                }
            }
        }
        return frequenza;
    }

    @Override
    public int[][] getBigrammi() throws IOException {

        System.out.println("accessing the file - heavy operation");
        try (FileReader fin = new FileReader(super.getFileBigrammi())) {
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
        }
        return bigrammi;
    }
}
