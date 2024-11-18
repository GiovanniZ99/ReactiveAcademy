package academy.esercizi.esercizio_29_1;

import academy.esercizi.esercizio_27_2.Carta;

import java.util.Scanner;

/* In un gioco di carte in cui si cercano le coppie, le carte vengono disposte in una griglia con righe e colonne.
Il giocatore scopre due carte per volta e, se sono uguali, guadagna un punto (e un punto ulteriore se le due carte sono
adiacenti). Progettate un programma che consenta di giocare, usando le classi Tile (carta), Location (posizione,
che incapsula l’indicazione di una riga e di una colonna), Grid  (griglia) e MatchingGame (il gioco). Eseguire il gioco
richiedendo tramite la classe Scanner ai giocatori le coordinate delle carte da mostrare. Il gioco finisce quando tutte
le coppie sono state rivelate.
 */
public class MatchingGame {
    public static void main(String[] args) throws InterruptedException {
        Grid grid = new Grid();

        Tile[][] tiles = grid.faiGriglia();
        grid.stampaGriglia(grid.mischiaCarte(tiles));
        grid.copriCarte();
        grid.stampaGrigliaCoperta(tiles);

        int punteggio = 0;

        while (!grid.checkCarteScoperte()) {
            System.out.println("Inserisci le coordinate delle due carte che vuoi scoprire");

            Scanner scanner = new Scanner(System.in);

            int x = scanner.nextInt();
            int y = scanner.nextInt();

            Location locationPrimaCarta = new Location(x, y);
            Tile primaCarta = tiles[locationPrimaCarta.getCoordinataX()][locationPrimaCarta.getCoordinataY()];

            int xSecondaCarta = scanner.nextInt();
            int ySecondaCarta = scanner.nextInt();
            Location locationSecondaCarta = new Location(xSecondaCarta, ySecondaCarta);
            Tile secondaCarta = tiles[locationSecondaCarta.getCoordinataX()][locationSecondaCarta.getCoordinataY()];

            if (primaCarta.getValore() == secondaCarta.getValore() && primaCarta.isCoperta() && secondaCarta.isCoperta()) {
                if (grid.checkAdiacenza(locationPrimaCarta.getCoordinataX(), locationPrimaCarta.getCoordinataY(),
                        locationSecondaCarta.getCoordinataX(), locationSecondaCarta.getCoordinataY())) {
                    punteggio += 2;
                    System.out.println("Carte uguali e adiacenti, punteggio aumentato di 2");
                } else {

                    punteggio++;
                    System.out.println("Carte uguali, punteggio aumentato di 1");
                }
                primaCarta.setCoperta(false);
                secondaCarta.setCoperta(false);
            } else if (!primaCarta.isCoperta()) {
                System.out.println("Carte già scoperte");
            } else {
                System.out.println("Carte diverse!");
                primaCarta.setCoperta(true);
                secondaCarta.setCoperta(true);
            }
            System.out.println("Il tuo punteggio è " + punteggio);
        }

    }
}
