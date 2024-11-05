package academy.esercizi.esercizi9;

import java.util.Scanner;

/* Scrivete un programma che calcoli le tasse secondo questi schemi (il primo americano
ed il secondo italiano). Chiedere la nazione di residenza dell’utente e quindi,
sulla base della risposta, utilizzare lo schema appropriato. Anche in Italia il calcolo
deve essere progressivo, come esplicato nel sistema americano e con una RAL di 30.000 deve
risultare un netto di 22.600. */
public class Esercizio1 {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        int ralDaInserire = prendiRal();
        float tasseItaliane = calcolaTasseItaliane(ralDaInserire);
        float tasseAmericane = calcolaTasseAmericane(ralDaInserire, coniugato());
        System.out.print("Calcolo tasse italiane: ");
        System.out.println(tasseItaliane);
        System.out.println("La tua RAL italiana netta sarà: "
                + (ralDaInserire - tasseItaliane));
        System.out.print("Calcolo tasse americane: ");
        System.out.printf("%.2f \n", tasseAmericane);
        System.out.print("La tua RAL americana netta sarà: " +
                (ralDaInserire - tasseAmericane));

    }

    private static int prendiRal() {

        System.out.println("Inserisci la RAL per calcolare le tasse italiane e americane");

        return SCANNER.nextInt();
    }

    private static boolean coniugato() {
        System.out.print("Sei coniugato? (true/false): ");

        return SCANNER.nextBoolean();
    }

    public static float calcolaTasseItaliane(int ral) {
        float[] tasse = new float[4];
        float primoScaglione = 15000;
        float secondoScaglione = 28000;
        float terzoScaglione = 50000;
        // devi sottrarre il ral iniziale e tassare quello che ti viene
        if (ral < primoScaglione) {
            tasse[0] = (float) ral * 0.23f;
        } else if (ral < secondoScaglione) {
            tasse[0] = primoScaglione * 0.23f;
            tasse[1] = (ral - primoScaglione) * 0.25f;
        } else if (ral < terzoScaglione) {
            tasse[0] = primoScaglione * 0.23f;
            tasse[1] = (secondoScaglione - primoScaglione) * 0.25f;
            tasse[2] = (ral - secondoScaglione) * 0.35f;
        } else if (ral > terzoScaglione) {
            tasse[0] = primoScaglione * 0.23f;
            tasse[1] = (secondoScaglione - primoScaglione) * 0.25f;
            tasse[2] = (terzoScaglione - secondoScaglione) * 0.35f;
            tasse[3] = (ral - terzoScaglione) * 0.43f;
        }
        float sommaScaglioni = 0;
        for (float j : tasse) {
            sommaScaglioni += j;
        }

        return sommaScaglioni;
    }

    public static float calcolaTasseAmericane(int ral, boolean coniugato) {
        float tasse = 0;
        if (coniugato) {
            if (ral > 0 && ral < 8000) {
                tasse = 800 * 0.10f;
            } else if (ral > 6000 && ral < 32000) {
                tasse = 24000 * 0.15f;
            } else if (ral > 32000) {
                tasse = (ral - 32000) * 0.25f + 4400;
            }
        } else {
            if (ral > 0 && ral < 16000) {
                tasse = 16000 * 0.10f;
            } else if (ral > 16000 && ral < 64000) {
                tasse = 48000 * 0.15f + 1600;
            } else if (ral > 64000) {
                tasse = (ral - 64000) * 0.25f + 8800;
            }
        }
        return tasse;
    }
}
