package academy.esercizi.esercizio_29_1;

import java.util.Objects;

public class Tile {
    private int valore;
    private static final char dorso = 'X';
    private boolean coperta;

    public Tile(int valore) {
        this.valore = valore;
        this.coperta = true;
    }

    public boolean isCoperta() {
        return coperta;
    }

    public void setCoperta(boolean coperta) {
        this.coperta = coperta;
    }

    public char getDorso() {
        return dorso;
    }

    public int getValore() {
        return valore;
    }

    public void setValore(int valore) {
        this.valore = valore;
    }

}
