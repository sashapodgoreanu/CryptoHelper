//interfaccia MessaggioAstratto
package cryptohelper.bean;

public interface MessaggioAstratto
{
    public String getTesto();
    public String getTestoCifrato();
    public String getLingua();
    public String getTitolo();
    public boolean elimina();
}
