package academy.esercizi.Esercizi1;
/*Scrivete un programma che calcoli e visualizzi il saldo di un conto bancario dopo il primo,
secondo e terzo anno. Il conto ha un saldo iniziale di 1000 euro e vi
vengono accreditati annualmente interessi pari al 5% del saldo */
public class Esercizio1_2 {
    public static void main(String[] args) {
        float saldoBancario = 1000f;
        float percentuale = 5f;
        for (int i = 0; i <4;i++){
           saldoBancario += saldoBancario * (percentuale / 100);
        }
        System.out.println(saldoBancario);
    }
}