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
package util.temp.ExperimentSaveObject;

import cryptohelper.data.MappaPosizioni;

/**
 *
 * @author SashaAlexandru
 */
public class TestMappaPos {

    public static void main(String[] args) {
        String testoCifrato = "Per gli studenti UNITO iscritti ad un corso di studi NON in Informatica (come, ad es., Matematica)\n"
                + "\n"
                + "Per ottenere l'accesso ai corsi on-line resi disponibili su questa piattaforma, gli studenti NON iscritti ad uno dei Corsi di Studi in Informatica devono seguire la seguente procedura:\n"
                + "\n"
                + "assicurarsi che le credenziali a loro assegnate per accedere alla posta di Ateneo ( www.mail-edu.unito.it ) siano abilitate;\n"
                + "scrivere (dal proprio account di posta di Ateneo) al docente del corso che stanno seguendo, chiedendogli di autorizzare il servizio ICT del Dipartimento di Informatica alla abilitazione dell'account a questa piattaforma.\n"
                + "!! Chiusura per tutorato !!\n"
                + "\n"
                + "Gli studenti che incorrono nelle sanzioni relative al tutorato devono contattare uno dei componenti della commissione tutorato. Questo è l'unico modo con cui possono ottenere la riapertura dell'accesso all'e-learning.";
        MappaPosizioni alfabeto2 = new MappaPosizioni("sss");
       alfabeto2.inizializza(testoCifrato);
       System.out.println(alfabeto2.toString());
        System.out.println((int) 'A');
        
        

    }
}
