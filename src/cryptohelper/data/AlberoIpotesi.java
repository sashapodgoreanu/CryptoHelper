//Classe che gestisce l'albero delle ipotesi
package cryptohelper.data;

public class AlberoIpotesi {

    private Ipotesi root;

    //METODI GETTER
    public Ipotesi getRoot() {
        return root;
    }

    
    
    public static class Ipotesi {

        private Mossa mossa;
        private Ipotesi padre;
        private boolean ultima;
        private boolean valid;

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

