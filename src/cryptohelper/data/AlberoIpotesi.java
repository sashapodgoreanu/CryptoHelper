//Classe che gestisce l'albero delle ipotesi
package cryptohelper.data;

import java.util.ArrayList;

public class AlberoIpotesi {

    private Ipotesi root;

    public AlberoIpotesi()
    {
        root = null;
    }
    
   
    //Cerca la mossa. Restituisce TRUE in caso di riscontro, FALSE altrimenti
    public boolean cerca(Mossa mossa) {

        return false;
    }

    //Aggiunge un ipotesi. Restituisce TRUE se l'operazione va a buon fine, FALSE altrimenti
    public boolean addIpotesi(Ipotesi ip) {

        return false;
    }

    //Restituisce l'ipotesi corrente
    public Ipotesi getIpotesiCorrente() {

        return new Ipotesi();
    }

    //Effettua sostituzione di due caratteri. Restituisce TRUE se l'operazione va a buon fine, FALSE altrimenti
    public boolean seffettuaSostituzione(char c1, char c2) {

        return false;
    }

    //METODI GETTER
    public Ipotesi getRoot() {
        return root;
    }

    //Classe ipotesi: gestisce i nodi dell'albero
    public static class Ipotesi {

        private Mossa mossa;
        private Ipotesi padre;
        private ArrayList<Ipotesi> figli;
        private boolean ultima;
        private boolean valid;

        //Restituisce la mossa corrente
        public Mossa getMossaCorrente() {

            return new Mossa();
        }

        //Restituisce la mossa precedente
        public Mossa getMossaPrecedente() {

            return new Mossa();
        }

        //METODI GETTER IPOTESI
        public Mossa getMossa() {
            return mossa;
        }

        public Ipotesi getPadre() {
            return padre;
        }

        public boolean isUltima() {
            return ultima;
        }

        public boolean isValid() {
            return valid;
        }

        //METODI SETTER IPOTESI
        public void setMossa(Mossa mossa) {
            this.mossa = mossa;
        }

        public void setPadre(Ipotesi padre) {
            this.padre = padre;
        }

        public void setUltima(boolean ultima) {
            this.ultima = ultima;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

    }
}
