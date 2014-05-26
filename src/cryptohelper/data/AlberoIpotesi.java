//Classe che gestisce l'albero delle ipotesi
package cryptohelper.data;

public class AlberoIpotesi {

    private Ipotesi root;
    private MappaPosizioni mappaPosizioni;

    public AlberoIpotesi() {
        root = new Ipotesi();
        mappaPosizioni = new MappaPosizioni();
    }

    //Cerca la mossa. Restituisce TRUE in caso di riscontro, FALSE altrimenti
    public boolean cerca(Mossa mossa) {

        return false;
    }

    public boolean addIpotesi(Ipotesi ipCorrente) {
        if (isEmpty() || ipCorrente == null) {
            System.out.println("not Ok");
            return false;

        }
        return addIpotesi(ipCorrente, root);
    }

    private boolean addIpotesi(Ipotesi ipCorrente, Ipotesi rootI) {
        if (mappaPosizioni == null) {
            return false;
        }
        if (rootI.isUltima()) {
            rootI.getFigli().add(ipCorrente);
            ipCorrente.setPadre(rootI);
            rootI.setUltima(false);
            //to-do
            //rootI.setMossaPrecedente(mappaPosizioni.);
            System.out.println("ok");
            return true;
        } else {
            for (int i = 0; i < rootI.getFigli().size(); i++) {
                return addIpotesi(ipCorrente, rootI.getFigli().get(i));
            }
        }
        return false;
    }

    //Restituisce l'ipotesi corrente
    public Ipotesi getIpotesiCorrente() {
        if (isEmpty()) {
            return null;
        }
        return null;
    }

    /**
     *
     * @param ch1 carattere Cifrato o sostituito con un presunto carattere in
     * chiaro in precedenza
     * @param ch2 carattere da sostituire con un presunto carattere in chiaro
     * @return
     */
    public String effettuaSostituzione(char ch1, char ch2) {
        if (isEmpty()) {
            return null;
        }
        //Tutti i caratteri dentro la MappaturaPosizioni sono lettere Upercase.
        //Una volta fatta effettuaSostituzione(ch1, ch2), ch2 sara un presunto carattere in chiaro qundi deve essere carattere minuscolo
        if (ch2 >= 'A' && ch2 <= 'Z') {
            //trasforma
            ch2 = (char) (ch2 + 32);
        }
        Ipotesi ipCorrente  = new Ipotesi(ch1,ch2);

        return null;
    }

    //verifica se l'albero è vuoto
    public boolean isEmpty() {
        return (root == null || mappaPosizioni == null);
    }

    /**
     *
     * @param ch1 carattere nuova
     * @param ch2 carattere da sostituire
     * @return nuova ipotesi corrente (se l'albero non è vuoto)
     */
    public Ipotesi createIpotesiCorrente(char ch1, char ch2) {
        if (isEmpty()) {
            return null;
        }
        return new Ipotesi(ch1, ch2);
    }

    public Ipotesi getRoot() {
        return root;
    }

    public void display() {
        if (isEmpty()) {
            return;
        }
        int profondita = 0;
        display(root, profondita);
    }

    private void display(Ipotesi ipotesi, int profondita) {
        if (ipotesi != null) {
            System.out.println("profondita:" + profondita + " " + ipotesi.toString());
            profondita++;
            for (int i = 0; i < ipotesi.getFigli().size(); i++) {
                display(ipotesi.getFigli().get(i), profondita);
            }
        }
    }

}
