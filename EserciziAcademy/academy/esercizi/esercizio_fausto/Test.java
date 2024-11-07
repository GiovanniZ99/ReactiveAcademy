package academy.esercizi.esercizio_fausto;

import java.util.Scanner;

/*Dati gli input una stringa verificarne la validita
  la stringa si definisce valida se contiene solo caratteri parentesi (tutti i tipi) sono aperte
   e chiuse tutte le coppie di parentesi la chiusura avviene nell ordine corretto: */
// l ultima apeta prima chiusa ([]{}())  []{()}
// la parentesi che si chiude deve stare o subio dopo oppure nel suo indice speculare

//se si sono tutte chiuse allora rifai il controllo sopra,
// se una di un tipo e aperta e subito dopo chiusa allora va bene e rifai.

// devono essere pari dello stesso tipo  e appena c'è un tipo nuovo
// devono esserci prima le aperte e poi le chiuse
// devi chiuderle seguendo la stessa posizione in modo speculare
// quando si aprono e chiudono subito allora le levi
// La logica della palindromia non aveva senso

    public class Test {
        public static void main(String[] args) {
            System.out.println("Se il programma non stampa parentesi la stringa è corretta, altrimenti stampa le parentesi non valide");
            Scanner scanner = new Scanner(System.in);
            StringBuilder stringa = new StringBuilder(scanner.next());
            if (controllaSeCiSonoParentesi(stringa)) {
                System.out.println(controllaParentesiAperteChiuse(stringa));
            } else {
                System.out.println("Stringa non valida.");
            }
        }

        private static StringBuilder controllaParentesiAperteChiuse(StringBuilder controlloValidita) {
            boolean found;
            do {
                found = false;
                for (int i = 0; i < controlloValidita.length() - 1; i++) {
                    if (controlloValidita.charAt(i) == '(' && controlloValidita.charAt(i + 1) == ')') {
                        controlloValidita.delete(i, i + 2);
                        found = true;
                        i--;
                    } else if (controlloValidita.charAt(i) == '[' && controlloValidita.charAt(i + 1) == ']') {
                        controlloValidita.delete(i, i + 2);
                        found = true;
                        i--;
                    } else if (controlloValidita.charAt(i) == '{' && controlloValidita.charAt(i + 1) == '}') {
                        controlloValidita.delete(i, i + 2);
                        found = true;
                        i--;
                    }
                }
            } while (found);
            return controlloValidita;
        }

        private static boolean controllaSeCiSonoParentesi(StringBuilder controlloValidita) {
            boolean check = false;
            for (int i = 0; i < controlloValidita.length(); i++) {
                char ch = controlloValidita.charAt(i);
                if(ch == '(' || ch == ')' || ch == '[' || ch == ']'
                        || ch == '{' || ch == '}'){
                    check= true;
                }else{
                    return false;
                }
            }
            return check;
        }
    }

