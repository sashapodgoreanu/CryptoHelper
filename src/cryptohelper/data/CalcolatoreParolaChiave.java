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
public class CalcolatoreParolaChiave extends CalcolatoreMappatura {

    @Override
    public Mappatura calcola(String chiave) {
        chiave = chiave.toLowerCase();
        Mappatura m = new Mappatura();
        //m.setMappaInversa(merge(chiave, m.getMappa()));
        return m;
    }
    /*
     private char[] merge(String key, char[] map){
     char[] res = new char[26];
     boolean duplicate;
     for (int i = 0; i < 10; i++) {
     duplicate = false;
     for (int j = i; !duplicate && j >=0; j--) {
     if(key.charAt(i) == )
     }
     }
     return null;
     }*/

}
