package academy.esercizi.esercizi9;

import java.util.Arrays;
import java.util.Scanner;

/* Scrivete un programma che calcoli le tasse secondo questi schemi (il primo americano
ed il secondo italiano). Chiedere la nazione di residenza dell’utente e quindi,
sulla base della risposta, utilizzare lo schema appropriato. Anche in Italia il calcolo
deve essere progressivo, come esplicato nel sistema americano e con una RAL di 30.000 deve
risultare un netto di 22.600. */
public class Esercizio1 {
    public static void main(String[] args) {
        int[] array = calcolaTasseItaliane(60000);
        System.out.println(Arrays.toString(array));
        int sum= 0;
        for (int j : array) {
            sum += j;
        }
        System.out.println(sum);

    }
    public static int [] calcolaTasseItaliane(int reddito){
        int[] tasse = new int[4];

        // devi sottrarre il reddito iniziale e tassare quello che ti viene
        if(reddito<15000){
            tasse[0] = 15000 * 23/1000;
        }else if(reddito >15001 && reddito < 28000){
            tasse[0] = 15000 * 23/100;
            tasse[1] = (reddito - 15000) * 25/100;
        }else if(reddito > 28001 && reddito < 50000){
            tasse[0] = 15000 * 23/100;
            tasse[1] =  (28000 - 15000) * 25/100;
            tasse[2] =  (reddito - 28000) * 35/100;
        }else if(reddito > 50000){
            tasse[0] = 15000 * 23/100;
            tasse[1] = (28000 - 15000) * 25/100;
            tasse[2] = (50000 - 28000) * 35/100;
            tasse[3] = (reddito - 50000) * 43/100;
        }
        return tasse;
    }
}
