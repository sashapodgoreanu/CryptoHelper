//interfaccia MessaggioAstratto
package cryptohelper.interfaces;

import cryptohelper.data.UserInfo;

public interface MessaggioAstratto {

    public int getId();

    public String getTesto();

    public String getTestoCifrato();

    public String getLingua();

    public String getTitolo();

    public boolean elimina();

    public UserInfo getDestinatario();

    public void setDestinatario(UserInfo u);

    public void setMittente(UserInfo u);

    public UserInfo getMittente();
}
