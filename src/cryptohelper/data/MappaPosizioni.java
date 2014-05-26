package cryptohelper.data;

import java.util.ArrayList;

public class MappaPosizioni {

    private MappaPosCarattere[] mappa;

    public MappaPosizioni(String testoCifrato) {
        inizializza(testoCifrato);
    }

    public String executeMossa(Mossa mossa, String testoLavoro) {
        StringBuilder sb = new StringBuilder(testoLavoro);
        //System.out.println("executeMossa sb " + sb + " Mossa: " + mossa.toString());
        for (int i = 'A'; i <= 'z'; i++) {
            //System.out.println("executeMossa " + (char) i + " mappa[i] " + mappa[i]);
            if (mappa[i] != null && (mappa[i].getCarattere() == mossa.getCharacter())) {
                //System.out.println(" mappa[i].getCarattere() " + mappa[i].getCarattere());
                mappa[i].setCarattere(mossa.getInverseChar());
                ArrayList<Integer> lista = mappa[i].getListaPos();
                for (int j = 0; j < lista.size(); j++) {
                    sb.setCharAt(lista.get(j), mappa[i].getCarattere());
                }
            } else {
                //System.out.println("Mossa non valida");
            }
        }
        return sb.toString();
    }
    
    

    /**
     * metodo final - non e redifinibile in una sottoclase
     *
     * @param testoCifrato
     */
    public final void inizializza(String testoCifrato) {
        mappa = new MappaPosCarattere[256];
        testoCifrato = testoCifrato.toUpperCase(); //trasforma tutti i carratteri in caratteri minuscoli
        for (int i = 0; i < testoCifrato.length(); i++) {
            if (testoCifrato.charAt(i) >= 'A' || testoCifrato.charAt(i) <= 'Z') {
                addPos(testoCifrato.charAt(i), i);
            }
        }
    }

    private void addPos(char ch1, int pos) {
        //controllo se ch1 esiste nella mappa
        boolean exists = false;
        for (int i = 'A'; i <= 'Z'; i++) {
            if (mappa[i] != null && mappa[i].getCarattere() == ch1) {
                //si aggiunge una nuova posizione in un testo per ch1 nella mappa
                mappa[i].addPosizione(pos);
                exists = true;
            }
        }
        if (!exists) { // se il ch1 non esiste ancora nella mappa si crea una nuova calsse MappaPosCarattere e si aggiunge alla mappa
            MappaPosCarattere temp = new MappaPosCarattere(ch1);
            mappa[ch1] = temp;
            mappa[ch1].addPosizione(pos);
        }
    }

    public MappaPosCarattere[] getMappa() {
        return mappa;
    }

    public void setMappa(MappaPosCarattere[] mappa) {
        this.mappa = mappa;
    }

    @Override
    public String toString() {
        StringBuilder a = new StringBuilder();
        for (int i = 'A'; i <= 'Z'; i++) {
            if (this.mappa[i] != null) {
                a.append("Carattere immutabile = ").append((char) i).append(", ").append(mappa[i].toString()).append("\n");
            }
        }
        return a.toString();
    }

}
