package academy.esercizi.esercizi_17_2;

public class Microonde {
    private  int tempo;
    private int potenza;

    public Microonde(int tempo, int potenza) {
        this.tempo = tempo;
        this.potenza = potenza;
    }

    public void aumentoDiTrentaSecondi() {
        this.tempo += 30;
    }

    public void aumentoDiPotenza() {
        if (potenza == 1) {
            this.potenza++;
            return;
        }
        if (potenza == 2) {
            this.potenza--;
        }
    }

    public void reset() {
        this.tempo = 0;
        this.potenza = 1;
    }

    public void start() {
        System.out.printf("Cooking for %d seconds at level %d %n", this.tempo, this.potenza);
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getPotenza() {
        return potenza;
    }

    public void setPotenza(int potenza) {
        this.potenza = potenza;
    }

}
