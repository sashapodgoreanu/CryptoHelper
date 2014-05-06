/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptohelper.data;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author st116628
 */
public class QueryResult {

    private ArrayList<Map<String, Object>> lista;

    public QueryResult(ArrayList<Map<String, Object>> lista) {
        this.lista = lista;
    }
 
    public boolean next() throws Exception {
        if(lista.isEmpty())
            return false;
        return true;
    }
    
    public String getString(String key) throws Exception{
        if(lista.isEmpty())
            throw new Exception("Lista vuota");
        String result = (String) lista.get(0).get(key.toLowerCase());
        lista.remove(0);
        return result;
    }
    public int getInt(String key) throws Exception{
        if(lista.isEmpty())
            throw new Exception("Lista vuota");
        int result = (int) lista.get(0).get(key.toLowerCase());
        lista.remove(0);
        return result;
    }

    public ArrayList<Map<String, Object>> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Map<String, Object>> lista) {
        this.lista = lista;
    }
}
