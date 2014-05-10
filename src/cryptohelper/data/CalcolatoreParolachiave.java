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
        for (int i = 0; i < chiave.length(); i++) {
           m.map(chiave.charAt(i));
        }
        return m;
    }   
}
