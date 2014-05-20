//interfaccia MessaggioDestinatario
package cryptohelper.interfaces;

public interface MessaggioDestinatario extends MessaggioAstratto {

    public boolean isLetto();
    public void decifra();

}
