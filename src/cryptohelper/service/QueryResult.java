package cryptohelper.service;

import java.util.ArrayList;
import java.util.Map;

public class QueryResult {

    private ArrayList<Map<String, Object>> lista;
    private int iterator;
    private int size;

    public QueryResult(ArrayList<Map<String, Object>> lista) {
        this.lista = lista;
        iterator = -1;
    }

    @Override
    public String toString() {
        return "QueryResult{" + "lista=" + lista + "}";
    }

    public boolean next() {
        //System.out.println(lista.size() + " " + lista.isEmpty() + "  " + iterator);
        if (lista.isEmpty()) {
            //System.out.println("return false is empty");
            return false;
        } else if (iterator == lista.size() - 1) {
            reset();
            //qui esce pero fa il reset
            //System.out.println("return false");
            return false;
        } else {
            iterator++;
            System.out.println("return true");
            return true;
        }
    }

    public void reset() {
        iterator = -1;
    }

    public String getString(String key) throws Exception {
        if (lista.isEmpty()) {
            System.out.println(lista.isEmpty());
            throw new Exception("Lista vuota");
        }
        String result = (String) lista.get(iterator).get(key.toLowerCase());
        return result;
    }

    public int getInt(String key) throws Exception {
        if (lista.isEmpty()) {
            System.out.println(lista.isEmpty());
            throw new Exception("Lista vuota");
        }
        int result = (int) lista.get(iterator).get(key.toLowerCase());
        return result;
    }

    //METODI GETTER
    public int getIterator() {
        return iterator;
    }

    public ArrayList<Map<String, Object>> getLista() {
        return lista;
    }

    public int getSize() {
        return lista.size();
    }

    //METODI SETTER
    public void setIterator(int iterator) {
        this.iterator = iterator;
    }

    public void setLista(ArrayList<Map<String, Object>> lista) {
        this.lista = lista;
    }
}
