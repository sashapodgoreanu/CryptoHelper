//interfaccia MessaggioAstratto
package cryptohelper.interfaces;

public interface MessaggioAstratto {

    public int getId ();
    public String getTesto();
    public String getTestoCifrato();
    public String getLingua();
    public String getTitolo();
    public boolean elimina();
}
