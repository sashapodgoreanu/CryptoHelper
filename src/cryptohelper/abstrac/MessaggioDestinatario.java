//interfaccia MessaggioDestinatario

package cryptohelper.abstractC;

import cryptohelper.data.UserInfo;

public interface MessaggioDestinatario extends MessaggioAstratto
{
    public boolean isLetto();
    public UserInfo getDestinatario();
    public void setDestinatario(UserInfo u);
    
}
