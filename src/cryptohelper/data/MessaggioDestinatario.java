//interfaccia MessaggioDestinatario

package cryptohelper.data;

public interface MessaggioDestinatario extends MessaggioAstratto
{
    public boolean isLetto();
    public UserInfo getDestinatario();
    public void setDestinatario(UserInfo u);
    
}
