package cryptohelper.data.proxy;

import java.io.IOException;

public class ProxyFrequenzaFiller extends FrequenzaFiller {

    private RealFrequenzaFiller realFiller;
    private double[] frequenza;
    private int[][] bigrammi;
    private boolean hasLoadedFrequenza;
    private boolean hasLoadedBigrammi;

    public ProxyFrequenzaFiller(String frequenzeFile, String bigrammiFile) {
        super(frequenzeFile, bigrammiFile);
        hasLoadedFrequenza = false;
        hasLoadedBigrammi = false;
        System.out.println("Creating a proxy cache");
    }

    @Override
    public double[] getFreq() throws IOException {

        if (!hasLoadedFrequenza) {
            System.out.println("accessing from file  --  getFreq()");
            realFiller = new RealFrequenzaFiller(super.getFileFreq(), super.getFileBigrammi());
            frequenza = realFiller.getFreq();
            hasLoadedFrequenza = true;
            return frequenza;
        } else {
            System.out.println("accessing from proxy cache  --  getFreq()");
            return frequenza;
        }
    }

    @Override
    public int[][] getBigrammi() throws IOException {

        if (!hasLoadedBigrammi) {
            System.out.println("accessing from file  -- getBigrammi()");
            realFiller = new RealFrequenzaFiller(super.getFileFreq(), super.getFileBigrammi());
            bigrammi = realFiller.getBigrammi();
            hasLoadedBigrammi = true;
            return bigrammi;
        } else {
            System.out.println("accessing from proxy cache -- getBigrammi()");
            return bigrammi;
        }
    }
}
