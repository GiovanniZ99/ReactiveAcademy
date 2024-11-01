package academy.esercizi.esercizi_17_2;

import java.util.Scanner;

/*Il pannello di controllo di un forno a microonde ha quattro pulsanti: uno per aumentare
di 30 secondi il tempo di funzionamento, uno per cambiare il livello di potenza (che può avere
i valori 1 o 2), uno per riportare i valori a uno stato predefinito (pulsante di reset) e uno
per far funzionare il forno (pulsante start). Realizzate una classe che simuli il funzionamento
del microonde, con un metodo per ciascun pulsante. Il metodo corrispondente al pulsante start
deve visualizzare il messaggio “Cooking for ... seconds at level ...” (in funzione per ...
secondi al livello di potenza ...”).*/

public class Main {
    public static void main(String[] args) {
        Microonde microonde = new Microonde(0, 1);
        System.out.println("Premi uno per aumentare i secondi di trenta");
        System.out.println("Premi due per aumentare o diminuire il livello di potenza");
        System.out.println("Premi tre per startare il microonde");
        System.out.println("Premi quattro per il reset");
        System.out.println("Premi cinque per spegnere il microonde");
        int scelta = 0;
        while (scelta != 5) {
            Scanner scanner = new Scanner(System.in);
            scelta = scanner.nextInt();
            switch (scelta) {
                case 1:
                    microonde.setTempo(GestioneElettrodomestico.aumentoDiTrentaSecondi(microonde.getTempo()));
                    System.out.println("Tempo aumentato di trenta secondi");
                    break;
                case 2:
                    microonde.setPotenza(GestioneElettrodomestico.aumentoDiPotenza(microonde.getPotenza()));
                    System.out.println("La potenza adesso è " + microonde.getPotenza());
                    break;
                case 3:
                    GestioneElettrodomestico.start(microonde.getTempo(), microonde.getPotenza());
                    System.out.println("Microonde startato");
                    break;
                case 4:
                    System.out.println("Microonde resettato");
                    int[] reset = GestioneElettrodomestico.reset();
                    microonde.setTempo(reset[0]);
                    microonde.setPotenza(reset[1]);
                    break;
                case 5:
                    System.out.println("Microonde spento");
                    break;
                default:
                    System.out.println("Numero inserito non valido");
            }
        }

    }
}
