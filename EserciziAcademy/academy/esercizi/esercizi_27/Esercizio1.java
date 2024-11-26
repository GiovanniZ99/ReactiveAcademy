package academy.esercizi.esercizi_27;

import java.util.Scanner;
/* Quadrati magici. Una matrice n × n riempita con i numeri 1, 2, 3, …, n² è
un quadrato magico se la somma degli elementi di ogni riga, di ogni colonna e delle due diagonali ha lo stesso valore.
Scrivete un programma che legga 16 valori dalla tastiera e verifichi se, disposti in una matrice 4 × 4,
formano un quadrato magico. Dovete verificare due caratteristiche:
I dati inseriti dall’utente sono presenti tutti i numeri 1, 2, …, 16?
Quando i numeri vengono disposti in un quadrato, la somma degli elementi di ogni riga, di ogni colonna e delle due
diagonali ha lo stesso valore?
*/

public class Esercizio1 {
    static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Inserisci 16 valori da 1 a 16 per riempire il quadrato");
        int[][] quadrato = (prendiValori(SCANNER));

        if (checkQuadratoMagico(quadrato)) return;
        System.out.println("Le somme delle righe, delle colonne e delle diagonali sono uguali");
    }

    private static boolean checkQuadratoMagico(int[][] quadrato) {
        int sumRighe = 0;
        int sumPrimaRiga = 0;
        int sumPrimaColonna = 0;
        int sumColonne = 0;

        for (int i = 0; i < quadrato.length; i++) {
            for (int j = 0; j < quadrato[i].length; j++) {
                if (!checkNumeri(quadrato[i][j])) {
                    System.out.println("I valori inseriti non sono da 1 a 16");
                    return true;
                }
                sumPrimaRiga += quadrato[0][i];
                sumRighe += quadrato[i][j];
                if (!checkSommeRighe(sumPrimaRiga, sumRighe)) {
                    System.out.println("Le somme delle righe sono diverse");
                    return true;
                }

                sumPrimaColonna += quadrato[i][0];
                sumColonne += quadrato[j][i];

                if (!checkSommeColonne(sumPrimaColonna, sumColonne)) {
                    System.out.println("Le somme delle colonne sono diverse");
                    return true;
                }
            }
        }
        if (!checkSommaDiagonali(quadrato)) {
            System.out.println("Le somme delle diagonali sono diverse");
            return true;
        }
        return false;
    }

    public static int[][] prendiValori(Scanner scanner) {
        int[][] matriceQuadrata = new int[4][4];
        for (int i = 0; i < matriceQuadrata.length; i++) {
            for (int j = 0; j < matriceQuadrata[i].length; j++) {
                matriceQuadrata[i][j] = scanner.nextInt();
            }
        }
            for (int n = 0; n < matriceQuadrata.length; n++) {
                for (int k = 0; k < matriceQuadrata[n].length; k++) {
                    System.out.print(matriceQuadrata[n][k] + " ");
                }
                System.out.println();
            }
        return matriceQuadrata;
    }

    public static boolean checkNumeri(int valore) {
        return valore > 0 && valore <= 16;
    }

    public static boolean checkSommeRighe(int sumPrimaRiga, int sum) {
        return sum == sumPrimaRiga;
    }

    private static boolean checkSommeColonne(int sumPrimaColonna, int sum) {
        return sumPrimaColonna == sum;
    }

    private static boolean checkSommaDiagonali(int[][] matriceDaVerificare) {
        int sumPrimaDiagonale = 0;
        int sumSecondaDiagonale = 0;
        int index = 0;
        for (int i = 0; i < matriceDaVerificare.length; i++) {
            sumPrimaDiagonale += matriceDaVerificare[i][index];
            index++;
        }

        for (int i = 0; i < matriceDaVerificare.length; i++) {
            sumSecondaDiagonale += matriceDaVerificare[i][matriceDaVerificare.length - i - 1];
        }

        return sumPrimaDiagonale == sumSecondaDiagonale;
    }

}