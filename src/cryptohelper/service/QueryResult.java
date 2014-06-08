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
        Object obj = objFromMap(key);
        String result = (String) obj;
        return result;
    }

    public int getInt(String key) throws Exception {
        if (lista.isEmpty()) {
            System.out.println(lista.isEmpty());
            throw new Exception("Lista vuota");
        }
        Object obj = objFromMap(key);
        int result = (int) obj;
        return result;
    }

    public boolean getBoolean(String key) throws Exception {
        if (lista.isEmpty()) {
            System.out.println(lista.isEmpty());
            throw new Exception("Lista vuota");
        }
        Object obj = objFromMap(key);
        boolean result = Boolean.parseBoolean((String) obj);
        return result;
    }

    private Object objFromMap(String key) throws NullPointerException {
        Object obj = lista.get(iterator).get(key.toLowerCase());
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
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
