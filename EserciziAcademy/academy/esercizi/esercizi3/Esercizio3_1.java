package academy.esercizi.esercizi3;

import java.util.Arrays;

/* Definire una matrice 10x9 e popolarla completamente come fosse un tabellone della tombola.
Creare un metodo che riceve le coordinate espresse in righe e colonne e stampa gli elementi
a sinistra, destra, in alto ed in basso rispetto alle coordinate passate.
Gestire le posizioni ai bordi con opportune segnalazioni.Implementare anche un metodo
che restituisca lo stesso output ma in input prende il numero al posto delle coordinate */
public class Esercizio3_1 {
    public static void main(String[] args) {
        int[][] matriceTombola = new int[10][9];
        int numero = 1;
        for (int i = 0; i < matriceTombola.length; i++) {
            for (int j = 0; j < matriceTombola[i].length; j++) {
                matriceTombola[i][j] = numero++;
            }
        }
        for (int[] ints : matriceTombola) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
        System.out.println(Arrays.toString(trovaNumAdiacenti(matriceTombola, 4, 5)));
    }

    private static int[] trovaNumAdiacenti(int[][] matrice, int x, int y) {

        int[] results = new int[4];
        if (x > 0) {
            results[0] = matrice[x - 1][y];
        }  else {
                System.out.println("Hai preso il muro superiore");
            }
            if (x < matrice.length - 1) {
                results[1] = matrice[x + 1][y];}else{
                System.out.println("hai preso il muro in basso");
            }
                if (y > 0) {
                    results[2] = matrice[x][y - 1];}else{
                    System.out.println("Hai preso il muro sinistro");
                }
                    if (y < matrice[x].length - 1) {
                        results[3] = matrice[x][y + 1];
                    }else{
                        System.out.println("Hai preso il muro destro");
                    }
        return results;
    }
}

