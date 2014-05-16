/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.data;

/**
 *
 * @author st116628
 */
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
    public char map(char c) {
        for (int i = 0; i < 26; i++) {
            if (mappa[i] == c) {
                return mappaInversa[i];
            }
        }
        return 1;
    }

    public char inverseMap(char c) {
        for (int i = 0; i < 26; i++) {
            if (mappaInversa[i] == c) {
                return mappa[i];
            }
        }
        return 0;
    }
  
    public static char getChar(int posizione){
        return mappa[posizione];
    }
    
    public static int getposizione(char carattere){
        for (int i = 0; i < mappa.length; i++) {
            if (mappa[i] == carattere)
                return i;
        }
        return -1;
    }

    public void setMappaInversa(char[] mappaInversa) {
        this.mappaInversa = mappaInversa;
    }
}
