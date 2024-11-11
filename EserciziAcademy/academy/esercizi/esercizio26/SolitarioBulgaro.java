package academy.esercizi.esercizio26;

import java.util.Arrays;
import java.util.Random;

/* Scrivere un programma per giocare al solitario «bulgaro». Il gioco inizia con 45 carte
(non è necessario che siano carte da gioco, bastano dei cartoncini impilabili senza segni di
distinzione), che vanno divise in un certo numero di mucchietti di dimensione casuale: potreste,
ad esempio, iniziare con mucchietti di dimensioni pari a 20, 5, 1, 9 e 10. Ad ogni turno prendete
una carta da ciascun mucchietto, formando con queste un nuovo mucchietto: la configurazione
iniziale che abbiamo usato prima come esempio verrebbe trasformata in un insieme di mucchietti
aventi dimensioni pari a 19, 4, 8, 9 e 5. Il solitario termina quando sono presenti,
in qualsiasi ordine, le pile aventi dimensioni pari a 1, 2, 3, 4, 5, 6, 7, 8 e 9
(e si può dimostrare che ciò accade sempre). Nel vostro programma generate una configurazione
iniziale casuale e visualizzatela. Poi, eseguite turni del solitario, uno dopo l’altro,
visualizzando la situazione dopo ogni turno. Fermatevi quando è stata raggiunta la
configurazione finale del solitario.
Il metodo di start del programma chiede il numero di mucchietti iniziali.
Definire una costante per il numero di carte (45), un array di interi pile pari al numero di
carte e una variabile contamosse.
Progettare i metodi:
Gioca  richiamato dal main con il numero di mucchetti iniziali. Richiama la configurazione ed
in loop muovi fino a quando  il gioco non è finito. Al termine stampa il numero di mosse
necessarie per concludere il gioco.
NumeroCasuale  prende in input un valore e restituisce un numero casuale compreso tra 1 ed il
valore in input estremi compresi
ConfigurazioneIniziale  prende in input il numero di pile iniziali e valorizza tutte le pile
tranne l’ultima con un numero casuale di elementi che va calcolato generando un valore casuale
compreso tra 1 ed un «tetto» calcolato per poter generare le restanti pile con almeno 1 elemento.
L’ultima pila sarà calcolata per differenza con il numero di carte rimaste libere dalle pile
precedenti. Al termine richiama il metodo stampa.
Muovi  sottrae una carta da ciascuna pila con almeno 1 elemento e crea una nuova pila con il
totale delle carte rimosse. Al termine richiama il metodo stampa.
Stampa  stampa il numero di carte per ciascuna pila il cui numero di carte sia almeno di 1
elemento. Al termine verifica la correttezza del gioco stampando la somma di carte di ciascuna
pila (la somma deve fare 45!).
Finito  restituisce un boolean a true se il gioco è finito. Possiamo usare un array d’appoggio
di size 9 e memorizzare il valore 1 nella posizione corrispondente al numero di elementi di tutte
le pile (ovviamente scartando le pile con elementi <1 o >9). Se l’array d’appoggio avrà tutte le
9 posizione valorizzate con 1 il gioco sarà concluso.
*/
public class SolitarioBulgaro {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        int[] pile = configurazioneIniziale(numeroCasuale(6));

       do{
         muovi(pile);

       }while(!finito(pile));

    }

    public static int numeroCasuale(int numeroMucchietto) {
        return RANDOM.nextInt(numeroMucchietto) + 1;
    }

    public static int[] configurazioneIniziale(int numeroMucchietto) {
        int[] pile = new int[45];
        int carteDisponibili = 45;
        for (int i = 0; i < numeroMucchietto - 1; i++) {
            pile[i] = RANDOM.nextInt(carteDisponibili / numeroMucchietto + 1) + 1;
            carteDisponibili -= pile[i];
        }
        int sum = 0;
        for (int i = 0; i < pile.length; i++) {
            sum += pile[i];
        }
        pile[numeroMucchietto - 1] = 45 - sum;
        for (int i = 0; i < pile.length; i++) {
            System.out.println(pile[i]);
        }
        return pile;
    }

    // in pratica devo diminuire di 1 array [I] di pile,
    // mettere ogni uno in una nuova cella dell'array fino a quando non sono valorizzate tutte le posizioni con valori da 1 a 9
    public static int[] muovi(int[] pile) {
        int contatore = 0;
        for (int i = 0; i < pile.length - 1; i++) {
            if (pile[i] > 0) {
                pile[i]--;
                contatore++;
            }
        }
// se la pila della posizione corrente e > 0 allora sottraggo uno e aumento un contatore di 1, se quella dopo è una cella
            // uguale a 0 allora metto il contatore a quella posizione, altirmenti vado avanti col ciclo
        for (int i = 0; i < pile.length ; i++) {
            if (pile[i] == 0 && contatore > 0) {
                pile[i] = contatore;
                break;
            }
        }

        stampa(pile);
        return pile;
    }

    public static void stampa(int[] pile) {
        int sum = 0;
        for (int i = 0; i < pile.length; i++) {
            if (pile[i] != 0) {
                System.out.println(" " + pile[i] + " ");
                sum += pile[i];
            }
        }
        System.out.println(sum);
    }

    private static boolean finito(int[] pile) {
        boolean[] arrayAppoggio = new boolean[9];

        for (int i = 0; i < pile.length; i++) {
            if (pile[i] >= 1 && pile[i] <= 9) {
                arrayAppoggio[pile[i] - 1] = true;
            }
        }

        for (boolean valore : arrayAppoggio) {
            if (!valore) {
                return false;
            }
        }

        return true;
    }
}



