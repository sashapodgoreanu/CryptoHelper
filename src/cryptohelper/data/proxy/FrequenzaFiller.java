//Classe astratta del pattern proxy. E' estesa sia da ProxyFrequenzaFiller, sia da ReaFrequenzaFiller

package cryptohelper.data.proxy;

import java.io.IOException;

public abstract class FrequenzaFiller {

    protected String fileFreq;
    protected String fileBigrammi;

    public FrequenzaFiller(String frequenzeFile, String bigrammiFile) {
        fileFreq = frequenzeFile;
        fileBigrammi = bigrammiFile;
    }

    public String getFileFreq() {
        return fileFreq;
    }

    public String getFileBigrammi() {
        return fileBigrammi;
    }

    public abstract double[] getFreq() throws IOException;

    public abstract int[][] getBigrammi() throws IOException;
}
