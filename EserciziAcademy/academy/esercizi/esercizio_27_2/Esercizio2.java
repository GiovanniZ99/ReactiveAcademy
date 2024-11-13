package academy.esercizi.esercizio_27_2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/* In questo progetto realizzerete un simulatore del popolare gioco d’azzardo solitamente chiamato “video poker”.
Il mazzo di carte ne contiene 52, 13 per ciascun seme, e viene mescolato all’inizio del gioco: dovete individuare
una modalità di mescolamento che sia equa, anche se non è necessario che sia efficiente. Successivamente vengono mostrate
le prime cinque carte del mazzo al giocatore, che ne può rifiutare alcune, anche tutte, o nessuna. Le carte rifiutate vengono
sostituite con altre, prelevate ordinatamente dal mazzo. A questo punto, sulla base delle cinque carte che il giocatore ha in
mano, il programma comunica il punteggio ottenuto, che deve essere il maggiore tra i seguenti, elencati in ordine crescente:
• No pair (“Niente”). La configurazione peggiore, che contiene cinque carte spaiate che non compongono alcuna delle
configurazioni elencate nel seguito.
• One pair (“Coppia”). Due carte dello stesso valore, ad esempio due regine.
• Two pairs (“Doppia coppia”). Due coppie, ad esempio due regine e due cinque.
• Three of a kind (“Tris”). Tre carte dello stesso valore, ad esempio tre regine.
• Straight (“Scala”). Cinque carte con valori consecutivi, non del medesimo seme, come 4, 5, 6, 7 e 8.
L’asso può precedere il 2 oppure seguire il re.
• Flush (“Colore”). Cinque carte dello stesso seme, con valori non consecutivi.
• Full House (“Full”). Un tris e una coppia, ad esempio tre regine e due 5.
• Four of a kind (“Poker”). Quattro carte con lo stesso valore, ad esempio quattro regine.
• Straight Flush (“Scala colore”). Una scala e, contemporaneamente, un colore: cinque carte con valori consecutivi e
dello stesso seme.
• Royal Flush (“Scala reale”). La mano migliore possibile: 10, fante, regina, re e asso, tutti del medesimo seme.
*/
public class Esercizio2 {
    public static final int NUMEROCARTESTESSOSEME = 13;
    public static final int NUMEROCARTETOTALI = 52;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Carte mazzo");
        Carta[] carte = mischiaCarte(faiMazzo());
        Carta[] carteGiocatore = daiCarte(carte);
        System.out.println("Carte iniziali");
        for (int i = 0; i < carteGiocatore.length; i++) {
            System.out.println();
            System.out.println(carteGiocatore[i].getValore());
            System.out.println(carteGiocatore[i].getSeme());
        }
        Carta[] carteGiocatoreCambiate = cambiaCarte(carteGiocatore, carte, scanner);
        System.out.println("Carte finali");
        for (int i = 0; i < carteGiocatoreCambiate.length; i++) {
            System.out.println(carteGiocatoreCambiate[i].getValore());
            System.out.println(carteGiocatoreCambiate[i].getSeme());
        }
        Carta[] carteEsempio = {new Carta(Seme.PICCHE, 10), new Carta(Seme.PICCHE, 9),
                new Carta(Seme.PICCHE, 10), new Carta(Seme.PICCHE, 13), new Carta(Seme.PICCHE, 11),};

