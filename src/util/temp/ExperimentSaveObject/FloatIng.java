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
public class FloatIng {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        float x = 2.4f;
        double y = 2.4;
        System.out.println(x+"   "+y);
        char a = 'a';
        char b = 'b';
        char c = 'c';
        char d = 'd';
        char f= 'g';
        char g = 'z';
        System.out.println(x+"   "+(int) a+"   "+(int) b+"   "+(int) c+"   "+(int) g);
        char ch = 'A';
        ch = (char) (ch + 32);
        System.out.println(ch);

        StringBuilder sb = new StringBuilder("1234567890");
        sb.setCharAt(0, 'a');
        sb.setCharAt(2, 'a');
        sb.setCharAt(3, 'a');
        sb.setCharAt(4, 'a');
        System.out.println(sb);
        
    }   
}
