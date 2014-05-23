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

/**
 *
 * @author SashaAlexandru
 */
public class GetNextInt {

    public double[] random_numbers; //contains "U[0,1]"-pseudo-random numbers
    public int[] int_random_numbers; //contains the sequence of integer random numbers

    /*
     * generates a sequence of pseudo-random numbers using the linear congruential method
     l generatore è definito ricorsivamente:

     X_n+1 = ( a*X_n + c ) mod m
     dove X_n è un valore della successione dei numeri pseudocasuali e

     m, 0<m  — il "modulo"
     a, 0 < a < m — il "moltiplicatore"
     c, 0 <= c < m — l'"incremento"
     X_0 0 <= X_0 < m — il "seme" o il "valore iniziale"
     Il periodo di un LCG è al più m, e per alcune scelte di a può essere molto più piccolo. Il LCG ha un periodo pieno se e solo se:

     c e m sono coprimi
     a - 1 è divisibile per tutti i fattori primi di m,
     * 
     */
    public void generate(int z0, int a, int c, int m, int how_many) {
        this.random_numbers = new double[how_many];
        this.int_random_numbers = new int[how_many];
        int z = z0;
        /*
         * generate pseudo-random  numbers in {0,...,m-1}
         */
        for (int i = 0; i < int_random_numbers.length; i++) {
            z = (a * z + c) % m;
            int_random_numbers[i] = z;
        }
        
        /*
         * scale the pseudo-random  numbers to the interval [0,1)
         */
        for (int i = 0; i < random_numbers.length; i++) {
            random_numbers[i] = ((double) int_random_numbers[i]) / ((double) m);
        }
    }

    public void print_int_random_numbers() {

        for (int i = 0; i < int_random_numbers.length; i++) {
            System.out.println(int_random_numbers[i]);
        }
    }

    public static void main(String[] args) {
        GetNextInt n = new GetNextInt();
        
        n.print_int_random_numbers();

    }

}
