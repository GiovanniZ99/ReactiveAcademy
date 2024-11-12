package academy.esercizi.esercizio_18_5;
/* Scrivete un programma che calcoli e visualizzi il saldo di un conto bancario dopo il primo anno.
 Il conto ha un saldo iniziale di  1824 euro (double saldo = 1824) e vi vengono accreditati
 interessi pari al 0,69% (double interessi = 0.69/100) del saldo. Provate con una calcolatrice,
 il risultato deve venire 1836,5856 e ricorda uno dei primi esercizi… è stato svolto con la stessa
 implementazione?
 */
public class Esercizio5 {
    public static void main(String[] args) {
        Conto conto = new Conto(1824, 0.69 / 100);
        double risultato = conto.calcolaInteresse(conto.getSaldo(), conto.getInteressi());

        System.out.printf("%.4f\n", risultato);
    }
}
