/*
 * Copyright 2014 st116629.
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

package util.temp.ExperimentSaveObject;

/**
 *
 * @author st116629
 */
//DEVE IMPLEMENTARE Serializable
public class Persona{
    String nome;
    String cognome;
    Persona figlio;
    Phonenumber number;

    public Persona(String nome, String cognome, Persona a) {
        this.nome = nome;
        this.cognome = cognome;
        this.figlio = a;
        number = new Phonenumber(1);
    }
    /*
       @Override
    public String toString() {
        if(figlio == null)
            return "Persona{" + "nome=" + nome + ", cognome=" + cognome + ", figlio=" + figlio + ", number=" + number.toString() + '}';
        else return "Persona{" + "nome=" + nome + ", cognome=" + cognome + ", figlio=" + figlio.toString() + ", number=" + number.toString() + '}';
    }
    */

 
    
    
}

class Phonenumber{
    int a;

    public Phonenumber(int a) {
        this.a = a;
    }
/*
    @Override
    public String toString() {
        return "Phonenumber{" + "a=" + a + '}';
    }
    */
    
    
}
