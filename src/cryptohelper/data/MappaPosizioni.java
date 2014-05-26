package cryptohelper.data;

public class MappaPosizioni {

    private MappaPosCarattere[] mappa;

    public MappaPosizioni(String testoCifrato) {
        inizializza(testoCifrato);
    }

    public String executeMossa(Mossa mossa, String testoLavoro) {

        return null;
    }

    private void updateMappa(Mossa mossa) {

    }

    public void inizializza(String testoCifrato) {
        mappa = new MappaPosCarattere[256];
        testoCifrato = testoCifrato.toUpperCase(); //trasforma tutti i carratteri in caratteri minuscoli
        for (int i = 0; i < testoCifrato.length(); i++) {
            if (testoCifrato.charAt(i) >= 'A' || testoCifrato.charAt(i) <= 'Z') {
                addPos(testoCifrato.charAt(i), i);
            }
        }
    }

    public Mossa createMossaUndo(char ch2) {
        //System.out.println("createMossaUndoUppercase " + ch2);
        char ch1 = mappa[(int) ch2].getCarattere();
        if ((ch1 - 32) == ch2) {
            mappa[(int) ch2].setIsUpercase(true);//
        }
        return new Mossa(ch1, ch2);
    }

    public MappaPosCarattere[] getMappa() {
        return mappa;
    }

    public void setMappa(MappaPosCarattere[] mappa) {
        this.mappa = mappa;
    }

    private void addPos(char ch1, int pos) {
        boolean exists = false;
        for (int i = 'A'; i <= 'Z'; i++) {
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
        for (int i = 'A'; i <= 'Z'; i++) {
            if (this.mappa[i] != null) {
                a.append("Carattere immutabile = ").append((char) i).append(", ").append(mappa[i].toString()).append("\n");
            }
        }
        return a.toString();
    }

}
