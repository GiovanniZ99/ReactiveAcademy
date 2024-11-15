package academy.esercizi.esercizio_28;
/* Il Gioco della Vita (The Game of Life) è un gioco matematico molto conosciuto che genera un comportamento
incredibilmente complesso, sebbene sia definibile con poche e semplici regole (in realtà, non è un vero e proprio gioco
in senso tradizionale, perché non ha giocatori che gareggiano per vincere). Il gioco si svolge su una scacchiera
rettangolare e ogni suo riquadro può essere vuoto o occupato. All’inizio deve essere possibile specificare in qualche modo
quali siano i riquadri vuoti e quali siano, invece, occupati, dopodiché il gioco procede in modo autonomo. A ogni passo
viene calcolata la generazione successiva della popolazione sulla scacchiera: si assiste a una “nascita” in un riquadro
vuoto se questo ha tre riquadri adiacenti occupati; si verifica una “morte per sovraffollamento” in un riquadro occupato
se questo ha quattro o più riquadri adiacenti occupati, mentre c’è una “morte per solitudine” se il riquadro occupato ha
al più un solo riquadro adiacente occupato. Un riquadro adiacente può trovarsi a sinistra, a destra, sopra, sotto o in
direzione diagonale. La figura mostra un riquadro e i suoi adiacenti. Molte configurazioni, quando vengono assoggettate
a queste regole, presentano un comportamento interessante. Nella figura più in basso si vede un aliante (glider),
osservato in una successione di cinque generazioni: ogni quattro generazioni riprende la stessa forma, spostata di un riquadro verso il basso e verso destra. Una delle configurazioni più sorprendenti è lo spara-alianti (glider gun), un complesso insieme di riquadri che dopo 30 mosse ritorna alla sua forma iniziale. Scrivete un programma che elimini l’ingrato compito del calcolo manuale delle generazioni che si susseguono, mostrandole sullo schermo. Usate un array bidimensionale per memorizzare la configurazione rettangolare e chiedete all’utente di indicare la configurazione iniziale,
 fornendo in ingresso una sequenza di spazi (per i riquadri vuoti) e di caratteri o (per i riquadri occupati).*/

import java.util.Scanner;

public class Esercizio1 {
    public static void main(String[] args) {

        int[][] scacchiera = faiScacchieraIniziale();  // Scacchiera iniziale vuota

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.println("Inserisci le coordinate x e y per riempire la matrice di 6 righe e 7 colonne con o ");
            int riga = scanner.nextInt();
            int colonna = scanner.nextInt();

            if (riga >= 0 && riga < scacchiera.length && colonna >= 0 && colonna < scacchiera[0].length) {
                scacchiera[riga][colonna] = 1;
            }
        }

        System.out.println("Scacchiera iniziale:");
        stampaScacchiera(scacchiera);
        int count = 0;
        int countWhile = 0;

        // non modifico direttamente la matrice, mi salvo le coordinate in una matrice d'appoggio insieme al numero
        // una volta che mi sono segnato tutte le coordinate che devono essere cambiate con 1 o 0 (quindi dopo i for)
        // modifico la matrice
        // no, non metto nelle coordinate, devo modificare in corsa avendo come riferimento la matrice iniziale
        while (countWhile != 4) {
            int[][] matriceModificata = copiaMatrice(scacchiera);
            for (int i = 0; i < scacchiera.length; i++) {
                for (int j = 0; j < scacchiera[i].length; j++) {

                    if (i < scacchiera.length - 1 && scacchiera[i + 1][j] == 1) {
                        count++;
                    }

                    if (j > 0 && i < scacchiera.length - 1 && scacchiera[i + 1][j - 1] == 1) {
                        count++;
                    }
                    if (j > 0 && i < scacchiera.length - 1 &&
                            j < scacchiera[i].length - 1 && scacchiera[i + 1][j + 1] == 1) {
                        count++;
                    }

                    if (j < scacchiera[i].length - 1 && scacchiera[i][j + 1] == 1) {
                        count++;
                    }

                    if (j > 0 && scacchiera[i][j - 1] == 1) {
                        count++;
                    }

                    if (i > 0 && scacchiera[i - 1][j] == 1) {
                        count++;
                    }

                    if (i > 0 && j < scacchiera[i].length - 1 && scacchiera[i - 1][j + 1] == 1) {
                        count++;
                    }

                    if (i > 0 && j > 0 && scacchiera[i - 1][j - 1] == 1) {
                        count++;
                    }
                    if (count < 2 && scacchiera[i][j] == 1) {
                        matriceModificata[i][j] = 0;
                        count = 0;
                    } else if (count == 3 && scacchiera[i][j] == 0) {
                        matriceModificata[i][j] = 1;
                        count = 0;

                    } else if (count >= 4 && scacchiera[i][j] == 1) {
                        matriceModificata[i][j] = 0;
                        count = 0;
                    } else {
                        count = 0;
                    }
                }

            }

            System.out.println();
            for (int i = 0; i < scacchiera.length; i++) {
                for (int j = 0; j < scacchiera[i].length; j++) {
                    System.out.print(matriceModificata[i][j] == 1 ? "o " : "  ");
                }
                System.out.println();
            }
            scacchiera = matriceModificata;
            countWhile++;
        }
    }

    private static int[][] faiScacchieraIniziale() {
        int[][] scacchiera = new int[6][7];
        for (int i = 0; i < scacchiera.length; i++) {
            for (int j = 0; j < scacchiera[i].length; j++) {
                scacchiera[i][j] = 0;
            }
        }

        return scacchiera;
    }
    private static void stampaScacchiera(int[][] scacchiera) {
        for (int i = 0; i < scacchiera.length; i++) {
            for (int j = 0; j < scacchiera[i].length; j++) {
                System.out.print(scacchiera[i][j] == 1 ? "o " : "  ");
            }
            System.out.println();
        }
    }

    public static int[][] copiaMatrice(int[][] matriceOriginale){
        int[][] copiaMatrice = new int[6][7];
            for (int k = 0; k < copiaMatrice.length; k++) {
                for (int l = 0; l < copiaMatrice[k].length; l++) {
                    copiaMatrice[k][l] = matriceOriginale[k][l];
                }
            }
        return copiaMatrice;
        }

    }



