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
//Classe ipotesi: gestisce i nodi dell'albero
public class Ipotesi {

    private Mossa mossaCorrente;
    private Mossa mossaPrecedente;
    private Ipotesi padre;
    private ArrayList<Ipotesi> figli;
    private boolean ultima;
    private boolean valid;

    public Ipotesi() {
        mossaCorrente = null;
        mossaPrecedente = null;
        padre = null;
        figli = new ArrayList<>();
        ultima = true;
        valid = true;
    }

    public Ipotesi(char ch1, char ch2) {
        mossaCorrente = new Mossa(ch1, ch2);
        padre = null;
        figli = new ArrayList<Ipotesi>();
        valid = true;
        ultima = true;
    }

    public Mossa getMossaCorrente() {
        return mossaCorrente;
    }

    public void setMossaCorrente(Mossa mossaCorrente) {
        this.mossaCorrente = mossaCorrente;
    }

    public Mossa getMossaPrecedente() {
        return mossaPrecedente;
    }

    public void setMossaPrecedente(Mossa mossaPrecedente) {
        this.mossaPrecedente = mossaPrecedente;
    }

    public Ipotesi getPadre() {
        return padre;
    }

    public void setPadre(Ipotesi padre) {
        this.padre = padre;
    }

    public ArrayList<Ipotesi> getFigli() {
        return figli;
    }

    public void setFigli(ArrayList<Ipotesi> figli) {
        this.figli = figli;
    }

    public boolean isUltima() {
        return ultima;
    }

    public void setUltima(boolean ultima) {
        this.ultima = ultima;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "Ipotesi{" + "mossaCorrente=" + mossaCorrente.toString() + ", mossaPrecedente=" + mossaPrecedente.toString() + ", ultima=" + ultima + ", valid=" + valid + '}';
    }


}
