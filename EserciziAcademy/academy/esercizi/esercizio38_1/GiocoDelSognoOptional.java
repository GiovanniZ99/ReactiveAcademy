package academy.esercizi.esercizio38_1;

import java.util.Optional;

/*se sbaglia ed il numero non era NULL viene dimezzato
        (troncandolo all’intero più piccolo). Il gioco termina nei seguenti casi:
il giocatore indovina a
il numero da indovinare diventa 1
Il giocatore tenta null dopo la terza mossa ma è valorizzato
Se il giocatore tenta null nelle prime tre mosse ed è valorizzato perde solamente la mossa e può continuare a giocare.*/
public class GiocoDelSognoOptional {
    private final ElementoCasualeOptional casualeOptional;
    private int count;

    public GiocoDelSognoOptional(ElementoCasualeOptional casualeOptional) {
        this.casualeOptional = casualeOptional;
        count = 0;
    }

    public boolean checkRisposta(boolean valoreInput) {
        // contiamo numero di mosse
        this.count++;

        boolean check = true;

        if (!casualeOptional.getValore().isPresent() && valoreInput) {
            System.out.println("Hai perso la mossa, riprova");
            check = false;
        }
        if (this.count < 3) {
            if(casualeOptional.getValore().isPresent() && casualeOptional.getValore().get().equals(1)){
                System.out.println("Il numero è diventato 1, hai perso");
                check = true;
            } else if (!valoreInput) {
                System.out.println("Il numero non è null, il valore sarà dimezzato");
                casualeOptional.setValore(Optional.ofNullable(casualeOptional.getValore().map(v -> (v / 2)).orElseThrow(() -> new NullPointerException("Il valore è null, hai vinto!"))));
                check = false;
            }
        } else {
            System.out.println("Hai tentato null dopo la terza mossa, hai perso!");
            check = true;
        }
        if (valoreInput && casualeOptional.getValore().isPresent()) {
            System.out.println("Hai vinto!");
            check = true;
        }

        return check;
    }
}
