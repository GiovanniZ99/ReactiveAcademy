package academy.esercizi.esercizio37_7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* Scrivere un programma che legge un file di testo e visualizza un elenco contenente, in ordine alfabetico,
tutte le parole presenti nel file, seguite da un conteggio che indica il numero di ripetizioni di ciascuna parola */
public class LeggiTesto {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\G.Zaffinelli-cons\\Documents\\rootGit\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\esercizio37_7\\parole.txt";
        BufferedReader br = new BufferedReader(new FileReader(path));
        Map<String, Integer> parolaCount = new TreeMap<>();
        String line;
        while ((line = br.readLine()) != null) {

            String[] listaParole = line.split(", ");
            for (String parola : listaParole) {
                if (parolaCount.containsKey(parola)) {
                    int valore = parolaCount.get(parola);

                    parolaCount.put(parola, ++valore);
                }else{
                    parolaCount.put(parola, 1);
                }
            }
        }
        System.out.println(parolaCount);

    }


}
