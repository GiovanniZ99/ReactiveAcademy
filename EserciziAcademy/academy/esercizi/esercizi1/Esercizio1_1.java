package academy.esercizi.Esercizi1;
// Scrivete un programma che calcoli e visualizzi la somma dei primi dieci numeri interi positivi
public class Esercizio1_1 {
    public static void main(String[] args) {
        byte sum = 0;
        for(byte i=0; i<11;i++ ){
            sum +=i;
        }
        System.out.println(sum);
    }
}
