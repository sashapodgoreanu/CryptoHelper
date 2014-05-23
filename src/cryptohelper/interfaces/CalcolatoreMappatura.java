/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.interfaces;

import cryptohelper.data.CalcolatoreCesare;
import cryptohelper.data.CalcolatoreParolachiave;
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
        if (metodo.equals("parola chiave")) {
            return new CalcolatoreParolachiave();
        } else if (metodo.equals("cifrario cesare")) {
            return new CalcolatoreCesare();
        } else {
            return new CalcolatorePseudocasuale();
        }
    }
}
