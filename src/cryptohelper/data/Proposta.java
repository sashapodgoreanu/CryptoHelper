// classe Proposta

package cryptohelper.data;

public class Proposta
{
    private SistemaCifratura sdc;
    private UserInfo proponente;
    private UserInfo partner;
    private SistemaCifratura sistemaCifratura;
    
    
    //METODI GETTER
    
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
