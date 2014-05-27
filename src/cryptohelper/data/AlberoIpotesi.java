//Classe che gestisce l'albero delle ipotesi
package cryptohelper.data;

public class AlberoIpotesi {

    private Ipotesi root;
    private MappaPosizioni mappaPosizioni;

    public AlberoIpotesi(String testoCifrato) {
        //la root non ha una mossa corrente. questo nodo serve per memorizare l'informazione per un eventuale undo()
        root = new Ipotesi('-', '-');
        mappaPosizioni = new MappaPosizioni(testoCifrato);

    }

    //Cerca la mossa. Restituisce TRUE in caso di riscontro, FALSE altrimenti
    public boolean cerca(Mossa mossa) {

        return false;
    }

    /**
     *
     * @param ch1 carattere Cifrato o sostituito con un presunto carattere in
     * chiaro in precedenza
     * @param ch2 carattere da sostituire con un presunto carattere in chiaro
     * @param testoLavoro - testo su quale si sta lavorando per decifrare
     * @return testoLavoro modificato sostituendo ch1 cifrato con ch2 presunto
     * carattere in chiaro
     */
    public String effettuaSostituzione(char ch1, char ch2, String testoLavoro) {
        if (isEmpty()) {
            return null;
        }
        Ipotesi ipCorrente = new Ipotesi(ch1, ch2);
        addIpotesi(ipCorrente);
        return mappaPosizioni.executeMossa(ipCorrente.getMossaCorrente(), testoLavoro);

    }

    public boolean cerca(char ch1, char ch2) {
        if (isEmpty()) {
            return false;
        }
        return cerca(ch1, ch2, root);
    }

    private boolean cerca(char ch1, char ch2, Ipotesi ip) {
        if (ip != null) {
            System.out.println(ip.getMossaCorrente().getCharacter() + " " + ch1 + " " + ip.getMossaCorrente().getInverseChar() + " " + ch2);
            if (ip.getMossaCorrente().getCharacter() == ch1 && ip.getMossaCorrente().getInverseChar() == ch2) {
                return true;
            } else {
                for (int i = 0; i < ip.getFigli().size(); i++) {
                    return cerca(ch1, ch2, ip.getFigli().get(i));
                }
            }
        }
        return false;
    }

    /**
     * Aggiunge una nuova ipotesi al'AlberoIpotesi.
     *
     * @param ipCorrente ipotesi corrente che contiene solo mossa corrente
     * @return true se ipCorrente aggiunto con successo
     */
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
            rootI.setMossaPrecedente(ipCorrente.getMossaCorrente().createMossaUndo());
            rootI.setUltima(false);
            rootI.getFigli().add(ipCorrente);
            ipCorrente.setPadre(rootI);
            System.out.println("ok");
            return true;
        } else {
            for (int i = 0; i < rootI.getFigli().size(); i++) {
                return addIpotesi(ipCorrente, rootI.getFigli().get(i));
            }
        }
        return false;
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

    //Restituisce l'ipotesi corrente
    public Ipotesi getIpotesiCorrente() {
        if (isEmpty()) {
            return null;
        }
        return getIpotesiCorrente(root);
    }

    private Ipotesi getIpotesiCorrente(Ipotesi ip) {
        if (ip.isUltima()) {
            return ip;
        }
        for (int i = 0; i < ip.getFigli().size(); i++) {
            return getIpotesiCorrente(ip.getFigli().get(i));
        }
        return null;
    }

    //verifica se l'albero è vuoto
    public boolean isEmpty() {
        return (root == null || mappaPosizioni == null);
    }

    public MappaPosizioni getMappaPosizioni() {
        return mappaPosizioni;
    }

    public void setMappaPosizioni(MappaPosizioni mappaPosizioni) {
        this.mappaPosizioni = mappaPosizioni;
    }

}
