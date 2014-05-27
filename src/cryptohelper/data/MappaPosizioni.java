package cryptohelper.data;

import java.util.ArrayList;

public class MappaPosizioni {

    private MappaPosCarattere[] mappa;

    public MappaPosizioni(String testoCifrato) {
        inizializza(testoCifrato);
    }

    /**
     * Il metodo cambia il testoLavoro e aggiorna la mappa con parametri forniti
     * dalla mossa
     *
     * @param mossa Una nuova assunzione
     * @param testoLavoro testo su quale si sta lavornado
     * @return testoLavoro cambiato dopo aver eseguito la mossa
     */
    public String executeMossa(Mossa mossa, String testoLavoro) {
        StringBuilder sb = new StringBuilder(testoLavoro);
        //cambia il carrattere cifrato con quello presunto
        mappa[mossa.getCharacter()].setCarattere(mossa.getInverseChar());
        //cambia il nuovo carattere presunto anche nel testoLavoro
        ArrayList<Integer> lista = mappa[mossa.getCharacter()].getListaPos();
        for (int j = 0; j < lista.size(); j++) {
            sb.setCharAt(lista.get(j), mappa[mossa.getCharacter()].getCarattere());
        }
        return sb.toString();
    }

    /**
     * metodo final - non e redifinibile in una sottoclase Inizzializa la mappa
     * dei caratteri di un testoCifrato
     *
     * @param testoCifrato su quale si lavora
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

    /**
     * Metodo ausiliario per aggiungere un carattere e aggiungere/aggiornare
     * posizione nel testo del carattere nella mappa
     *
     * @param ch1 - nuovo carattere o una occorenza
     * @param pos - dove si trova ch1 net testo
     */
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
