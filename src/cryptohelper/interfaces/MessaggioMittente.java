//interfaccia MessaggioMittente
package cryptohelper.interfaces;

public interface MessaggioMittente extends MessaggioAstratto {

    public boolean isBozza();

    public boolean salva();

    public void cifra();
}
