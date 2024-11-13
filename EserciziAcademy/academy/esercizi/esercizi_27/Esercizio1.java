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
        int[][] quadrato = inserisciValori(prendiValori(SCANNER));

        if(checkNumeri(quadrato)){
            System.out.println("I dati inseriti sono da 1 a 16");
        }else{
            System.out.println("I dati inseriti non sono da 1 a 16");
        }
        if(checkSommeRighe(quadrato)){
            System.out.println("Le somme delle righe sono uguali");
        }
       if(checkSommeColonne(quadrato)){
           System.out.println("Le somme delle colonne sono uguali");
       }
       if(checkSommaDiagonali(quadrato)) {
           System.out.println("Le somme delle diagonali sono uguali");
       }
    }

    public static int[] prendiValori(Scanner scanner) {
        int[] valori = new int[16];
        System.out.println("Inserisci i 16 valori da inserire nella matrice");
        for (int i = 0; i < valori.length; i++) {
            valori[i] = scanner.nextInt();
        }
        return valori;
    }

    public static int[][] inserisciValori(int[] valori) {
        int[][] matriceQuadrata = new int[4][4];
        int index = 0;
        for (int i = 0; i < matriceQuadrata.length; i++) {
            for (int j = 0; j < matriceQuadrata[i].length; j++) {
                matriceQuadrata[i][j] = valori[index];
                index++;
            }
        }
        for (int i = 0; i < matriceQuadrata.length; i++) {
            for (int j = 0; j < matriceQuadrata[i].length; j++) {
                System.out.print(matriceQuadrata[i][j] + " ");
            }
            System.out.println();
        }
        return matriceQuadrata;
    }

    public static boolean checkNumeri(int[][] matriceDaVerificare) {
        boolean check = false;
        for (int i = 0; i < matriceDaVerificare.length; i++) {
            for (int j = 0; j < matriceDaVerificare[i].length; j++) {
                if (matriceDaVerificare[i][j] <= 16 || matriceDaVerificare[i][j] > 0) {
                    check = true;
                } else {
                    return false;
                }
            }
        }
        return check;
    }

    public static boolean checkSommeRighe(int[][] matriceDaVerificare) {
        int sum = 0;
        int sumPrimaRiga = 0;

        for (int i = 0; i < matriceDaVerificare.length; i++) {
            sumPrimaRiga += matriceDaVerificare[0][i];
        }
        for (int i = 1; i < matriceDaVerificare.length; i++) {
            for (int j = 0; j < matriceDaVerificare[i].length; j++) {
                sum += matriceDaVerificare[i][j];
            }
            if (sum != sumPrimaRiga) {
                return false;
            }
            sum = 0;
        }
        return true;
    }

    private static boolean checkSommeColonne(int[][] matriceDaVerificare) {
        int sumPrimaColonna = 0;
        int sum;
        for (int i = 0; i < matriceDaVerificare.length; i++) {
            sumPrimaColonna += matriceDaVerificare[i][0];
        }

        for (int i = 0; i < matriceDaVerificare[0].length; i++) {
            sum = 0;
            for (int j = 0; j < matriceDaVerificare.length; j++) {
                sum += matriceDaVerificare[j][i];

            }
            if (sum != sumPrimaColonna) {
                return false;
            }

        }
        return true;
    }
    private static boolean checkSommaDiagonali(int [][] matriceDaVerificare){
        int sumPrimaDiagonale = 0;
        int sumSecondaDiagonale = 0;
        int index = 0;
        for (int i = 0; i < matriceDaVerificare.length ; i++) {
                sumPrimaDiagonale += matriceDaVerificare[i][index];
                index ++;
        }

        for (int i = 0; i < matriceDaVerificare.length; i++) {
            sumSecondaDiagonale += matriceDaVerificare[i][matriceDaVerificare.length - i - 1];
        }

        return sumPrimaDiagonale == sumSecondaDiagonale;
    }

}