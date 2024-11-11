package academy.esercizi.esercizi9;

import java.util.Scanner;

/* Scrivete un programma che legga le coordinate x e y dei quattro vertici di un quadrilatero
e visualizzi un messaggio che indichi se è un quadrato, un rettangolo, un trapezio rettangolo,
un rombo oppure non ha nessuna di queste forme. Stampare a video la figura corrispondente ai vertici
 ricevuti in input con stringhe O (nelle posizioni vuote) e X (nelle posizioni con un vertice)
 e la figura geometrica identificata. N.B. I quadrati, rettangoli rettangolo e trapezi devono
 avere la base parallela all’asse X, il rombo deve avere la diagonale verticale parallela
 all’asse Y e quella orizzontale parallela all’asse X. I vertici verrano inseriti con questo
 ordine: prima tutti i vertici con colonna minore e a parità di colonna tutti i vertici
 con riga minore. */
/* un quadrato ha tutti i lati uguali, ma anche un rombo, si distinguono perche un quadrato
ha le diagonali uguali mentre il rombo no.
il trapezio ha i lati paralleli quindi stesse x o y a due a due e un angolo retto, quindi un lato
deve avere la stessa x degli altri due.
far inserire i valori massimi e minimi per fare gli assi cartesiani
funzione per inserire coordinate
funzione per fare le figure
 */
public class Esercizio3 {
    public static void main(String[] args) {
        faiFormaNelloSPazio();
    }

    public static int prendiCoordinata() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserire qui la coordinata: ");
        return scanner.nextInt();
    }

    public static int[][] creaSpazio() {
        System.out.println("Inserire le dimensioni massime della matrice");
        int[][] spazio = new int[prendiCoordinata()][prendiCoordinata()];
        for (int i = 0; i < spazio.length; i++) {
            for (int j = 0; j < spazio[i].length; j++) {
                spazio[i][j] = 0;
            }
        }
        return spazio;
    }

    public static int[][] faiForma() {
        System.out.println("Fai una forma con le coordinate");
        int[] coordinatePrimo = {prendiCoordinata(), prendiCoordinata()};
        int[] coordinateSecondo = {prendiCoordinata(), prendiCoordinata()};
        int[] coordinateTerzo = {prendiCoordinata(), prendiCoordinata()};
        int[] coordinateQuarto = {prendiCoordinata(), prendiCoordinata()};
        return new int[][]{coordinatePrimo, coordinateSecondo, coordinateTerzo, coordinateQuarto};
    }

    public static void faiFormaNelloSPazio() {
        int[][] coordinateForma = faiForma();
        System.out.println("Fai una forma con le coordinate");
        int[] coordinatePrimo = {coordinateForma[0][0], coordinateForma[0][1]};
        int[] coordinateSecondo = {coordinateForma[1][0], coordinateForma[1][1]};
        int[] coordinateTerzo = {coordinateForma[2][0], coordinateForma[2][1]};
        int[] coordinateQuarto = {coordinateForma[3][0], coordinateForma[3][1]};
        int[] tutteLeX = {coordinatePrimo[0], coordinateSecondo[0], coordinateTerzo[0],
                coordinateQuarto[0]};
        int[] tutteLeY = {coordinatePrimo[1], coordinateSecondo[1], coordinateTerzo[1],
                coordinateQuarto[1]};
        int[][] spazio = creaSpazio();
        for (int i = 0; i < tutteLeX.length; i++) {
            int x = tutteLeX[i];
            int y = tutteLeY[i];

            if (x == coordinateForma[0][0] && y == coordinateForma[0][1]) {
                spazio[y][x] = 1;
            } else if (x == coordinateForma[1][0] && y == coordinateForma[1][1]) {
                spazio[y][x] = 2;
            } else if (x == coordinateForma[2][0] && y == coordinateForma[2][1]) {
                spazio[y][x] = 3;
            } else if (x == coordinateForma[3][0] && y == coordinateForma[3][1]) {
                spazio[y][x] = 4;
            }
        }
        stampaMatrice(spazio);

        distingui(spazio);

    }

    public static void distingui(int[][] spazioConForma) {
        int[] coordinate = new int[8];

        for (int i = 0; i < spazioConForma.length; i++) {
            for (int j = 0; j < spazioConForma[i].length; j++) {
                if (spazioConForma[i][j] == 1) {
                    coordinate[0] = j;
                    coordinate[1] = i;

                }
                if (spazioConForma[i][j] == 2) {
                    coordinate[2] = j;
                    coordinate[3] = i;
                }
                if (spazioConForma[i][j] == 3) {
                    coordinate[4] = j;
                    coordinate[5] = i;
                }
                if (spazioConForma[i][j] == 4) {
                    coordinate[6] = j;
                    coordinate[7] = i;
                }
            }
        }
        if (coordinate[0] == coordinate[4] && coordinate[1] == coordinate[3] && coordinate[3] == coordinate[6]
                && coordinate[5] == coordinate[7] && coordinate[3] - coordinate[0] == coordinate[7] - coordinate[3]) {
            System.out.println("Quadrato");
        } else if (coordinate[0] == coordinate[4] && coordinate[1] == coordinate[3] && coordinate[3] == coordinate[6]
                && coordinate[5] == coordinate[7] && coordinate[3] - coordinate[0] != coordinate[7] - coordinate[3]) {
            System.out.println("Rettangolo");
        } else if (coordinate[0] == coordinate[6] && coordinate[2] == coordinate[4] && coordinate[4] - coordinate[0]
        == coordinate[0] - coordinate[2] && coordinate[6] - coordinate[2] == coordinate[4] - coordinate[6]) {
            System.out.println("Rombo");
        } else if (coordinate[1] == coordinate[3] && coordinate[5] == coordinate[7] && coordinate[2] > coordinate[6]) {
            System.out.println("Trapezio rettangolo");
        } else {
            System.out.println("Nessuna forma");
        }


    }

    public static void stampaMatrice(int[][] matrice) {
        for (int i = matrice.length - 1; i >= 0; i--) {
            for (int j = 0; j < matrice[i].length; j++) {
                if (matrice[i][j] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }
}