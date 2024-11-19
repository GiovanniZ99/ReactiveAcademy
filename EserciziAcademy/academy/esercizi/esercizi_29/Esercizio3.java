package academy.esercizi.esercizi_29;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/*Il formato CSV (l’acronimo di comma-separated values, cioè “valori separati da virgole”) è molto usato per
memorizzare dati in forma tabulare. Ogni riga della tabella è una riga del file, con colonne separate da virgole.
I singoli valori possono essere racchiusi tra virgolette, cosa che deve certamente accadere se contengono virgole o virgolette.
Le virgolette presenti all’interno di valori racchiusi tra virgolette vanno raddoppiate. Realizzate la classe CSVReader che
legga un file CSV e metta a disposizione i metodi seguenti: int numberOfRows()  numero di righe int numberOfFields(int row)
 numero di campi della riga row String field(int row, int column)  campo presente nella riga row e colonna comumn. La cosa
comoda è che con excel si aprono i CSV. Provate a vedere se il file prodotto dal vostro applicativo si apre con excel,
modificatelo con questo programma ed una volta stampato provate a leggerlo dal vostro programma (fate attenzione ai
separatori di colonne che potrebbero variare da «,» a «;»)*/
public class Esercizio3 {
    private final static Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) throws IOException {

        try (BufferedReader lettore = new BufferedReader(new FileReader("academy/esercizi/esercizi_29/elenco.csv"))) {
            System.out.printf("Le righe presenti nel file sono %d.\n", numberOfRows(lettore));
        }
        try (BufferedReader lettore = new BufferedReader(new FileReader("academy/esercizi/esercizi_29/elenco.csv"))) {
            System.out.println("In quale riga vuoi conoscere il numero di campi predisposti: ");
            System.out.printf("Alla riga selezionata ci sono: %d\n", numberOfFields(lettore, prendiInput()));
        }
        try (BufferedReader lettore = new BufferedReader(new FileReader("academy/esercizi/esercizi_29/elenco.csv"))) {
            System.out.println("Inserisci la riga e la colonna per ottenere il campo (riga,colonna): ");

            int riga = prendiInput();
            int colonna = prendiInput();
            System.out.printf("Il valore della cella selezionata è: %s\n", field(lettore, riga, colonna));

        }

    }

    public static int numberOfRows(BufferedReader lettore) throws IOException {
        int numRow = 0;
        while (lettore.readLine() != null) {
            numRow += 1;
        }
        return numRow;
    }

    public static int numberOfFields(BufferedReader lettore, int row) throws IOException {
        int numRow = 0;

        String rigaSelezionata;
        while ((rigaSelezionata = lettore.readLine()) != null) {
            if (numRow == row) {
                return rigaSelezionata.split(";").length;
            }
            numRow++;
        }
        return 0;
    }

    public static String field(BufferedReader lettore, int row, int column) throws IOException {
        int numRow = 0;
        String rigaSelezionata;
        while ((rigaSelezionata = lettore.readLine()) != null) {
            if (numRow == row) {
                String[] campi = rigaSelezionata.split(";");
                if (column >= 0 && column < campi.length) {
                    return campi[column];
                } else {
                    return "Colonna non valida";
                }
            }
            numRow++;
        }
        return "Riga non valida";
    }

    private static int prendiInput() {
        return SCANNER.nextInt();
    }
}



