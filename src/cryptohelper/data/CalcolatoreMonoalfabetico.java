/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.data;

import cryptohelper.interfaces.CalcolatoreMappatura;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
public class CalcolatoreMonoalfabetico extends CalcolatoreMappatura {

    @Override
    public Mappatura calcola(String chiave){
        chiave = chiave.toLowerCase();
        if (containsDigit(chiave))
            return null;
        Mappatura m = new Mappatura();
        char[] res = new char[26];
        for (int i = 0; i < chiave.length(); i++) {
            res[i] = chiave.charAt(i);
        }
        m.setMappaInversa(res);
        return m;
    }

    private boolean containsDigit(String string) {
        return string.matches(".*\\d.*");
    }
}
