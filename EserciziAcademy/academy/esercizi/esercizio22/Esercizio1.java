package academy.esercizi.esercizio22;

import java.util.Scanner;

/* Realizzate un programma che aiuti un cassiere a dare il resto. Il programma riceve due dati
in ingresso: la somma da pagare e la quantità di denaro pagata dal cliente. Visualizzate il resto
dovuto sotto forma di quantità di monete da un dollaro, da un quarto di dollaro (quarter),
da dieci centesimi (dime), da cinque centesimi (nickel) e da un centesimo (penny).Per evitare
errori di arrotondamento, l’utente del programma deve fornire entrambi i valori in centesimi,
scrivendo, ad esempio, 274 invece di 2.74. */
class Esercizio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sommaDaPagare = scanner.nextInt();
        int denaroCliente = scanner.nextInt();
        int resto = denaroCliente - sommaDaPagare;
        int []contoMonete =daiResto(resto);
        System.out.println("La quantità di monete in ordine da 1 dollaro a 1 centesimo sono:");
        for (int j : contoMonete) {
            System.out.println(j);
        }

    }

    public static int[] daiResto(int resto) {
        int moneteDaUnDollaro = 0;
        int moneteDaVenticinqueCentesimi = 0;
        int moneteDaDieciCentesimi = 0;
        int moneteDaCinqueCentesimi = 0;
        int moneteDaUnCentesimo = 0;
        while (resto > 0) {
            if (resto >= 100) {
                resto -= 100;
                moneteDaUnDollaro++;
            }else if (resto >= 25) {
                resto -= 25;
                moneteDaVenticinqueCentesimi++;
            }else if (resto >= 10) {
                resto -= 10;
                moneteDaDieciCentesimi++;
            }else if (resto >= 5) {
                resto -= 5;
                moneteDaCinqueCentesimi++;
            }else {
                resto -= 1;
                moneteDaUnCentesimo++;
            }
        }
        return new int[] {moneteDaUnDollaro, moneteDaVenticinqueCentesimi, moneteDaDieciCentesimi,
                moneteDaCinqueCentesimi, moneteDaUnCentesimo};
    }


}

