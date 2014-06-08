package cryptohelper.data.proxy;

import java.io.IOException;


public class testProxy {

    public static void main(String[] args) throws IOException {

        ProxyFrequenzaFiller instance = new ProxyFrequenzaFiller("frequenzeIta.txt", "bgItaliano.txt");
        int[][] a = instance.getBigrammi();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }

        /*
         a = instance.getBigrammi();
         for (int i = 0; i < a.length; i++) {
         for (int j = 0; j < a.length; j++) {
         System.out.print(a[i][j] + " ");
         }
         System.out.println();
         }
         */
        double[] b = instance.getFreq();
        /*        for (int i = 0; i < b.length; i++) {
         System.out.print(b[i] + " ");
         }
         System.out.println();

         b = instance.getFreq();
         for (int i = 0; i < b.length; i++) {
         System.out.print(b[i] + " ");
         }
         System.out.println();
         */ }
}
