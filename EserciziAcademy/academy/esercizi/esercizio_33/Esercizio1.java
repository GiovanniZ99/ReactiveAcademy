package academy.esercizi.esercizio_33;

import java.util.Arrays;

/* Considerate la seguente interfaccia, che descrive il comportamento di un oggetto che trasforma in stringa un numero intero:
public interface NumberFormatter
	{
		String format(int n);
	}
Progettate quattro classi che implementino questa interfaccia.
DefaultFormatter  trasforma in stringa un numero intero nel modo consueto.
DecimalSeparatorFormatter  aggiunge virgole come separatori delle migliaia: ad esempio, il numero un milione viene
trasformato nella stringa "1,000,000".
AccountingFormatter  racchiude i numeri negativi tra parentesi tonde: ad esempio, il numero –1 viene trasformato nella
stringa "(1)".
BaseFormatter  considera il numero in base n, dove n è un numero fornito al costruttore e compreso tra 2 e 36.
Integer.toString(int n, int b)
restituisce una string con n in base b
Scrivete un metodo che riceva come parametri un array di numeri interi e un oggetto NumberFormatter e visualizzi ciascun
numero su una riga separata, dopo averlo trasformato in stringa usando l’oggetto NumberFormatter ricevuto. I numeri
visualizzati devono essere incolonnati a destra*/
public class Esercizio1 {
    public static void main(String[] args) {
        DecimalSeparatorFormatter dcf = new DecimalSeparatorFormatter();
        System.out.println(dcf.format(10000001));
        AccountingFormatter acf = new AccountingFormatter();
        System.out.println(acf.format(1));
        System.out.println(acf.format(-12121211));

        try {
            BaseFormatter bsf = new BaseFormatter(1);
            System.out.println(bsf.format(2));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            BaseFormatter bsfValido = new BaseFormatter(2);
            System.out.println(bsfValido.format(2));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        NumberFormatter numberFormatter = new DefaultFormatter();

        int[] prova = {1, 2, 3, 4};
        formattaResult(prova, numberFormatter);

    }
    public static void formattaResult(int[] array, NumberFormatter oggetto){
        for (int i = 0; i < array.length; i++) {
            System.out.println("\t" + oggetto.format(array[i]));
        }
    }
}
