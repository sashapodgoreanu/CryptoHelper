/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.interfaces;

import cryptohelper.data.CalcolatoreCesare;
import cryptohelper.data.CalcolatoreMonoalfabetico;
//import cryptohelper.data.CalcolatoreParolaChiave;
import cryptohelper.data.CalcolatorePseudocasuale;
import cryptohelper.data.Mappatura;

/**
 *
 * @author st116628
 */
public abstract class CalcolatoreMappatura {

    /**
     *
     * @param chiave utilizata per generare mappatura
     * @return Mappatura completa
     */
    public abstract Mappatura calcola(String chiave);

    /**
     * 
     * @param metodo puo essere parola chiave, cifrario cesare oppure cifrario preudocasuale
     * @return Classe che corrisponde al metodo
     */
    public static CalcolatoreMappatura create(String metodo) {
        if (metodo.equals(Cifrario.MONOALFABETICO)) {
            return new CalcolatoreMonoalfabetico();
        } else if (metodo.equals(Cifrario.CESARE)) {
            return new CalcolatoreCesare();
        } /*else if (metodo.equals(Cifrario.PAROLA_CHIAVE)) {
            return new CalcolatoreParolaChiave();
        }*/ else if (metodo.equals(Cifrario.PSEUDOCASUALE)) {
            return new CalcolatorePseudocasuale();
        }
        return null;
    }
}
