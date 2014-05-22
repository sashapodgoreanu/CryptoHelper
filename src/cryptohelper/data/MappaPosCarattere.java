/*
 * Copyright 2014 SashaAlexandru.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cryptohelper.data;

import java.util.ArrayList;

/**
 *
 * @author SashaAlexandru
 */
public class MappaPosCarattere {
    private char carattere;
    private ArrayList<Integer> listaPos;

    public MappaPosCarattere(char carattere) {
        this.carattere = carattere;
        listaPos = new ArrayList<>();
    }
    
    public ArrayList<Integer> getListaPos() {
        throw new UnsupportedOperationException();
    }

    public void setListaPos(ArrayList<Integer> listaPos) {
        throw new UnsupportedOperationException();
    }

    public char getCarattere() {
        return this.carattere;
    }

    public void setCarattere(char carattere) {
        this.carattere = carattere;
    }
    
    public void addPosizione(int pos){
        if(!listaPos.contains(pos))
            listaPos.add(pos);
    }

    @Override
    public String toString() {
        return "MappaPosCarattere{" + "carattere=" + carattere + ", listaPos=" + listaPos + '}';
    }
    
    
}
