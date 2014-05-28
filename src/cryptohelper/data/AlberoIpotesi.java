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
        System.out.println("prima");
        addIpotesi(ipCorrente);
        System.out.println("dopo");
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
            System.out.println("addIpotesi not Ok");
            return false;
        }
        return addIpotesi(ipCorrente, root);
    }

    private boolean addIpotesi(Ipotesi ipCorrente, Ipotesi rootI) {
        if (mappaPosizioni == null) {
            System.out.println("addIpotesi mappaPosizioni == null");
            return false;
        }
        if (rootI.isUltima()) {
            System.out.println("addIpotesi NON DEVE ESSERE NULL" + rootI.getMossaPrecedente());
            rootI.setMossaPrecedente(mappaPosizioni.createMossaUndo(ipCorrente.getMossaCorrente()));
            rootI.setUltima(false);
            rootI.getFigli().add(ipCorrente);
            ipCorrente.setPadre(rootI);
            System.out.println("ok");
            return true;
        }
        for (int i = 0; i < rootI.getFigli().size(); i++) {
            System.out.println("addIpotesi FOOORR" + rootI.getFigli().get(i));
            return addIpotesi(ipCorrente, rootI.getFigli().get(i));
        }
        System.out.println("addIpotesi Return falseeee");
        return false;
    }

    public String undo(String testoLavoro) {
        Ipotesi ip = getIpotesiCorrente();
        System.out.println("ipcorrente " + ip.toString());
        Ipotesi padre = ip.getPadre();
        ip.setValid(false);
        ip.setUltima(false);
        padre.setUltima(true);
        Mossa mossaUndo = padre.getMossaPrecedente();
        return mappaPosizioni.executeMossa(mossaUndo, testoLavoro);
    }

    //Restituisce l'ipotesi corrente
    public Ipotesi getIpotesiCorrente() {
        if (isEmpty()) {
            System.out.println(this.getClass() + ": return null");
            return null;
        }
        return getIpotesiCorrente(root);
    }

    private Ipotesi getIpotesiCorrente(Ipotesi ip) {
        if (ip.isUltima()) {
            System.out.println("getIpotesiCorrente:" + ip.toString() + " padre " + ip.getPadre());
            return ip;
        }
        for (int i = 0; i < ip.getFigli().size(); i++) {
            return getIpotesiCorrente(ip.getFigli().get(i));
        }
        System.out.println("Return a nulll !!!!!!!!!!!!!!!!!!!!!!");
        return null;
    }

    public void display() {
        if (isEmpty()) {
            return;
        }
        int profondita = 0;
        int figlio = 0;
        display(root, profondita, figlio);
    }

    private void display(Ipotesi ipotesi, int profondita, int figlio) {
        if (ipotesi != null) {
            System.out.println("profondita:" + profondita + " figlio:" + figlio + " " + ipotesi.toString());
            profondita++;
            for (int i = 0; i < ipotesi.getFigli().size(); i++) {
                display(ipotesi.getFigli().get(i), profondita, i);
            }
        }
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
