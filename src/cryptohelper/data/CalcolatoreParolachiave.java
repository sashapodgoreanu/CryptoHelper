/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cryptohelper.data;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
public class CalcolatoreParolachiave extends CalcolatoreMappatura {

    @Override
    public Mappatura calcola(String chiave) {
        Mappatura m = new Mappatura();
        char[] res = new char[26];
        for (int i = 0; i < chiave.length(); i++) {
           res[i] = chiave.charAt(i);
        }
        m.setMappaInversa(res);
        return m;
    }   
}
