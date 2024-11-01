package academy.esercizi.esercizio24_4;

import java.util.Scanner;

/*(*) Scrivete un programma che legga una sequenza di valori in ingresso ed un titolo e
visualizzi un diagramma a barre corrispondente ai valori acquisiti, simile a questo:
      Mario       *************************
      Giovanni  ************
      Aldo          **************************************
Potete ipotizzare che i valori siano tutti positivi. Per prima cosa individuate il valore massimo:
la barra corrispondente a quel valore deve avere 40 asterischi e le altre devono avere un numero
di asterischi proporzionale al loro valore rispetto a tale valore massimo.
Aggiungere un parametro al metodo di stampa per produrre un diagramma in orizzontale o in
verticale (con la barra più alta pari a 20 asterischi parametrizzando gli altri valori con questa
scala)
*/
public class Esercizio4 {

    public static int trovaMax(int[] elementi) {
        int numMax = 0;
        for (int j : elementi) {
            if (numMax < j) {
                numMax = j;
            }
        }
        return numMax;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] titoli = new String[3];
        int[] valori = new int[3];
        System.out.println("Inserisci i titoli e poi i valori rispettivamente");
        for (int i = 0; i < valori.length; i++) {
            titoli[i] = scanner.nextLine();
            valori[i] = scanner.nextInt();
            scanner.nextLine();
        }

        double proporzione = (double) trovaMax(valori) / 40;

        for (int i = 0; i < titoli.length; i++) {
            System.out.print(titoli[i] + ": ");
            for (int j = 0; j < Math.floor(valori[i] / proporzione); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        double proporzioneVerticale = (double) trovaMax(valori) / 20;
        for (int i = 0; i < titoli.length; i++) {
            System.out.print(titoli[i] + ": ");
            for (int j = 0; j < Math.floor(valori[i] / proporzioneVerticale); j++) {
                System.out.print("\n *");
            }
            System.out.println();
        }
    }
}
