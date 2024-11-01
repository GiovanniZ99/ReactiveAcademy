package academy.esercizi.esercizi_17_2;
// utilizziamo una classe di Utils per rendere il codice scalabile
public class GestioneElettrodomestico {

    public static int aumentoDiTrentaSecondi(int tempo) {
        tempo += 30;
        return tempo;
    }

    public static int aumentoDiPotenza(int potenza) {
        if (potenza == 1) {
           potenza++;
           return potenza;
        }
        if (potenza == 2) {
           potenza--;
           return potenza;
        }
        return -1;
    }

    public static int[] reset() {
        int tempo = 0;
        int potenza = 1;
      return  new int[]{tempo, potenza};

    }

    public static void start(int tempo, int potenza) {
        System.out.printf("Cooking for %d seconds at level %d %n", tempo, potenza);
    }
}
