package academy.esercizi.esercizi9;

import java.util.Arrays;
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
faiFormaNelloSPazio();    }

    public static int prendiCoordinata() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserire qui la coordinata: ");
        return scanner.nextInt();
    }

    public static int[][] creaSpazio() {
        System.out.println("Inserire le dimensioni massime della matrice");
        int[][] spazio = new int[prendiCoordinata()][prendiCoordinata()];
        for (int[] ints : spazio) {
            Arrays.fill(ints, 0);
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
            for (int j = 1; j < tutteLeX.length; j++) {
                {
                    if (tutteLeX[i] < tutteLeX[j] ||
                            tutteLeX[i] > tutteLeX[j]
                            || tutteLeX[i] == tutteLeX[j]
                            && tutteLeY[i] < tutteLeY[j]) {

                        if (tutteLeX[i] == coordinatePrimo[0]) {
                            spazio[coordinatePrimo[1]][coordinatePrimo[0]] = 'x';
                        } else if (tutteLeX[i] == coordinateSecondo[0]) {
                            spazio[coordinateSecondo[1]][coordinateSecondo[0]] = 'x';
                        } else if (tutteLeX[i] == coordinateTerzo[0]) {
                            spazio[coordinateTerzo[1]][coordinateTerzo[0]] = 'x';
                        } else if (tutteLeX[i] == coordinateQuarto[0]) {
                            spazio[coordinateQuarto[1]][coordinateQuarto[0]] = 'x';
                        }
                    }
                }
            }
        }
        stampaMatrice(spazio);

        distinzioneForma(coordinateForma);

    }

    public static void distinzioneForma(int[][] coordinate) {
        int[] lunghezzeLati = new int[4];
        int[] lunghezzeDiagonali = new int[2];

        for (int i = 0; i < 4; i++) {
            int x1 = coordinate[i][0];
            int y1 = coordinate[i][1];
            int x2 = coordinate[(i + 1) % 4][0];
            int y2 = coordinate[(i + 1) % 4][1];
            lunghezzeLati[i] = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
        }

        lunghezzeDiagonali[0] = (coordinate[0][0] - coordinate[2][0]) * (coordinate[0][0] - coordinate[2][0]) +
                (coordinate[0][1] - coordinate[2][1]) * (coordinate[0][1] - coordinate[2][1]);
        lunghezzeDiagonali[1] = (coordinate[1][0] - coordinate[3][0]) * (coordinate[1][0] - coordinate[3][0]) +
                (coordinate[1][1] - coordinate[3][1]) * (coordinate[1][1] - coordinate[3][1]);

        boolean latiUguali = (lunghezzeLati[0] == lunghezzeLati[1] && lunghezzeLati[1] == lunghezzeLati[2] && lunghezzeLati[2] == lunghezzeLati[3]);
        boolean diagonaliUguali = (lunghezzeDiagonali[0] == lunghezzeDiagonali[1]);

        String forma;
        if (latiUguali && diagonaliUguali) {
            forma = "Quadrato";
        } else if (latiUguali) {
            forma = "Rombo";
        } else if (lunghezzeLati[0] == lunghezzeLati[2] && lunghezzeLati[1] == lunghezzeLati[3]) {
            forma = "Rettangolo";
        } else if ((coordinate[0][0] == coordinate[1][0] && coordinate[2][0] == coordinate[3][0]) ||
                (coordinate[0][1] == coordinate[1][1] && coordinate[2][1] == coordinate[3][1])) {
            forma = "Trapezio";
        } else {
            forma = "Nessuna forma";
        }

        System.out.println("La forma è: " + forma);
    }
    public static void stampaMatrice(int[][] matrice) {
        for (int i = matrice.length - 1; i >= 0; i--) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
    }
}