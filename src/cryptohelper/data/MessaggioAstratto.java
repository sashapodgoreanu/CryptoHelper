//interfaccia MessaggioAstratto
package cryptohelper.data;

public interface MessaggioAstratto {

    public String getTesto();
    public String getTestoCifrato();
    public String getLingua();
    public String getTitolo();
    public boolean elimina();
}
