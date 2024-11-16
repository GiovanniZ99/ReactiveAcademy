package academy.esercizi.esercizi25;


import java.util.Scanner;

/*Un lucchetto per bicicletta a combinazione numerica ha quattro anelli (ring),
ciascuno avente i numeri da 0 a 9. Scrivete un programma che, conoscendo i numeri su cui
sono attualmente posizionati gli anelli e la combinazione di sblocco, visualizzi le istruzioni
 necessarie a sbloccare il lucchetto facendo il numero minimo di rotazioni (twist). Una
 “rotazione verso l’alto” (twist up) aumenta di un’unità il valore presente nell’anello su cui
 agisce, mentre una “rotazione verso il basso” (twist down) lo diminuisce. Ad esempio, se gli
 anelli fossero impostati al valore 1729 e la combinazione corretta fosse 5714, le istruzioni di
 sblocco dovrebbe essere queste:Ring 1: Twist up 4 times Ring 3: Twist down once Ring 4: Twist
 up or down 5 times Ricordate che once significa «una volta». Nell’ultimo caso l’istruzione
 afferma «alto o basso», perché, dovendo fare 5 rotazioni, il senso di rotazione è indifferente
 (potete, però, anche decidere che in tal caso la rotazione sia sempre «verso l’alto»,
 oppure sempre «verso il basso»).
 */
// se sta nella metà non conviene andare al contrario
// se un numero è maggiore di 5 e un altro è minore di 5 allora fai 10 meno il primo numero + il secondo numero
public class Esercizio3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numeriPosizionati = prendiCombinazioneIniziale(scanner);
        final int[] combinazioneDiSblocco = {1, 2, 1, 9};
        StringBuilder istruzioni = new StringBuilder();
        int numeroDiGiri;

        // se il primo numero è maggiore del secondo allora vediamo cos'è maggiore tra
        // la differenza del primo e il secondo e la differenza tra 10 e il primo aumentata del secondo
        // se il secondo numero è minore del secondo facciamo la stessa cosa ma al contrario

        for (int i = 0; i < numeriPosizionati.length; i++) {
            int diff = numeriPosizionati[i] - combinazioneDiSblocco[i];
            int diffContraria = combinazioneDiSblocco[i] - numeriPosizionati[i];
            int distanzaALContrario = 10 - numeriPosizionati[i] + combinazioneDiSblocco[i];
            int distanzaAlContrarioSecondadiff = 10 - combinazioneDiSblocco[i] + numeriPosizionati[i];

            if (numeriPosizionati[i] > combinazioneDiSblocco[i]) {
                if (diff < distanzaALContrario) {
                    numeroDiGiri = numeriPosizionati[i] - combinazioneDiSblocco[i];

                    istruzioni.append(" Ring ").append(i + 1).append(": Twist down ")
                            .append(numeroDiGiri == 1 ? "once " : numeroDiGiri + " times");
                } else {
                    numeroDiGiri = distanzaALContrario;

                    istruzioni.append(" Ring ").append(i + 1).append(": Twist up ")
                            .append(numeroDiGiri == 1 ? "once " : numeroDiGiri + " times");
                }
            } else {
                if (diffContraria < distanzaAlContrarioSecondadiff) {
                    numeroDiGiri = diffContraria;

                    istruzioni.append(" Ring ").append(i + 1).append(": Twist up ")
                            .append(numeroDiGiri == 1 ? "once " : numeroDiGiri + " times");
                } else {
                    numeroDiGiri = distanzaAlContrarioSecondadiff;

                    istruzioni.append(" Ring ").append(i + 1).append(": Twist down ")
                            .append(numeroDiGiri == 1 ? "once " : numeroDiGiri + " times");
                }
            }
        }
        System.out.println(istruzioni);
    }

    public static int[] prendiCombinazioneIniziale(Scanner scanner) {
        System.out.println("Inserisci la combinazione di 4 numeri");
        int[] arrayCombinazione = new int[4];
        for (int i = 0; i < 4; i++) {
            arrayCombinazione[i] = scanner.nextInt();
        }
        return arrayCombinazione;
    }
}

