package cryptohelper.data;

public class MappaPosizioni {

    private MappaPosCarattere[] mappa;

    public MappaPosizioni() {
        mappa = new MappaPosCarattere[256];
    }

    public String executeMossa(Mossa mossa, String testoLavoro) {
        throw new UnsupportedOperationException();
    }

    public void inizializza(String testoCifrato) {
        testoCifrato = testoCifrato.toLowerCase(); //trasforma tutti i carratteri in caratteri minuscoli
        for (int i = 0; i < testoCifrato.length(); i++) {
            if(testoCifrato.charAt(i)>= 'a' || testoCifrato.charAt(i) <='z')
                addPos(testoCifrato.charAt(i), i);
        }
    }

    public Mossa createMossaCorrente(char ch) {
        throw new UnsupportedOperationException();
    }

    public MappaPosCarattere[] getMappa() {
        return mappa;
    }

    public void setMappa(MappaPosCarattere[] mappa) {
        this.mappa = mappa;
    }

    public void addPos(char ch1, int pos) {
        boolean exists = false;
        for (int i = 'a'; i <= 'z'; i++) {
            if (mappa[i] != null && mappa[i].getCarattere() == ch1) {
                mappa[i].addPosizione(pos);
                exists = true;
            }
        }
        if (!exists) {
            MappaPosCarattere temp = new MappaPosCarattere(ch1);
            mappa[ch1] = temp;
            mappa[ch1].addPosizione(pos);
        }
    }

    @Override
    public String toString() {
        StringBuilder a = new StringBuilder();
        for(int i = 'a'; i< 'z'; i++){
            if (this.mappa[i] != null)
                a.append(mappa[i].toString()).append("\n");
        }
        return a.toString();
    }

}
