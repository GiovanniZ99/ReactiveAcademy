package academy.esercizi.esercizi2;

/* Scrivere un programma che definisce un array di 10 elementi e popolarlo con valori di fantasia,
si cancelli l'elemento a posizione 4 facendo shiftare i valori di tutti gli elementi successivi
a sinistra di uno */
public class Esercizio2_6 {
    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for(int i = 3; i<num.length-1; i++){
            num[i] = num[i+1];

        }
        num[num.length-1] = 0;

        for (int j : num) {
            System.out.println(j);
        }
    }
}