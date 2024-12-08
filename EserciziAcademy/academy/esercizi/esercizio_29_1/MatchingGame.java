package academy.esercizi.esercizio_29_1;

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
        Tile[][] tiles  = grid.mischiaCarte(grid.faiGriglia());
        grid.stampaGriglia(tiles);
        grid.copriCarte();
        grid.stampaGrigliaCoperta(tiles);

        int punteggio = 0;

        while (!grid.checkCarteScoperte()) {
            boolean checkImbroglio;
            boolean checkOutOfBound;
            Location locationPrimaCarta;
            Location locationSecondaCarta;

            do {
                System.out.println("Inserisci le coordinate delle due carte che vuoi scoprire");
                Scanner scanner = new Scanner(System.in);
                System.out.println("Inserisci la coordinate x della prima carta");
                int x = scanner.nextInt() -1;
                System.out.println("Inserisci la coordinata y della prima carta");
                int y = scanner.nextInt() -1;

                locationPrimaCarta = new Location(x, y);
                System.out.println("Inserisci la coordinata x della seconda carta");
                int xSecondaCarta = scanner.nextInt() -1;
                System.out.println("Inserisci la coordinata y della seconda carta");
                int ySecondaCarta = scanner.nextInt() -1;

                locationSecondaCarta = new Location(xSecondaCarta, ySecondaCarta);

                checkImbroglio = locationPrimaCarta.getCoordinataX() == locationSecondaCarta.getCoordinataX()
                        && locationPrimaCarta.getCoordinataY() == locationSecondaCarta.getCoordinataY();
                checkOutOfBound = x > 3 || y > 3 || xSecondaCarta > 3 || ySecondaCarta > 3;

                if (checkImbroglio) {
                    System.out.println("Non puoi scoprire la stessa carta due volte, riprova!");
                }else if(checkOutOfBound){
                    System.out.println("Dimensione di una delle coordinate troppo grande, riprova");
                }

            } while (checkImbroglio || checkOutOfBound);

            Tile primaCarta = tiles[locationPrimaCarta.getCoordinataY()][locationPrimaCarta.getCoordinataX()];
            Tile secondaCarta = tiles[locationSecondaCarta.getCoordinataY()][locationSecondaCarta.getCoordinataX()];

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
