package academy.esercizi.esercizio_29_1;

import java.util.Random;

public class Grid {
    private final static Tile[][] carte = new Tile[4][4];

    public Tile[][] getCarta() {
        return carte;
    }

    public Tile[][] faiGriglia() {
        int count = 1;
        for (int i = 0; i < carte.length; i++) {
            if (i == 1) {
                count = 1;
            }
            if (i == 3) {
                count = 5;
            }
            for (int j = 0; j < carte[i].length; j++) {
                carte[i][j] = new Tile(count);
                count++;
            }
        }
        return carte;
    }

    public Tile[][] mischiaCarte(Tile[][] carte) {
        Random random = new Random();
        Tile temp;

        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[i].length; j++) {
                int numeroCasuale = random.nextInt(carte.length);
                temp = carte[i][j];
                carte[i][j] = carte[numeroCasuale][numeroCasuale];
                carte[numeroCasuale][numeroCasuale] = temp;
            }
        }
        System.out.println("-----------------------------------------------");
        return carte;
    }

    public void stampaGriglia(Tile[][] carte) {
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[i].length; j++) {
                System.out.print(carte[i][j].getValore() + "\t");
            }
            System.out.println();
        }
    }

    public void stampaGrigliaCoperta(Tile[][] carte) {
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[i].length; j++) {
                System.out.print(carte[i][j].getDorso() + "\t");
            }
            System.out.println();
        }
    }

    public boolean checkAdiacenza(int coordinataX, int coordinataY, int coordinataXSeconda, int coordinataYSeconda){
        return coordinataX == coordinataXSeconda + 1 && coordinataY == coordinataYSeconda ||

                coordinataX == coordinataXSeconda - 1  && coordinataY == coordinataYSeconda
                || coordinataY == coordinataYSeconda + 1 && coordinataX == coordinataXSeconda
                || coordinataY == coordinataYSeconda - 1 && coordinataX == coordinataXSeconda;
    }
    public boolean checkCarteScoperte(){
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[i].length; j++) {
                if(carte[i][j].isCoperta()){
                    return false;
                }
            }
        }
        return true;
    }
    public void copriCarte() throws InterruptedException {
        Thread.sleep(3000);
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
