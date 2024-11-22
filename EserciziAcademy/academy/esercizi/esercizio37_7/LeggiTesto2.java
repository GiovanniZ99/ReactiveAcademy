package academy.esercizi.esercizio37_7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class LeggiTesto2 {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\G.Zaffinelli-cons\\Documents\\rootGit\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\esercizio37_7\\parole.txt";
        BufferedReader br = new BufferedReader(new FileReader(path));
        Map<String, Integer> parolaCount = new TreeMap<>();
        String line;
        while ((line = br.readLine()) != null) {

            String[] listaParole = line.split(", ");
            for (String parola : listaParole) {
                if (!parolaCount.containsKey(parola)) {
                    parolaCount.put(parola, 0);
                }

                int valore = parolaCount.get(parola);
                parolaCount.put(parola, ++valore);
            }
        }
        System.out.println(parolaCount);
    }

}
