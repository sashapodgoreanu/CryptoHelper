package cryptohelper.data;

public class Soluzione {

    private int id;
    private boolean valida;
    private Mappatura mappatura;

    public Mappatura getMappatura() {
        return mappatura;
    }

    public void setMappatura(Mappatura mappatura) {
        this.mappatura = mappatura;
    }

    //METODI GETTER
    public int getId() {
        return id;
    }

    public boolean isValida() {
        return valida;
    }

    //METODI SETTER
    public void setId(int id) {
        this.id = id;
    }

    public void setValida(boolean valida) {
        this.valida = valida;
    }

}
