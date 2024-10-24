package academy.esercizi.esercizi2;

import java.util.Arrays;
import java.util.Random;

/* Dovete decidere se un dado è ecquo, contando la frequenza con cui,
lanciandolo, compaiono le diverse facce: 1,2,..,6 .1 dati in ingresso sono una sequenza di
valori ottenuti con ripetuti lanci del dado.
Dovete visualizzare una tabella che riporti la frequenza rilevata per ciascuna faccia del
dado doop aver eseguito il lancio 1000 volte.
 */
public class Esercizio2_8 {
    public static void main(String[] args) {
        Random r = new Random();

        int[] frequenzaNumeri = new int[6];
        for (int i = 0; i < 1000; i++) {
            int lancioDado = r.nextInt(6) + 1;

            frequenzaNumeri[--lancioDado]++;
        }

        System.out.println(Arrays.toString(frequenzaNumeri));

    }
}

