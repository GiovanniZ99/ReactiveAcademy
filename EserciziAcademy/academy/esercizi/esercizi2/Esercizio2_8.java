package academy.esercizi.esercizi2;

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
        int lancioDado = r.nextInt(6) + 1;
        byte uno = 0;
        byte due = 0;
        byte tre = 0;
        byte quattro = 0;
        byte cinque = 0;
        byte sei = 0;
        for (int i = 0; i <= 1000; i++) {
            if (lancioDado == 1) {
                uno++;
            } else if (lancioDado == 2) {
                due++;
            } else if (lancioDado == 3) {
                tre++;
            } else if (lancioDado == 4) {
                quattro++;
            } else if (lancioDado == 5) {
                cinque++;
            } else if (lancioDado == 6) {
                sei++;
            }

            System.out.println("Frequenza dei lanci del dado:");
            System.out.println("Faccia\tFrequenza");
            System.out.println("1\t" + uno);
            System.out.println("2\t" + due);
            System.out.println("3\t" + tre);
            System.out.println("4\t" + quattro);
            System.out.println("5\t" + cinque);
            System.out.println("6\t" + sei);
        }
    }
}
