package academy.esercizi.esercizi24;

import java.util.Random;
import java.util.Scanner;

/*(*) Il gioco di Nim. Si tratta di un gioco molto noto, con un certo numero di varianti:
quella qui descritta ha una strategia vincente davvero interessante. Due giocatori prelevano
alternativamente biglie da un mucchietto. Ad ogni mossa il giocatore di turno sceglie quante
biglie prendere: almeno una e al massimo metà delle biglie disponibili. Poi è il turno dell’altro
giocatore. Il giocatore che prende l’ultima biglia perde la partita. Scrivete un programma che
consenta all’utente di giocare contro il computer. Generate un numero intero compreso tra 10 e
100 e usatelo come dimensione iniziale del mucchietto di biglie. Generate un numero intero, 0 o
1, e utilizzatelo per decidere se sarà l’utente o il computer a giocare per primo. Generate un
altro numero intero, 0 o 1, e usatelo per decidere se il computer giocherà in modo intelligente
o stupido: giocando in modo stupido, ad ogni sua mossa il computer semplicemente preleva dal
mucchietto un numero di biglie casuale (ma valido, cioè compreso tra 1 e n/2, se nel mucchietto
sono rimaste n biglie); in modalità intelligente, invece, preleva un numero di biglie tale che
il numero di quelle che rimangono nel mucchio sia una potenza di due diminuita di un’unità,
cioè 3, 7, 15, 31 o 63. Quest’ultima è sempre una mossa valida, tranne quando la dimensione
del mucchio è proprio uguale a una potenza di due diminuita di un’unità: in tal caso il computer
fa una mossa scelta a caso (ovviamente tra quelle valide). Come potrete verificare
sperimentalmente, il computer non può essere battuto quando gioca in modalità intelligente e fa
la prima mossa, a meno che la dimensione iniziale del mucchio non sia 15, 31 o 63. Analogamente,
un giocatore umano che faccia la prima mossa e conosca la strategia qui descritta è in grado di
battere il calcolatore.
 */
public class Esercizio1 {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Random random = new Random();
        int biglie = (random.nextInt(91)) + 10;
        System.out.println("Il numero di biglie iniziale è: " + biglie);
        int livelloDifficoltaComputer = random.nextInt(2);
        System.out.println("Il livello di difficolta è: " + livelloDifficoltaComputer);
        int turnoIniziale = random.nextInt(2);

        gioca(biglie, turnoIniziale, livelloDifficoltaComputer);
    }

    private static void gioca(int biglie, int turno, int livelloDifficoltaComputer) {
        while (biglie > 1) {
            if (turno == 0) {
                int biglieScelte;

                do {
                   biglieScelte = scegliBiglie(biglie);
                } while (biglieScelte == 0);
                biglie -= biglieScelte;
                System.out.printf("Il numero di biglie rimaste è: %d %n", biglie);
                turno = 1;
            } else {
                if(livelloDifficoltaComputer ==1){
                    biglie -= getBiglieIntelligente(biglie);
                }else{
                    biglie -= getBiglieStupido(biglie);
                }
                System.out.printf("Il numero di biglie rimaste è: %d %n", biglie);
                turno = 0;
            }
            if(biglie ==1){
                checkVincitore(turno);
            }
        }
    }

    private static int getBiglieIntelligente(int biglie) {
        int target = (int) (Math.pow(2, Math.floor(Math.log(biglie) / Math.log(2))) - 1);
        int numeroBigliePrelevatoDalComprIntelligente = biglie - target;

        // Se biglie è uno dei casi speciali
        if (biglie == 3 || biglie == 7 || biglie == 15 || biglie == 31 || biglie == 63) {
            Random random = new Random();
            int maxPrelevare = biglie / 2;
            int numeroBigliePrelevato = random.nextInt(maxPrelevare) + 1;
            System.out.println("Il computer ha preso " + numeroBigliePrelevato + " biglia/e");
            return numeroBigliePrelevato;
        }

        System.out.println("Il computer ha preso " + numeroBigliePrelevatoDalComprIntelligente + " biglia/e");
        return numeroBigliePrelevatoDalComprIntelligente;
    }

    private static int getBiglieStupido(int biglie) {
        Random random = new Random();
        int maxPrelevare = biglie / 2;
        int numeroBigliePrelevato = random.nextInt(maxPrelevare) + 1;
        System.out.println("Il computer ha preso " + numeroBigliePrelevato + " biglia/e");
        return numeroBigliePrelevato;
    }

    private static int scegliBiglie(int biglie) {
        System.out.println("Scegli il numero di biglie");
        int numeroBigliePrelevato;
        numeroBigliePrelevato = scanner.nextInt();

        if (numeroBigliePrelevato >= 1 && numeroBigliePrelevato <= biglie / 2) {
            return numeroBigliePrelevato;
        }
        System.out.println("Hai inserito un numero di biglie non valido, riprova");
        return 0;
    }
    private static void checkVincitore(int turno){
            if(turno == 0){
                System.out.println("Hai perso...");
            }else{
                System.out.println("Complimenti, hai vinto!");
            }
    }
}
