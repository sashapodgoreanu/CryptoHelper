package cryptohelper.data.proxy;

import java.io.IOException;

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
            System.out.println("accessing from file");
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
            System.out.println("accessing from file");
            realFiller = new RealFrequenzaFiller(super.getFileFreq(), super.getFileBigrammi());
            bigrammi = realFiller.getBigrammi();
            return bigrammi;
        } else {
            System.out.println("accessing from proxy cache");
            return bigrammi;
        } //chiudo else

    }
}