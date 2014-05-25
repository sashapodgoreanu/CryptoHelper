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
        if (rootI.isUltima()) {
            rootI.getFigli().add(ipCorrente);
            ipCorrente.setPadre(rootI);
            rootI.setUltima(false);
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

    public String effettuaSostituzione(char ch1, char ch2) {
        if (isEmpty()) {
            return null;
        }

        return null;
    }

    //verifica se l'albero è vuoto
    public boolean isEmpty() {
        return (root == null || mappaPosizioni == null);
    }

    //Crea una nuova ipotesi corrente (se l'albero non è vuoto)
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
