package cryptohelper.data;

/**
 *
 * @author st116628
 */
public class Cifratore {

    public static String cifraMonoalfabetica(Mappatura mappa, String testo) {
        String result = "";
        testo = testo.toLowerCase();
        for (int i = 0; i < testo.length(); i++) {
            if (testo.charAt(i) >= 'a' && testo.charAt(i) <= 'z') {
                result = result + mappa.map(testo.charAt(i));
            } else {
                result = result + testo.charAt(i);
            }
        }
        
        return result;
    }

    public static String decifraMonoalfabetica(Mappatura mappa, String testo) {
        String result = "";
        testo = testo.toLowerCase();
        for (int i = 0; i < testo.length(); i++) {
            if (testo.charAt(i) >= 'a' && testo.charAt(i) <= 'z') {
                result = result + mappa.inverseMap(testo.charAt(i));
            } else {
                result = result + testo.charAt(i);
            }
        }
        return result;
    }
    
    
    public String sostituisci(Mappatura mappa, String testo) {

        return null;
    }

}
