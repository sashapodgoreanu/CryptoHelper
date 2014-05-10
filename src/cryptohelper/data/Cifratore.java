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
public class Cifratore {
    public String cifraMonoalfabetica(Mappatura mappa, String testo){
        String result = "";
        for (int i = 0; i < testo.length(); i++) {
            if(testo.charAt(i) == ' ')
                result = result + " ";
            mappa.inverseMap(testo.charAt(i));
        }
        return null;
    }
    
    public String decifraMonoalfabetica(Mappatura mappa, String testo){
        
        return null;
    }
    
}
