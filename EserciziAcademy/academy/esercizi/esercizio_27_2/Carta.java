package academy.esercizi.esercizio_27_2;

public class Carta {
    private Seme seme;
    private int valore;

    public Carta(Seme seme, int valore) {
        this.seme = seme;
        this.valore = valore;
    }

    public Seme getSeme() {
        return seme;
    }

    public void setSeme(Seme seme) {
        this.seme = seme;
    }

    public int getValore() {
        return valore;
    }

    public void setValore(int valore) {
        this.valore = valore;
    }
}
