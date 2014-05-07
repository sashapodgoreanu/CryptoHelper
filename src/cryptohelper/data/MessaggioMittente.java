//interfaccia MessaggioMittente

package cryptohelper.data;

public interface MessaggioMittente extends MessaggioAstratto
{
    public boolean isBozza();
    public boolean salva();
    public void cifra();
    public void setMittente(UserInfo user);
    public void setDestinatario(UserInfo user);
    
}
