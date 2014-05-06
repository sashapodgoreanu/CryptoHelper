// classe Proposta

package cryptohelper.data;

public class Proposta
{
    private char[] mappa;
    private char[] mappaIversa;
    private UserInfo proponente;
    private UserInfo partner;
    private SistemaCifratura sistemaCifratura;
    
    
    //METODI GETTER
    public char[] getMappa() {
        return mappa;
    }

    public char[] getMappaIversa() {
        return mappaIversa;
    }
    
    public UserInfo getProponente() {
        return proponente;
    }

    public UserInfo getPartner() {
        return partner;
    }
    
    public SistemaCifratura getSistemaCifratura() {
        return sistemaCifratura;
    }

    //METODI SETTER
    public void setMappa(char[] mappa) {
        this.mappa = mappa;
    }

    public void setMappaIversa(char[] mappaIversa) {
        this.mappaIversa = mappaIversa;
    }
    
    public void setProponente(UserInfo proponente) {
        this.proponente = proponente;
    }

    public void setPartner(UserInfo partner) {
        this.partner = partner;
    }
    
    public void setSistemaCifratura(SistemaCifratura sc) {
        this.sistemaCifratura = sc;
    }

}
