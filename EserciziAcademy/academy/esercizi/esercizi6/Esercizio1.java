package academy.esercizi.esercizi6;

import java.util.Arrays;

/*Scrivere dei programmi per visualizzare le seguenti tabelle con l’uso del printf*/
public class Esercizio1 {
    public static void main(String[] args) {
       creaTbella();
    }

    public static void creaTbella() {
        System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "x", "x^2", "x^3", "x^4", "x^5");
        System.out.println("-------------------------------------------------------");

        for (int i = 1; i <= 10; i++) {
            System.out.printf("%-10d", i);

            for (int j = 2; j <= 5; j++) {
                System.out.printf("%-10d", (int) Math.pow(i, j));
            }
            System.out.println();
        }

        System.out.println("-------------------------------------------------------");
    }
}