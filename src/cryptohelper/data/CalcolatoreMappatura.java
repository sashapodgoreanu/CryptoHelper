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
public abstract class CalcolatoreMappatura {

    public abstract Mappatura calcola(String chiave);

    public static CalcolatoreMappatura create(String metodo) {
        if (metodo.equals("parola chiave")) {
            return new CalcolatoreParolachiave();
        } else if (metodo.equals("cesare")) {
            return new CalcolatoreCesare();
        } else {
            return new CalcolatorePseudocasuale();
        }
    }
}
