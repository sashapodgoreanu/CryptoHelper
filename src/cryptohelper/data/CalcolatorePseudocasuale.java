package cryptohelper.data;

import cryptohelper.interfaces.CalcolatoreMappatura;
import cryptohelper.service.DBController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CalcolatorePseudocasuale extends CalcolatoreMappatura {

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
        int[] temp = generate(key, 1, 3, 26, 26);
        for (int i = 0; i < 26; i++) {
            mappaInversa[i] = (char) (temp[i] + 97);
        }
        map.setMappaInversa(mappaInversa);
        return map;
    }

    /*
     LCG (linear casual congruence) - genera una sequenza pseudocasuale di numeri interi 
     Il generatore è definito ricorsivamente:
     X_n+1 = ( a*X_n + c ) mod m
     dove X_n è un valore della successione dei numeri pseudocasuali e
     m, 0<m  — il "modulo"
     a, 0 < a < m — il "moltiplicatore"
     c, 0 <= c < m — l'"incremento"
     X_0 0 <= X_0 < m — il "seme" o il "valore iniziale"
     Il periodo di un LCG è al più m, e per alcune scelte di a può essere molto più piccolo. 
     Il LCG ha un periodo pieno se e solo se:
     c e m sono coprimi
     a - 1 è divisibile per tutti i fattori primi di m,

     */
    private int[] generate(int X0, int a, int c, int m, int how_many) {
        int[] int_random_numbers = new int[how_many];
        int z = X0;
        /*
         * generate pseudo-random  numbers in {0,...,m-1}
         */
        for (int i = 0; i < int_random_numbers.length; i++) {
            z = (a * z + c) % m;
            int_random_numbers[i] = z;
        }
        return int_random_numbers;
    }
}
