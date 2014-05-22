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

/**
 *
 * @author SashaAlexandru
 */
public class MappaPosizioni {

    private MappaPosCarattere[] mappa;

    public MappaPosizioni() {
        mappa = new MappaPosCarattere[256];
    }

    public String executeMossa(Mossa mossa, String testoLavoro) {
        throw new UnsupportedOperationException();
    }

    public void inizializza(String testoCifrato) {
        testoCifrato = testoCifrato.toLowerCase(); //trasforma tutti i carratteri in caratteri minuscoli
        for (int i = 0; i < testoCifrato.length(); i++) {
            if(testoCifrato.charAt(i)>= 'a' || testoCifrato.charAt(i) <='z')
                addPos(testoCifrato.charAt(i), i);
        }
    }

    public Mossa createMossaCorrente(char ch) {
        throw new UnsupportedOperationException();
    }

    public MappaPosCarattere[] getMappa() {
        return mappa;
    }

    public void setMappa(MappaPosCarattere[] mappa) {
        this.mappa = mappa;
    }

    public void addPos(char ch1, int pos) {
        boolean exists = false;
        for (int i = 'a'; i <= 'z'; i++) {
            if (mappa[i] != null && mappa[i].getCarattere() == ch1) {
                mappa[i].addPosizione(pos);
                exists = true;
            }
        }
        if (!exists) {
            MappaPosCarattere temp = new MappaPosCarattere(ch1);
            mappa[ch1] = temp;
            mappa[ch1].addPosizione(pos);
        }
    }

    @Override
    public String toString() {
        StringBuilder a = new StringBuilder();
        for(int i = 'a'; i< 'z'; i++){
            if (this.mappa[i] != null)
                a.append(mappa[i].toString()).append("\n");
        }
        return a.toString();
    }

}
