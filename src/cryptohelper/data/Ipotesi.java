//Classe ipotesi: gestisce i nodi dell'albero
package cryptohelper.data;

import java.util.ArrayList;

public class Ipotesi {

    private Mossa mossaCorrente;
    private Mossa mossaPrecedente;
    private Ipotesi padre;
    private ArrayList<Ipotesi> figli;
    private boolean ultima;
    private boolean valid;

    public Ipotesi() {
        System.out.println("New ipotesi");
        mossaCorrente = null;
        mossaPrecedente = null;
        padre = null;
        figli = new ArrayList<>();
        ultima = true;
        valid = true;
    }

    public Ipotesi(char ch1, char ch2) {
        mossaCorrente = new Mossa(ch1, ch2);
        padre = null;
        figli = new ArrayList<>();
        valid = true;
        ultima = true;
    }

    public Mossa getMossaCorrente() {
        return mossaCorrente;
    }

    public void setMossaCorrente(Mossa mossaCorrente) {
        this.mossaCorrente = mossaCorrente;
    }

    public Mossa getMossaPrecedente() {
        return mossaPrecedente;
    }

    public void setMossaPrecedente(Mossa mossaPrecedente) {
        this.mossaPrecedente = mossaPrecedente;
    }

    public Ipotesi getPadre() {
        return padre;
    }

    public void setPadre(Ipotesi padre) {
        this.padre = padre;
    }

    public ArrayList<Ipotesi> getFigli() {
        return figli;
    }

    public void setFigli(ArrayList<Ipotesi> figli) {
        this.figli = figli;
    }

    public boolean isUltima() {
        return ultima;
    }

    public void setUltima(boolean ultima) {
        this.ultima = ultima;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void initMossaPrecedente() {
        createMossaCorrente(ch1);
    }

    @Override
    public String toString() {
        if (mossaPrecedente == null && mossaCorrente == null) {
            return "Ipotesi{ultima=" + ultima + ", valid=" + valid + '}';
        } else if (mossaPrecedente == null) {
            return "Ipotesi{" + "mossaCorrente=" + mossaCorrente.toString() + ", ultima=" + ultima + ", valid=" + valid + '}';
        } else if (mossaCorrente == null) {
            return "Ipotesi{" + "mossaCorrente=" + mossaPrecedente.toString() + ", ultima=" + ultima + ", valid=" + valid + '}';
        } else {
            return "Ipotesi{" + "mossaCorrente=" + mossaCorrente.toString() + ", mossaPrecedente=" + mossaPrecedente.toString() + ", ultima=" + ultima + ", valid=" + valid + '}';
        }
    }
}
