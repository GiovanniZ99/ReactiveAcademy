package academy.esercizi.esercizi10;

import java.util.Scanner;

/* Scrivete un programma che converta un numero intero positivo nel corrispondente
 valore espresso nel sistema di numerazione romano. I numeri romani sono costituiti
 da sette diverse cifre, con questi valori:
I 1
V 5
X 10
L 50
C 100
D 500
M 1000
e i numeri vengono composti seguendo queste regole:
a. Si possono rappresentare soltanto i numeri fino a 3999.
b. Come nel sistema decimale, le migliaia, le centinaia, le decine e
le unità vengono espresse separatamente.
c. I numeri da 1 a 9 sono rappresentati, in ordine crescente, da I, II, III, IV, V, VI, VII,
VIII, IX; come potete notare, una lettera I che precede la lettera V o X rappresenta un’unità
 che viene sottratta dal valore; inoltre, non si possono avere più di tre I consecutive.
d. Le decine e le centinaia sono gestite allo stesso modo, tranne per il fatto che, al posto
delle lettere I, V e X, si usano le lettere X, L e C e, rispettivamente, le lettere C, D e M.
Il programma deve acquisire un numero in ingresso, come 1978, e convertirlo secondo
la numerazione
romana, visualizzando, in questo esempio, MCMLXXVIII.
*/
public class Eserciziio1 {
    public static void main(String[] args) {
        System.out.println("Inserire numero da convertire in numero romano");
        System.out.println("Non è possibile convertire un numero maggiore di 3999");
        Scanner scanner = new Scanner(System.in);
        int numeroDaConvertire = scanner.nextInt();
        StringBuilder numeroConvertito = new StringBuilder();

        if(numeroDaConvertire > 3999){
            return;
        }
        while (numeroDaConvertire >= 1000) {
            numeroConvertito.append('M');
            numeroDaConvertire -= 1000;
        }
        if (numeroDaConvertire >= 900) {
            numeroConvertito.append("CM");
            numeroDaConvertire -= 900;
        }
        if (numeroDaConvertire >= 500) {
            numeroConvertito.append('D');
            numeroDaConvertire -= 500;
        }
        if (numeroDaConvertire >= 400) {
            numeroConvertito.append("CD");
            numeroDaConvertire -= 400;
        }
        while (numeroDaConvertire >= 100) {
            numeroConvertito.append('C');
            numeroDaConvertire -= 100;
        }
        if (numeroDaConvertire >= 90) {
            numeroConvertito.append("XC");
            numeroDaConvertire -= 90;
        }
        if (numeroDaConvertire >= 50) {
            numeroConvertito.append('L');
            numeroDaConvertire -= 50;
        }
        if (numeroDaConvertire >= 40) {
            numeroConvertito.append("XL");
            numeroDaConvertire -= 40;
        }
        while (numeroDaConvertire >= 10) {
            numeroConvertito.append('X');
            numeroDaConvertire -= 10;
        }
        if (numeroDaConvertire == 9) {
            numeroConvertito.append("IX");
            numeroDaConvertire -= 9;
        }
        if (numeroDaConvertire >= 5) {
            numeroConvertito.append('V');
            numeroDaConvertire -= 5;
        }
        if (numeroDaConvertire == 4) {
            numeroConvertito.append("IV");
            numeroDaConvertire -= 4;
        }
        while (numeroDaConvertire >= 1) {
            numeroConvertito.append('I');
            numeroDaConvertire -= 1;
        }

        System.out.println(numeroConvertito);
    }
}


