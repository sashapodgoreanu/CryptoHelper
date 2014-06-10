//Classe che gestisce l'albero delle ipotesi
package cryptohelper.data;

import java.util.ArrayList;

public class AlberoIpotesi {

    private Ipotesi root;
    private MappaPosizioni mappaPosizioni;

    public AlberoIpotesi(String testoCifrato) {
        //la root non ha una mossa corrente. questo nodo serve per memorizare l'informazione per un eventuale undo()
        root = new Ipotesi('-', '-');
        root.setIsRoot(true);
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
        if (ip.getMossaCorrente().getCharacter() == ch1 && ip.getMossaCorrente().getInverseChar() == ch2) {
            return true;
        } else {
            ArrayList<Ipotesi> temp = ip.getFigli();
            if (temp.size() == 0) {
                return false;
            }
            boolean result;
            int i = 0;
            result = cerca(ch1, ch2, ip.getFigli().get(i));
            i++;
            while (i < temp.size()) {
                if (result == true) {
                    return result;
                } else {
                    result = cerca(ch1, ch2, ip.getFigli().get(i));
                    i++;
                }
            }
            return result;
        }
    }

    /**
     * Aggiunge una nuova ipotesi al'AlberoIpotesi.
     *
     * @param ipCorrente ipotesi corrente che contiene solo mossa corrente
     * @return true se ipCorrente aggiunto con successo
     */
    public boolean addIpotesi(Ipotesi ipCorrente) {
        if (isEmpty() || ipCorrente == null) {
            return false;
        }
        return addIpotesi(ipCorrente, root);
    }

    private boolean addIpotesi(Ipotesi nuovaIpotesi, Ipotesi ultimaIpotesi) {
        if (ultimaIpotesi.isUltima()) {
            ultimaIpotesi.setMossaPrecedente(mappaPosizioni.createMossaUndo(nuovaIpotesi.getMossaCorrente()));
            ultimaIpotesi.setUltima(false);
            ultimaIpotesi.getFigli().add(nuovaIpotesi);
            nuovaIpotesi.setPadre(ultimaIpotesi);
            return true;
        } else {
            ArrayList<Ipotesi> temp = ultimaIpotesi.getFigli();
            if (temp.size() == 0) {
                return false;
            }
            boolean result;
            int i = 0;
            result = addIpotesi(nuovaIpotesi, ultimaIpotesi.getFigli().get(i));
            i++;
            while (i < temp.size()) {
                if (result == true) {
                    return result;
                } else {
                    result = addIpotesi(nuovaIpotesi, ultimaIpotesi.getFigli().get(i));
                    i++;
                }
            }
            return result;
        }
    }

    public Pair<Mossa, String> undo(String testoLavoro) {
        Mossa mossaUndo = null;
        Ipotesi ip = getIpotesiCorrente();
        System.out.println("PRIMA IPOTESI CORENTE: " + ip.toString());
        Ipotesi padre = ip.getPadre();
        if (padre == null) { //se padre = null allora ip == root
            System.out.println("ROOT");
            ip.setValid(true);
            ip.setUltima(true);
            mossaUndo = ip.getMossaPrecedente();
        } else {
            System.out.println("NO ROOT");
            ip.setValid(false);
            ip.setUltima(false);
            padre.setUltima(true);
            mossaUndo = padre.getMossaPrecedente();
        }
        System.out.println("**********************************");
        display();//debug
        System.out.println("**********************************");
        System.out.println("DOPO IPOTESI CORENTE: " + ip.toString());
        testoLavoro = mappaPosizioni.executeMossa(mossaUndo, testoLavoro);
        return new Pair(mossaUndo, testoLavoro);
    }

    //Restituisce l'ipotesi corrente
    public Ipotesi getIpotesiCorrente() {
        if (isEmpty()) {
            return null;
        }
        return getIpotesiCorrente(root);
    }

    private Ipotesi getIpotesiCorrente(Ipotesi ip) {
        if (ip != null && ip.isUltima()) {
            return ip;
        } else {
            ArrayList<Ipotesi> temp = ip.getFigli();
            if (temp.size() == 0) {
                return null;
            }
            Ipotesi ultimaIp;
            int i = 0;
            ultimaIp = getIpotesiCorrente(ip.getFigli().get(i));
            i++;
            while (i < temp.size()) {

                if (ultimaIp != null) {
                    return ultimaIp;
                } else {
                    ultimaIp = getIpotesiCorrente(ip.getFigli().get(i));
                    i++;
                }
            }
            return ultimaIp;
        }
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
