//interfaccia MessaggioMittente

package cryptohelper.bean;

public interface MessaggioMittente extends MessaggioAstratto
{
    public boolean isBozza();
    public boolean salva();
    public void cifra();
}
