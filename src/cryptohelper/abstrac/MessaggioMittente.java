//interfaccia MessaggioMittente
package cryptohelper.abstractC;

import cryptohelper.data.UserInfo;

public interface MessaggioMittente extends MessaggioAstratto {

    public boolean isBozza();

    public boolean salva();

    public void cifra();

    public void setMittente(UserInfo user);

    public UserInfo getMittente();

}
