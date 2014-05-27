//interfaccia MessaggioIntercettato
package cryptohelper.interfaces;

public interface MessaggioIntercettato extends MessaggioAstratto {

    public boolean isLetto();

    public String getAreaLavoro();

    public void setAreaLavoro(String test);
}
