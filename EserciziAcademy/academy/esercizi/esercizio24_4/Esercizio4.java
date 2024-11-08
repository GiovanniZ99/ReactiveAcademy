package academy.esercizi.esercizio24_4;

import java.util.Scanner;

/* Scrivete un programma che legga una sequenza di valori in ingresso ed un titolo e
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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci 4 titoli e 4 valori per determinare il numero di asterischi");
        System.out.println("Inserisci un titolo");
        String primoTitolo = scanner.next();
        System.out.println("Inserisci un valore corrispondente al titolo");
        int primoValore = scanner.nextInt();
        System.out.println("Inserisci un titolo");
        String secondoTitolo = scanner.next();
        System.out.println("Inserisci un valore corrispondente al titolo");
        int secondoValore = scanner.nextInt();
        System.out.println("Inserisci un titolo");
        String terzoTitolo = scanner.next();
        System.out.println("Inserisci un valore corrispondente al titolo");
        int terzoValore = scanner.nextInt();
        System.out.println("Inserisci un titolo");
        String quartoTitolo = scanner.next();
        System.out.println("Inserisci un valore corrispondente al titolo");
        int quartoValore = scanner.nextInt();

        String[] titoli = {primoTitolo, secondoTitolo, terzoTitolo, quartoTitolo};
        int[] numeroAsterischi = daiNumeroAsterischi(primoValore, secondoValore, terzoValore, quartoValore);

        String titoloMax = trovaTitoloMassimo(titoli);
        System.out.println("Seleziona 0 per stampare il diagramma orizzontale");
        System.out.println("Seleziona 1 per stampare il diagramma verticale");
        int scelta = scanner.nextInt();
        if(scelta== 0){
            stampaAsterischiOrizzontali(titoloMax, titoli, numeroAsterischi);
        }else{
            stampaAsterischiVerticali(titoli, numeroAsterischi);
        }


    }

    private static void stampaAsterischiOrizzontali(String titoloMax, String[] titoli, int[] numeroAsterischi) {
        for (int i = 0; i < 4; i++) {
            int lunghezzaSpazi = (titoloMax.length() + 1) - titoli[i].length();
            System.out.print(titoli[i]);
            String s = "%" + lunghezzaSpazi + "s";
            System.out.printf(s, " ");

            for (int j = 0; j < numeroAsterischi[i]; j++) {
                System.out.print(" * ");
            }
            System.out.println();
        }
    }

    private static void stampaAsterischiVerticali(String[] titoli, int[] numeroAsterischi) {
        for (int i = 40; i > 0; i--) {

            for (int j = 0; j < titoli.length; j++) {
                if (numeroAsterischi[j] >= i) {
                    System.out.print(" * ");
                } else {
                    System.out.print("   ");
                }
                int spazio = titoli[j].length();
                String s = "%" + spazio + "s";
                System.out.printf(s, " ");
            }
            System.out.println();

        }
        for (String titolo : titoli) {
            System.out.print(titolo);
            System.out.print("   ");
        }
    }

    private static String trovaTitoloMassimo(String[] titoli) {
        String titoloMassimo = titoli[0];
        for (int i = 1; i < titoli.length; i++) {
            if (titoloMassimo.length() < titoli[i].length()) {
                titoloMassimo = titoli[i];
            }
        }
        return titoloMassimo;
    }

    private static int[] daiNumeroAsterischi(int primoValore, int secondoValore, int terzoValore, int quartoValore) {
        int[] arrayValori = {primoValore, secondoValore, terzoValore, quartoValore};

        int valoreMassimo = Math.max(Math.max(primoValore, secondoValore),
                Math.max(terzoValore, quartoValore));

        for (int i = 0; i < arrayValori.length; i++) {
            arrayValori[i] = (int) ((double) arrayValori[i] / valoreMassimo * 40);
        }

        return arrayValori;
    }
}

