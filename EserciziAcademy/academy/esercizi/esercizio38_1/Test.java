package academy.esercizi.esercizio38_1;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

/*Scrivere un programma per giocare al Gioco del Sogno.
Il computer sceglie al 50% un valore NULL o un numero compreso tra 100 e 2000. Il giocatore deve indovinare se è NULL
oppure se è valorizzato con un intero, se sbaglia ed il numero non era NULL viene dimezzato
(troncandolo all’intero più piccolo). Il gioco termina nei seguenti casi:
il giocatore indovina
il numero da indovinare diventa 1
Il giocatore tenta null dopo la terza mossa ma è valorizzato
Se il giocatore tenta null nelle prime tre mosse ed è valorizzato perde solamente la mossa e può continuare a giocare.
Realizzare il programma creando:
Una classe ElementoCasualeOptional con una variabile valore di tipo Optional<Integer> Nel costruttore valorizzare il valore
empty oppure con un numero casuale tra 100 e 2000. Aggiungere un costruttore per forzare il valore e non utilizzare quello
casuale.
Una classe GiocoDelSognoOptional con tutta la logica del gioco
Una classe di test che permetta di eseguire il Gioco, con lo Scanner o con dei file contenenti le mosse.

N.B. Nelle classi ElementoCasualeOptional e GiocoDelSognoOptional non deve comparire la parola null
(tranne che nei metodi di Optional come ofNullable). Usare almeno una volta i metodi of, ofNullable, empty, isPresent,
get, orElse, map, orElseThrow di Optional

Per spiegazioni e/o suggerimenti è possible guardare su git dove è presente  una versione “nullable” del programma,
anche se è consigliato sviluppare l’esercizio da zero.
*/
public class Test {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        ElementoCasualeOptional elementoCasualeOptional = new ElementoCasualeOptional();
        GiocoDelSognoOptional giocoDelSognoOptional = new GiocoDelSognoOptional(elementoCasualeOptional);
        boolean continua;
        System.out.println("Indovinare se il numero casuale è null");
        System.out.println("Inserire false se pensi che il numero sia null, altrimenti inserire true");
        int i = 0;
        do {
            try {
                boolean valore = SCANNER.nextBoolean();
                continua = giocoDelSognoOptional.checkRisposta(valore, i);
            } catch (InputMismatchException e) {
                System.out.println("Il valore inserito non è un booleano, il valore sarà forzato a true");
                // non avevo usato l'orElse quindi me ho forzato l'uso
                Optional<Boolean> valoreDaSostituire = Optional.empty();
                valoreDaSostituire = Optional.of(valoreDaSostituire.orElse(true));

                continua = giocoDelSognoOptional.checkRisposta(valoreDaSostituire.get(), i);
            }
            i++;
        } while (!continua);
    }
}
