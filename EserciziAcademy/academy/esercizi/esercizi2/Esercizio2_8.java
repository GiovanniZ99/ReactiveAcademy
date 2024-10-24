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

        int[] numeriUsciti = new int[1000];
        for (int i = 0; i < 1000; i++) {
            int lancioDado = r.nextInt(6) + 1;
            numeriUsciti[i] = lancioDado;
        }
        int uno = 0;
        int due = 0;
        int tre = 0;
        int quattro = 0;
        int cinque = 0;
        int sei = 0;
        for (int i = 0; i < numeriUsciti.length - 1; i++) {
            if (numeriUsciti[i] == 1) {
                uno++;
            }
            if (numeriUsciti[i] == 2) {
                due++;
            }
            if (numeriUsciti[i]==3){
                tre++;
            }
            if (numeriUsciti[i]==4){
                quattro++;
            }
            if (numeriUsciti[i]==5){
                cinque++;
            }
            if(numeriUsciti[i] == 6){
                sei++;
            }
        }
        int[] frequenzaNumeri = {uno, due, tre, quattro, cinque, sei};

        System.out.println(Arrays.toString(frequenzaNumeri));

        System.out.println(Arrays.toString(numeriUsciti));
    }
}

