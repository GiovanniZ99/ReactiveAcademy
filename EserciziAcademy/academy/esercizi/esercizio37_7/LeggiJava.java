package academy.esercizi.esercizio37_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/*Scrivete un programma che legga un file di codice sorgente Java e generi un elenco di tutti gli identificatori presenti,
visualizzando, accanto a ciascuno di essi, i numeri delle righe in cui compare. Per semplicità considereremo che qualsiasi
stringa costituita soltanto da lettere, cifre numeriche e caratteri di sottolineatura sia un identificatore. Dichiarate la
variabile Scanner in per leggere il file, ciclatela con nextLine e splittate ciascuna riga con il pattern "[^A-Za-z0-9_]+"
in modo da ottenere un array di identificatori ed il numero di righe contato in base alle volte in cui si richiama nextLine.*/
public class LeggiJava {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\G.Zaffinelli-cons\\Documents\\rootGit\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\esercizio37_7\\LeggiTesto.java";
        File file = new File(path);
        Scanner righe = new Scanner(file);
        int count = contaRighe(righe);
        Scanner in = new Scanner(file);
        String[] identificatori = new String[count];
        faiArrayIdentificatori(in, identificatori);
        System.out.println(count);
    }

    private static void faiArrayIdentificatori(Scanner in, String[] identificatori) {
        int i = 0;
        while (in.hasNext()) {
            String frase = in.nextLine();
            identificatori[i] = Arrays.toString(frase.split("[^A-Za-z0-9_]+"));
            i++;
        }
        System.out.println(Arrays.toString(identificatori));
    }

    private static int contaRighe(Scanner in) {
        int count = 0;
        while (in.hasNext()) {
            in.nextLine();
            count++;
        }
        return count;
    }
}
