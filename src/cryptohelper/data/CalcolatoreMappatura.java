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
public interface CalcolatoreMappatura {
    public Mappatura calcola(java.lang.String chiave);
    public CalcolatoreMappatura create(java.lang.String metodo);
}
