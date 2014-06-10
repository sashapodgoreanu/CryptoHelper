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
    private boolean isRoot;

    public Ipotesi() {
        System.out.println("New ipotesi");
        mossaCorrente = null;
        mossaPrecedente = null;
        padre = null;
        figli = new ArrayList<>();
        ultima = true;
        valid = true;
        isRoot = false;
    }

    public Ipotesi(char ch1, char ch2) {
        mossaCorrente = new Mossa(ch1, ch2);
        mossaPrecedente = null;
        padre = null;
        figli = new ArrayList<>();
        valid = true;
        ultima = true;
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

    //METODI GETTER
    public Mossa getMossaCorrente() {
        return mossaCorrente;
    }

    public Mossa getMossaPrecedente() {
        return mossaPrecedente;
    }

    public Ipotesi getPadre() {
        return padre;
    }

    public ArrayList<Ipotesi> getFigli() {
        return figli;
    }

    public boolean isUltima() {
        return ultima;
    }

    public boolean isValid() {
        return valid;
    }

    public boolean isIsRoot() {
        return isRoot;
    }

    //METODI SETTER
    public void setMossaCorrente(Mossa mossaCorrente) {
        this.mossaCorrente = mossaCorrente;
    }

    public void setMossaPrecedente(Mossa mossaPrecedente) {
        this.mossaPrecedente = mossaPrecedente;
    }

    public void setPadre(Ipotesi padre) {
        this.padre = padre;
    }

    public void setFigli(ArrayList<Ipotesi> figli) {
        this.figli = figli;
    }

    public void setUltima(boolean ultima) {
        this.ultima = ultima;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }
}
