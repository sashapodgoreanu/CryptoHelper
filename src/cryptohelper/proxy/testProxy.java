/*
 * Copyright 2014 Luigi.
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
package cryptohelper.proxy;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Luigi
 */
public class testProxy {

    public static void main(String[] args) throws IOException {

        ProxyFrequenzaFiller instance = new  ProxyFrequenzaFiller("frequenzeIta.txt","bgItaliano.txt");
        int[][] a = instance.getBigrammi();
 /*       for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                System.out.print(a[i][j]); 
            }
            System.out.println(); 
        }
   */ }
}