        System.out.println(daiPunteggio(carteGiocatore));
    }

    public static Carta[] faiMazzo() {
        Carta[] arrayCarte = new Carta[NUMEROCARTETOTALI];
        int valoreCuori = 1;
        int valoreQuadri = 1;
        int valoreFiori = 1;
        int valorePicche = 1;
        for (int i = 0; i < arrayCarte.length; i++) {
            if (i < NUMEROCARTESTESSOSEME) {
                arrayCarte[i] = new Carta(Seme.CUORI, valoreCuori);
                valoreCuori++;
            } else if (i < (NUMEROCARTESTESSOSEME * 2)) {
                arrayCarte[i] = new Carta(Seme.QUADRI, valoreQuadri);
                valoreQuadri++;
            } else if (i < (NUMEROCARTESTESSOSEME * 3)) {
                arrayCarte[i] = new Carta(Seme.FIORI, valoreFiori);
                valoreFiori++;
            } else {
                arrayCarte[i] = new Carta(Seme.PICCHE, valorePicche);
                valorePicche++;
            }
        }
        return arrayCarte;
    }

    public static Carta[] mischiaCarte(Carta[] carte) {
        Random random = new Random();
        Carta temp;

        for (int i = 0; i < NUMEROCARTETOTALI; i++) {
            int numeroCasuale = random.nextInt(NUMEROCARTETOTALI);
            temp = carte[i];
            carte[i] = carte[numeroCasuale];
            carte[numeroCasuale] = temp;
        }
        for (int i = 0; i < NUMEROCARTETOTALI; i++) {
            System.out.println(carte[i].getSeme());
            System.out.println(carte[i].getValore());
        }
        System.out.println("-----------------------------------------------");
        return carte;
    }

    public static Carta[] daiCarte(Carta[] carte) {
        Carta[] carteGiocatore = new Carta[5];
        for (int i = 0; i < carteGiocatore.length; i++) {
            carteGiocatore[i] = carte[i];
        }
        return carteGiocatore;
    }

    public static Carta[] cambiaCarte(Carta[] cartePrese, Carta[] carteTot, Scanner scanner) {
        System.out.println("Quante carte vuoi cambiare?");

        int numeroCarteDaCambiare = scanner.nextInt();

        while (numeroCarteDaCambiare > 5) {
            System.out.println("Non puoi cambiare più di 5 carte, reinserire il numero di carte da cambiare");
            numeroCarteDaCambiare = scanner.nextInt();

        }

        System.out.println("Inserisci il numero della posizione delle carte che vuoi cambiare e premi invio (da 1)");
        int[] scelte = new int[numeroCarteDaCambiare];
        for (int i = 0; i < numeroCarteDaCambiare; i++) {
            scelte[i] = scanner.nextInt();
        }

        for (int i = 0; i < numeroCarteDaCambiare - 1; i++) {
            for (int j = i + 1; j < numeroCarteDaCambiare; j++) {
                if (scelte[i] == scelte[j]) {
                    scelte[j] = 0;
                }
            }
        }
        // inizializzo a 5 perché le prime 4 carte sono già state prese
        int count = 5;
        for (int i = 0; i < numeroCarteDaCambiare; i++) {

            switch (scelte[i]) {
                case 1:
                    cartePrese[0].setValore(carteTot[count].getValore());
                    cartePrese[0].setSeme(carteTot[count].getSeme());
                    count++;
                    break;
                case 2:
                    cartePrese[1].setValore(carteTot[count].getValore());
                    cartePrese[1].setSeme(carteTot[count].getSeme());
                    count++;
                    break;
                case 3:
                    cartePrese[2].setValore(carteTot[count].getValore());
                    cartePrese[2].setSeme(carteTot[count].getSeme());
                    count++;
                    break;
                case 4:
                    cartePrese[3].setValore(carteTot[count].getValore());
                    cartePrese[3].setSeme(carteTot[count].getSeme());
                    count++;
                    break;
                case 5:
                    cartePrese[4].setValore(carteTot[count].getValore());
                    cartePrese[4].setSeme(carteTot[count].getSeme());
                    count++;
                    break;
                default:
                    break;
            }
        }
        return cartePrese;
    }

    public static String daiPunteggio(Carta[] carteGiocatore) {
        int countCoppia = 0;
        int countColore = 0;
        int countScalaReale = 0;
        for (int i = 0; i < carteGiocatore.length - 1; i++) {
            if (carteGiocatore[i].getSeme().equals(carteGiocatore[i + 1].getSeme())) {
                countColore++;
            }
        }
        for (int i = 0; i <carteGiocatore.length-1 ; i++) {
            for (int j = 0; j < carteGiocatore.length; j++) {
                if (i != j && carteGiocatore[i].getValore() == carteGiocatore[j].getValore()) {
                    countCoppia++;
                }
            }
        }

        for (int i = 0; i < carteGiocatore.length; i++) {
            if (carteGiocatore[i].getValore() == 10 || carteGiocatore[i].getValore() == 11
                    || carteGiocatore[i].getValore() == 12 || carteGiocatore[i].getValore() == 13
                    || carteGiocatore[i].getValore() == 1) {
                countScalaReale++;
            }
        }
        int countScala = 0;
        for (int i = 0; i < carteGiocatore.length - 1; i++) {
            for (int j = 0; j < carteGiocatore.length; j++) {
                if (carteGiocatore[i].getValore() == (carteGiocatore[j].getValore() + 1)) {
                    countScala++;
                }
            }
        }

        if (countScalaReale == 5 && countColore == 4) {
            return "Scala reale";
        }
        if (countScala == 3 && countColore == 4) {
            return "Scala colore";
        }
        if (countScala == 3) {
            return "Scala";
        }
        if (countColore == 5) {
            return "Colore";
        }

        if (countCoppia == 10) {
            return "Poker";
        } else if (countCoppia == 4) {
            return "Full";
        } else if (countCoppia == 3) {
            return "Tris";
        } else if (countCoppia == 2) {
            return "Doppia coppia";
        } else if (countCoppia == 1) {
            return "Coppia";
        }
        return "Niente";
    }

}
