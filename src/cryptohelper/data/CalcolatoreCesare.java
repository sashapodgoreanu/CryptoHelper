/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.data;

import cryptohelper.service.DBController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
public class CalcolatoreCesare extends cryptohelper.interfaces.CalcolatoreMappatura {

    //debug
    Log log = LogFactory.getLog(DBController.class);

    @Override
    public Mappatura calcola(String chiave) {
        int key = 0;
        try {
            key = Integer.parseInt(chiave);
        } catch (NumberFormatException e) {
            log.fatal(e.getMessage());
        }
        Mappatura map = new Mappatura();
        char[] mappaInversa = new char[26];
        for (int i = 0; i < 26; i++) {
            mappaInversa[(i+key)%26] = map.getChar(i);
        }
        map.setMappaInversa(mappaInversa);
        return map;
    }
    
    
}
