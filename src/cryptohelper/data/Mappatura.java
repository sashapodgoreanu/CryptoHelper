package cryptohelper.data;

public class Mappatura {

    private static final char[] mappa = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private char[] mappaInversa;

    public Mappatura() {
        mappaInversa = new char[26];
    }

    /**
     * Il metodo restituisce mappatura di un carattere c
     *
     * @param c carattere di quale si vuole la mappatura
     * @return carattere mappato di c
     */
    public char mapOf(char c) {
        for (int i = 0; i < 26; i++) {
            if (mappa[i] == c) {
                return mappaInversa[i];
            }
        }
        return 1;
    }

    public char inverseMapOf(char c) {
        for (int i = 0; i < 26; i++) {
            if (mappaInversa[i] == c) {
                return mappa[i];
            }
        }
        return 0;
    }

    public char getChar(int posizione) {
        return mappa[posizione];
    }

    public int getposizione(char carattere) {
        for (int i = 0; i < mappa.length; i++) {
            if (mappa[i] == carattere) {
                return i;
            }
        }
        return -1;
    }

    public void setMappaInversa(char[] mappaInversa) {
        this.mappaInversa = mappaInversa;
    }

    public char[] getMappa() {
        return mappa;
    }

    public char[] getMappaInversa() {
        return mappaInversa;
    }

    @Override
    public String toString() {
        String a = "Mappatura- Inversemap{";
        for (int i = 0; i < 26; i++) {
            a = a + "," + mappaInversa[i];
        }
        return a + '}';
    }

}
